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
                    <a href="http://localhost:8080/itineraries">Itineraries</a>
                </li>
                <li>
                    <Link to="/search">Search</Link>
                </li>
                <li>
                    <Link to="/login">Login</Link>
                </li>
            <li>
                    <Link to="/userSignUp">SignUp</Link>
                </li>
            </ul>
        </nav>
);
};

export default NavigationBar;
