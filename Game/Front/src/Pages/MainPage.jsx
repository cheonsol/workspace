import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../assets/Navbar';
import { MainContainer, HeroImage, BottomLeftGroup, GameButton } from './MainPage.style';
import MainIllust from '../assets/MainIllust.png';

const MainPage = () => {
    const navigate = useNavigate();
    const [selectedIndex, setSelectedIndex] = useState(0);
    const menuItems = [
        { label: 'LOGIN', path: '/login' },
        { label: 'SIGN UP', path: '/signup' },
        { label: 'BOARD', path: '/board' },
        { label: 'SETTING', path: '/setting' }
    ];

    useEffect(() => {
        const handleKeyDown = (e) => {
            if (e.key === 'ArrowUp') {
                setSelectedIndex((prev) => (prev === 0 ? menuItems.length - 1 : prev - 1));
            } else if (e.key === 'ArrowDown') {
                setSelectedIndex((prev) => (prev === menuItems.length - 1 ? 0 : prev + 1));
            } else if (e.key === 'Enter') {
                navigate(menuItems[selectedIndex].path);
            }
        };

        window.addEventListener('keydown', handleKeyDown);
        
        return () => window.removeEventListener('keydown', handleKeyDown);
    }, [selectedIndex, navigate]);

    return (
        <>
            <MainContainer>
                <HeroImage 
                    src={MainIllust}
                    alt="Main Illustration"
                />

                <BottomLeftGroup>
                    {menuItems.map((item, index) => (
                        <GameButton 
                            key={index}
                            isActive={selectedIndex === index}
                        >
                            {item.label}
                        </GameButton>
                    ))}
                </BottomLeftGroup>
            </MainContainer>
        </>
    );
};

export default MainPage;