import { Link, useNavigate } from 'react-router-dom';
import React, { useState } from 'react';
import {UserAuth} from '../Context/AuthContext'
import { getAuth, sendSignInLinkToEmail } from "firebase/auth";
import firebaseApp from '../firebase';

const UserSignUp = () => {
     const [email, setEmail] = useState('');
     const [password, setPassword] = useState('');
     const [error, setError] = useState('');
     const { createUser } = UserAuth();
     const navigate = useNavigate()

const actionCodeSettings = {
url: 'http://localhost:3000/userPage',
  handleCodeInApp: true,
};


     const handleSubmit = async (e) => {
        e.preventDefault();
        if (!email) {
        setError('Must provide email and password must be at least 6 characters long')
        return;
        } else {
        setError('');
        }

        if (password.length < 6) {
        setError('Password must be at least 6 characters long');
        return;
        } else {
        setError('');
        }

        try{
            await createUser(email, password)

//Send Auth Link
     const auth = getAuth(firebaseApp);
     await sendSignInLinkToEmail(auth, email, actionCodeSettings);

//Store locally
     window.localStorage.setItem('emailForSignIn', email);

            navigate('/userPage')
        } catch (error) {
            setError(error.message)
            console.log(error.message)
        }
     };

return(
 <div className='max-w-700 mx-auto my-16 p-4'>
    <div>
        <h1 className='text-2xl font-bold py-2'>Create account</h1>
          <p className='py-2'>
           Already have an account? {''}
           <Link to ='/Login' className='underline'>Sign in.</Link>
           </p>
    </div>
    {error && (
            <div className='py-2 text-red-500'>
              <p>{error}</p>
            </div>
    )}
    <form onSubmit={handleSubmit}>
        <div className='flex flex-col py-2'>
            <label className='py-2 font-medium'>Email Address</label>
            <input onChange={(e) => setEmail(e.target.value)} className='border p-3' type="email" />
        </div>
        <div className='flex flex-col py-2'>
            <label className='py-2 font-medium'>Password</label>
             <input onChange={(e) => setPassword(e.target.value)} className='border p-3' type="password" />
        </div>
        <button className='border border-blue-500 bg-blue-600 hover:bg-blue-500 w-full p-4 my-2 text-white'>Sign Up
        </button>
    </form>
 </div>
);
};

export default UserSignUp;