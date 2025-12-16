import { create } from 'zustand';
import * as skillApi from '../api/skillApi';

const useSkillStore = create((set) => ({
    skills: [],
    loading: false,
    error: null,

    fetchSkills: async () => {
        set({ loading: true, error: null });
        try {
            const skills = await skillApi.fetchSkills();
            set({ skills, loading: false });
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    fetchSkillById: async (id) => {
        set({ loading: true, error: null });
        try {
            const skill = await skillApi.fetchSkill(id);
            set((state) => ({
                skills: state.skills.some(s => s.id === skill.id)
                    ? state.skills.map(s => s.id === skill.id ? skill : s)
                    : [...state.skills, skill],
                loading: false,
            }));
            return skill;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    createSkill: async (skillData) => {
        set({ loading: true, error: null });
        try {
            const newSkill = await skillApi.createSkill(skillData);
            set((state) => ({
                skills: [...state.skills, newSkill],
                loading: false,
            }));
            return newSkill;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    updateSkill: async (id, skillData) => {
        set({ loading: true, error: null });
        try {
            const updatedSkill = await skillApi.updateSkill(id, skillData);
            set((state) => ({
                skills: state.skills.map((skill) =>
                    skill.id === id ? updatedSkill : skill
                ),
                loading: false,
            }));
            return updatedSkill;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    deleteSkill: async (id) => {
        set({ loading: true, error: null });
        try {
            await skillApi.deleteSkill(id);
            set((state) => ({
                skills: state.skills.filter((skill) => skill.id !== id),
                loading: false,
            }));
            return { success: true };
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },
}));

export default useSkillStore;