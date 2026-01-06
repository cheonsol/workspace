import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import MainIllust from '../assets/MainIllust.png';
import { 
    LoginContainer, BackgroundImage, LoginBox, Title, 
    InputGroup, StyledInput, ButtonGroup, LoginButton, SubButton 
} from './Login.style';

const Login = () => {
    const navigate = useNavigate();
    const [loginData, setLoginData] = useState({
        userId: '',
        password: ''
    });

    const handleChange = (e) => {
        setLoginData({ ...loginData, [e.target.name]: e.target.value });
    };

    const handleLogin = async (e) => {
    e.preventDefault();

    try {
        const response = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(loginData),
        });

        if (response.ok) {
            const message = await response.text();
            alert(message);
            navigate('/');
        } else {
            const errorMsg = await response.text();
            alert(errorMsg);
        }
    } catch (error) {
        console.error("로그인 에러:", error);
        alert("서버 연결에 실패했습니다.");
    }
};

    return (
        <LoginContainer>
            <BackgroundImage src={MainIllust} alt="background" />
            
            <LoginBox>
                <Title>ENTER ARCHIVE</Title>
                
                <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
                    <InputGroup>
                        <StyledInput 
                            name="userId"
                            value={loginData.userId}
                            onChange={handleChange}
                            placeholder="USER ID"
                            required
                        />
                        <StyledInput 
                            type="password"
                            name="password"
                            value={loginData.password}
                            onChange={handleChange}
                            placeholder="PASSWORD"
                            required
                        />
                    </InputGroup>

                    <ButtonGroup>
                        <LoginButton type="submit">LOGIN</LoginButton>
                        <SubButton type="button" onClick={() => navigate('/')}>
                            BACK TO TITLE
                        </SubButton>
                    </ButtonGroup>
                </form>
            </LoginBox>
        </LoginContainer>
    );
};

export default Login;