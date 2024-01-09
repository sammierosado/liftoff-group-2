import React from 'react'
import {Navigate} from 'react-router-dom'
import {UserAuth} from '../Context/AuthContext'

const ProtectedRoutes = ( { children} ) => {
    const {user} = UserAuth()

    if (!user) {
        return<Navigate to='/Login' />;

    }
    return children
}

export default ProtectedRoutes

