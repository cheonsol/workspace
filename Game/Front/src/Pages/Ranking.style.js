import styled from 'styled-components';

export const RankingContainer = styled.div`
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
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    z-index: 0;
    filter: brightness(0.9) blur(2px);
`;

export const RankingBox = styled.div`
    position: relative;
    z-index: 10;
    width: 500px;
    max-height: 85vh;
    padding: 40px;
    background-color: rgba(255, 255, 255, 0.95);
    border: 4px solid #8b4513;
    border-radius: 35px;
    box-shadow: 0 15px 0 #d97706;
    display: flex;
    flex-direction: column;
`;

export const RankingTitle = styled.h2`
    color: #8b4513;
    text-align: center;
    font-size: 2.2rem;
    font-weight: 900;
    margin-bottom: 30px;
    border-bottom: 5px solid #ffde59;
    padding-bottom: 10px;
    text-shadow: 1px 1px 0px #fff;
`;

export const RankList = styled.div`
    display: flex;
    flex-direction: column;
    gap: 12px;
    margin-bottom: 30px;
    overflow-y: auto;
    padding-right: 10px;

    &::-webkit-scrollbar {
        width: 8px;
    }
    &::-webkit-scrollbar-thumb {
        background: #d97706;
        border-radius: 10px;
    }
    &::-webkit-scrollbar-track {
        background: #fef3c7;
    }
`;

export const RankRow = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 20px;
    background-color: ${props => props.isTop3 ? '#fef3c7' : '#f8fafc'};
    border: 3px solid ${props => props.isTop3 ? '#ffde59' : '#e2e8f0'};
    border-radius: 20px;
    transition: transform 0.2s;

    &:hover {
        transform: scale(1.02);
    }
`;

export const RankInfo = styled.div`
    display: flex;
    align-items: center;
    gap: 15px;
`;

export const RankNumber = styled.span`
    font-size: 1.2rem;
    font-weight: 900;
    color: ${props => props.index === 0 ? '#d4af37' : props.index === 1 ? '#94a3b8' : props.index === 2 ? '#b45309' : '#8b4513'};
`;

export const Nickname = styled.span`
    color: #4a3728;
    font-size: 1.1rem;
    font-weight: 800;
`;

export const ScoreValue = styled.span`
    color: #15803d;
    font-size: 1.3rem;
    font-weight: 900;
`;

export const ButtonGroup = styled.div`
    display: flex;
    justify-content: center;
`;

export const HomeButton = styled.button`
    padding: 14px 40px;
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