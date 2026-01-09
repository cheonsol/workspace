import styled, { keyframes } from 'styled-components';

const pulse = keyframes`
    0%, 100% { transform: scale(1); }
    50% { transform: scale(1.05); }
`;

const shake = keyframes`
    0%, 100% { transform: translateX(0); }
    25% { transform: translateX(-5px); }
    75% { transform: translateX(5px); }
`;

const fadeIn = keyframes`
    from { opacity: 0; transform: translateY(-20px); }
    to { opacity: 1; transform: translateY(0); }
`;

export const GameContainer = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    cursor: pointer;
    user-select: none;
`;

export const ScoreBoard = styled.div`
    position: fixed;
    top: 30px;
    right: 30px;
    font-size: 2rem;
    font-weight: 900;
    color: #8b4513;
    background: rgba(255, 222, 89, 0.95);
    padding: 15px 25px;
    border-radius: 20px;
    border: 4px solid #8b4513;
    box-shadow: 0 6px 0 #d97706, 0 10px 30px rgba(0,0,0,0.3);
    z-index: 100;
    font-family: 'Segoe UI', sans-serif;
    backdrop-filter: blur(5px);
`;

export const SpeedIndicator = styled.div`
    position: fixed;
    top: 30px;
    left: 30px;
    font-size: 1.4rem;
    font-weight: 800;
    color: #fff;
    background: ${props => {
        const speed = props.speed || 8;
        if (speed >= 18) return 'linear-gradient(135deg, #ff416c, #ff4b2b)';
        if (speed >= 14) return 'linear-gradient(135deg, #f093fb, #f5576c)';
        if (speed >= 11) return 'linear-gradient(135deg, #4facfe, #00f2fe)';
        return 'linear-gradient(135deg, #43e97b, #38f9d7)';
    }};
    padding: 12px 20px;
    border-radius: 15px;
    border: 3px solid rgba(255,255,255,0.6);
    box-shadow: 0 5px 20px rgba(0,0,0,0.3);
    z-index: 100;
    animation: ${props => props.speed >= 14 ? pulse : 'none'} 0.5s infinite;
    backdrop-filter: blur(5px);
`;

export const Overlay = styled.div`
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: linear-gradient(
        135deg,
        rgba(255, 255, 255, 0.95) 0%,
        rgba(255, 248, 220, 0.95) 100%
    );
    z-index: 200;
    padding: 50px;
    border-radius: 30px;
    border: 5px solid #8b4513;
    box-shadow: 0 10px 0 #d97706, 0 20px 60px rgba(0,0,0,0.4);
    animation: ${fadeIn} 0.3s ease-out;
    max-width: 90vw;
    backdrop-filter: blur(10px);
`;

export const StartMessage = styled.div`
    text-align: center;
    
    h2 {
        font-size: 3.5rem;
        color: #8b4513;
        margin-bottom: 25px;
        text-shadow: 3px 3px 0 #ffde59;
        animation: ${pulse} 2s infinite;
    }
    
    p {
        font-size: 1.5rem;
        color: #5d4037;
        font-weight: 600;
    }
`;

export const GameOverTitle = styled.h2`
    font-size: 3.5rem;
    color: #c62828;
    margin-bottom: 20px;
    text-shadow: 3px 3px 0 #ffcdd2;
    animation: ${shake} 0.5s ease-out;
`;

export const FinalScore = styled.div`
    font-size: 2rem;
    color: #8b4513;
    font-weight: 900;
    margin-bottom: 30px;
    background: linear-gradient(135deg, #ffecd2, #fcb69f);
    padding: 20px 40px;
    border-radius: 20px;
    border: 4px solid #8b4513;
    box-shadow: 0 5px 0 #d97706;
`;

export const NicknameInput = styled.input`
    width: 100%;
    max-width: 350px;
    padding: 18px 25px;
    font-size: 1.2rem;
    font-weight: 600;
    border: 4px solid #8b4513;
    border-radius: 20px;
    background-color: #fffdf7;
    color: #5d4037;
    outline: none;
    margin-bottom: 25px;
    text-align: center;
    box-shadow: inset 0 3px 8px rgba(0,0,0,0.1);
    transition: all 0.2s;
    cursor: text;

    &::placeholder {
        color: #a1887f;
    }

    &:focus {
        border-color: #ffde59;
        box-shadow: 
            inset 0 3px 8px rgba(0,0,0,0.1),
            0 0 0 4px rgba(255, 222, 89, 0.5);
    }
`;

export const ButtonRow = styled.div`
    display: flex;
    gap: 20px;
    flex-wrap: wrap;
    justify-content: center;
`;

export const ActionButton = styled.button`
    padding: 16px 35px;
    font-size: 1.2rem;
    font-weight: 900;
    border: 4px solid #8b4513;
    border-radius: 50px;
    cursor: pointer;
    transition: all 0.15s;
    
    background: ${props => props.primary 
        ? 'linear-gradient(135deg, #ffde59 0%, #ffcc02 100%)' 
        : 'linear-gradient(135deg, #fff 0%, #f5f5f5 100%)'};
    color: #8b4513;
    box-shadow: 0 6px 0 ${props => props.primary ? '#d97706' : '#bdbdbd'};

    &:hover:not(:disabled) {
        transform: translateY(-3px);
        box-shadow: 0 9px 0 ${props => props.primary ? '#d97706' : '#bdbdbd'};
        background: ${props => props.primary 
            ? 'linear-gradient(135deg, #ffeb3b 0%, #ffde59 100%)' 
            : 'linear-gradient(135deg, #f5f5f5 0%, #eeeeee 100%)'};
    }

    &:active:not(:disabled) {
        transform: translateY(3px);
        box-shadow: 0 3px 0 ${props => props.primary ? '#d97706' : '#bdbdbd'};
    }

    &:disabled {
        opacity: 0.7;
        cursor: not-allowed;
    }
`;
