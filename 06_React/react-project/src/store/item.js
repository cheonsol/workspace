import { create } from "zustand";
import { persist } from "zustand/middleware";

// Store 생성
const useItemStore = create(
    persist(
        set => ({
            // 전체 아이템 목록 (상점에서 구매 가능)
            allItems: [
                {
                    id: 1,
                    name: "체력 물약",
                    description: "체력을 50 회복합니다",
                    type: "potion",
                    icon: "🧪",
                    effect: "heal",
                    value: 50,
                    price: 100,
                    rarity: "common"
                },
                {
                    id: 2,
                    name: "마나 물약",
                    description: "마나를 30 회복합니다",
                    type: "potion",
                    icon: "🔵",
                    effect: "mana",
                    value: 30,
                    price: 80,
                    rarity: "common"
                },
                {
                    id: 3,
                    name: "철 검",
                    description: "공격력 +10을 주는 무기입니다",
                    type: "weapon",
                    icon: "🗡️",
                    effect: "attack",
                    value: 10,
                    price: 500,
                    rarity: "uncommon"
                },
                {
                    id: 4,
                    name: "가죽 갑옷",
                    description: "방어력 +8을 주는 방어구입니다",
                    type: "armor",
                    icon: "🛡️",
                    effect: "defense",
                    value: 8,
                    price: 400,
                    rarity: "uncommon"
                },
                {
                    id: 5,
                    name: "마법사의 로브",
                    description: "마나 +50을 제공합니다",
                    type: "armor",
                    icon: "👗",
                    effect: "maxMana",
                    value: 50,
                    price: 600,
                    rarity: "rare"
                },
                {
                    id: 6,
                    name: "전설의 검",
                    description: "공격력 +25를 주는 강력한 무기입니다",
                    type: "weapon",
                    icon: "⚔️",
                    effect: "attack",
                    value: 25,
                    price: 2000,
                    rarity: "legendary"
                },
                // 보스 처치 시 획득 가능한 레어 아이템들
                {
                    id: 7,
                    name: "드래곤 슬레이어",
                    description: "공격력 +35를 주는 전설적인 검",
                    type: "weapon",
                    icon: "🐉",
                    effect: "attack",
                    value: 35,
                    price: 5000,
                    rarity: "legendary",
                    isBossItem: true
                },
                {
                    id: 8,
                    name: "불사조의 갑옷",
                    description: "방어력 +50, 체력 +100",
                    type: "armor",
                    icon: "🔥",
                    effect: "maxHp",
                    value: 100,
                    price: 8000,
                    rarity: "legendary",
                    isBossItem: true
                },
                {
                    id: 9,
                    name: "마법사의 왕관",
                    description: "마나 +100, 마나 재생 속도 2배",
                    type: "armor",
                    icon: "👑",
                    effect: "maxMana",
                    value: 100,
                    price: 7000,
                    rarity: "legendary",
                    isBossItem: true
                }
            ],

            // 플레이어 인벤토리
            inventory: [],

            // 아이템 구매
            buyItem: (itemId, quantity = 1) => set((state) => {
                const item = state.allItems.find(i => i.id === itemId);
                if (!item) return state;

                // 인벤토리에 같은 아이템이 있으면 수량 증가
                const existingItem = state.inventory.find(i => i.id === itemId);
                if (existingItem) {
                    return {
                        inventory: state.inventory.map(i =>
                            i.id === itemId
                                ? { ...i, quantity: i.quantity + quantity }
                                : i
                        )
                    };
                }

                // 새로운 아이템 추가
                return {
                    inventory: [...state.inventory, { ...item, quantity }]
                };
            }),

            // 아이템 판매 (인벤토리에서 제거)
            sellItem: (itemId, quantity = 1) => set((state) => ({
                inventory: state.inventory
                    .map(i =>
                        i.id === itemId
                            ? { ...i, quantity: Math.max(0, i.quantity - quantity) }
                            : i
                    )
                    .filter(i => i.quantity > 0)
            })),

            // 아이템 사용 (소비 아이템)
            useItem: (itemId) => set((state) => ({
                inventory: state.inventory
                    .map(i =>
                        i.id === itemId
                            ? { ...i, quantity: Math.max(0, i.quantity - 1) }
                            : i
                    )
                    .filter(i => i.quantity > 0)
            })),

            // 인벤토리에서 아이템 제거
            removeItem: (itemId) => set((state) => ({
                inventory: state.inventory.filter(i => i.id !== itemId)
            }))
        }),
        {
            name: 'item-storage'
        }
    )
);

export default useItemStore;
