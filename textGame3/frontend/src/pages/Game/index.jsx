import React, { useEffect, useState } from 'react';
import ClearMenu from '../../components/ClearMenu';
import useGameStore from '../../store/user';
import useMonsterStore from '../../store/monster';
import { useNavigate } from 'react-router-dom';
import { attack } from '../../api/gameApi';

// 스타일 컴포넌트 import (기존 유지)import { 
    GameContainer, StatusWindow, StatusHeader, CharacterInfo, 
    HpBarFrame, HpBarFill, MonsterImage, DialogueBox, NextArrow, 
    ButtonGrid, ActionButton 
} from './Game.style';

const Game = () => {
    const navigator = useNavigate();
    
    // 1. 스토어 데이터
    const user = useGameStore((state) => state.currentUser);
    const { advanceToNextFloor } = useGameStore();
    
    const monsters = useMonsterStore((state) => state.monsters);
    const killCount = useMonsterStore((state) => state.killCount);
    const incrementKillCount = useMonsterStore((state) => state.incrementKillCount);
    const resetKillCount = useMonsterStore((state) => state.resetKillCount);
    
    const playerSkills = user?.skills || [];
    const inventory = user?.memberItems || [];
    
    // 몬스터 데이터 찾기. 유저의 현재 층과 몬스터의 출몰 층을 비교
    // 5 킬마다 보스 생성
    const currentFloor = user?.floor || 1;
    const floorMonsters = monsters.filter(m => m.floor === currentFloor);
    const normalMonsters = floorMonsters.filter(m => !m.isBoss);
    const bossMonster = floorMonsters.find(m => m.isBoss);
    const currentKillCount = killCount[currentFloor] || 0;
    const shouldSpawnBoss = currentKillCount >= 5;
    
    const getRandomMonster = () => {
        return shouldSpawnBoss ? bossMonster : 
            normalMonsters[Math.floor(Math.random() * normalMonsters.length)];
    };

    // --- State 정의 ---
        // 던전 클리어 메뉴 상태
        const [showClearMenu, setShowClearMenu] = useState(false);
    const [currentMonsterData, setCurrentMonsterData] = useState(getRandomMonster());
    const [monsterHp, setMonsterHp] = useState(Number(currentMonsterData?.maxHp) || 100);
    const [userHp, setUserHp] = useState(Number(user?.currentHp) || 100);
    const [message, setMessage] = useState(""); 
    const [battleQueue, setBattleQueue] = useState([]);
    const [isProcessingTurn, setIsProcessingTurn] = useState(true);
    const [showSkillSelect, setShowSkillSelect] = useState(false);
    const [showItemSelect, setShowItemSelect] = useState(false);
    const [showBossChoice, setShowBossChoice] = useState(false);

    let userDamage = 0;
    let isMonsterDead = false;

    // 초기 진입에만 실행 (한 번만)
    const [isInitialized, setIsInitialized] = useState(false);
    
    useEffect(() => {
       if (currentMonsterData && !isInitialized) {
            setMessage(`야생의 [${currentMonsterData.name}]이(가) 나타났다!`);
            setMonsterHp(currentMonsterData.maxHp);
            setUserHp(user.currentHp);
            setIsInitialized(true);
            setIsProcessingTurn(false);
        }
    }, [currentMonsterData, user, isInitialized]);

    const displayEvents = (events, callback) => {
        let currentIndex = 0;

        function nextEvent() {
            if (currentIndex < events.length) {
                const event = events[currentIndex];
                setMessage(event.message);
                if(event.type === "PLAYER_ATTACK") {
                    setMonsterHp(hp => Math.max(0, hp - event.damage));
                } else if (event.type === "MONSTER_ATTACK") {
                    setUserHp(hp => Math.max(0, hp - event.damage));
                }
                currentIndex++;
                setTimeout(nextEvent, 1000); // 1초 간격으로 다음 메시지
            } else if (callback) {
                callback();
            }
        }
        nextEvent();
    };

    const handleBattleAction = async (type) => {
        if (!user || !currentMonsterData || isProcessingTurn) return;

        setIsProcessingTurn(true);

        if (type === 'ATTACK') {
            try {
                const response = await attack(currentMonsterData.id);
                const result = response.data;

                displayEvents(result.events, () => {
                    // 모든 메시지 표시 후 상태 최종 동기화
                    setUserHp(result.member.currentHp);
                    setMonsterHp(result.monster.hp);

                    if (result.battleOver) {
                        if (result.playerWon) {
                            incrementKillCount(currentFloor);
                            if (currentMonsterData.isBoss) {
                                if (currentFloor === 3) {
                                    setMessage('🎉 던전 클리어! 축하합니다!');
                                    setShowClearMenu(true);
                                } else {
                                    setMessage(`🎉 보스를 격파했습니다! 다음 층으로 진입하시겠습니까?`);
                                    setShowBossChoice(true);
                                }
                            } else {
                                const newMonster = getRandomMonster();
                                if(newMonster) {
                                    setCurrentMonsterData(newMonster);
                                    setMonsterHp(newMonster.maxHp);
                                    setMessage(`새로운 [${newMonster.name}]이(가) 나타났다!`);
                                } else {
                                     setMessage('더 이상 싸울 몬스터가 없습니다.');
                                }
                            }
                        } else {
                            setMessage("GAME OVER");
                            setTimeout(() => navigator('/'), 2000);
                        }
                    }
                    setIsProcessingTurn(false);
                });

            } catch (error) {
                console.error("Attack failed:", error);
                setMessage("에러: 공격을 수행할 수 없습니다.");
                setIsProcessingTurn(false);
            }
        } else if (type === 'SKILL') {
            setShowSkillSelect(true);
            setIsProcessingTurn(false);
        } else if (type === 'ITEM') {
            setShowItemSelect(true);
            setIsProcessingTurn(false);
        }
    };

    const handleBossChoice = (goNext) => {
        setShowBossChoice(false);
        if (goNext) {
            advanceToNextFloor();
            resetKillCount(currentFloor);
            // After advancing, the user data will be re-fetched by the store,
            // which will trigger a re-render with the new floor's monsters.
            setIsInitialized(false); // Allow useEffect to run for the new floor
        } else {
            const newMonster = getRandomMonster(false); // Get a non-boss monster
            setCurrentMonsterData(newMonster);
            setMonsterHp(newMonster.maxHp);
            setMessage(`현재 층에 머무릅니다. 새로운 [${newMonster.name}]이(가) 나타났다!`);
        }
    };
    
    const handleUseSkill = (skill) => {
        setShowSkillSelect(false);
        setMessage(`'${skill.name}' 스킬 사용은 아직 구현되지 않았습니다.`);
    };

    const handleUseItemInBattle = (item) => {
        setShowItemSelect(false);
        setMessage(`'${item.itemName}' 아이템 사용은 아직 구현되지 않았습니다.`);
    };

    if (!user || !currentMonsterData) return <div>로딩 중...</div>;

    return (
        <GameContainer>
            {/* 상태창 영역 */}
            <StatusWindow>
                <StatusHeader>
                    {/* 몬스터 정보 */}
                    <CharacterInfo>
                        <strong style={{color: '#222'}}>{currentMonsterData.name} <span style={{color: '#222'}}>Lv.{Math.floor(currentMonsterData.atk/2)}</span> {currentMonsterData.isBoss ? '👑 보스' : ''}</strong>
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

            {/* 대화창 */}
            <DialogueBox 
                // 대화창을 클릭했을 때, isProcessingTurn에 따라 processNextEvent 혹은 undefined를 실행한다.
                onClick={isProcessingTurn ? processNextEvent : undefined}
                $isProcessing={isProcessingTurn}
            >
                {message}
                {isProcessingTurn && <NextArrow>▼</NextArrow>}
            </DialogueBox>

            {/* 버튼 영역 */}
            {!isProcessingTurn && !showSkillSelect && !showItemSelect && !showBossChoice && !showClearMenu && (
                <ButtonGrid>
                    <ActionButton onClick={() => handleBattleAction('ATTACK')}>
                        ⚔️ 공격
                    </ActionButton>
                    <ActionButton onClick={() => handleBattleAction('SKILL')}>
                        ⚡ 스킬
                    </ActionButton>
                    <ActionButton onClick={() => handleBattleAction('ITEM')}>
                        💊 아이템
                    </ActionButton>
                    <ActionButton onClick={() => navigator('/')}> 
                        🏃 도망
                    </ActionButton>
                </ButtonGrid>
            )}

            {/* 던전 클리어 메뉴 컴포넌트 */}
            {showClearMenu && (
                <ClearMenu onRetry={() => {
                    setShowClearMenu(false);
                    setIsInitialized(false);
                    const newMonster = getRandomMonster();
                    setCurrentMonsterData(newMonster);
                    if(newMonster) {
                        setMonsterHp(newMonster.maxHp);
                        setMessage(`다시 도전! [${newMonster.name}]이(가) 나타났다!`);
                    }
                    setUserHp(user.currentHp);
                }} />
            )}

            {/* 보스 처치 후 선택 메뉴 */}
            {showBossChoice && (
                <ButtonGrid>
                    <ActionButton onClick={() => handleBossChoice(true)} style={{backgroundColor: '#4ade80'}}>
                        ✅ 다음 층으로 진입
                    </ActionButton>
                    <ActionButton onClick={() => handleBossChoice(false)} style={{backgroundColor: '#60a5fa'}}>
                        🔄 현재 층에서 계속
                    </ActionButton>
                </ButtonGrid>
            )}

            {/* 스킬 선택 메뉴 */}
            {showSkillSelect && (
                <ButtonGrid>
                    {playerSkills.map((skill) => (
                        <ActionButton key={skill.id} onClick={() => handleUseSkill(skill)}>
                            {skill.icon} {skill.name}
                        </ActionButton>
                    ))}
                    <ActionButton onClick={() => {
                        setShowSkillSelect(false);
                        setIsProcessingTurn(false);
                        setMessage("무엇을 할까?");
                    }} style={{backgroundColor: '#666'}}>
                        ❌ 취소
                    </ActionButton>
                </ButtonGrid>
            )}

            {/* 아이템 선택 메뉴 */}
            {showItemSelect && (
                <ButtonGrid>
                    {inventory.filter(i => i.type === 'potion').map((item) => (
                        <ActionButton key={item.id} onClick={() => handleUseItemInBattle(item)}>
                            {item.icon} {item.name} (x{item.quantity})
                        </ActionButton>
                    ))}
                    <ActionButton onClick={() => {
                        setShowItemSelect(false);
                        setIsProcessingTurn(false);
                        setMessage("무엇을 할까?");
                    }} style={{backgroundColor: '#666'}}>
                        ❌ 취소
                    </ActionButton>
                </ButtonGrid>
            )}
            
        </GameContainer>
    )
}

export default Game