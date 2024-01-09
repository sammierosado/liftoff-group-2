import React from 'react';
import { UserAuth } from '../Context/AuthContext';
import { useNavigate } from 'react-router-dom';

const UserPage = () => {
    const {user, logout} = UserAuth();
    const navigate = useNavigate();

const handleLogout = async () => {
    try {
    await logout();
    navigate('/Login');
    } catch (e) {
        console.log(e.message)
    }
};

return (
 <div className='max-w-{600px} mx-auto my-16 p-4'>
    <h1 className='text-2xl font-bold py-4'>Profile</h1>
    <p>Account Email: {user && user.email}</p>

    <button onClick={handleLogout} className='border px-6 py-2 my-4'> Logout</button>
 </div>

)};

export default UserPage;
