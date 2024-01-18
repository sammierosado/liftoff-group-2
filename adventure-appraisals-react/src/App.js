import React from 'react';
import Login from'./components/Login';
import UserPage from'./components/UserPage';
import UserSignUp from'./components/UserSignUp';
import HomePage from'./components/HomePage';
import { Route, Routes } from 'react-router-dom';
import { AuthContextProvider} from './Context/AuthContext';
import ProtectedRoute from './components/ProtectedRoutes';
import NavigationBar from './components/NavigationBar';
import { getAuth, isSignInWithEmailLink, signInWithEmailLink } from "firebase/auth";
import { useEffect } from "react";
import firebaseApp from './firebase';
import ReviewPage from './components/ReviewPage';
import WeatherApi from './components/WeatherApi';

  function App() {
  useEffect(() => {
      // Check if the link is a sign-in with an email link
      const checkEmailVerification = async () => {
        const auth = getAuth(firebaseApp);

        if (isSignInWithEmailLink(auth, window.location.href)) {
          let email = window.localStorage.getItem('emailForSignIn');

          // If email is not available locally, prompt the user
          if (!email) {
            email = window.prompt('Please provide your email for confirmation');
          }

          try {
            // Complete the sign-in with the email link
            const result = await signInWithEmailLink(auth, email, window.location.href);

            // Clear email from storage
            window.localStorage.removeItem('emailForSignIn');

            // Access the new user via result.user
            // Check if the user is new or existing: result.additionalUserInfo.isNewUser
            console.log("User signed in:", result.user);
          } catch (error) {
            // Handle errors
            // Common errors could be invalid email and invalid or expired OTPs.
            console.error("Error during email link verification:", error.message);
          }
        }
      };

      // Call the function when the component mounts
      checkEmailVerification();
    }, []);
    return (
  <div>
  <h1 className='text-center text-3xl text-blue-900 font-bold mt-4'>
      Adventure Appraisals
      </h1>
    <NavigationBar/>

    <AuthContextProvider>
    <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/userSignUp" element={<UserSignUp />} />
          <Route path="/login" element={<Login />} />
          <Route path="/userPage" element={
          <ProtectedRoute>
          <UserPage />
          </ProtectedRoute> } />


          <Route path="/review/*" element={
          <>
           <WeatherApi />
          <ReviewPage />
           </>
           } />


    </Routes>
    </AuthContextProvider>
   </div>

   );
   }

export default App;


