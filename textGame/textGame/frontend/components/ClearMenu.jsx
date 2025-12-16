import React from 'react';
import { ButtonGrid, ActionButton, DialogueBox } from '../style/Game.style';
import { useNavigate } from 'react-router-dom';

const ClearMenu = ({ onRetry }) => {
  const navigator = useNavigate();
  return (
    <DialogueBox $isProcessing={false} style={{ textAlign: 'center', fontSize: '1.3rem', color: '#222' }}>
      <div style={{ marginBottom: '20px' }}>🎉 던전 클리어! 축하합니다!</div>
      <ButtonGrid>
        <ActionButton onClick={() => navigator('/')} style={{backgroundColor: '#4ade80', fontWeight: 'bold'}}>
          🏠 홈으로 가기
        </ActionButton>
        <ActionButton onClick={onRetry} style={{backgroundColor: '#60a5fa', fontWeight: 'bold'}}>
          🔄 다시 도전하기
        </ActionButton>
      </ButtonGrid>
    </DialogueBox>
  );
};

export default ClearMenu;
