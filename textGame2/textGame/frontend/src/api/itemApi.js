import apiClient from './apiClient';

const BASE_URL = '/items';

export const fetchItems = async () => {
    const response = await apiClient.get(BASE_URL);
    return response.data;
};

export const fetchItem = async (id) => {
    const response = await apiClient.get(`${BASE_URL}/${id}`);
    return response.data;
};

export const createItem = async (itemData) => {
    const response = await apiClient.post(BASE_URL, itemData);
    return response.data;
};

export const updateItem = async (id, itemData) => {
    const response = await apiClient.put(`${BASE_URL}/${id}`, itemData);
    return response.data;
};

export const deleteItem = async (id) => {
    await apiClient.delete(`${BASE_URL}/${id}`);
};