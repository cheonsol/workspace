import React from 'react'
import { useState } from 'react'
import {BrowserRouter, Routes, Route, Link} from 'react-router-dom'
import './App.css'
import UserList from './components/UserList'
import UserDetail from './components/UserDetail'
import UserRegistration from './components/UserRegistration'
import NotFound from './components/NotFound'
import styled from 'styled-components'


export const userContext = React.createContext();

const userList =  [{
    id : 1,
    name : "김민수",
    age : 22,
    isOnline : true,
  },{
    id : 2,
    name : "이민아",
    age : 24,
    isOnline : true,
  },{
    id : 3,
    name : "강백호",
    age : 33,
    isOnline : false,
  }]

  const Nav =styled.nav`
    display : flex;
    gap : 12px;
    justify-content: center;
    align-items: center;
  `

function App() {
  const [users, setUsers] = useState(userList)
  
  
  const addUser = (user) => {
    setUsers([...users, user])
  }

   const setOnline = (id) => {
    setUsers(users.map(u => u.id === id ? {...u, isOnline : !u.isOnline} : u));
  }



  return (
    <userContext.Provider value = {users}>
      <BrowserRouter>
        <Nav> 
          <Link to = "/">홈</Link>
          <Link to = "/user">생성</Link>
        </Nav>
        <Routes>
          <Route path = "/" element = {<UserList />}/>
          <Route path = "/user/:id" element = {<UserDetail setOnline = {setOnline} />}/>
          <Route path = "/user" element = {<UserRegistration addUser = {addUser} />}/>
          <Route path = "*" element = {<NotFound />}/>
        </Routes>
      </BrowserRouter>
    </userContext.Provider>
  )
}

export default App
