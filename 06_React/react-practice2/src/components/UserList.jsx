import React from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import { userContext } from '../App'

  const CheckOnline = styled.div`
    width : 20px;
    height : 20px;
    border-radius : 50%;
    background-color: ${props => props.$isOnline ? 'green' : 'red'};
  `
  const Title = styled.div`
    display: grid;  
    grid-template-columns: 1fr 1fr 1fr;
    justify-content: center;
    align-items: center;
    gap : 12px;
    width: 100%;
  `
  const Container = styled.div`
  border : 1px solid black;
  padding : 12px;
  `
  const UserBox = styled(Link)`
    display : grid;
    grid-template-columns : 1fr 1fr 1fr;
    justify-content: center;
    align-items: center;
    text-decoration: none;
    color : inherit;
    width: 100%;
    gap : 12px;
   
    &+&{
      border-top : none;
    }  
  `
   const NameCard = styled.div`
      margin: 10px;
  `

const UserList = () => {
  const users = React.useContext(userContext)
  return (
    <Container>
        <h2>유저 목록</h2>
       
        <Title>
          <p>이름</p>
          <p>나이</p>
          <p>상태</p>
        </Title>
        {users.map((u) =>
        <UserBox key = {u.id} to = {`/user/${u.id}`}>
          <NameCard>{u.name}</NameCard>
          <NameCard>{u.age}</NameCard>
          <CheckOnline $isOnline ={u.isOnline} />
        </UserBox>
        )}
       
    </Container>
    
  )
}

export default UserList