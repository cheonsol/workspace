import apiClient from './apiClient';

const BASE_URL = '/members';

export const fetchMemberDetails = async () => {
    const response = await apiClient.get(`${BASE_URL}/me`);
    return response.data;
};

export const updateMemberStats = async (statUpdateData) => {
    const response = await apiClient.put(`${BASE_URL}/me/stats`, statUpdateData);
    return response.data;
};

export const buyItem = async (itemId, quantity = 1) => {
    const response = await apiClient.post(`${BASE_URL}/me/items/${itemId}/buy?quantity=${quantity}`);
    return response.data;
};

export const sellItem = async (memberItemId, quantity = 1) => {
    const response = await apiClient.post(`${BASE_URL}/me/items/${memberItemId}/sell?quantity=${quantity}`);
    return response.data;
};

export const useItem = async (memberItemId) => {
    const response = await apiClient.post(`${BASE_URL}/me/items/${memberItemId}/use`);
    return response.data;
};

export const toggleEquipItem = async (memberItemId) => {
    const response = await apiClient.post(`${BASE_URL}/me/items/${memberItemId}/toggle-equip`);
    return response.data;
};