import {create} from 'zustand';
import { persist } from 'zustand/middleware';


// useGameStore라는 hook을 만듬
const useGameStore = create(
    // persist로 저장할 수 있도록 할당
    persist(
        // set을 통해 설정들을 입력
        (set) => ({

        // user에 대한 배열 생성. db를 사용할 시 db에서 불러오면 됨.
        users : [],

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
            }]
        })),

        // user 매개변수를 받아 currentUser에 user를 저장
        login : (user) => set({currentUser : user}),
        // currentUser에 null를 저장
        logout : () => set({currentUser : null}),
    }),
        {
            name : 'user-storage',
        }
    )
)
        
    


export default useGameStore;