import styled from 'styled-components';

export const LoginContainer = styled.div`
    position: relative;
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #121212;
    overflow: hidden;
`;

export const BackgroundImage = styled.img`
    position: absolute;
    width: 100%;
    height: 100%;
    object-fit: cover;
    z-index: 0;
    filter: brightness(0.4) blur(3px);
`;

export const LoginBox = styled.div`
    position: relative;
    z-index: 10;
    width: 360px;
    padding: 50px 40px;
    background-color: rgba(0, 0, 0, 0.85);
    border: 2px solid #d4af37;
    display: flex;
    flex-direction: column;
    gap: 25px;
`;

export const Title = styled.h2`
    color: #d4af37;
    text-align: center;
    font-size: 1.8rem;
    letter-spacing: 4px;
    margin: 0;
`;

export const InputGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 10px;
`;

export const StyledInput = styled.input`
    background: rgba(26, 26, 26, 0.8);
    border: 1px solid #444;
    padding: 14px;
    color: white;
    font-size: 1rem;
    outline: none;
    transition: all 0.3s;

    &:focus {
        border-color: #d4af37;
        box-shadow: 0 0 15px rgba(212, 175, 55, 0.4);
    }
`;

export const ButtonGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 12px;
`;

export const LoginButton = styled.button`
    padding: 14px;
    background-color: #d4af37;
    color: #1a1a1a;
    border: none;
    font-weight: bold;
    font-size: 1rem;
    cursor: pointer;
    transition: all 0.2s;

    &:hover {
        background-color: #fff;
        transform: translateY(-2px);
    }
`;

export const SubButton = styled.button`
    background: none;
    border: none;
    color: #888;
    font-size: 0.9rem;
    cursor: pointer;
    text-decoration: underline;
`;