import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import useGameStore from '../store/store';
import Header from '../layout/Header';
// 스타일 재사용
import { AuthWrapper, AuthBox, Title, InputGroup, StyledInput, AuthButton, LinkText } from '../style/Auth.style';

const SignUp = () => {
    const navigator = useNavigate();
    const users  = useGameStore((state) => state.users);
    const addUser = useGameStore((state) => state.addUser);
    
    const [inputs, setInputs] = useState({
        Uid : '', 
        password : '',
        nickname : '',
    });

    const { Uid, password, nickname } = inputs;
    
    const handleChange = (e) => {
        const { name, value } = e.target;
        setInputs({ ...inputs, [name] : value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // 1. 빈칸 체크
        if(!Uid.trim() || !password.trim() || !nickname.trim()){
            alert('모든 항목을 입력해주세요.');
            return;
        }

        // 2. 중복 체크
        const isIdExist = users.find(u => u.Uid === Uid);
        const isNickExist = users.find(u => u.nickname === nickname);

        if(isIdExist) {
            alert('이미 존재하는 아이디입니다.');
            return;
        }
        if(isNickExist) {
            alert('이미 존재하는 닉네임입니다.');
            return;
        }

        // 3. 가입 완료 (inputs 그대로 넘김 - Uid 이름 맞춤)
        addUser(inputs);

        alert('모험가 등록이 완료되었습니다! 로그인해주세요.');
        navigator('/login');
    };

    return (
    <>
      <Header />
      <AuthWrapper>
        <AuthBox onSubmit={handleSubmit}>
            <Title>모험가 등록</Title>
            
            <InputGroup>
                <label>아이디</label> 
                <StyledInput 
                    type='text' 
                    name='Uid' 
                    value={Uid} 
                    onChange={handleChange}
                    placeholder="사용할 아이디"
                />
            </InputGroup>

            <InputGroup>
                <label>비밀번호</label> 
                <StyledInput 
                    type='password' 
                    name='password' 
                    value={password} 
                    onChange={handleChange}
                    placeholder="사용할 비밀번호"
                />
            </InputGroup>

            <InputGroup>
                <label>닉네임 (모험가 이름)</label> 
                <StyledInput 
                    type='text' 
                    name='nickname' 
                    value={nickname} 
                    onChange={handleChange}
                    placeholder="게임에서 쓸 이름"
                />
            </InputGroup>

            <AuthButton type="submit">등록 완료</AuthButton>
            
            <LinkText>
                이미 계정이 있으신가요?
                <Link to="/login">로그인 하러 가기</Link>
            </LinkText>
        </AuthBox>
      </AuthWrapper>
    </>
    )
}

export default SignUp;