import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import KiwiMain from '../assets/KiwiMain.png';
import { 
    RankingContainer, 
    BackgroundImage, 
    RankingBox, 
    RankingTitle, 
    RankList, 
    RankRow, 
    RankInfo, 
    RankNumber, 
    Nickname, 
    ScoreValue, 
    ButtonGroup, 
    HomeButton 
} from './Ranking.style';

const RankingPage = () => {
    const navigate = useNavigate();
    const [rankings, setRankings] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/ranking')
            .then(res => res.json())
            .then(data => setRankings(data))
            .catch(err => console.error("데이터 로딩 실패:", err));
    }, []);

    return (
        <RankingContainer>
            <BackgroundImage src={KiwiMain} alt="background" />
            <RankingBox>
                <RankingTitle>KIWI TOP 10 RANKING</RankingTitle>
                
                <RankList>
                    {rankings.length > 0 ? rankings.map((rank, index) => (
                        <RankRow key={rank.id} isTop3={index < 3}>
                            <RankInfo>
                                <RankNumber index={index}>{index + 1}</RankNumber>
                                <Nickname>{rank.nickname}</Nickname>
                            </RankInfo>
                            <ScoreValue>{rank.score.toLocaleString()} PTS</ScoreValue>
                        </RankRow>
                    )) : (
                        <p style={{ textAlign: 'center', color: '#8b4513', fontWeight: 'bold' }}>
                            아직 등록된 랭킹이 없습니다!
                        </p>
                    )}
                </RankList>

                <ButtonGroup>
                    <HomeButton onClick={() => navigate('/')}>
                        BACK TO TITLE
                    </HomeButton>
                </ButtonGroup>
            </RankingBox>
        </RankingContainer>
    );
};

export default RankingPage;