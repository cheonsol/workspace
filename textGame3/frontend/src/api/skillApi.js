import apiClient from './apiClient';

const BASE_URL = '/skills';

export const fetchSkills = async () => {
    const response = await apiClient.get(BASE_URL);
    return response.data;
};

export const fetchSkill = async (id) => {
    const response = await apiClient.get(`${BASE_URL}/${id}`);
    return response.data;
};

export const createSkill = async (skillData) => {
    const response = await apiClient.post(BASE_URL, skillData);
    return response.data;
};

export const updateSkill = async (id, skillData) => {
    const response = await apiClient.put(`${BASE_URL}/${id}`, skillData);
    return response.data;
};

export const deleteSkill = async (id) => {
    await apiClient.delete(`${BASE_URL}/${id}`);
};