import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api', // Spring Boot 백엔드 API의 기본 URL
    headers: {
        'Content-Type': 'application/json',
    },
});

// 요청 인터셉터: 모든 요청에 JWT 토큰을 포함
apiClient.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token'); // 로컬 스토리지에서 토큰 가져오기
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// 응답 인터셉터: 에러 처리 등을 할 수 있음
apiClient.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        // 예: 401 Unauthorized 에러 발생 시 로그인 페이지로 리다이렉트
        if (error.response && error.response.status === 401) {
            // 여기에 로그인 페이지로 리다이렉트하는 로직을 추가할 수 있습니다.
            // 예: window.location.href = '/login';
            console.error('Unauthorized, redirecting to login...', error.response);
        }
        return Promise.reject(error);
    }
);

export default apiClient;
