
import React, { useState } from 'react';

const UserSignUp = ({ onSignUp }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [setConfirmPassword, setConfirmPassword] = useState('');

    const handleSignUp = () => {

    if(password !== setConfirmPassword) {
        alert('Passwords must match');
        return;
    }

    const newUser = {
    username,
    password,
    };

    const signUpSuccessful = true;

    if (signUpSuccessful){
    OnSignUp(newUser);
    } else {
    alert('Unable to make account please try again later.');
    }
    };

    return(
    <div>
        <h3> User Sign Up </h3>
        <div>
            <label>Username:</label>
            <input type="text" value={username} onChange={(e)} => setUsername(e.target.value)} />
    </div>
    <div>
            <label>Password:</label>
            <input type="text" value={password} onChange={(e)} => setPassword(e.target.value)} />
    </div>

    <div>
            <label>Confirm Password:</label>
            <input type="text" value={confirmPassword} onChange={(e)} => setConfirmPassword(e.target.value)} />
    </div>
        <button onClick={handleSignUp}>Sign Up</button>
    </div>
    );

};