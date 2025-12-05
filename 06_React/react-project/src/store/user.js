import {create} from 'zustand';
import { persist } from 'zustand/middleware';


// useGameStore라는 hook을 만듬
const useGameStore = create(
    // persist로 저장할 수 있도록 할당
    persist(
        // set을 통해 설정들을 입력
        (set) => ({

        // user에 대한 배열 생성. db를 사용할 시 db에서 불러오면 됨.
        users : [
            {
                Uid: 'testuser',
                password: '12345678',
                nickname: '최강의 테스터',
                LV: 100,
                exp: 999999,
                maxHp: 500,
                currentHp: 500,
                maxMp: 500,
                currentMp: 500,
                atk: 999,
                def: 999,
                dex: 999,
                luk: 100,
                floor: 1,
                gold: 999999,
                equippedItems: {},
                statPoints: 999,
                baseAtk: 100,
                baseDef: 100,
                baseDex: 100,
                baseLuk: 100,
                baseMaxHp: 500,
                baseMaxMp: 500
            }
        ],

        // newUser === 매개변수, set은 함수를 작동시켜 내용을 변경하는 명령어. state는 set에서 가져오는 매개변수로 저장된 값 불러오기.
        // 처음에 users 저장 정보 자체를 열고  그 안에 users의 state를 불러온다. 그 후 {}를 열어 객체 생성. 이때, newUser를 스프레드로 펼친다.
        // 그 후, 입력된 것들 외에 다른 정보들을 넣기 위해 아래에 규칙을 작성한다.
        addUser : (newUser) => set((state) => ({
            users : [...state.users, {
                    ...newUser,
                    LV : 1,
                    exp : 0,
                    maxHp : 100,
                    currentHp : 100,
                    maxMp : 100,
                    currentMp : 100,
                    atk : 10,
                    def : 10,
                    dex : 10,
                    luk : 10,
                    floor : 1,
                    gold : 0,
                    equippedItems : {},
                    statPoints : 0,
                    baseAtk : 10,
                    baseDef : 10,
                    baseDex : 10,
                    baseLuk : 10,
                    baseMaxHp : 100,
                    baseMaxMp : 100
            }]
        })),

        // user 매개변수를 받아 currentUser에 user를 저장
        login : (user) => set({currentUser : user}),
        // currentUser에 null를 저장
        logout : () => set({currentUser : null}),

        // 경험치 획득
        addExp : (amount) => set((state) => ({
            currentUser : state.currentUser ? {
                ...state.currentUser,
                exp : state.currentUser.exp + amount
            } : null,
            users : state.users.map(u => 
                u.nickname === state.currentUser?.nickname
                    ? { ...u, exp: u.exp + amount }
                    : u
            )
        })),

        // 골드 획득
        addGold : (amount) => set((state) => ({
            currentUser : state.currentUser ? {
                ...state.currentUser,
                gold : state.currentUser.gold + amount
            } : null,
            users : state.users.map(u => 
                u.nickname === state.currentUser?.nickname
                    ? { ...u, gold: u.gold + amount }
                    : u
            )
        })),

        // 체력 업데이트
        updateHp : (hp) => set((state) => ({
            currentUser : state.currentUser ? {
                ...state.currentUser,
                currentHp : Math.min(state.currentUser.maxHp, Math.max(0, hp))
            } : null
        })),

        // 마나 업데이트
        updateMp : (mp) => set((state) => ({
            currentUser : state.currentUser ? {
                ...state.currentUser,
                currentMp : Math.min(state.currentUser.maxMp, Math.max(0, mp))
            } : null
        })),

        // 층 이동
        moveToNextFloor : () => set((state) => ({
            currentUser : state.currentUser ? {
                ...state.currentUser,
                floor : state.currentUser.floor + 1
            } : null,
            users : state.users.map(u =>
                u.nickname === state.currentUser?.nickname
                    ? { ...u, floor: u.floor + 1 }
                    : u
            )
        })),

        // 아이템 착용
        equipItem : (itemId) => set((state) => {
            if (!state.currentUser) return state;
            
            const equippedItems = state.currentUser.equippedItems || {};
            
            // 이미 착용된 같은 타입의 아이템이 있으면 벗기
            const newEquipped = { ...equippedItems, [itemId]: true };
            
            return {
                currentUser : {
                    ...state.currentUser,
                    equippedItems: newEquipped
                },
                users : state.users.map(u =>
                    u.nickname === state.currentUser?.nickname
                        ? { ...u, equippedItems: newEquipped }
                        : u
                )
            };
        }),

        // 아이템 벗기
        unequipItem : (itemId) => set((state) => {
            if (!state.currentUser) return state;
            
            const equippedItems = state.currentUser.equippedItems || {};
            const newEquipped = { ...equippedItems };
            delete newEquipped[itemId];
            
            return {
                currentUser : {
                    ...state.currentUser,
                    equippedItems: newEquipped
                },
                users : state.users.map(u =>
                    u.nickname === state.currentUser?.nickname
                        ? { ...u, equippedItems: newEquipped }
                        : u
                )
            };
        }),

        // 레벨 업
        levelUp : () => set((state) => {
            if (!state.currentUser) return state;
            
            const newLV = state.currentUser.LV + 1;
            const isMaxLevel = newLV >= 100;
            
            const updatedUser = {
                ...state.currentUser,
                LV: Math.min(newLV, 100),
                exp: isMaxLevel ? 999999 : 0,
                maxHp: state.currentUser.maxHp + 10,
                currentHp: state.currentUser.maxHp + 10,
                maxMp: state.currentUser.maxMp + 10,
                currentMp: state.currentUser.maxMp + 10,
                atk: state.currentUser.atk + 5,
                def: state.currentUser.def + 3,
                dex: state.currentUser.dex + 2,
                luk: state.currentUser.luk + 2,
                statPoints: state.currentUser.statPoints + 5,
                baseAtk: (state.currentUser.baseAtk || 10) + 5,
                baseDef: (state.currentUser.baseDef || 10) + 3,
                baseDex: (state.currentUser.baseDex || 10) + 2,
                baseLuk: (state.currentUser.baseLuk || 10) + 2,
                baseMaxHp: (state.currentUser.baseMaxHp || 100) + 10,
                baseMaxMp: (state.currentUser.baseMaxMp || 100) + 10
            };
            
            return {
                currentUser: updatedUser,
                users: state.users.map(u =>
                    u.nickname === state.currentUser?.nickname
                        ? updatedUser
                        : u
                )
            };
        }),

        // 스탯 분배 (statPoints 소비하여 특정 스탯 증가)
        allocateStat : (statName) => set((state) => {
            if (!state.currentUser || state.currentUser.statPoints <= 0) return state;
            
            const statIncrement = {
                hp: 1,
                mp: 1,
                atk: 1,
                def: 1,
                dex: 1,
                luk: 1
            };
            
            const increment = statIncrement[statName] || 0;
            if (increment === 0) return state;
            
            const updatedUser = {
                ...state.currentUser,
                statPoints: state.currentUser.statPoints - 1,
                ...(statName === 'hp' && {
                    maxHp: state.currentUser.maxHp + increment,
                    currentHp: Math.min(state.currentUser.currentHp + increment, state.currentUser.maxHp + increment),
                    baseMaxHp: (state.currentUser.baseMaxHp || state.currentUser.maxHp) + increment
                }),
                ...(statName === 'mp' && {
                    maxMp: state.currentUser.maxMp + increment,
                    currentMp: Math.min(state.currentUser.currentMp + increment, state.currentUser.maxMp + increment),
                    baseMaxMp: (state.currentUser.baseMaxMp || state.currentUser.maxMp) + increment
                }),
                ...(statName === 'atk' && { 
                    atk: state.currentUser.atk + increment,
                    baseAtk: (state.currentUser.baseAtk || state.currentUser.atk) + increment
                }),
                ...(statName === 'def' && { 
                    def: state.currentUser.def + increment,
                    baseDef: (state.currentUser.baseDef || state.currentUser.def) + increment
                }),
                ...(statName === 'dex' && { 
                    dex: state.currentUser.dex + increment,
                    baseDex: (state.currentUser.baseDex || state.currentUser.dex) + increment
                }),
                ...(statName === 'luk' && { 
                    luk: state.currentUser.luk + increment,
                    baseLuk: (state.currentUser.baseLuk || state.currentUser.luk) + increment
                })
            };
            
            return {
                currentUser: updatedUser,
                users: state.users.map(u =>
                    u.nickname === state.currentUser?.nickname
                        ? updatedUser
                        : u
                )
            };
        })
    }),
        {
            name : 'user-storage',
        }
    )
)
        
    


export default useGameStore;