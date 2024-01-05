
import React, { useState } from 'react';
import firebase, { auth, firestore } from "../firebase";

const UserSignUp = ({ onSignUp }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const handleUserSignUp = async () => {

    if(password !== confirmPassword) {
        alert('Passwords must match');
        return;
    }
    try {
        const userCredential = await auth.createUserWithEmailAndPassword(
    email,
    password,
    );
    console.log("User registered:", userCredential.user);

    if (onSignUp) {
        onSignUp(userCredential.user);
        }
    } catch (error) {
    console.error("Registration failed:", error.message);
    }
    };


    return(
    <div>
        <h3> User Sign Up </h3>
        <div>
            <label>Email:</label>
            <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
    </div>
    <div>
            <label>Password:</label>
            <input type="text" value={password} onChange={(e) => setPassword(e.target.value)} />
    </div>

    <div>
            <label>Confirm Password:</label>
            <input type="text" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
    </div>
        <button onClick={handleUserSignUp}>Sign Up</button>
    </div>
    );

};

export default UserSignUp;
