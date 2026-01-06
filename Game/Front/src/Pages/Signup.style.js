import styled from 'styled-components';

export const SignupContainer = styled.div`
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
    filter: brightness(0.3) blur(5px);
`;

export const FormBox = styled.div`
    position: relative;
    z-index: 10;
    width: 400px;
    padding: 40px;
    background-color: rgba(0, 0, 0, 0.85);
    border: 2px solid #d4af37;
    box-shadow: 0 0 20px rgba(212, 175, 55, 0.2);
    display: flex;
    flex-direction: column;
    gap: 20px;
`;

export const Title = styled.h2`
    color: #d4af37;
    text-align: center;
    margin-bottom: 10px;
    letter-spacing: 3px;
    font-family: 'serif';
`;

export const InputGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
`;

export const Label = styled.label`
    color: #d4af37;
    font-size: 0.9rem;
`;

export const StyledInput = styled.input`
    background: rgba(26, 26, 26, 0.8);
    border: 1px solid #444;
    padding: 12px;
    color: white;
    outline: none;
    transition: all 0.3s;

    &:focus {
        border-color: #d4af37;
        box-shadow: 0 0 10px rgba(212, 175, 55, 0.5);
    }
`;

export const ButtonGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 10px;
    margin-top: 10px;
`;

export const ActionButton = styled.button`
    padding: 12px;
    background-color: ${props => props.primary ? '#d4af37' : 'transparent'};
    color: ${props => props.primary ? '#1a1a1a' : '#d4af37'};
    border: 1px solid #d4af37;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.2s;

    &:hover {
        background-color: #d4af37;
        color: #1a1a1a;
    }
`;