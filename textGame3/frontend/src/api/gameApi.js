import apiClient from './apiClient';

export const attack = (monsterId) => {
    return apiClient.post('/game/attack', { monsterId });
};
