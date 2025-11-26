import React from 'react'
import styled from 'styled-components'

const Online = styled.div`
    width : 20px;
    height : 20px;
    border-radius : 50%;
    background-color: ${props => (props.$isOnline ? 'green' : 'red')};
`
const Profile = ({p}) => {
    
  return (
    <>
    <div>
        이름 : {p.name} <br />
        나이 : {p.age}세
    </div>
    <Online
        $isOnline = {p.isOnline} />
   </>
  )
}

export default Profile