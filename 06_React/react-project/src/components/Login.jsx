import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import useGameStore from '../store/store';
import Header from '../layout/Header';
// 스타일 컴포넌트 import
import { AuthWrapper, AuthBox, Title, InputGroup, StyledInput, AuthButton, LinkText } from '../style/Auth.style';

const Login = () => {
  const navigator = useNavigate();
  const users  = useGameStore((state) => state.users);
  const login = useGameStore((state) => state.login);

  const [inputs, setInput] = useState({
    Uid : '',
    password : '',
  });

  const { Uid, password } = inputs;

  const handleChange = (e) => {
    const {name, value} = e.target;
    setInput({ ...inputs, [name] : value })
  }  

  const handleSubmit = (e) => {
    e.preventDefault();

    if(!Uid.trim() || !password.trim()){
        alert('아이디와 비밀번호를 모두 입력해주세요.');
        return;
    }

    const findUser = users.find(u => u.Uid === Uid);

    if(!findUser){
      alert('존재하지 않는 모험가(ID)입니다.');
      return;
    }
    
    if(findUser.password !== password){
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }

    // 로그인 성공
    login(findUser); 
    alert(`환영합니다. ${findUser.nickname}님!`); 
    navigator('/');
  }

  return (
    <>
      <Header />
      <AuthWrapper>
        <AuthBox onSubmit={handleSubmit}>
          <Title>모험가 로그인</Title>
          
          <InputGroup>
            <label>아이디</label>
            <StyledInput 
              type="text" 
              name="Uid" 
              value={Uid} 
              onChange={handleChange} 
              placeholder="아이디를 입력하세요"
            />
          </InputGroup>

          <InputGroup>
            <label>비밀번호</label>
            <StyledInput 
              type="password" 
              name="password" 
              value={password} 
              onChange={handleChange} 
              placeholder="비밀번호를 입력하세요"
            />
          </InputGroup>

          <AuthButton type="submit">길드 입장하기</AuthButton>

          <LinkText>
            아직 계정이 없으신가요? 
            <Link to="/signup">모험가 등록</Link>
          </LinkText>
        </AuthBox>
      </AuthWrapper>
    </>
  )
}

export default Login;