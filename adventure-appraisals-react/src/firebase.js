import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
import "firebase/firestore";

const firebaseConfig = {
  apiKey: "AIzaSyAAjX74ECPiDYpyYVMfE1R0cuM511Tb1p0",
  authDomain: "adventure-appraisals.firebaseapp.com",
  projectId: "adventure-appraisals",
  storageBucket: "adventure-appraisals.appspot.com",
  messagingSenderId: "605052820745",
  appId: "1:605052820745:web:6b678d77569def4ef107d8"

};

const app = initializeApp(firebaseConfig);

const auth = getAuth(app);
const firestore = getFirestore();

export { auth, firestore };
export default app;

