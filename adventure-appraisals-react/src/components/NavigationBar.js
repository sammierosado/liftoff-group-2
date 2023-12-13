import { Component } from 'react';
import { Link } from 'react-router-dom';

const NavigationBar = () => {
    return(
        <nav>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/userPage">User Page</Link>
                </li>
                <li>
                    <Link to="/destinations">Destinations</Link>
                </li>
                <li>
                    <Link to="/itineraries">Itineraries</Link>
                </li>
                <li>
                    <Link to="/search">Search</Link>
                </li>
                <li>
                    <Link to="/login">Login</Link>
                </li>
            </ul>
        </nav>
);
};

export default NavigationBar;
