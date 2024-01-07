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
                    <a href = "http://localhost:8080/itineraries/destination">Destinations</a>
                </li>
                <li>
                    <a href="http://localhost:8080/itineraries">Itineraries</a>
                </li>
                <li>
                    <a href="http://localhost:8080/itineraries/search">Search</a>
                </li>
                <li>
                    <Link to="/login">Login</Link>
                </li>
            </ul>
        </nav>
);
};

export default NavigationBar;
