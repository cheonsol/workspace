import React, { useContext, useState } from 'react'
import { UserContext } from './../user/UserContext';
import { useNavigate } from 'react-router-dom';

const Login = () => {

  const { users } = useContext(UserContext)
  const [inputs, setInput] = useState({
    id : '',
    password : '',
  });

  const { id, password } = inputs;


  const navigator = useNavigate();


  const handleChange = (e) => {
    const {name, value} = e.target;

    setInput({
      ...inputs,
      [name] : value,
    })
  }  


  const hadleSubmit = (e) => {
    e.preventDefault();

    const checkId = users.find(u => u.id === inputs.id)
    const values = Object.values(inputs);
    
      if(values.includes('')){
      alert('모든 항목을 입력해주십시오.')
      return;
    }

    if(!checkId){
      alert('아이디가 존재하지 않습니다.')
      return;
    }
    
    const checkPwd = users.find(u => u.password === inputs.password)
    
    if(!checkPwd){
      alert('비밀번호가 일치하지 않습니다.')
      return;
    }

    navigator('/');
  }

  return (
    <form onSubmit={hadleSubmit}>
    <div>
      <div>아이디 : <input type="text" name = 'id'  value = {id} onChange={handleChange} /></div>
      <div>비밀번호 : <input type="password" name = 'password' value = {password} onChange={handleChange} /></div>
      <div><button>로그인</button></div>
    </div>
    </form>
  )
}

export default Login