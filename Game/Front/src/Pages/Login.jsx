import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import KiwiMain from '../assets/KiwiMain.png';
import { 
    LoginContainer, BackgroundImage, LoginBox, Title, 
    InputGroup, StyledInput, ButtonGroup, LoginButton, SubButton 
} from './Login.style';

const Login = () => {
    const navigate = useNavigate();
    const [loginData, setLoginData] = useState({
        email: '',
        password: ''
    });

    const handleChange = (e) => {
        setLoginData({ ...loginData, [e.target.name]: e.target.value });
    };

const handleLogin = async (e) => {
    e.preventDefault();
    const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginData),
    });

    if (response.ok) {
        const data = await response.json();
        localStorage.setItem('token', data.token);
        localStorage.setItem('nickname', data.nickname);
        
        alert(`${data.nickname}님, 환영합니다!`);
        navigate('/mypage');
    }else{
        const errorMsg = await response.text();
        alert(errorMsg);
    }
};

    return (
        <LoginContainer>
            <BackgroundImage src={KiwiMain} alt="background" />
            
            <LoginBox>
                <Title>ENTER ARCHIVE</Title>
                
                <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
                    <InputGroup>
                        <StyledInput 
                            name="email"
                            value={loginData.email}
                            onChange={handleChange}
                            placeholder="EMAIL"
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