import React from 'react'
import { useState } from 'react'
import styled from 'styled-components'
import { userContext } from '../App'



const InputTable = styled.table`
  td{
    border : 1px solid black;
  }
`


const UserRegistration = ({addUser}) => {
  const users = React.useContext(userContext)
  
  const [id, setId] = useState(users.length + 1)
  const [name,setName] = useState("")
  const [age,setAge] = useState("")

  const onChange = (ev) => {
  const {id, value} = ev.target
  {
    if(id === "name"){
      setName(value)
    }else if(id === "age"){
      setAge(value)
    }
  }
}

 const onClick = (event) => {
  event.preventDefault()
  setId(id)
  const newUser = {
      id : id,
      name : name,
      age : Number(age),
      isOnline : true,
  }
  addUser(newUser)
  

  console.log(users)
}

  
 
  return (
    <>
    {console.log(id)}
    <form onSubmit={onClick}>
      <InputTable>
        <thead>
          <tr>
            <th colSpan={2}>
              유저 등록 
            </th>
            
          </tr>
        </thead>
        <tbody>
        <tr>
          <td>이름</td>
          <td><input type="text" id = "name" onChange={onChange}/></td>
        </tr>
        <tr>
          <td>나이</td>
          <td><input type="text" id = "age" onChange={onChange}/></td>
        </tr>
        </tbody>
        <tfoot>
          <tr>
            <td></td>
            <td>
            <button type = "submit">저장</button>
            <button type = "reset">취소</button>
            </td>
          </tr>
        </tfoot>
      </InputTable>
      </form>
    </>
  )
}

export default UserRegistration