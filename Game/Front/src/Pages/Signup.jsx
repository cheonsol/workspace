import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../assets/Navbar';
import KiwiMain from '../assets/KiwiMain.png';
import { 
    SignupContainer, BackgroundImage, FormBox, Title, 
    InputGroup, Label, StyledInput, ButtonGroup, ActionButton 
} from './Signup.style';

const Signup = () => {
    const navigate = useNavigate();
    
    const [formData, setFormData] = useState({
        email: '',      
        password: '',
        confirmPassword: '', 
        nickname: ''
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSignup = async (e) => {
        e.preventDefault();

        if (formData.password !== formData.confirmPassword) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        if (!formData.email.includes('@')) {
            alert("올바른 이메일 형식이 아닙니다.");
            return;
        }

        try {
            const { confirmPassword, ...submitData } = formData;

            const response = await fetch('http://localhost:8080/api/signup', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(submitData),
            });

            if (response.ok) {
                const message = await response.text();
                alert(message);
                navigate('/login');
            } else {
                const errorMsg = await response.text();
                alert(errorMsg);
            }
        } catch (error) {
            alert("서버 연결에 실패했습니다.");
        }
    };

    return (
        <>
            <SignupContainer>
                <BackgroundImage src={KiwiMain} alt="background" />
                <FormBox>
                    <Title>NEW ARCHIVIST</Title>
                    
                    <InputGroup>
                        <Label>EMAIL (ID)</Label>
                        <StyledInput 
                            name="email"
                            type="email"
                            value={formData.email}
                            onChange={handleChange}
                            placeholder="이메일을 입력하세요"
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
                        <Label>CONFIRM PASSWORD</Label>
                        <StyledInput 
                            type="password"
                            name="confirmPassword"
                            value={formData.confirmPassword}
                            onChange={handleChange}
                            placeholder="비밀번호를 다시 입력하세요"
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

                    <ButtonGroup>
                        <ActionButton primary onClick={handleSignup}>
                            CREATE ACCOUNT
                        </ActionButton>
                        <ActionButton type="button" onClick={() => navigate('/')}>
                            CANCEL
                        </ActionButton>
                    </ButtonGroup>
                </FormBox>
            </SignupContainer>
        </>
    );
};

export default Signup;