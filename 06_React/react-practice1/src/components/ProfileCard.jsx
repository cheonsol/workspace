import React from 'react'
import styled from 'styled-components'

const Box = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap : 12px;
`
const Info = styled.p`
  text-align: start;
`
const IsOnline = styled.div`
width : 20px;
height : 20px;
border-radius: 50%;
border: solid 1px black;
background-color : ${props => (props.$isOnline ? 'green' : 'red')};
`


const ProfileCard = ({p}) => {
  const {name, age, isOnline} = p;
  return (
    <Box>
      <Info>
      이름 : {name} <br />
      나이 : {age}세
      </Info>
      <IsOnline $isOnline={isOnline} />
    </Box>
  )
}

export default ProfileCard