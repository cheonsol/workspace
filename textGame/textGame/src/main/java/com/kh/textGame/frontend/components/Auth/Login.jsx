import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import useGameStore from '../../store/user';
import Header from '../../layout/Header';
import { AuthWrapper, AuthBox, Title, InputGroup, StyledInput, AuthButton, LinkText } from '../../style/Auth.style';
// 로그인 시 사용자 입력값 검증 함수들
import { 
    validateId,       // 아이디 유효성 검사
    validatePassword  // 비밀번호 유효성 검사
} from '../../utils/validation';

const Login = () => {
  // navigator 생성.
  // uesNavigate : 페이지 이동 함수
  // navigate와의 차이점.
  // navigate : 실행 즉시 이동
  // useNavigate : 함수 형태로 반환, 필요할 때 실행하여 이동.
  const navigator = useNavigate();
  // zustand : useContext 대신 사용하는 개념.
  // 전역함수를 관리.
  // store를 만듬. 이때, use로 시작하는 훅을 만듬
  // state로 저장해 원하는 값만 불러올 수 있음.
  const users  = useGameStore((state) => state.users);
  const login = useGameStore((state) => state.login);

  // 디버깅: 현재 users 배열 확인
  console.log('현재 등록된 사용자:', users);

  // 입력값을 관리하는 state
  // inputs 객체로 관리하고 setInput을 통해 변환
  // 사용이유는 state를 통한 리렌더링
  // react는 prors와 state가 변할 때마다 리렌더링이 발생
  const [inputs, setInput] = useState({
    Uid : '',
    password : '',
  });

  // 구조 분해 할당 : inputs에서 Uid와 password를 꺼내옴
  const { Uid, password } = inputs;

  const handleChange = (e) => {
    // input에 값을 입력했을 때, name과 value를 가져옴
    // 원래 있던 inputs 객체를 복사하고, 스프레드로 펼친 뒤, name을 key로 하여 value를 덮어씀
    // 객체를 다루는 것이기 때문에 {}를 사용.
    // 불변성 유지 : 기존 객체를 직접 수정하지 않고, 새로운 객체를 만들어 변경사항을 반영
    const {name, value} = e.target;
    setInput({ ...inputs, [name] : value })
  }  

  const handleSubmit = (e) => {
    e.preventDefault();

    // 빈 값 체크
    if(!Uid.trim() || !password.trim()){
        alert('아이디와 비밀번호를 모두 입력해주세요.');
        return;
    }

    // validateId: 아이디 유효성 검사
    const idValidation = validateId(Uid);
    if (!idValidation.isValid) {
        alert(idValidation.message);
        return;
    }

    // validatePassword: 비밀번호 유효성 검사
    const passwordValidation = validatePassword(password);
    if (!passwordValidation.isValid) {
        alert(passwordValidation.message);
        return;
    }

    // 입력한 id를 find 함수를 통해 users 배열에서 찾음.
    const findUser = users.find(u => u.Uid === Uid);

    // findUser가 false일 경우, 즉, 존재하지 않는 id일 경우
    if(!findUser){
      alert('존재하지 않는 모험가(ID)입니다.');
      return;
    }
    // findUser가 존재하지만, 비밀번호가 일치하지 않을 경우
    if(findUser.password !== password){
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }

    // findUser를 매개변수로 하여 zustand의 login 함수를 실행
    login(findUser); 
    // 로그인 후, 환영메세지를 출력하고 메인 페이지로 이동
    alert(`환영합니다. ${findUser.nickname}님!`); 
    navigator('/');
  }

  // 실제 화면에 표시되는 항목
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
  )
}

export default Login;