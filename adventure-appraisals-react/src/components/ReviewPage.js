import { Button, Input, TextareaAutosize,  } from "@mui/base";
import { UserAuth } from "../Context/AuthContext";
import { useEffect, useState } from "react";
import { Box, Container, Paper, Rating, TextField } from "@mui/material";

const ReviewPage = () => {
    const { user } = UserAuth();
    const paperStyle= {padding:'50px 20px', width:600, margin:'20px auto', textAlign:"center"};

    const [ newReview, setNewReview ] = useState();
    const [ name, setName ] = useState();
    const [ rating, setRating ] = useState();
    const [ itinerary, setItinerary ] = useState({});

    const [ reviews, setReviews ] = useState();

    var url = window.location.pathname;
    const itineraryId = Number(url.substring(url.lastIndexOf('/') + 1));
    const userEmail = user?.email;

    useEffect(() => {
        fetch(`http://localhost:8080/itineraries/itinerary/${itineraryId}`).then(res => res.json()).then(result => setItinerary(result));
    }, []);

    useEffect(() => {
        setReviews(itinerary.reviews);
        console.log(reviews);
    }, [itinerary]);

    const handleSubmit = (e) => {
        e.preventDefault();
        const reviewObject = {name, userEmail, review: newReview, itinerary, rating};
        fetch("http://localhost:8080/reviews/create", {
            method:"POST",
            headers:{'Content-Type':"application/json"},
            body:JSON.stringify(reviewObject)
        });
        setNewReview('');
        setName('');
    };

    return (
        <Container>
            {(user) ? (
                <Paper elevation={3} style={paperStyle}>
                <h1 style={{color:"blue"}}>Add Review</h1>
                    <Box
                        component="form"
                        sx={{
                            '& > :not(style)': { m: 1 },
                        }}
                        noValidate
                        autoComplete="off"
                    >
                        <TextField id="outlined-basic" label="Review Title" variant="outlined" fullWidth value={name} onChange={(e)=>{setName(e.target.value)}} />
                        <Rating name="simple-controlled" value={rating} onChange={(event, newValue) => {setRating(newValue)}} size="large" />
                        <TextField id="outlined-multiline-static" label="Leave a Review" fullWidth multiline rows={4} value={newReview} onChange={(e)=>{setNewReview(e.target.value)}} />
                        <Button onClick={handleSubmit}>Submit Review</Button>
                    </Box>
                </Paper>
            ) : (
                <h1>Sign in to add a new review</h1>
            )}
            <Paper elevation={3} style={paperStyle}>
                <h1>Reviews</h1>
                <br />
                {!reviews ? 
                    <h3>No reviews created yet!</h3> :
                    reviews.map(review => (
                        <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={review.id}>
                            {review.userEmail} rated and said:<br />
                            <Rating value={review.rating} disabled /><br />
                            {review.review}
                        </Paper>
                    ))
                }
            </Paper>
        </Container>
    )
};

export default ReviewPage;