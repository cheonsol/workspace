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

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSignup = async (e) => {
    e.preventDefault();

    try {
        const response = await fetch('http://localhost:8080/api/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData), // 리액트의 formData를 JSON으로 변환
        });

        if (response.ok) {
            const message = await response.text();
            alert(message);
            navigate('/login'); // 성공 시 로그인 페이지로 이동
        } else {
            alert("가입 실패: 서버 에러가 발생했습니다.");
        }
    } catch (error) {
        console.error("네트워크 에러:", error);
        alert("서버와 통신할 수 없습니다.");
    }
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