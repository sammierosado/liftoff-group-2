import React, { useState } from 'react';
import UserSignUp from './UserSignUp';
import { Link, useNavigate } from 'react-router-dom';
import { UserAuth } from '../Context/AuthContext';


const Login = () =>{
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate()
    const { signIn } = UserAuth();

    const handleSubmit = async (e) => {
    e.preventDefault();
    if (!email) {
            setError('Must provide email and password.')
            return;
            } else {
            setError('');
            }

            if (!password) {
            setError('Must provide email and password.');
            return;
            } else {
            setError('');
            }
    try {
    await signIn(email, password)
    navigate('/userPage')
    }catch (error) {
       setError('Invalid email or password. Please try again.');
    console.error(error.message);
    }
   };

return(
 <div className='max-w-{700px} mx-auto my-16 p-4'>
    <div>
        <h1 className='text-2xl font-bold py-2'>Sign in to your account</h1>
          <p className='py-2'>
           Need to create an account? <Link to='/UserSignUp' className='underline'>Sign up here.</Link>
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
            <input onChange={(e) => setEmail(e.target.value)}  className='border p-3' type="email" />
        </div>
        <div className='flex flex-col py-2'>
            <label className='py-2 font-medium'>Password</label>
             <input onChange={(e) => setPassword(e.target.value)} className='border p-3' type="password" />
        </div>
        <button className='border border-blue-500 bg-blue-600 hover:bg-blue-500 w-full p-4 my-2 text-white'>Sign In</button>
    </form>
 </div>
);
};


export default Login;