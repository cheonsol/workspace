import { create } from 'zustand';
import * as monsterApi from '../api/monsterApi';

const useMonsterStore = create((set) => ({
    monsters: [],
    loading: false,
    error: null,

    fetchMonsters: async () => {
        set({ loading: true, error: null });
        try {
            const monsters = await monsterApi.fetchMonsters();
            set({ monsters, loading: false });
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    fetchMonsterById: async (id) => {
        set({ loading: true, error: null });
        try {
            const monster = await monsterApi.fetchMonster(id);
            set((state) => ({
                monsters: state.monsters.some(m => m.id === monster.id)
                    ? state.monsters.map(m => m.id === monster.id ? monster : m)
                    : [...state.monsters, monster],
                loading: false,
            }));
            return monster;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    createMonster: async (monsterData) => {
        set({ loading: true, error: null });
        try {
            const newMonster = await monsterApi.createMonster(monsterData);
            set((state) => ({
                monsters: [...state.monsters, newMonster],
                loading: false,
            }));
            return newMonster;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    updateMonster: async (id, monsterData) => {
        set({ loading: true, error: null });
        try {
            const updatedMonster = await monsterApi.updateMonster(id, monsterData);
            set((state) => ({
                monsters: state.monsters.map((monster) =>
                    monster.id === id ? updatedMonster : monster
                ),
                loading: false,
            }));
            return updatedMonster;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    deleteMonster: async (id) => {
        set({ loading: true, error: null });
        try {
            await monsterApi.deleteMonster(id);
            set((state) => ({
                monsters: state.monsters.filter((monster) => monster.id !== id),
                loading: false,
            }));
            return { success: true };
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },
}));

export default useMonsterStore;