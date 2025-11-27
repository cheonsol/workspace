import React from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import { userContext } from '../App'

  const CheckOnline = styled.div`
    width : 20px;
    height : 20px;
    border-radius : 50%;
    background-color: ${(props => props.$isOnline) ? 'green' : 'red'};
  `
  const UserBox = styled(Link)`
    display : flex;
    gap : 12px;
    border : 1px solid black;
    padding: 12px;
    justify-Content : center;
    align-Items : center;
    text-decoration: none;
    color : inherit;
   
    &+&{
      border-top : none;
    }  
  `

const UserList = () => {
  const users = React.useContext(userContext)
  return (
    <>
    {console.log(users)}
      <h2>유저 목록</h2>
      
        
        {users.map((u) =>
        <UserBox key = {u.id} to = {`/user/${u.id}`}>
          <p>{u.name}</p>
          <p>{u.age}</p>
          <CheckOnline $isOnline ={u.isOnline} />
        </UserBox>
        )}
        
     
    </>
    
  )
}

export default UserList