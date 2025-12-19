import apiClient from './apiClient'; // apiClient 임포트

const BASE_URL = '/boards'; // 백엔드 BoardController의 @RequestMapping("/api/boards") 에 맞춰 수정

export const fetchBoards = async () => {
    const response = await apiClient.get(BASE_URL);
    return response.data;
};

export const fetchBoard = async (id) => {
    const response = await apiClient.get(`${BASE_URL}/${id}`);
    return response.data;
};

export const createBoard = async (boardData) => {
    const response = await apiClient.post(BASE_URL, boardData);
    return response.data;
};

export const updateBoard = async (id, boardData) => {
    const response = await apiClient.put(`${BASE_URL}/${id}`, boardData);
    return response.data;
};

export const deleteBoard = async (id) => {
    await apiClient.delete(`${BASE_URL}/${id}`);
};
