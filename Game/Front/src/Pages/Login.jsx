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

    const handleLogin = (e) => {
        e.preventDefault();
        console.log("로그인 시도:", loginData);
        alert("기록보관소에 접속합니다.");
        navigate('/'); 
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