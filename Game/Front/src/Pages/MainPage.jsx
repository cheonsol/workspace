import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { MainContainer, HeroImage, BottomLeftGroup, GameButton, BestScoreBadge, LogoContainer, LogoTitle, LogoKiwi, LogoSubtitle } from './MainPage.style';
import KiwiMain from '../assets/KiwiMain.png';

const MainPage = () => {
    const navigate = useNavigate();
    const [hoveredIndex, setHoveredIndex] = useState(null);
    const [bestScore, setBestScore] = useState(0);

   const menuItems = [
    { label: 'START', path: '/game' },
    { label: 'RANKING', path: '/ranking' }
];

useEffect(() => {
        const guestId = localStorage.getItem('guestId');
        
        
        if (guestId) {
            fetch(`http://localhost:8080/api/ranking/my-best/${guestId}`)
                .then(res => res.json())
                .then(score => {
                    setBestScore(score);
                })
                .catch(err => console.error("최고 점수 로딩 실패", err));
        }
    },[]);

    return (
        <MainContainer>
            <LogoContainer>
                <LogoTitle>
                    <LogoKiwi>🥝</LogoKiwi>
                    KIWI RUN
                </LogoTitle>
                <LogoSubtitle>Jump & Survive!</LogoSubtitle>
            </LogoContainer>
            
            <BestScoreBadge>
                <small>MY BEST</small>
                <div>{bestScore.toLocaleString()}</div>
            </BestScoreBadge>
            <HeroImage 
                src={KiwiMain}
                alt="Main Illustration"
            />

            <BottomLeftGroup>
                {menuItems.map((item, index) => (
                    <GameButton 
                        key={index}
                        isActive={hoveredIndex === index}
                        onMouseEnter={() => setHoveredIndex(index)}
                        onMouseLeave={() => setHoveredIndex(null)}
                        onClick={() => navigate(item.path)}
                    >
                        {item.label}
                    </GameButton>
                ))}
            </BottomLeftGroup>
        </MainContainer>
    );
};

export default MainPage;