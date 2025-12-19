import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import useGameStore from '../../store/user';
import Header from '../../layout/Header';
import { AuthWrapper, AuthBox, Title, InputGroup, StyledInput, AuthButton, LinkText } from './Auth.style';
import {
    validateId,
    validatePassword,
    validateNickname
} from '../../utils/validation';

const SignUp = () => {
    const navigate = useNavigate();
    const signup = useGameStore((state) => state.signup); // Zustand store의 signup 액션 사용

    const [inputs, setInputs] = useState({
        Uid: '',
        password: '',
        nickname: '',
    });

    const { Uid, password, nickname } = inputs;

    const handleChange = (e) => {
        const { name, value } = e.target;
        setInputs({ ...inputs, [name]: value });
    };

    const handleSubmit = async (e) => { // async 함수로 변경
        e.preventDefault();

        if (!Uid.trim() || !password.trim() || !nickname.trim()) {
            alert('모든 항목을 입력해주세요.');
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

        const nicknameValidation = validateNickname(nickname);
        if (!nicknameValidation.isValid) {
            alert(nicknameValidation.message);
            return;
        }

        try {
            const result = await signup({ userId: Uid, password, nickname }); // 백엔드는 userId를 받음
            if (result.success) {
                alert('모험가 등록이 완료되었습니다! 로그인해주세요.');
                navigate('/login');
            } else {
                alert(result.message || '회원가입에 실패했습니다.');
            }
        } catch (error) {
            console.error('Signup submission error:', error);
            alert('회원가입 처리 중 오류가 발생했습니다.');
        }
    };

    return (
        <>
            <Header />
            <AuthWrapper>
                <AuthBox onSubmit={handleSubmit}>
                    <Title>모험가 등록</Title>

                    <InputGroup>
                        <label>아이디</label>
                        <StyledInput
                            type='text'
                            name='Uid'
                            value={Uid}
                            onChange={handleChange}
                            placeholder="사용할 아이디"
                        />
                    </InputGroup>

                    <InputGroup>
                        <label>비밀번호</label>
                        <StyledInput
                            type='password'
                            name='password'
                            value={password}
                            onChange={handleChange}
                            placeholder="사용할 비밀번호"
                        />
                    </InputGroup>

                    <InputGroup>
                        <label>닉네임 (모험가 이름)</label>
                        <StyledInput
                            type='text'
                            name='nickname'
                            value={nickname}
                            onChange={handleChange}
                            placeholder="게임에서 쓸 이름"
                        />
                    </InputGroup>

                    <AuthButton type="submit">등록 완료</AuthButton>

                    <LinkText>
                        이미 계정이 있으신가요?
                        <Link to="/login">로그인 하러 가기</Link>
                    </LinkText>
                </AuthBox>
            </AuthWrapper>
        </>
    );
};

export default SignUp;
