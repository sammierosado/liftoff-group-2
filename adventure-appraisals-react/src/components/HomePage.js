import React from 'react';
import NavigationBar from './NavigationBar';
import { UserAuth } from "../Context/AuthContext";
import { Paper } from "@mui/material";

let logo = "Adventure Appraisals"

const HomePage = () => {
    const { user } = UserAuth();
    const paperStyle = {padding:'50px 20px', width:600, margin:'20px auto', textAlign:"center"};
    const userEmail = user ? user.email : null;

    const [ userFavoritesList, setUserFavoritesList ] = React.useState();

    React.useEffect(() => {
        userEmail && fetch(`http://localhost:8080/userfavorites/${userEmail}`).then(res => res.json()).then(result => setUserFavoritesList(result));
        console.log(userFavoritesList);
    })

    return (
        <>
            <div>
                <h1 className='text-center text-2xl text-blue-900 mt-4'>
                Welcome to Adventure Appraisals a Travel Itineraries Site! </h1>



                <p className='max-w-lg mx-auto text-center text-1xl mt-4'>
                We found that we often could not find our old itineraries
                to share with friends so we designed a new platform for easily creating
                and tracking trip itineraries and their reviews so users will always be
                able to share the fun experiences of past trips! Our site allows users to
                search for pre made itineraries at specific destinations. You can also review
                your favorite itineraries and make your own!
                </p>
            </div>
            <Paper elevation={3} style={paperStyle}>
                <h1>Favorited Itineraries</h1>
                <br />
                {userFavoritesList?.length === 0 ?
                    <h3>No favorited itineraries!</h3> :
                    <h3>Justin is still working on this part! But you have favorited itineraries</h3>
    //                reviews.map(review => (
    //                    <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={review.id}>
    //                        {review.userEmail} rated and said:<br />
    //                        <Rating value={review.rating} disabled /><br />
    //                        {review.review}
    //                    </Paper>
    //                ))
                }
                <br/>
                //TODO Add Showing the Favorited Itineraries
            </Paper>
        </>
    );
};

export default HomePage;