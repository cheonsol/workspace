import { create } from 'zustand';
import * as itemApi from '../api/itemApi';

const useItemStore = create((set) => ({
    items: [],
    loading: false,
    error: null,

    fetchItems: async () => {
        set({ loading: true, error: null });
        try {
            const items = await itemApi.fetchItems();
            set({ items, loading: false });
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    fetchItemById: async (id) => {
        set({ loading: true, error: null });
        try {
            const item = await itemApi.fetchItem(id);
            set((state) => ({
                items: state.items.some(i => i.id === item.id)
                    ? state.items.map(i => i.id === item.id ? item : i)
                    : [...state.items, item],
                loading: false,
            }));
            return item;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    createItem: async (itemData) => {
        set({ loading: true, error: null });
        try {
            const newItem = await itemApi.createItem(itemData);
            set((state) => ({
                items: [...state.items, newItem],
                loading: false,
            }));
            return newItem;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    updateItem: async (id, itemData) => {
        set({ loading: true, error: null });
        try {
            const updatedItem = await itemApi.updateItem(id, itemData);
            set((state) => ({
                items: state.items.map((item) =>
                    item.id === id ? updatedItem : item
                ),
                loading: false,
            }));
            return updatedItem;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    deleteItem: async (id) => {
        set({ loading: true, error: null });
        try {
            await itemApi.deleteItem(id);
            set((state) => ({
                items: state.items.filter((item) => item.id !== id),
                loading: false,
            }));
            return { success: true };
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },
}));

export default useItemStore;