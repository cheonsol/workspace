import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import apiClient from '../api/apiClient';
import { jwtDecode } from 'jwt-decode';
import * as memberApi from '../api/memberApi'; // memberApi 임포트

const useGameStore = create(
    persist(
        (set, get) => ({
            token: null,
            currentUser: null,
            loading: false,
            error: null,

            fetchMemberDetails: async () => {
                set({ loading: true, error: null });
                try {
                    const memberDetails = await memberApi.fetchMemberDetails();
                    set({ currentUser: memberDetails, loading: false });
                    return { success: true, currentUser: memberDetails };
                } catch (error) {
                    console.error('Failed to fetch member details:', error);
                    set({ error: error.message, loading: false });
                    get().logout();
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            signup: async (userData) => {
                set({ loading: true, error: null });
                try {
                    const response = await apiClient.post('/auth/signup', userData);
                    set({ loading: false });
                    return { success: true, message: response.data };
                } catch (error) {
                    console.error('Signup failed:', error);
                    set({ error: error.response?.data?.message || error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            login: async (credentials) => {
                set({ loading: true, error: null });
                try {
                    const response = await apiClient.post('/auth/login', credentials);
                    const { token } = response.data;

                    localStorage.setItem('token', token);
                    set({ token, loading: false });

                    const fetchResult = await get().fetchMemberDetails();
                    if (!fetchResult.success) {
                        get().logout();
                        return { success: false, message: fetchResult.message };
                    }
                    return { success: true, currentUser: get().currentUser };
                } catch (error) {
                    console.error('Login failed:', error);
                    localStorage.removeItem('token');
                    set({ token: null, currentUser: null, error: error.response?.data?.message || error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            logout: () => {
                localStorage.removeItem('token');
                set({ token: null, currentUser: null });
                console.log('Logged out');
            },

            initializeAuth: async () => {
                const storedToken = localStorage.getItem('token');
                if (storedToken) {
                    try {
                        const decodedToken = jwtDecode(storedToken);
                        if (decodedToken.exp * 1000 > Date.now()) {
                            set({ token: storedToken });
                            await get().fetchMemberDetails();
                            console.log('Auth state restored and member details fetched.');
                        } else {
                            get().logout();
                        }
                    } catch (error) {
                        console.error('Failed to decode or validate stored token:', error);
                        get().logout();
                    }
                }
            },
            
            addExp: async (amount) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.updateMemberStats({ exp: (get().currentUser?.exp || 0) + amount });
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            addGold: async (amount) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.updateMemberStats({ gold: (get().currentUser?.gold || 0) + amount });
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            updateHp: async (hp) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.updateMemberStats({ currentHp: hp });
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            updateMp: async (mp) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.updateMemberStats({ currentMp: mp });
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            moveToNextFloor: async () => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.updateMemberStats({ floor: (get().currentUser?.floor || 1) + 1 });
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            buyItem: async (itemId, quantity = 1) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.buyItem(itemId, quantity);
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            sellItem: async (memberItemId, quantity = 1) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.sellItem(memberItemId, quantity);
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            useItem: async (memberItemId) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.useItem(memberItemId);
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            toggleEquipItem: async (memberItemId) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.toggleEquipItem(memberItemId);
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },
            
            levelUp: async () => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.levelUp(); // Assuming a levelUp API
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            allocateStat: async (statName) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.allocateStat(statName); // Assuming an allocateStat API
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            },

            learnSkill: async (skillId) => {
                set({ loading: true, error: null });
                try {
                    const updatedMember = await memberApi.learnSkill(skillId); // Assuming a learnSkill API
                    set({ currentUser: updatedMember, loading: false });
                    return { success: true, currentUser: updatedMember };
                } catch (error) {
                    set({ error: error.message, loading: false });
                    return { success: false, message: error.response?.data?.message || error.message };
                }
            }
        }),
        {
            name: 'game-storage',
    
        }
    )
);

export default useGameStore;