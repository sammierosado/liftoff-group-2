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
                    <Link to="/">User Page</Link>
                </li>
                <li>
                    <Link to="/">Destinations</Link>
                </li>
                <li>
                    <Link to="/">Itineraries</Link>
                </li>
                <li>
                    <Link to="/">Search</Link>
                </li>
                <li>
                    <Link to="/">Login</Link>
                </li>
            </ul>
        </nav>
);
};

export default NavigationBar;
