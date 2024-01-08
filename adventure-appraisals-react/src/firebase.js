// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyAAjX74ECPiDYpyYVMfE1R0cuM511Tb1p0",
    authDomain: "adventure-appraisals.firebaseapp.com",
    projectId: "adventure-appraisals",
    storageBucket: "adventure-appraisals.appspot.com",
    messagingSenderId: "605052820745",
    appId: "1:605052820745:web:bd07bde854af58c4f107d8"
  };

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export default app;