import styled from 'styled-components';

export const SignupContainer = styled.div`
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

export const FormBox = styled.div`
    position: relative;
    z-index: 10;
    width: 420px;
    padding: 40px;
    background-color: rgba(255, 255, 255, 0.95);
    border: 4px solid #8b4513;
    border-radius: 30px;
    box-shadow: 0 12px 0 #d97706;
    display: flex;
    flex-direction: column;
    gap: 20px;
`;

export const Title = styled.h2`
    color: #8b4513;
    text-align: center;
    margin-bottom: 5px;
    font-size: 2rem;
    font-weight: 900;
    text-shadow: 1px 1px 0px #fff;
`;

export const InputGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
`;

export const Label = styled.label`
    color: #8b4513;
    font-size: 1rem;
    font-weight: 800;
    margin-left: 5px;
`;

export const StyledInput = styled.input`
    background: #ffffff;
    border: 3px solid #e2e8f0;
    border-radius: 15px;
    padding: 12px;
    color: #4a3728;
    font-size: 1rem;
    font-weight: 600;
    outline: none;
    transition: all 0.2s;

    &:focus {
        border-color: #8b4513;
        background-color: #fff;
    }
`;

export const ButtonGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 12px;
    margin-top: 15px;
`;

export const ActionButton = styled.button`
    padding: 14px;
    background-color: ${props => props.primary ? '#ffde59' : 'transparent'};
    color: #8b4513;
    border: 3px solid #8b4513;
    border-radius: 50px;
    font-weight: 900;
    font-size: 1.1rem;
    cursor: pointer;
    box-shadow: ${props => props.primary ? '0 5px 0 #d97706' : 'none'};
    transition: all 0.1s;

    &:hover {
        background-color: ${props => props.primary ? '#ffeb3b' : '#f8fafc'};
        transform: translateY(-2px);
        box-shadow: ${props => props.primary ? '0 7px 0 #d97706' : 'none'};
    }

    &:active {
        transform: translateY(3px);
        box-shadow: ${props => props.primary ? '0 2px 0 #d97706' : 'none'};
    }
`;