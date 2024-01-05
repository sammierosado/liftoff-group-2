import React, { useState } from 'react';
import firebase, { auth, firestore } from "../firebase";



const Login = ({ Login }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');


    const handleLogin = async () => {
     try {
     const userCredential = await auth.signInWithEmailAndPassword(
     email,
     password
     );

    console.log("User logged in:", userCredential.user);
    } catch (error) {
      console.error("unable to log in", error.message);
    }
   };

    return(
    <div>
        <h3>Login</h3>
        <div>
            <label>Email:</label>
            <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
    </div>
    <div>
            <label>Password:</label>
            <input type="text" value={password} onChange={(e) => setPassword(e.target.value)} />
    </div>

        <button onClick={handleLogin}>Login</button>
    </div>
    );

};

export default Login;


