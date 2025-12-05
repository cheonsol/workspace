import React, { useEffect, useState } from 'react';
import useGameStore from '../store/user';
import useSkillStore from '../store/skill';
import useItemStore from '../store/item';
import useMonsterStore from '../store/monster';
import { useNavigate } from 'react-router-dom';
// 게임 유틸리티 함수 import - 데미지 계산, 크리티컬, 경험치 계산 등
import { 
    calculateDamage,        // 플레이어의 기본 공격 데미지 계산
    isCritical,             // 크리티컬 발생 여부 확인
    calculateSkillDamage,   // 스킬 사용 시 데미지 계산
    calculateMonsterDamage, // 몬스터 데미지 계산
    getRequiredExp,         // 레벨업에 필요한 경험치
    checkLevelUp            // 레벨업 체크
} from '../utils/gameUtils';

// 스타일 컴포넌트 import (기존 유지)
import { 
    GameContainer, StatusWindow, StatusHeader, CharacterInfo, 
    HpBarFrame, HpBarFill, MonsterImage, DialogueBox, NextArrow, 
    ButtonGrid, ActionButton 
} from '../style/Game.style';

const Game = () => {
    const navigator = useNavigate();
    
    // 1. 스토어 데이터
    const user = useGameStore((state) => state.currentUser);
    const addExp = useGameStore((state) => state.addExp);
    const addGold = useGameStore((state) => state.addGold);
    const updateHp = useGameStore((state) => state.updateHp);
    const moveToNextFloor = useGameStore((state) => state.moveToNextFloor);
    const levelUp = useGameStore((state) => state.levelUp);
    
    const monsters = useMonsterStore((state) => state.monsters);
    const killCount = useMonsterStore((state) => state.killCount);
    const incrementKillCount = useMonsterStore((state) => state.incrementKillCount);
    const resetKillCount = useMonsterStore((state) => state.resetKillCount);
    
    const playerSkills = useSkillStore((state) => state.playerSkills);
    const learnSkillFromBook = useSkillStore((state) => state.learnSkillFromBook);
    
    const inventory = useItemStore((state) => state.inventory);
    const useItem = useItemStore((state) => state.useItem);
    const buyItem = useItemStore((state) => state.buyItem);
    
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
        }
    }, []);

    // 스킬 사용 핸들러
    const handleUseSkill = (skill) => {
        setShowSkillSelect(false);
        setIsProcessingTurn(true);

        const newQueue = [];
        // calculateSkillDamage: 스킬 기본 데미지와 플레이어 공격력을 고려한 총 데미지 계산
        const skillDamage = calculateSkillDamage(skill.damage, user.atk);

        newQueue.push({ type: 'MESSAGE', text: `✨ ${user.nickname}이(가) [${skill.name}]을(를) 사용했다!` });
        newQueue.push({ type: 'MESSAGE', text: `💥 ${currentMonsterData.name}에게 ${skillDamage}의 피해!` });
        newQueue.push({ type: 'MONSTER_DAMAGE', amount: skillDamage });

        const predictedMonsterHp = monsterHp - skillDamage;

        if (predictedMonsterHp > 0) {
            // calculateMonsterDamage: 몬스터의 공격력과 행운도로 실제 데미지 계산
            // 행운도가 높을수록 크리티컬 확률 증가, 크리티컬 시 데미지 2배
            const monsterDamage = calculateMonsterDamage(currentMonsterData);

            newQueue.push({ type: 'MESSAGE', text: `💢 ${currentMonsterData.name}의 반격!` });
            newQueue.push({ type: 'MESSAGE', text: `🩸 당신은 ${monsterDamage}의 피해를 입었다.` });
            newQueue.push({ type: 'USER_DAMAGE', amount: monsterDamage });
        } else {
            const gainExp = currentMonsterData.exp;
            const gainGold = currentMonsterData.gold;

            newQueue.push({ type: 'MESSAGE', text: `🎉 ${currentMonsterData.name}을(를) 처치했다!` });
            newQueue.push({ type: 'MESSAGE', text: `⭐ 경험치 ${gainExp}을(를) 획득했다!` });
            newQueue.push({ type: 'MESSAGE', text: `💰 ${gainGold} 골드를 획득했다!` });

            if (currentMonsterData.isBoss) {
                // 보스 처치 시 보상
                newQueue.push({ type: 'MESSAGE', text: `👑 보스를 격파했다! 다음 층으로 진입할 수 있습니다!` });
                newQueue.push({ type: 'BOSS_REWARD', exp: gainExp, gold: gainGold });
            } else {
                // 일반 몬스터 처치
                newQueue.push({ type: 'REWARD', exp: gainExp, gold: gainGold });
            }
            
            newQueue.push({ type: 'Battle_Win' });
        }

        setBattleQueue(newQueue);
        processNextEvent(newQueue);
    };

    // 아이템 사용 핸들러
    const handleUseItemInBattle = (item) => {
        setShowItemSelect(false);
        setIsProcessingTurn(true);

        const newQueue = [];
        const healAmount = item.value;

        newQueue.push({ type: 'MESSAGE', text: `💊 ${user.nickname}은(는) [${item.name}]을(를) 사용했다.` });
        newQueue.push({ type: 'MESSAGE', text: `💖 체력이 ${healAmount} 회복되었다.` });
        newQueue.push({ type: 'USER_HEAL', amount: healAmount });

        useItem(item.id); // 인벤토리에서 아이템 소비

        // calculateMonsterDamage: 몬스터 데미지 계산 (행운도 기반 크리티컬 포함)
        const monsterDamage = calculateMonsterDamage(currentMonsterData);

        newQueue.push({ type: 'MESSAGE', text: `💢 ${currentMonsterData.name}의 공격!` });
        newQueue.push({ type: 'MESSAGE', text: `🩸 당신은 ${monsterDamage}의 피해를 입었다.` });
        newQueue.push({ type: 'USER_DAMAGE', amount: monsterDamage });

        setBattleQueue(newQueue);
        processNextEvent(newQueue);
    };
    // --- ⚔️ 전투 행동 통합 함수 (공격, 스킬, 아이템) ---
    // type: 'ATTACK' | 'SKILL' | 'ITEM'
    // type을 받아 요소 실행
    const handleBattleAction = (type) => {
      // 유저나 몬스터가 없을 경우 리턴.
        if (!user || !currentMonsterData) return;

      // 턴 true로 실행
        setIsProcessingTurn(true); 

      // 로그를 위한 배열 생성
        const newQueue = []; 

        // type이 ATTACK일 때, 기본 공격
        if (type === 'ATTACK') {
          // calculateDamage: 플레이어 공격력과 크리티컬 여부로 데미지 계산
          // isCritical: 행운도(luk) 기반 크리티컬 확률 판정 (true시 데미지 2배)
            const isCrit = isCritical(user.luk);
            userDamage = calculateDamage(user.atk, isCrit ? 2 : 1);
            
          // 대본에 type 별 정보를 저장  
            newQueue.push({ type: 'MESSAGE', text: `⚔️ ${user.nickname}의 공격!` });
            newQueue.push({ type: 'MESSAGE', text: `💥 ${currentMonsterData.name}에게 ${userDamage}의 피해!${isCrit ? ' 💥 크리티컬!' : ''}` });
            newQueue.push({ type: 'MONSTER_DAMAGE', amount: userDamage });

        } else if (type === 'SKILL') {
            // [스킬] - 배운 스킬 사용
            if (playerSkills.length === 0) {
                setIsProcessingTurn(false);
                setMessage("배운 스킬이 없습니다!");
                return;
            }
            setShowSkillSelect(true);
            setIsProcessingTurn(false);
            return;

        } else if (type === 'ITEM') {
            // [아이템] - 인벤토리에서 아이템 사용
            const potions = inventory.filter(i => i.type === 'potion');
            if (potions.length === 0) {
                setIsProcessingTurn(false);
                setMessage("사용할 포션이 없습니다!");
                return;
            }
            setShowItemSelect(true);
            setIsProcessingTurn(false);
            return;
        }

        // 몬스터 체력 계산. ITEM 사용 시, 몬스터의 HP는 줄지 않음.
        const predictedMonsterHp = (type === 'ITEM') 
            ? monsterHp 
            : monsterHp - userDamage;

        // 몬스터의 HP가 0 이상일 때, 반격. 몬스터의 스킬도 추가 예정.
        if (predictedMonsterHp > 0) {
            // calculateMonsterDamage: 몬스터 데미지 계산 (행운도 기반 크리티컬 포함)
            const monsterDamage = calculateMonsterDamage(currentMonsterData);

            newQueue.push({ type: 'MESSAGE', text: `💢 ${currentMonsterData.name}의 반격!` });
            newQueue.push({ type: 'MESSAGE', text: `🩸 당신은 ${monsterDamage}의 피해를 입었다.` });
            newQueue.push({ type: 'USER_DAMAGE', amount: monsterDamage });
        } else {
            // 몬스터 사망
            const gainExp = currentMonsterData.exp;
            const gainGold = currentMonsterData.gold;
            
            newQueue.push({ type: 'MESSAGE', text: `🎉 ${currentMonsterData.name}을(를) 처치했다!` });
            newQueue.push({ type: 'MESSAGE', text: `⭐ 경험치 ${gainExp}을(를) 획득했다!` });
            newQueue.push({ type: 'MESSAGE', text: `💰 ${gainGold} 골드를 획득했다!` });
            newQueue.push({ type: 'REWARD', exp: gainExp, gold: gainGold });
            newQueue.push({ type: 'Battle_Win' });
        }

        
        // 로그를 setBattleQueue으로 저장함
        setBattleQueue(newQueue);
        processNextEvent(newQueue);

    }

    // 보스 처치 후 선택
    const handleBossChoice = (goNext) => {
        setShowBossChoice(false);
        
        if (goNext) {
            // 다음 층으로 이동
            resetKillCount(currentFloor);
            moveToNextFloor();
            
            // 새로운 층의 일반 몬스터 선택
            const nextFloor = currentFloor + 1;
            const nextFloorMonsters = monsters.filter(m => m.floor === nextFloor);
            const nextNormalMonsters = nextFloorMonsters.filter(m => !m.isBoss);
            const newMonster = nextNormalMonsters[Math.floor(Math.random() * nextNormalMonsters.length)];
            
            // setTimeout으로 상태 업데이트 지연
            setTimeout(() => {
                setMessage("새로운 층에 입장했다!");
                setCurrentMonsterData(newMonster);
                setMonsterHp(newMonster?.maxHp || 100);
                setUserHp(user.currentHp);
                setIsProcessingTurn(false);
            }, 100);
        } else {
            // 현재 층에서 계속 전투 (보스 제외한 일반 몬스터)
            const continueNormalMonsters = normalMonsters;
            const newMonster = continueNormalMonsters[Math.floor(Math.random() * continueNormalMonsters.length)];
            setMessage(`야생의 [${newMonster.name}]이(가) 나타났다!`);
            setCurrentMonsterData(newMonster);
            setMonsterHp(newMonster?.maxHp || 100);
            setIsProcessingTurn(false);
        }
    } 
 

    // --- 실행 ---

    // 이벤트 작동을 위한 함수 생성
    // manualQueue를 매개변수로 받는 이유는 setTimeOut을 사용할 때, 최신 정보를 사용하지 않아 무한 루프가 발생하기 때문이다.
    const processNextEvent = (manualQueue) => {

        // manualQueue가 있을 경우, manualQueue를 없을 경우 battleQueue를 받는다.
        const questList = Array.isArray(manualQueue) ? manualQueue : battleQueue;

        // 로그가 없을 경우, 무엇을 할까를 출력한다.
        if (questList.length === 0) {
            setIsProcessingTurn(false);
            setMessage("무엇을 할까?");
            return;
        }

        // 로그를 저장한다.
        const currentQueue = [...questList];
        // 하나씩 뺀다. shift를 사용하는 이유는 배열에서 제거해 전부 수행한 뒤에는 length를 0으로 만들기 위함.
        const event = currentQueue.shift(); 
        
        // event의 타입에 따라 값을 출력한다.
        switch (event.type) {
            case 'MESSAGE':
                setMessage(event.text);
                break;
            
            case 'MONSTER_DAMAGE':
                setMonsterHp(prev => Math.max(0, prev - event.amount));
                if(currentQueue.length > 0) {
                    setBattleQueue(currentQueue);
                    setTimeout(() => processNextEvent(currentQueue), 0);
                    return;
                }
                break;
            
            case 'USER_DAMAGE':
                setUserHp(prev => {
                    const newHp = Math.max(0, prev - event.amount);
                    if (newHp === 0) {
                        // 유저 사망
                        setTimeout(() => {
                            setMessage("터어어어어얼렸구나!");
                            setIsProcessingTurn(false);
                            setTimeout(() => {
                                navigator('/');
                            }, 2000);
                        }, 500);
                    }
                    return newHp;
                });
                if(currentQueue.length > 0 && !(event.amount && userHp - event.amount <= 0)) {
                      setBattleQueue(currentQueue);
                      setTimeout(() => processNextEvent(currentQueue), 0);
                      return;
                }
                break;

            case 'USER_HEAL':
                setUserHp(prev => Math.min(user.maxHp, prev + event.amount));
                if(currentQueue.length > 0) {
                    setBattleQueue(currentQueue);
                    setTimeout(() => processNextEvent(currentQueue), 0);
                    return;
                }
                break;

            case 'REWARD':
                addExp(event.exp);
                addGold(event.gold);
                incrementKillCount(currentFloor);

                // checkLevelUp: 경험치 획득 후 레벨업 가능 여부 확인
                const levelUpResult = checkLevelUp(user.exp + event.exp, user.LV);
                // newQueue 선언 추가 (currentQueue 복사)
                let newQueue = [...currentQueue];
                if (levelUpResult.canLevelUp && user.LV < 100) {
                    levelUp();
                    newQueue.unshift({ 
                        type: 'MESSAGE', 
                        text: `🎊 레벨업! 이제 LV.${user.LV + 1}입니다!` 
                    });
                } else if (user.LV === 100) {
                    newQueue.unshift({ 
                        type: 'MESSAGE', 
                        text: `👑 최강! 만렙 LV.100에 도달했습니다!` 
                    });
                }
                if(newQueue.length > 0) {
                    setBattleQueue(newQueue);
                    setTimeout(() => processNextEvent(newQueue), 0);
                    return;
                }
                break;

            case 'BOSS_REWARD':
                addExp(event.exp);
                addGold(event.gold);
                
                // 레어 아이템 또는 스킬북 랜덤 획득
                const reward = Math.random() > 0.5;
                if (reward) {
                    // 레어 아이템 획득
                    const rareItems = [7, 8, 9]; // 드래곤 슬레이어, 불사조 갑옷, 마법사 왕관
                    const randomItem = rareItems[Math.floor(Math.random() * rareItems.length)];
                    buyItem(randomItem, 1);
                } else {
                    // 스킬북 획득
                    const allSkills = [1, 2, 3, 4];
                    const randomSkill = allSkills[Math.floor(Math.random() * allSkills.length)];
                    learnSkillFromBook(randomSkill);
                }

                // 층 이동은 handleBossChoice에서 수행 (사용자 선택 후)

                if(currentQueue.length > 0) {
                    setBattleQueue(currentQueue);
                    setTimeout(() => processNextEvent(currentQueue), 0);
                    return;
                }
                break;

              case 'Battle_Win':
                // 1. 몬스터 처치 후 새로운 몬스터 등장
                const newMonster = getRandomMonster();
                setCurrentMonsterData(newMonster);
                setMonsterHp(newMonster.maxHp);
                
                // 2. 메시지 출력
                if (currentMonsterData.isBoss) {
                    setMessage(`🎉 보스를 격파했습니다! 다음 층으로 진입하시겠습니까?`);
                    setShowBossChoice(true);
                    setIsProcessingTurn(false);
                } else {
                    setMessage(`새로운 [${newMonster.name}]이(가) 나타났다!`);
                    // 3. 전투 종료 상태로 변경 (버튼 다시 활성화)
                    setIsProcessingTurn(false);
                }
                
                // 4. 여기서 큐를 비우고 리턴 (더 이상 진행할 큐가 없음)
                setBattleQueue([]);
                return;

            default:
                break;
        }

        setBattleQueue(currentQueue);
    }

    if (!user || !currentMonsterData) return <div>로딩 중...</div>;

    return (
        <GameContainer>
            {/* 상태창 영역 */}
            <StatusWindow>
                <StatusHeader>
                    {/* 몬스터 정보 */}
                    <CharacterInfo>
                        <strong>{currentMonsterData.name} Lv.{Math.floor(currentMonsterData.atk/2)} {currentMonsterData.isBoss ? '👑 보스' : ''}</strong> 
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
            {!isProcessingTurn && !showSkillSelect && !showItemSelect && !showBossChoice && (
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