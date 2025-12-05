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
                password: '1234',
                nickname: '최강의 테스터',
                LV: 100,
                exp: 999999,
                maxHp: 500,
                currentHp: 500,
                maxMp: 500,
                currentMp: 500,
                atk: 100,
                def: 100,
                dex: 100,
                luk: 100,
                floor: 1,
                gold: 999999,
                equippedItems: {}
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
                    equippedItems : {}
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
        })
    }),
        {
            name : 'user-storage',
            version: 2,
        }
    )
)
        
    


export default useGameStore;