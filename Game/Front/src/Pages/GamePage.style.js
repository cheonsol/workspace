import styled from 'styled-components';

export const GameContainer = styled.div`
    position: relative;
    width: 100vw;
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: linear-gradient(to bottom, #e0f2fe 0%, #f0fdf4 100%);
    overflow: hidden;
`;

export const CanvasWrapper = styled.div`
    position: relative;
    border: 8px solid #8b4513;
    border-radius: 20px;
    box-shadow: 0 15px 35px rgba(0,0,0,0.2);
    background-color: #fff;
    overflow: hidden;
`;

export const ScoreBoard = styled.div`
    position: absolute;
    top: 20px;
    right: 20px;
    font-size: 2rem;
    font-weight: 900;
    color: #8b4513;
    text-shadow: 2px 2px 0px #fff;
    z-index: 10;
`;

export const Overlay = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: rgba(255, 255, 255, 0.7);
    z-index: 20;
`;