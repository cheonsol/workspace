import React from 'react';
import useGameStore from '../store/store';
// 위에서 만든 스타일 컴포넌트들 import
import { PageWrapper, ProfileCard, CardTitle, StatGrid, StatItem, LoginPrompt } from '../style/Mypage.style';
import Header from '../layout/Header';

const Mypage = () => {

  const currentUser = useGameStore((state) => state.currentUser);

  return (
    <>
      <Header />
      
      <PageWrapper>
        {currentUser ? (
          <ProfileCard>
            <CardTitle>
              {currentUser.nickname}
              <span>LV. {currentUser.LV} 모험가</span>
            </CardTitle>

            <StatGrid>
              {/* 스탯들을 깔끔하게 정리 */}
              <StatItem>
                <span className="label">경험치</span>
                <span className="value">{currentUser.exp} EXP</span>
              </StatItem>
              
              <StatItem>
                <span className="label">최대 체력 (HP)</span>
                <span className="value" style={{ color: '#4ade80' }}>{currentUser.maxHp}</span>
              </StatItem>
              
              <StatItem>
                <span className="label">최대 마나 (MP)</span>
                <span className="value" style={{ color: '#60a5fa' }}>{currentUser.maxMp}</span>
              </StatItem>

              <StatItem className="highlight">
                <span className="label">⚔️ 공격력</span>
                <span className="value">{currentUser.atk}</span>
              </StatItem>

              <StatItem>
                <span className="label">🛡️ 방어력</span>
                <span className="value">{currentUser.def}</span>
              </StatItem>

              <StatItem>
                <span className="label">🦶 회피력</span>
                <span className="value">{currentUser.dex}</span>
              </StatItem>

              <StatItem>
                <span className="label">🍀 치명타 확률</span>
                <span className="value">{currentUser.luk}%</span>
              </StatItem>
            </StatGrid>
          </ProfileCard>
        ) : (
          <ProfileCard>
             <LoginPrompt>
                <p>🔒 로그인이 필요한 서비스입니다.</p>
                <p style={{ fontSize: '0.9rem' }}>모험을 떠나시려면 로그인을 진행해주세요.</p>
             </LoginPrompt>
          </ProfileCard>
        )}
      </PageWrapper>
    </>
  )
}

export default Mypage;