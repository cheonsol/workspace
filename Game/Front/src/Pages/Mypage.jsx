import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../assets/Navbar';
import MainIllust from '../assets/MainIllust.png';
import { 
    MypageContainer, BackgroundImage, ProfileCard, ProfileTitle, 
    InfoSection, InfoRow, Label, Value, ButtonGroup, MenuButton 
} from './Mypage.style';

const Mypage = () => {
    const navigate = useNavigate();
    const [user, setUser] = useState({
        email: 'loading...',
        nickname: 'loading...',
        regDate: 'loading...'
    });

    const formatDate = (dateString) => {
    if (!dateString || dateString === 'loading...') return dateString;
    
    const date = new Date(dateString);
    
    return new Intl.DateTimeFormat('ko-KR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
    }).format(date);
};

useEffect(() => {
    const fetchMyInfo = async () => {
        const token = localStorage.getItem('token');
        
        if (!token) {
            alert("로그인이 필요합니다.");
            navigate('/login');
            return;
        }

        const response = await fetch('http://localhost:8080/api/member/me', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (response.ok) {
            const data = await response.json();
            setUser(data);
        }
    };
    fetchMyInfo();
}, []);

    return (
        <>
            <MypageContainer>
                <BackgroundImage src={MainIllust} alt="background" />
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
                            <Label>REGISTRATION DATE</Label>
                            <Value>{user.regDate}</Value>
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
        </>
    );
};

export default Mypage;