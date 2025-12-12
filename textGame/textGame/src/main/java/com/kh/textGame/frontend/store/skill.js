import { create } from "zustand";
import { persist } from "zustand/middleware";

// Store 생성
const useSkillStore = create(
    persist(
        set => ({
            // 스킬 배열
            skills: [
                {
                    id: 1,
                    name: "파이어볼",
                    description: "적에게 불 공격을 날립니다",
                    damage: 30,
                    manaCost: 20,
                    cooldown: 3,
                    icon: "🔥"
                },
                {
                    id: 2,
                    name: "아이스 샤드",
                    description: "적을 얼음으로 공격합니다",
                    damage: 25,
                    manaCost: 15,
                    cooldown: 2,
                    icon: "❄️"
                },
                {
                    id: 3,
                    name: "라이트닝 스트라이크",
                    description: "번개로 강력한 공격을 합니다",
                    damage: 40,
                    manaCost: 30,
                    cooldown: 5,
                    icon: "⚡"
                },
                {
                    id: 4,
                    name: "힐",
                    description: "자신의 체력을 회복합니다",
                    damage: 0,
                    manaCost: 25,
                    cooldown: 4,
                    icon: "💚",
                    isHealing: true,
                    healAmount: 50
                }
            ],

            // 사용자 스킬 (배운 스킬들)
            playerSkills: [],

            // 스킬 배우기
            learnSkill: (skillId) => set((state) => {
                const skill = state.skills.find(s => s.id === skillId);
                if (skill && !state.playerSkills.find(ps => ps.id === skillId)) {
                    return {
                        playerSkills: [...state.playerSkills, { ...skill, learned: true }]
                    };
                }
                return state;
            }),

            // 스킬북을 사용해서 스킬 배우기
            learnSkillFromBook: (skillId) => set((state) => {
                const skill = state.skills.find(s => s.id === skillId);
                if (skill && !state.playerSkills.find(ps => ps.id === skillId)) {
                    return {
                        playerSkills: [...state.playerSkills, { ...skill, learned: true, fromBook: true }]
                    };
                }
                return state;
            }),

            // 스킬 제거
            forgetSkill: (skillId) => set((state) => ({
                playerSkills: state.playerSkills.filter(ps => ps.id !== skillId)
            })),

            // 모든 스킬 가져오기
            getSkills: () => {
                // 이 함수는 컴포넌트에서 직접 state에 접근하므로 필요 없음
            }
        }),
        {
            name: 'skill-storage'
        }
    )
);

export default useSkillStore;
