import React, { useEffect, useState } from 'react';
import { UserAuth } from '../Context/AuthContext';
import { useNavigate } from 'react-router-dom';
import { getAuth } from 'firebase/auth';

const UserPage = () => {
    const {user, logout} = UserAuth();
    const [isEmailVerified, setIsEmailVerified] = useState(false);
    const navigate = useNavigate();

 useEffect(() => {
 const checkEmailVerification = () => {
    const auth = getAuth();

    if (user) {
    setIsEmailVerified(user.emailVerified);
    }
 };

   checkEmailVerification();
   console.log (user);
 }, [user]);

const handleLogout = async () => {
    try {
    await logout();
    navigate('/Login');
    } catch (e) {
        console.log(e.message)
    }
};



return (
 <div className='max-w-{600px} mx-auto my-16 p-4 mt-4'>
    <h1 className='text-2xl font-bold py-4 mt-4'>Profile</h1>
    <p>Account Email: {user && user.email}</p>
    {isEmailVerified ? (
    <p>Account Verification Status: Email Verified</p>
    ) : (
    <p>Account Verification Status: Email Not Verified. Please check your email to verify your account</p>
    )}

    <button onClick={handleLogout} className='border px-6 py-2 my-4'> Logout</button>

 </div>
)};

export default UserPage;
