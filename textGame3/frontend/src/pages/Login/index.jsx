import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import useGameStore from '../../store/user';
import Header from '../../layout/Header';
import { AuthWrapper, AuthBox, Title, InputGroup, StyledInput, AuthButton, LinkText } from './Auth.style';
import {
    validateId,
    validatePassword
} from '../../utils/validation';

const Login = () => {
    const navigate = useNavigate();
    const login = useGameStore((state) => state.login); // Zustand store의 login 액션 사용

    const [inputs, setInput] = useState({
        Uid: '',
        password: '',
    });

    const { Uid, password } = inputs;

    const handleChange = (e) => {
        const { name, value } = e.target;
        setInput({ ...inputs, [name]: value });
    };

    const handleSubmit = async (e) => { // async 함수로 변경
        e.preventDefault();

        if (!Uid.trim() || !password.trim()) {
            alert('아이디와 비밀번호를 모두 입력해주세요.');
            return;
        }

        const idValidation = validateId(Uid);
        if (!idValidation.isValid) {
            alert(idValidation.message);
            return;
        }

        const passwordValidation = validatePassword(password);
        if (!passwordValidation.isValid) {
            alert(passwordValidation.message);
            return;
        }

        try {
            const result = await login({ userId: Uid, password }); // 백엔드는 userId를 받으므로 Uid를 userId로 변경
            if (result.success) {
                alert(`환영합니다. ${result.currentUser?.nickname || Uid}님!`); // 닉네임이 없으면 Uid 표시
                navigate('/');
            } else {
                alert(result.message || '로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요.');
            }
        } catch (error) {
            console.error('Login submission error:', error);
            alert('로그인 처리 중 오류가 발생했습니다.');
        }
    };

    return (
        <>
            <Header />
            <AuthWrapper>
                <AuthBox onSubmit={handleSubmit}>
                    <Title>모험가 로그인</Title>

                    <InputGroup>
                        <label>아이디</label>
                        <StyledInput
                            type="text"
                            name="Uid"
                            value={Uid}
                            onChange={handleChange}
                            placeholder="아이디를 입력하세요"
                        />
                    </InputGroup>

                    <InputGroup>
                        <label>비밀번호</label>
                        <StyledInput
                            type="password"
                            name="password"
                            value={password}
                            onChange={handleChange}
                            placeholder="비밀번호를 입력하세요"
                        />
                    </InputGroup>

                    <AuthButton type="submit">길드 입장하기</AuthButton>

                    <LinkText>
                        아직 계정이 없으신가요?
                        <Link to="/signup">모험가 등록</Link>
                    </LinkText>
                </AuthBox>
            </AuthWrapper>
        </>
    );
};

export default Login;