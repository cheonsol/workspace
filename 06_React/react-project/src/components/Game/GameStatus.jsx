import React from 'react';
import { StatusWindow, StatusHeader, CharacterInfo, HpBarFrame, HpBarFill, MonsterImage } from '../../style/Game.style';

const GameStatus = ({ currentMonsterData, monsterHp, user, userHp, shouldSpawnBoss, currentKillCount }) => {
  return (
    <StatusWindow>
      <StatusHeader>
        {/* 몬스터 정보 */}
        <CharacterInfo>
          <strong>{currentMonsterData.name}</strong> 
          <small>Lv.{Math.floor(currentMonsterData.atk/2)} {currentMonsterData.isBoss ? '👑 보스' : ''}</small>
          {shouldSpawnBoss && <small style={{color: '#ff6b6b', fontWeight: 'bold'}}>⚠️ 보스 출현!</small>}
          {!shouldSpawnBoss && <small style={{color: '#aaa'}}>처치: {currentKillCount}/5</small>}
          <HpBarFrame>
            <HpBarFill 
              $width={(monsterHp / currentMonsterData.maxHp) * 100} 
              $color="#ff4d4d" 
            />
          </HpBarFrame>
        </CharacterInfo>
        
        {/* 유저 정보 */}
        <CharacterInfo $align="right">
          <strong>{user.nickname}</strong>
          <small>LV.{user.LV} - {user.floor}층</small>
          <small style={{color: '#4ade80'}}>❤️ {userHp}/{user.maxHp}</small>
          <HpBarFrame $align="right">
            <HpBarFill 
              $width={(userHp / user.maxHp) * 100} 
              $color="#4ade80" 
            />
          </HpBarFrame>
          <small style={{color: '#60a5fa'}}>💙 {user.currentMp}/{user.maxMp}</small>
          <HpBarFrame $align="right">
            <HpBarFill 
              $width={(user.currentMp / user.maxMp) * 100} 
              $color="#60a5fa" 
            />
          </HpBarFrame>
        </CharacterInfo>
      </StatusHeader>
      
      <MonsterImage>
        {currentMonsterData.img || '🐉'}
      </MonsterImage>
    </StatusWindow>
  );
};

export default GameStatus;
