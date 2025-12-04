import React, { useState } from 'react';
import useGameStore from '../store/user';
import useSkillStore from '../store/skill';
import useItemStore from '../store/item';
// 위에서 만든 스타일 컴포넌트들 import
import { PageWrapper, ProfileCard, CardTitle, StatGrid, StatItem, LoginPrompt, TabContainer, TabButton, ShopGrid, ShopCard, ItemIcon, ItemName, ItemDesc, ItemInfo, ItemPrice, LevelLock, BuyButton } from '../style/Mypage.style';
import Header from '../layout/Header';

const Mypage = () => {

  const [activeTab, setActiveTab] = useState('profile'); // 'profile', 'shop'
  const currentUser = useGameStore((state) => state.currentUser);
  const allSkills = useSkillStore((state) => state.skills);
  const playerSkills = useSkillStore((state) => state.playerSkills);
  const learnSkill = useSkillStore((state) => state.learnSkill);
  
  const allItems = useItemStore((state) => state.allItems);
  const inventory = useItemStore((state) => state.inventory);
  const buyItem = useItemStore((state) => state.buyItem);

  return (
    <>
      <Header />
      
      <PageWrapper>
        {currentUser ? (
          <>
            <TabContainer>
              <TabButton 
                active={activeTab === 'profile'}
                onClick={() => setActiveTab('profile')}
              >
                👤 프로필
              </TabButton>
              <TabButton 
                active={activeTab === 'shop'}
                onClick={() => setActiveTab('shop')}
              >
                🏪 상점
              </TabButton>
            </TabContainer>

            {activeTab === 'profile' && (
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

                  <StatItem>
                    <span className="label">🪙 보유 골드</span>
                    <span className="value">{currentUser.gold}</span>
                  </StatItem>
                </StatGrid>
              </ProfileCard>
            )}

            {activeTab === 'shop' && (
              <ProfileCard style={{ width: '100%', maxWidth: '1000px' }}>
                <CardTitle>🏪 상점</CardTitle>
                
                {/* 스킬 상점 */}
                <div>
                  <h3 style={{ color: '#d4af37', marginBottom: '15px' }}>⚡ 스킬 상점</h3>
                  <ShopGrid>
                    {allSkills.map((skill) => {
                      const isLearned = playerSkills.some(ps => ps.id === skill.id);
                      const requiredLevel = Math.ceil(skill.id * 10); // 스킬 레벨 별 해금 (간단한 계산)
                      const canLearn = currentUser.LV >= requiredLevel;

                      return (
                        <ShopCard key={skill.id} isLocked={!canLearn && !isLearned}>
                          <ItemIcon>{skill.icon}</ItemIcon>
                          <ItemName>{skill.name}</ItemName>
                          <ItemDesc>{skill.description}</ItemDesc>
                          
                          {!isLearned ? (
                            <>
                              <LevelLock>필요 레벨: {requiredLevel}</LevelLock>
                              <ItemPrice>무료</ItemPrice>
                              <BuyButton 
                                disabled={!canLearn}
                                onClick={() => {
                                  if (canLearn) {
                                    learnSkill(skill.id);
                                    alert(`[${skill.name}] 스킬을 배웠습니다!`);
                                  } else {
                                    alert(`레벨 ${requiredLevel}에 도달하면 배울 수 있습니다.`);
                                  }
                                }}
                              >
                                {canLearn ? '배우기' : '잠금'}
                              </BuyButton>
                            </>
                          ) : (
                            <BuyButton disabled>✓ 배운 스킬</BuyButton>
                          )}
                        </ShopCard>
                      );
                    })}
                  </ShopGrid>
                </div>

                {/* 아이템 상점 */}
                <div style={{ marginTop: '40px' }}>
                  <h3 style={{ color: '#d4af37', marginBottom: '15px' }}>🎁 아이템 상점</h3>
                  <ShopGrid>
                    {allItems.map((item) => {
                      const hasItem = inventory.some(i => i.id === item.id);
                      const canBuy = currentUser.gold >= item.price;

                      return (
                        <ShopCard key={item.id}>
                          <ItemIcon>{item.icon}</ItemIcon>
                          <ItemName>{item.name}</ItemName>
                          <ItemDesc>{item.description}</ItemDesc>
                          <ItemPrice>💰 {item.price}원</ItemPrice>
                          {hasItem && (
                            <ItemInfo>보유: {inventory.find(i => i.id === item.id)?.quantity || 0}개</ItemInfo>
                          )}
                          <BuyButton 
                            disabled={!canBuy}
                            onClick={() => {
                              if (canBuy) {
                                buyItem(item.id, 1);
                                alert(`[${item.name}]을 구매했습니다!`);
                              } else {
                                alert('골드가 부족합니다!');
                              }
                            }}
                          >
                            {canBuy ? '구매' : '골드 부족'}
                          </BuyButton>
                        </ShopCard>
                      );
                    })}
                  </ShopGrid>
                </div>
              </ProfileCard>
            )}
          </>
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