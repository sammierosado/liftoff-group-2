import { Component } from 'react';
import { Link } from 'react-router-dom';

const NavigationBar = () => {
    return(
        <nav className="bg-blue-200 p-4 mt-4">
            <ul className="flex space-x-4 w-full">
                <li className="flex-grow flex-1">
                    <Link to="/" className="text-white">Home</Link>
                </li>
                <li className="flex-grow flex-1">
                   <Link to="/search" className="text-white">Search</Link>
                </li>
                <li className="flex-grow flex-1">
                  <a href="http://localhost:8080/itineraries" className="text-white">Itineraries</a>
                </li>
                <li className="flex-grow flex-1">
                    <Link to="/userPage" className="text-white">User Page</Link>
                </li>
                <li className="flex-grow flex-1">
                    <Link to="/destinations" className="text-white">Destinations</Link>
                </li>

                <li className="flex-grow flex-1">
                    <Link to="/login" className="text-white">Login</Link>
                </li>
            <li className="flex-grow flex-1">
                    <Link to="/userSignUp" className="text-white">SignUp</Link>
                </li>
            </ul>
        </nav>
);
};

export default NavigationBar;
