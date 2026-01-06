import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import MainIllust from '../assets/MainIllust.png';
import { 
    SignupContainer, BackgroundImage, FormBox, Title, 
    InputGroup, Label, StyledInput, ButtonGroup, ActionButton 
} from './Signup.style';

const Signup = () => {
    const navigate = useNavigate();
    
    const [formData, setFormData] = useState({
        userId: '',
        password: '',
        nickname: '',
        email: ''
    });

    const handleSignup = (e) => {
        e.preventDefault();
        console.log("회원가입 데이터:", formData);
        alert("기록관으로 등록되었습니다!");
        navigate('/login');
    };


    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    return (
        <>
            <SignupContainer>
                <BackgroundImage src={MainIllust} alt="background" />
                <FormBox>
                    <Title>NEW ARCHIVIST</Title>
                    
                    <InputGroup>
                        <Label>ID (ACCOUNT)</Label>
                        <StyledInput 
                            name="userId"
                            value={formData.userId}
                            onChange={handleChange}
                            placeholder="아이디를 입력하세요"
                        />
                    </InputGroup>

                    <InputGroup>
                        <Label>PASSWORD</Label>
                        <StyledInput 
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            placeholder="비밀번호를 입력하세요"
                        />
                    </InputGroup>

                    <InputGroup>
                        <Label>NICKNAME</Label>
                        <StyledInput 
                            name="nickname"
                            value={formData.nickname}
                            onChange={handleChange}
                            placeholder="닉네임을 입력하세요"
                        />
                    </InputGroup>

                    <InputGroup>
                        <Label>EMAIL</Label>
                        <StyledInput 
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            placeholder="이메일을 입력하세요"
                        />
                    </InputGroup>

                    <ButtonGroup>
                        <ActionButton 
                            primary 
                            onClick={handleSignup}
                        >
                            CREATE ACCOUNT
                        </ActionButton>
                        <ActionButton 
                            type="button"
                            onClick={() => navigate('/')}
                        >
                            CANCEL
                        </ActionButton>
                    </ButtonGroup>
                </FormBox>
            </SignupContainer>
        </>
    );
};

export default Signup;