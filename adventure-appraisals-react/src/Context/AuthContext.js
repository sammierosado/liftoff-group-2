import React, { createContext, useContext, useState, useEffect } from 'react';
import {
createUserWithEmailAndPassword,
signInWithEmailAndPassword,
signOut,
onAuthStateChanged,
} from 'firebase/auth';
import {auth} from '../firebase';


const UserContext = createContext()

export const AuthContextProvider = ({ children }) => {
    const[user, setUser] = useState({});

const createUser = (email, password) => {
return createUserWithEmailAndPassword(auth, email, password)
};

const signIn = (email, password) => {
return signInWithEmailAndPassword(auth, email, password)
}
useEffect(()=> {
const unsubscribe = onAuthStateChanged(auth, (currentUser) => {
    console.log(currentUser);
    setUser(currentUser);
    });
    return () => {
    unsubscribe();
    }
},[])

const logout = () => {
  return signOut(auth)
  }
    return (
    <UserContext.Provider value={{ createUser, user, logout, signIn }}>
        {children}
    </UserContext.Provider>

    );
};

export const UserAuth = () => {
return useContext(UserContext);
};