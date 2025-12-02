import React from 'react'

const UseUser = ({user, setUser}) => {
    const addUser = () => {
        setUser(prevUsers => [...prevUsers, newUser])
    }
}



export default UseUser