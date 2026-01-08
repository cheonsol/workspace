import styled from 'styled-components';

export const LoginContainer = styled.div`
    position: relative;
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f0f9ff;
    overflow: hidden;
`;

export const BackgroundImage = styled.img`
    position: absolute;
    width: 100%;
    height: 100%;
    object-fit: cover;
    z-index: 0;
    filter: brightness(0.9) blur(2px);
`;

export const LoginBox = styled.div`
    position: relative;
    z-index: 10;
    width: 380px;
    padding: 50px 40px;
    background-color: rgba(255, 255, 255, 0.95);
    border: 4px solid #8b4513;
    border-radius: 30px;
    box-shadow: 0 12px 0 #d97706;
    display: flex;
    flex-direction: column;
    gap: 30px;
`;

export const Title = styled.h2`
    color: #8b4513;
    text-align: center;
    font-size: 2.2rem;
    font-weight: 900;
    letter-spacing: 1px;
    margin: 0;
    text-shadow: 1px 1px 0px #fff;
`;

export const InputGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 15px;
`;

export const StyledInput = styled.input`
    background: #ffffff;
    border: 3px solid #e2e8f0;
    border-radius: 15px;
    padding: 16px;
    color: #4a3728;
    font-size: 1.1rem;
    font-weight: 600;
    outline: none;
    transition: all 0.2s;

    &:focus {
        border-color: #8b4513;
        background-color: #fff;
    }

    &::placeholder {
        color: #a0aec0;
    }
`;

export const ButtonGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 15px;
`;

export const LoginButton = styled.button`
    padding: 16px;
    background-color: #ffde59;
    color: #8b4513;
    border: 3px solid #8b4513;
    border-radius: 50px;
    font-weight: 900;
    font-size: 1.2rem;
    cursor: pointer;
    box-shadow: 0 5px 0 #d97706;
    transition: all 0.1s;

    &:hover {
        background-color: #ffeb3b;
        transform: translateY(-2px);
        box-shadow: 0 7px 0 #d97706;
    }

    &:active {
        transform: translateY(3px);
        box-shadow: 0 2px 0 #d97706;
    }
`;

export const SubButton = styled.button`
    background: none;
    border: none;
    color: #22c55e;
    font-size: 1rem;
    font-weight: 700;
    cursor: pointer;
    text-decoration: none;
    transition: color 0.2s;

    &:hover {
        color: #15803d;
        text-decoration: underline;
    }
`;