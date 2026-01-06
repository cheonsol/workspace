import styled from 'styled-components';


export const MainContainer = styled.div`
    position: relative;
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #121212;
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
    filter: brightness(0.7); 
`;

export const BottomLeftGroup = styled.div`
    position: absolute;
    left: 50px;
    bottom: 50px;
    display: flex;
    flex-direction: column;
    gap: 15px;
    z-index: 10; 
`;


export const GameButton = styled.div`
 
    width: 250px;
    padding: 12px 20px;
    font-size: 1.2rem;
    font-family: 'Courier New', Courier, monospace;
    cursor: pointer;
    transition: all 0.2s;
    
    background-color: ${props => props.isActive ? '#d4af37' : 'rgba(26, 26, 26, 0.8)'};
    color: ${props => props.isActive ? '#1a1a1a' : '#d4af37'};
    border: 1px solid #d4af37;
    transform: ${props => props.isActive ? 'translateX(20px)' : 'translateX(0)'};
    
    &::before {
        content: '${props => props.isActive ? '▶ ' : ''}';
    }
`;