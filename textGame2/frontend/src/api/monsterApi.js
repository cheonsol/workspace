import apiClient from './apiClient';

const BASE_URL = '/monsters';

export const fetchMonsters = async () => {
    const response = await apiClient.get(BASE_URL);
    return response.data;
};

export const fetchMonster = async (id) => {
    const response = await apiClient.get(`${BASE_URL}/${id}`);
    return response.data;
};

export const createMonster = async (monsterData) => {
    const response = await apiClient.post(BASE_URL, monsterData);
    return response.data;
};

export const updateMonster = async (id, monsterData) => {
    const response = await apiClient.put(`${BASE_URL}/${id}`, monsterData);
    return response.data;
};

export const deleteMonster = async (id) => {
    await apiClient.delete(`${BASE_URL}/${id}`);
};