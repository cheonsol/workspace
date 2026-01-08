import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import KiwiMain from '../assets/KiwiMain.png';
import { 
    MypageContainer, BackgroundImage, ProfileCard, ProfileTitle, 
    InfoSection, InfoRow, Label, Value, ButtonGroup, MenuButton 
} from './Mypage.style';

const Mypage = () => {
    const navigate = useNavigate();
    const [user, setUser] = useState({
        email: 'loading...',
        nickname: 'loading...',
        highScore: 0
    });

    useEffect(() => {
        const fetchMyInfo = async () => {
            const token = localStorage.getItem('token');
            
            if (!token) {
                alert("로그인이 필요합니다.");
                navigate('/login');
                return;
            }

            try {
                const response = await fetch('http://localhost:8080/api/member/me', {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });

                if (response.ok) {
                    const data = await response.json();
                    setUser(data);
                }
            } catch (error) {
                console.error("데이터 로딩 실패:", error);
            }
        };
        fetchMyInfo();
    }, [navigate]);

    return (
        <MypageContainer>
            <BackgroundImage src={KiwiMain} alt="background" />
            <ProfileCard>
                <ProfileTitle>ARCHIVIST PROFILE</ProfileTitle>
                
                <InfoSection>
                    <InfoRow>
                        <Label>ID (EMAIL)</Label>
                        <Value>{user.email}</Value>
                    </InfoRow>
                    <InfoRow>
                        <Label>NICKNAME</Label>
                        <Value>{user.nickname}</Value>
                    </InfoRow>
                    <InfoRow>
                        <Label>HIGHEST SCORE</Label>
                        <Value>{user.highScore.toLocaleString()}</Value>
                    </InfoRow>
                </InfoSection>

                <ButtonGroup>
                    <MenuButton onClick={() => navigate('/edit-profile')}>
                        EDIT PROFILE
                    </MenuButton>
                    <MenuButton exit onClick={() => navigate('/')}>
                        BACK TO TITLE
                    </MenuButton>
                </ButtonGroup>
            </ProfileCard>
        </MypageContainer>
    );
};

export default Mypage;