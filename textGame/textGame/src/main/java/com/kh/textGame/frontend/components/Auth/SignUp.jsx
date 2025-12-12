import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import useGameStore from '../../store/user';
import Header from '../../layout/Header';
import { AuthWrapper, AuthBox, Title, InputGroup, StyledInput, AuthButton, LinkText } from '../../style/Auth.style';
// 회원가입 시 사용자 입력값 검증 함수들
import { 
    validateId,       // 아이디 유효성 검사 (길이, 특수문자 등)
    validatePassword, // 비밀번호 유효성 검사 (길이, 복잡도)
    validateNickname  // 닉네임 유효성 검사 (길이, 특수문자)
} from '../../utils/validation';

const SignUp = () => {
    const navigator = useNavigate();
    const users  = useGameStore((state) => state.users);
    const addUser = useGameStore((state) => state.addUser);
    
    const [inputs, setInputs] = useState({
        Uid : '', 
        password : '',
        nickname : '',
    });

    const { Uid, password, nickname } = inputs;
    
    const handleChange = (e) => {
        const { name, value } = e.target;
        setInputs({ ...inputs, [name] : value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // 빈 값 체크
        if(!Uid.trim() || !password.trim() || !nickname.trim()){
            alert('모든 항목을 입력해주세요.');
            return;
        }

        // validateId: 아이디 유효성 검사 (4-20자, 영문/숫자만 허용)
        const idValidation = validateId(Uid);
        if (!idValidation.isValid) {
            alert(idValidation.message);
            return;
        }

        // validatePassword: 비밀번호 유효성 검사 (8-20자, 영문+숫자 포함)
        const passwordValidation = validatePassword(password);
        if (!passwordValidation.isValid) {
            alert(passwordValidation.message);
            return;
        }

        // validateNickname: 닉네임 유효성 검사 (2-10자, 한글/영문/숫자 허용)
        const nicknameValidation = validateNickname(nickname);
        if (!nicknameValidation.isValid) {
            alert(nicknameValidation.message);
            return;
        }

        // users에서 find 함수를 통해 동일한 Uid 또는 nickname이 있는지 확인
        const isIdExist = users.find(u => u.Uid === Uid);
        const isNickExist = users.find(u => u.nickname === nickname);

        // 중복된 아이디 혹은 닉네임이 존재할 경우, 각각 알림 후 종료
        if(isIdExist) {
            alert('이미 존재하는 아이디입니다.');
            return;
        }
        if(isNickExist) {
            alert('이미 존재하는 닉네임입니다.');
            return;
        }

        // store의 addUser 함수에 inputs 객체를 넘겨 새 유저 등록
        addUser(inputs);

        alert('모험가 등록이 완료되었습니다! 로그인해주세요.');
        navigator('/login');
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
    )
}

export default SignUp;
