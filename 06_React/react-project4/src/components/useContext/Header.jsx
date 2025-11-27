import React, { useContext } from 'react'
import { useUser } from './UserContext'

const Header = () => {
    const user = useUser();
  return (
    <div>
        <h2>안녕하세요, {user.name}님</h2>
        <p>나이 {user.age}에 {user.role}권한 입니다.</p>
    </div>
  )
}

export default Header