import React from 'react';
import NavigationBar from './NavigationBar';

let logo = "Adventure Appraisals"

const HomePage = () => {
    return (
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
    );
};

export default HomePage;