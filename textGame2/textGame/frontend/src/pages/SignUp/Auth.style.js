import styled from 'styled-components';
import { Link } from 'react-router-dom';

// 화면 중앙 정렬 컨테이너
export const AuthWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 70px); // 헤더 높이 빼고 중앙
  background-color: #121212;
`

// 로그인/회원가입 박스
export const AuthBox = styled.form`
  width: 400px;
  padding: 3rem;
  background-color: #1e1e1e;
  border: 1px solid #333;
  border-radius: 16px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.7);
  text-align: center;
  border-top: 3px solid #d4af37; // 상단 금색 포인트
`

// 제목
export const Title = styled.h2`
  color: #d4af37;
  font-size: 2rem;
  margin-bottom: 2rem;
  font-weight: bold;
`

// 입력창 그룹
export const InputGroup = styled.div`
  margin-bottom: 1.5rem;
  text-align: left;

  label {
    display: block;
    color: #aaa;
    margin-bottom: 8px;
    font-size: 0.9rem;
  }
`

// 실제 입력칸 (Input)
export const StyledInput = styled.input`
  width: 100%;
  padding: 12px;
  background-color: #2a2a2a;
  border: 1px solid #444;
  border-radius: 8px;
  color: white;
  font-size: 1rem;
  outline: none;
  transition: all 0.2s;

  &:focus {
    border-color: #d4af37; // 포커스 되면 금색 테두리
    box-shadow: 0 0 8px rgba(212, 175, 55, 0.3);
  }

  &::placeholder {
    color: #555;
  }
`

// 확인 버튼
export const AuthButton = styled.button`
  width: 100%;
  padding: 12px;
  margin-top: 1rem;
  background-color: #d4af37;
  color: #000;
  font-weight: bold;
  font-size: 1.1rem;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.2s;

  &:hover {
    background-color: #fff; // 호버시 하얗게
    transform: translateY(-2px);
  }
`

// 하단 링크 (회원가입하러 가기 등)
export const LinkText = styled.p`
  margin-top: 1.5rem;
  color: #777;
  font-size: 0.9rem;

  a {
    color: #d4af37;
    margin-left: 5px;
    text-decoration: underline;
    
    &:hover {
      color: #fff;
    }
  }
`