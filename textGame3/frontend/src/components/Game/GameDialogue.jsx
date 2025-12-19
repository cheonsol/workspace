import React from 'react';
import { DialogueBox, NextArrow } from '../../style/Game.style';

const GameDialogue = ({ message, isProcessingTurn, onClick }) => {
  return (
    <DialogueBox 
      onClick={isProcessingTurn ? onClickEvent : undefined}
      $isProcessing={isProcessingTurn}
    >
      {message}
      {isProcessingTurn && <NextArrow>▼</NextArrow>}
    </DialogueBox>
  );
};

export default GameDialogue;
