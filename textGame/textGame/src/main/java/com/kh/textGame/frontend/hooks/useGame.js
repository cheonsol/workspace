// 게임 로직 커스텀 훅

import { useCallback } from 'react';
import useGameStore from '../store/user';
import useMonsterStore from '../store/monster';

/**
 * 게임 전투 시스템 훅
 * @returns {object} 전투 관련 함수들
 */
export const useGameBattle = () => {
  const addExp = useGameStore((state) => state.addExp);
  const addGold = useGameStore((state) => state.addGold);
  const monsters = useMonsterStore((state) => state.monsters);
  const killCount = useMonsterStore((state) => state.killCount);
  const incrementKillCount = useMonsterStore((state) => state.incrementKillCount);

  const handleMonsterDefeated = useCallback((monster, currentFloor) => {
    addExp(monster.exp);
    addGold(monster.gold);
    incrementKillCount(currentFloor);
  }, [addExp, addGold, incrementKillCount]);

  return {
    handleMonsterDefeated,
    monsters,
    killCount
  };
};

/**
 * 층 관리 훅
 * @returns {object} 층 관련 함수들
 */
export const useFloor = () => {
  const user = useGameStore((state) => state.currentUser);
  const moveToNextFloor = useGameStore((state) => state.moveToNextFloor);

  const canAdvanceFloor = useCallback(() => {
    return user && user.floor < 3;
  }, [user]);

  return {
    currentFloor: user?.floor || 1,
    canAdvanceFloor,
    moveToNextFloor
  };
};

/**
 * 스탯 관리 훅
 * @returns {object} 스탯 관련 함수들
 */
export const useStatManagement = () => {
  const user = useGameStore((state) => state.currentUser);
  const allocateStat = useGameStore((state) => state.allocateStat);
  const addStatPoints = useGameStore((state) => state.addStatPoints);

  return {
    stats: user?.stats || {},
    statPoints: user?.statPoints || 0,
    allocateStat,
    addStatPoints
  };
};
