import React from 'react';
import { ButtonGrid, ActionButton } from '../../style/Game.style';

const GameActions = ({
  isProcessingTurn,
  showSkillSelect,
  showItemSelect,
  showBossChoice,
  playerSkills,
  inventory,
  onAttack,
  onSkill,
  onItem,
  onEscape,
  onUseSkill,
  onUseItem,
  onBossChoice,
  onCancelSkill,
  onCancelItem
}) => {
  // 일반 행동 버튼
  if (!isProcessingTurn && !showSkillSelect && !showItemSelect && !showBossChoice) {
    return (
      <ButtonGrid>
        <ActionButton onClick={onAttack}>⚔️ 공격</ActionButton>
        <ActionButton onClick={onSkill}>⚡ 스킬</ActionButton>
        <ActionButton onClick={onItem}>💊 아이템</ActionButton>
        <ActionButton onClick={onEscape}>🏃 도망</ActionButton>
      </ButtonGrid>
    );
  }

  // 스킬 선택 메뉴
  if (showSkillSelect) {
    return (
      <ButtonGrid>
        {playerSkills.map((skill) => (
          <ActionButton key={skill.id} onClick={() => onUseSkill(skill)}>
            {skill.icon} {skill.name}
          </ActionButton>
        ))}
        <ActionButton onClick={onCancelSkill} style={{backgroundColor: '#666'}}>
          ❌ 취소
        </ActionButton>
      </ButtonGrid>
    );
  }

  // 아이템 선택 메뉴
  if (showItemSelect) {
    const potions = inventory.filter(i => i.type === 'potion');
    return (
      <ButtonGrid>
        {potions.map((item) => (
          <ActionButton key={item.id} onClick={() => onUseItem(item)}>
            {item.icon} {item.name} (x{item.quantity})
          </ActionButton>
        ))}
        <ActionButton onClick={onCancelItem} style={{backgroundColor: '#666'}}>
          ❌ 취소
        </ActionButton>
      </ButtonGrid>
    );
  }

  // 보스 선택 메뉴
  if (showBossChoice) {
    return (
      <ButtonGrid>
        <ActionButton onClick={() => onBossChoice(true)} style={{backgroundColor: '#4ade80'}}>
          ✅ 다음 층으로 진입
        </ActionButton>
        <ActionButton onClick={() => onBossChoice(false)} style={{backgroundColor: '#60a5fa'}}>
          🔄 현재 층에서 계속
        </ActionButton>
      </ButtonGrid>
    );
  }

  return null;
};

export default GameActions;
