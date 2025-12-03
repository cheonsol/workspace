import styled, { keyframes } from 'styled-components';

// 등장 애니메이션
const fadeIn = keyframes`
  from { opacity: 0; transform: translateY(20px); }
  to { opacity: 1; transform: translateY(0); }
`;

// 전체 페이지 배경 및 레이아웃
export const PageWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  background-color: #121212; // 아주 어두운 배경
  color: #e0e0e0;
  padding-top: 3rem;
`;

// 캐릭터 정보 카드 (메인 박스)
export const ProfileCard = styled.div`
  width: 100%;
  max-width: 600px;
  background: #1e1e1e; // 카드 배경
  border: 1px solid #333;
  border-radius: 16px;
  padding: 2.5rem;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5); // 그림자
  animation: ${fadeIn} 0.6s ease-out;
  border-top: 3px solid #d4af37; // 상단 금색 포인트
`;

// 제목 (닉네임 등)
export const CardTitle = styled.h2`
  text-align: center;
  font-size: 2rem;
  color: #d4af37; // 금색 텍스트
  margin-bottom: 2rem;
  font-weight: bold;
  letter-spacing: 1px;
  
  span {
    font-size: 1rem;
    color: #888;
    font-weight: normal;
    display: block;
    margin-top: 0.5rem;
  }
`;

// 스탯 리스트 (Grid 레이아웃)
export const StatGrid = styled.ul`
  list-style: none;
  padding: 0;
  display: grid;
  grid-template-columns: repeat(2, 1fr); // 2열 배치
  gap: 1rem; // 간격

  @media (max-width: 500px) {
    grid-template-columns: 1fr; // 모바일에서는 1열
  }
`;

// 개별 스탯 박스
export const StatItem = styled.li`
  background: #2a2a2a;
  padding: 1rem 1.5rem;
  border-radius: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid transparent;
  transition: all 0.2s;

  &:hover {
    border-color: #d4af37; // 호버 시 금색 테두리
    transform: translateY(-2px);
    background: #333;
  }

  // 스탯 이름 (왼쪽)
  .label {
    color: #aaa;
    font-size: 0.9rem;
  }

  // 스탯 수치 (오른쪽)
  .value {
    color: #fff;
    font-weight: bold;
    font-size: 1.1rem;
  }
  
  // 강조해야 할 스탯 (공격력 등) 색상 변경
  &.highlight .value {
    color: #ff6b6b; // 붉은색
  }
`;

// 로그인 안 했을 때 안내 박스
export const LoginPrompt = styled.div`
  text-align: center;
  padding: 4rem 2rem;
  border: 2px dashed #444;
  border-radius: 16px;
  color: #777;
  font-size: 1.2rem;
  background: #1a1a1a;
  
  p {
    margin-bottom: 1rem;
  }
`;
