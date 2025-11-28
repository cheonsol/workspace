import React from 'react'
import { useParams } from 'react-router-dom'
import styled from 'styled-components'
import { userContext } from '../App'

const DetailTable = styled.table`
  width: 100%;
  max-width: 500px;
  margin-top: 20px;
  border-collapse: collapse;


  td {
    padding: 12px 15px;
    border-top: 1px solid #ddd;
    border-bottom: 1px solid #ddd;
  }

  td:first-child {
    background-color: #f8f8f8;
    font-weight: bold;
    text-align: center;
  }
`

const Button = styled.button`
display: flex;
justify-content: center;
align-items: center;
font-size: 12px;
`

const UserDetail = ({setOnline}) => {
  const users = React.useContext(userContext)

  const {id} = useParams();

  const user = users.find(u => u.id === parseInt(id))

  const onClick = () => {
      setOnline(user.isOnline = !user.isOnline)
      console.log(user.isOnline)
  }

  const onChange = () => {
    setOnline(user.id)
  }

  return (
    <>
      <h2>{user.name}님의 상세 정보</h2>
      <DetailTable>
        <tbody>
          <tr><td>이름</td><td colSpan={2}>{user.name}</td></tr>
          <tr><td>나이</td><td colSpan={2}>{user.age}</td></tr>
          <tr><td>상태</td><td onChange={onChange}>{user.isOnline ? "온라인" : "오프라인"}</td>
          <td><Button onClick={onClick}>전환</Button></td></tr>
        </tbody>
      </DetailTable>
    </>
  )
}

export default UserDetail