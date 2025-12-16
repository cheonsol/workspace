import apiClient from './apiClient';

const BASE_BOARD_URL = '/boards';
const BASE_COMMENT_URL = '/comments';

export const fetchCommentsByBoardId = async (boardId) => {
    const response = await apiClient.get(`${BASE_BOARD_URL}/${boardId}${BASE_COMMENT_URL}`);
    return response.data;
};

export const createComment = async (boardId, commentData) => {
    const response = await apiClient.post(`${BASE_BOARD_URL}/${boardId}${BASE_COMMENT_URL}`, commentData);
    return response.data;
};

export const updateComment = async (commentId, commentData) => {
    const response = await apiClient.put(`${BASE_COMMENT_URL}/${commentId}`, commentData);
    return response.data;
};

export const deleteComment = async (commentId) => {
    await apiClient.delete(`${BASE_COMMENT_URL}/${commentId}`);
};