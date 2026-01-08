import styled from 'styled-components';

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