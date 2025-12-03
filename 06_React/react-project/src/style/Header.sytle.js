import styled from 'styled-components';
import { Link } from 'react-router-dom';

// 헤더 전체 틀 (상단 고정 + 반투명 효과)
export const HeaderWrapper = styled.header`
  width: 100%;
  height: 70px;
  background-color: rgba(20, 20, 20, 0.85); // 뒤가 살짝 비치는 어두운 색
  backdrop-filter: blur(10px); // 유리처럼 뿌옇게 처리
  border-bottom: 1px solid #333; // 하단 경계선
  display: flex;
  justify-content: space-between; // 로고와 메뉴 양끝 정렬
  align-items: center;
  padding: 0 2rem;
  position: sticky; // 스크롤 내려도 상단에 붙어있음
  top: 0;
  z-index: 1000; // 다른 요소보다 위에 오도록
`;

// 왼쪽 게임 이름 (로고)
export const Logo = styled(Link)`
  font-size: 1.5rem;
  font-weight: 900;
  color: #fff;
  text-decoration: none;
  letter-spacing: 2px;
  
  span {
    color: #d4af37; // 'Guild' 부분만 금색
  }

  &:hover {
    text-shadow: 0 0 10px rgba(212, 175, 55, 0.5);
  }
`;

// 오른쪽 메뉴 리스트 (ul)
export const NavList = styled.ul`
  display: flex;
  gap: 30px; // 메뉴 사이 간격
  list-style: none;
  margin: 0;
  padding: 0;

  @media (max-width: 768px) {
    gap: 15px; // 모바일에서는 간격 좁히기
    font-size: 0.9rem;
  }
`;

// 각 메뉴 링크 (Link 커스텀)
export const StyledLink = styled(Link)`
  text-decoration: none;
  color: #b0b0b0; // 기본은 회색
  font-weight: 600;
  font-size: 1rem;
  transition: all 0.3s ease;
  padding: 8px 12px;
  border-radius: 8px;

  // 마우스 올렸을 때 효과
  &:hover {
    color: #fff; // 글자는 흰색
    background-color: rgba(212, 175, 55, 0.15); // 배경은 연한 금색
    text-shadow: 0 0 5px #d4af37; // 금색 빛남
  }
`;