import styled, { keyframes } from 'styled-components';

const bounce = keyframes`
    0%, 100% { transform: translateY(0); }
    50% { transform: translateY(-10px); }
`;

const shimmer = keyframes`
    0% { background-position: -200% center; }
    100% { background-position: 200% center; }
`;

export const MainContainer = styled.div`
    position: relative;
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f0f9ff;
    overflow: hidden;
`;

export const HeroImage = styled.img`
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    z-index: 0;
`;

export const BottomLeftGroup = styled.div`
    position: absolute;
    left: 60px;
    bottom: 60px;
    display: flex;
    flex-direction: column;
    gap: 20px;
    z-index: 10;
`;

export const GameButton = styled.div`
    width: 280px;
    padding: 16px 30px;
    font-size: 1.5rem;
    font-weight: 900;
    cursor: pointer;
    transition: all 0.15s ease-in-out;
    border-radius: 60px;
    border: 4px solid #8b4513;
    
    background-color: ${props => props.isActive ? '#ffeb3b' : '#ffde59'};
    color: #8b4513;
    box-shadow: ${props => props.isActive ? '0 3px 0 #d97706' : '0 8px 0 #d97706'};
    transform: ${props => props.isActive ? 'translateY(5px) translateX(25px)' : 'translateX(0)'};
    
    display: flex;
    align-items: center;
    justify-content: flex-start;

    &::before {
        content: '${props => props.isActive ? '🥝 ' : ''}';
        margin-right: 10px;
    }

    &:hover {
        background-color: #ffeb3b;
        transform: ${props => props.isActive ? 'translateY(5px) translateX(25px)' : 'translateY(-3px)'};
    }
`;

export const BestScoreBadge = styled.div`
    position: absolute;
    top: 30px;
    right: 30px;
    background-color: #8b4513;
    color: #ffde59;
    padding: 15px 25px;
    border: 4px solid #ffde59;
    border-radius: 15px;
    box-shadow: 0 5px 0 #5d4037;
    text-align: center;
    font-weight: 900;
    z-index: 20;

    small {
        display: block;
        font-size: 0.8rem;
        margin-bottom: 5px;
        color: #fff;
    }

    div {
        font-size: 1.5rem;
    }
`;

export const LogoContainer = styled.div`
    position: absolute;
    top: 60px;
    left: 60px;
    z-index: 20;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
`;

export const LogoTitle = styled.h1`
    font-size: 4.5rem;
    font-weight: 900;
    margin: 0;
    display: flex;
    align-items: center;
    gap: 10px;
    
    /* 그라데이션 텍스트 */
    background: linear-gradient(
        135deg,
        #8b4513 0%,
        #d2691e 25%,
        #8b4513 50%,
        #cd853f 75%,
        #8b4513 100%
    );
    background-size: 200% auto;
    background-clip: text;
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    animation: ${shimmer} 3s linear infinite;
    
    /* 텍스트 외곽선 효과 */
    filter: drop-shadow(3px 3px 0 #ffde59) 
            drop-shadow(-1px -1px 0 #ffde59)
            drop-shadow(5px 5px 0 rgba(0,0,0,0.2));
`;

export const LogoKiwi = styled.span`
    display: inline-block;
    font-size: 3.5rem;
    animation: ${bounce} 1.5s ease-in-out infinite;
    /* 이모지가 보이도록 그라데이션 효과 해제 */
    background: none;
    -webkit-background-clip: unset;
    background-clip: unset;
    -webkit-text-fill-color: initial;
    filter: drop-shadow(2px 2px 0 rgba(0,0,0,0.2));
`;

export const LogoSubtitle = styled.p`
    font-size: 1.2rem;
    font-weight: 700;
    color: #5d4037;
    margin: 10px 0 0 5px;
    padding: 8px 20px;
    background: rgba(255, 222, 89, 0.9);
    border-radius: 20px;
    border: 3px solid #8b4513;
    box-shadow: 0 3px 0 #d97706;
`;