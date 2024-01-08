import React from 'react';
import Login from'./components/Login';
import UserPage from'./components/UserPage';
import UserSignUp from'./components/UserSignUp';
import HomePage from'./components/HomePage';
import { Route, Routes } from 'react-router-dom';
import { AuthContextProvider} from './Context/AuthContext';
import ProtectedRoute from './components/ProtectedRoutes';

  function App() {
    return (
  <div>
    <h1 className='text-center text-3xl text-blue-900 font-bold'>
    Adventure Appraisals
    </h1>
    <AuthContextProvider>
    <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/userSignUp" element={<UserSignUp />} />
          <Route path="/login" element={<Login />} />
          <Route path="/userPage" element={
          <ProtectedRoute>
          <UserPage />
          </ProtectedRoute> } />

    </Routes>
    </AuthContextProvider>
   </div>

   );
   }

export default App;


