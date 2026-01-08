import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { MainContainer, HeroImage, BottomLeftGroup, GameButton } from './MainPage.style';
import KiwiMain from '../assets/KiwiMain.png';

const MainPage = () => {
    const navigate = useNavigate();
    const [hoveredIndex, setHoveredIndex] = useState(null);

   const menuItems = [
    { label: 'START', path: '/game' },
    { label: 'BOARD', path: '/board' },
    { label: 'RANKING', path: '/ranking' },
    { label: 'SETTING', path: '/setting' }
];

    return (
        <MainContainer>
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