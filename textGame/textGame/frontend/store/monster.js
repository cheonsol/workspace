import { create } from "zustand";
import { persist } from "zustand/middleware";

const useMonsterStore = create(
    persist(
        (set) => ({
             monsters : [{
                floor : 1,
                name : '고블린',
                exp : 10,
                gold : 50,
                maxHp : 100,
                currentHp : 100,
                maxMp : 0,
                currentMp : 0,
                atk : 10,
                def : 10,
                dex : 10,
                luk : 10,
                isBoss : false,
                img : '🐢'
            },{
                floor : 1,
                name : '슬라임',
                exp : 8,
                gold : 40,
                maxHp : 80,
                currentHp : 80,
                maxMp : 0,
                currentMp : 0,
                atk : 8,
                def : 5,
                dex : 8,
                luk : 5,
                isBoss : false,
                img : '🟢'
            },{
                floor : 1,
                name : '고블린 킹',
                exp : 50,
                gold : 200,
                maxHp : 300,
                currentHp : 300,
                maxMp : 30,
                currentMp : 30,
                atk : 25,
                def : 15,
                dex : 15,
                luk : 10,
                isBoss : true,
                img : '👑'
            },{
                floor : 2,
                name : '코볼트',
                exp : 30,
                gold : 100,
                maxHp : 150,
                currentHp : 150,
                maxMp : 0,
                currentMp : 0,
                atk : 15,
                def : 10,
                dex : 10,
                luk : 10,
                isBoss : false,
                img : '🐺'
            },{
                floor : 2,
                name : '늑대',
                exp : 25,
                gold : 80,
                maxHp : 120,
                currentHp : 120,
                maxMp : 0,
                currentMp : 0,
                atk : 18,
                def : 8,
                dex : 18,
                luk : 12,
                isBoss : false,
                img : '🐺'
            },{
                floor : 2,
                name : '늑대 왕',
                exp : 80,
                gold : 400,
                maxHp : 400,
                currentHp : 400,
                maxMp : 50,
                currentMp : 50,
                atk : 35,
                def : 20,
                dex : 25,
                luk : 15,
                isBoss : true,
                img : '🐺👑'
            },{
                floor : 3,
                name : '오크',
                exp : 70,
                gold : 200,
                maxHp : 300,
                currentHp : 300,
                maxMp : 50,
                currentMp : 50,
                atk : 30,
                def : 50,
                dex : 0,
                luk : 0,
                isBoss : false,
                img : '🗡️'
            },{
                floor : 3,
                name : '트롤',
                exp : 60,
                gold : 180,
                maxHp : 280,
                currentHp : 280,
                maxMp : 40,
                currentMp : 40,
                atk : 28,
                def : 45,
                dex : 5,
                luk : 5,
                isBoss : false,
                img : '👹'
            },{
                floor : 3,
                name : '오크 왕',
                exp : 150,
                gold : 800,
                maxHp : 600,
                currentHp : 600,
                maxMp : 100,
                currentMp : 100,
                atk : 50,
                def : 60,
                dex : 10,
                luk : 5,
                isBoss : true,
                img : '🗡️👑'
            }],

            // 층 별 처치한 일반 몬스터 카운트
            killCount : {
                1: 0,
                2: 0,
                3: 0
            },

            // 일반 몬스터 처치 카운트 증가
            incrementKillCount : (floor) => set((state) => ({
                killCount : {
                    ...state.killCount,
                    [floor] : state.killCount[floor] + 1
                }
            })),

            // 층 별 처치 카운트 리셋
            resetKillCount : (floor) => set((state) => ({
                killCount : {
                    ...state.killCount,
                    [floor] : 0
                }
            }))
        }),
        {
            name : 'monster-storage'
        }
    )
)
export default useMonsterStore
