import React from 'react';
import NavigationBar from './NavigationBar';
import { UserAuth } from "../Context/AuthContext";
import { Paper } from "@mui/material";
import { Link } from 'react-router-dom';

let logo = "Adventure Appraisals"

const HomePage = () => {
    const { user } = UserAuth();
    const paperStyle = {padding:'50px 20px', width:600, margin:'20px auto', textAlign:"center"};
    let itineraryList;

    const [ loaded, setLoaded ] = React.useState();
    const [ userEmail, setUserEmail ] = React.useState();
    const [ userFavoritesList, setUserFavoritesList ] = React.useState();
    const [ userFavoritesListOfItineraries, setUserFavoritesListOfItineraries ] = React.useState([]);

    React.useEffect(() => {
        setUserEmail(user?.email);
        userEmail && fetchData();
    }, [user, userEmail, loaded]);

    const fetchData = async () => {
        let response = await fetch(`http://localhost:8080/userfavorites/${userEmail}`);
        try {
            let responseJson = await response.json();
            setUserFavoritesList(responseJson);
            setLoaded(1);
            console.log(userFavoritesList);
        } catch (error) {
            console.log(error);
        }

        if (userFavoritesList) {
            let itineraryList = [];
            for (const itineraryId of userFavoritesList) {
                response = await fetch(`http://localhost:8080/itineraries/itinerary/${itineraryId}`);
                try {
                    let responseJson = await response.json();
                    if (responseJson !== null) {
                        itineraryList.push(responseJson);
                    }
                } catch (error) {
                    console.log(error);
                }
            }
            setUserFavoritesListOfItineraries(itineraryList);
            console.log(userFavoritesListOfItineraries);
        }
    }

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
                {userFavoritesListOfItineraries.length > 0 ?
                    userFavoritesListOfItineraries.map(itinerary => (
                        <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={itinerary.id}>
                            {itinerary.name}<br/>
                            <Link to={`/review/${itinerary.id}`}>See reviews</Link>
                        </Paper>
                    )) :
                    <h3>No favorited itineraries!</h3>
                }
            </Paper>
        </>
    );
};

export default HomePage;