import React, { useState } from 'react';
import Header from '../layout/Header';
import useGameStore from '../store/user';
import useSkillStore from '../store/skill';
import useItemStore from '../store/item';
import { PageWrapper, ProfileCard, CardTitle, StatGrid, StatItem, LoginPrompt, TabContainer, TabButton, ShopGrid, ShopCard, ItemIcon, ItemName, ItemDesc, ItemInfo, ItemPrice, LevelLock, BuyButton } from '../style/Mypage.style';
// 마이페이지 데이터 포매팅 함수들
import {
  formatDate,  // 날짜 포매팅
  formatGold,  // 골드 포매팅 (천 단위 쉼표)
  formatExp    // 경험치 포매팅
} from '../utils/formatters';
// 게임 유틸리티 함수
import { getRequiredExp } from '../utils/gameUtils';

const Mypage = () => {

  const [activeTab, setActiveTab] = useState('profile'); // 'profile', 'shop', 'equipment', 'stats'
  const currentUser = useGameStore((state) => state.currentUser);
  const equipItem = useGameStore((state) => state.equipItem);
  const unequipItem = useGameStore((state) => state.unequipItem);
  const allocateStat = useGameStore((state) => state.allocateStat);
  
  const allSkills = useSkillStore((state) => state.skills);
  const playerSkills = useSkillStore((state) => state.playerSkills);
  const learnSkill = useSkillStore((state) => state.learnSkill);
  
  const allItems = useItemStore((state) => state.allItems);
  const inventory = useItemStore((state) => state.inventory);
  const buyItem = useItemStore((state) => state.buyItem);

  // 착용된 아이템의 스탯 보너스를 계산하는 함수
  const calculateStatBonus = (statType) => {
    if (!currentUser?.equippedItems) return 0;
    
    let bonus = 0;
    Object.keys(currentUser.equippedItems).forEach(itemId => {
      const item = allItems.find(i => i.id === parseInt(itemId));
      if (item) {
        const effectMap = {
          'attack': 'atk',
          'defense': 'def',
          'maxMana': 'mp',
          'maxHp': 'hp',
          'dex': 'dex',
          'luk': 'luk'
        };
        if (effectMap[item.effect] === statType) {
          bonus += item.value;
        }
      }
    });
    return bonus;
  };

  // 실제 능력치 계산 함수 (기본값 + 아이템 보너스)
  const getActualStat = (baseStat, statType) => {
    return baseStat + calculateStatBonus(statType);
  };

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
                active={activeTab === 'stats'}
                onClick={() => setActiveTab('stats')}
              >
                ✨ 스탯 분배
              </TabButton>
              <TabButton 
                active={activeTab === 'equipment'}
                onClick={() => setActiveTab('equipment')}
              >
                🎒 착용 아이템
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
                    <span className="value">
                      {currentUser.LV === 100 
                        ? `0 (만렙)` 
                        : `${formatExp(Math.max(0, getRequiredExp(currentUser.LV + 1) - currentUser.exp))}`
                      }
                    </span>
                  </StatItem>
                  
                  <StatItem>
                    <span className="label">최대 체력 (HP)</span>
                    <span className="value" style={{ color: '#4ade80' }}>
                      {getActualStat(currentUser.maxHp, 'hp')}
                      {calculateStatBonus('hp') > 0 && <span style={{ color: '#22c55e', fontSize: '0.9rem', marginLeft: '5px' }}>+{calculateStatBonus('hp')}</span>}
                    </span>
                  </StatItem>
                  
                  <StatItem>
                    <span className="label">최대 마나 (MP)</span>
                    <span className="value" style={{ color: '#60a5fa' }}>
                      {getActualStat(currentUser.maxMp, 'mp')}
                      {calculateStatBonus('mp') > 0 && <span style={{ color: '#3b82f6', fontSize: '0.9rem', marginLeft: '5px' }}>+{calculateStatBonus('mp')}</span>}
                    </span>
                  </StatItem>

                  <StatItem className="highlight">
                    <span className="label">⚔️ 공격력</span>
                    <span className="value">
                      {getActualStat(currentUser.atk, 'atk')}
                      {calculateStatBonus('atk') > 0 && <span style={{ color: '#ef4444', fontSize: '0.9rem', marginLeft: '5px' }}>+{calculateStatBonus('atk')}</span>}
                    </span>
                  </StatItem>

                  <StatItem>
                    <span className="label">🛡️ 방어력</span>
                    <span className="value">
                      {getActualStat(currentUser.def, 'def')}
                      {calculateStatBonus('def') > 0 && <span style={{ color: '#8b5cf6', fontSize: '0.9rem', marginLeft: '5px' }}>+{calculateStatBonus('def')}</span>}
                    </span>
                  </StatItem>

                  <StatItem>
                    <span className="label">🦶 회피력</span>
                    <span className="value">
                      {getActualStat(currentUser.dex, 'dex')}
                      {calculateStatBonus('dex') > 0 && <span style={{ color: '#f59e0b', fontSize: '0.9rem', marginLeft: '5px' }}>+{calculateStatBonus('dex')}</span>}
                    </span>
                  </StatItem>

                  <StatItem>
                    <span className="label">🍀 치명타 확률</span>
                    <span className="value">
                      {getActualStat(currentUser.luk, 'luk')}%
                      {calculateStatBonus('luk') > 0 && <span style={{ color: '#ec4899', fontSize: '0.9rem', marginLeft: '5px' }}>+{calculateStatBonus('luk')}%</span>}
                    </span>
                  </StatItem>

                  <StatItem>
                    <span className="label">🪙 보유 골드</span>
                    <span className="value">{formatGold(currentUser.gold)}</span>
                  </StatItem>
                </StatGrid>
              </ProfileCard>
            )}

            {activeTab === 'stats' && (
              <ProfileCard>
                <CardTitle>✨ 스탯 분배</CardTitle>

                <div style={{ marginTop: '20px' }}>
                  <StatItem style={{ marginBottom: '20px', padding: '15px', backgroundColor: '#f0f0f0', borderRadius: '8px' }}>
                    <span className="label" style={{ fontSize: '1.2rem' }}>📊 남은 포인트</span>
                    <span className="value" style={{ fontSize: '1.5rem', color: '#fbbf24' }}>{currentUser.statPoints || 0}</span>
                  </StatItem>

                  <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: '15px', marginTop: '20px' }}>
                    {/* HP */}
                    <div style={{ padding: '15px', border: '2px solid #4ade80', borderRadius: '8px', backgroundColor: '#f0fdf4' }}>
                      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '10px' }}>
                        <span style={{ fontSize: '1.1rem', fontWeight: 'bold' }}>❤️ HP</span>
                        <span style={{ color: '#4ade80', fontWeight: 'bold' }}>{currentUser.stats?.hp || 0}</span>
                      </div>
                      <div style={{ marginBottom: '10px', color: '#666', fontSize: '0.9rem' }}>
                        현재 최대 체력: {getActualStat(currentUser.maxHp, 'hp')}
                        {calculateStatBonus('hp') > 0 && <span style={{ color: '#4ade80' }}> (+{calculateStatBonus('hp')})</span>}
                      </div>
                      <button
                        onClick={() => allocateStat('hp')}
                        disabled={!currentUser.statPoints || currentUser.statPoints < 1}
                        style={{
                          width: '100%',
                          padding: '8px',
                          backgroundColor: currentUser.statPoints >= 1 ? '#4ade80' : '#ccc',
                          color: 'white',
                          border: 'none',
                          borderRadius: '4px',
                          cursor: currentUser.statPoints >= 1 ? 'pointer' : 'not-allowed',
                          fontWeight: 'bold'
                        }}
                      >
                        +1 할당 (최대 HP +1)
                      </button>
                    </div>

                    {/* MP */}
                    <div style={{ padding: '15px', border: '2px solid #60a5fa', borderRadius: '8px', backgroundColor: '#f0f9ff' }}>
                      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '10px' }}>
                        <span style={{ fontSize: '1.1rem', fontWeight: 'bold' }}>💙 MP</span>
                        <span style={{ color: '#60a5fa', fontWeight: 'bold' }}>{currentUser.stats?.mp || 0}</span>
                      </div>
                      <div style={{ marginBottom: '10px', color: '#666', fontSize: '0.9rem' }}>
                        현재 최대 마나: {getActualStat(currentUser.maxMp, 'mp')}
                        {calculateStatBonus('mp') > 0 && <span style={{ color: '#60a5fa' }}> (+{calculateStatBonus('mp')})</span>}
                      </div>
                      <button
                        onClick={() => allocateStat('mp')}
                        disabled={!currentUser.statPoints || currentUser.statPoints < 1}
                        style={{
                          width: '100%',
                          padding: '8px',
                          backgroundColor: currentUser.statPoints >= 1 ? '#60a5fa' : '#ccc',
                          color: 'white',
                          border: 'none',
                          borderRadius: '4px',
                          cursor: currentUser.statPoints >= 1 ? 'pointer' : 'not-allowed',
                          fontWeight: 'bold'
                        }}
                      >
                        +1 할당 (최대 MP +1)
                      </button>
                    </div>

                    {/* ATK */}
                    <div style={{ padding: '15px', border: '2px solid #f87171', borderRadius: '8px', backgroundColor: '#fef2f2' }}>
                      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '10px' }}>
                        <span style={{ fontSize: '1.1rem', fontWeight: 'bold' }}>⚔️ ATK</span>
                        <span style={{ color: '#f87171', fontWeight: 'bold' }}>{currentUser.stats?.atk || 0}</span>
                      </div>
                      <div style={{ marginBottom: '10px', color: '#666', fontSize: '0.9rem' }}>
                        현재 공격력: {getActualStat(currentUser.atk, 'atk')}
                        {calculateStatBonus('atk') > 0 && <span style={{ color: '#f87171' }}> (+{calculateStatBonus('atk')})</span>}
                      </div>
                      <button
                        onClick={() => allocateStat('atk')}
                        disabled={!currentUser.statPoints || currentUser.statPoints < 1}
                        style={{
                          width: '100%',
                          padding: '8px',
                          backgroundColor: currentUser.statPoints >= 1 ? '#f87171' : '#ccc',
                          color: 'white',
                          border: 'none',
                          borderRadius: '4px',
                          cursor: currentUser.statPoints >= 1 ? 'pointer' : 'not-allowed',
                          fontWeight: 'bold'
                        }}
                      >
                        +1 할당 (공격력 +1)
                      </button>
                    </div>

                    {/* DEF */}
                    <div style={{ padding: '15px', border: '2px solid #a78bfa', borderRadius: '8px', backgroundColor: '#faf5ff' }}>
                      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '10px' }}>
                        <span style={{ fontSize: '1.1rem', fontWeight: 'bold' }}>🛡️ DEF</span>
                        <span style={{ color: '#a78bfa', fontWeight: 'bold' }}>{currentUser.stats?.def || 0}</span>
                      </div>
                      <div style={{ marginBottom: '10px', color: '#666', fontSize: '0.9rem' }}>
                        현재 방어력: {getActualStat(currentUser.def, 'def')}
                        {calculateStatBonus('def') > 0 && <span style={{ color: '#a78bfa' }}> (+{calculateStatBonus('def')})</span>}
                      </div>
                      <button
                        onClick={() => allocateStat('def')}
                        disabled={!currentUser.statPoints || currentUser.statPoints < 1}
                        style={{
                          width: '100%',
                          padding: '8px',
                          backgroundColor: currentUser.statPoints >= 1 ? '#a78bfa' : '#ccc',
                          color: 'white',
                          border: 'none',
                          borderRadius: '4px',
                          cursor: currentUser.statPoints >= 1 ? 'pointer' : 'not-allowed',
                          fontWeight: 'bold'
                        }}
                      >
                        +1 할당 (방어력 +1)
                      </button>
                    </div>

                    {/* DEX */}
                    <div style={{ padding: '15px', border: '2px solid #34d399', borderRadius: '8px', backgroundColor: '#f0fdf4' }}>
                      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '10px' }}>
                        <span style={{ fontSize: '1.1rem', fontWeight: 'bold' }}>🦶 DEX</span>
                        <span style={{ color: '#34d399', fontWeight: 'bold' }}>{currentUser.stats?.dex || 0}</span>
                      </div>
                      <div style={{ marginBottom: '10px', color: '#666', fontSize: '0.9rem' }}>
                        현재 회피력: {getActualStat(currentUser.dex, 'dex')}
                        {calculateStatBonus('dex') > 0 && <span style={{ color: '#34d399' }}> (+{calculateStatBonus('dex')})</span>}
                      </div>
                      <button
                        onClick={() => allocateStat('dex')}
                        disabled={!currentUser.statPoints || currentUser.statPoints < 1}
                        style={{
                          width: '100%',
                          padding: '8px',
                          backgroundColor: currentUser.statPoints >= 1 ? '#34d399' : '#ccc',
                          color: 'white',
                          border: 'none',
                          borderRadius: '4px',
                          cursor: currentUser.statPoints >= 1 ? 'pointer' : 'not-allowed',
                          fontWeight: 'bold'
                        }}
                      >
                        +1 할당 (회피력 +1)
                      </button>
                    </div>

                    {/* LUK */}
                    <div style={{ padding: '15px', border: '2px solid #fbbf24', borderRadius: '8px', backgroundColor: '#fffbeb' }}>
                      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '10px' }}>
                        <span style={{ fontSize: '1.1rem', fontWeight: 'bold' }}>🍀 LUK</span>
                        <span style={{ color: '#fbbf24', fontWeight: 'bold' }}>{currentUser.stats?.luk || 0}</span>
                      </div>
                      <div style={{ marginBottom: '10px', color: '#666', fontSize: '0.9rem' }}>
                        현재 치명타 확률: {getActualStat(currentUser.luk, 'luk')}%
                        {calculateStatBonus('luk') > 0 && <span style={{ color: '#fbbf24' }}> (+{calculateStatBonus('luk')}%)</span>}
                      </div>
                      <button
                        onClick={() => allocateStat('luk')}
                        disabled={!currentUser.statPoints || currentUser.statPoints < 1}
                        style={{
                          width: '100%',
                          padding: '8px',
                          backgroundColor: currentUser.statPoints >= 1 ? '#fbbf24' : '#ccc',
                          color: 'white',
                          border: 'none',
                          borderRadius: '4px',
                          cursor: currentUser.statPoints >= 1 ? 'pointer' : 'not-allowed',
                          fontWeight: 'bold'
                        }}
                      >
                        +1 할당 (치명타 확률 +1%)
                      </button>
                    </div>
                  </div>
                </div>
              </ProfileCard>
            )}

            {activeTab === 'equipment' && (
              <ProfileCard style={{ width: '100%', maxWidth: '1000px' }}>
                <CardTitle>🎒 착용 아이템</CardTitle>

                <div style={{ marginTop: '20px' }}>
                  <h3 style={{ color: '#d4af37', marginBottom: '15px' }}>👕 착용 중인 아이템</h3>
                  {inventory.length === 0 ? (
                    <p style={{ color: '#888', textAlign: 'center' }}>착용할 아이템이 없습니다.</p>
                  ) : (
                    <ShopGrid>
                      {inventory.map((item) => {
                        const isEquipped = currentUser.equippedItems && currentUser.equippedItems[item.id];
                        
                        return (
                          <ShopCard key={item.id} isLocked={false}>
                            <ItemIcon>{item.icon}</ItemIcon>
                            <ItemName>{item.name}</ItemName>
                            <ItemDesc>{item.description}</ItemDesc>
                            <ItemInfo>수량: {item.quantity}개</ItemInfo>
                            <ItemPrice>
                              {item.effect === 'heal' ? `💚 ${item.value}` :
                               item.effect === 'attack' ? `⚔️ +${item.value}` :
                               item.effect === 'defense' ? `🛡️ +${item.value}` :
                               item.effect === 'maxMana' ? `💙 +${item.value}` :
                               item.effect === 'maxHp' ? `❤️ +${item.value}` : ''}
                            </ItemPrice>
                            <BuyButton 
                              onClick={() => {
                                if (isEquipped) {
                                  unequipItem(item.id);
                                  alert('아이템을 벗었습니다.');
                                } else {
                                  equipItem(item.id);
                                  alert(`[${item.name}]을 착용했습니다.`);
                                }
                              }}
                            >
                              {isEquipped ? '✓ 착용 중' : '착용'}
                            </BuyButton>
                          </ShopCard>
                        );
                      })}
                    </ShopGrid>
                  )}
                </div>
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