// ... 기존 ProfileCard 등등 위쪽 코드 유지 ...

import { Link } from "react-router-dom";
import styled from "styled-components";

// --- 아래 내용 추가 ---

// 메인 히어로 섹션 (화면 꽉 차게)
export const HeroSection = styled.div`
  height: 90vh; // 헤더 제외하고 화면 꽉 차게
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: radial-gradient(circle at center, #2a2a2a 0%, #000000 100%); // 배경 그라데이션
  text-align: center;
`;

// 메인 타이틀
export const HeroTitle = styled.h1`
  font-size: 4rem;
  font-weight: 900;
  color: #fff;
  margin-bottom: 1rem;
  text-shadow: 0 0 20px rgba(212, 175, 55, 0.5); // 금색 빛 번짐

  span {
    color: #d4af37; // 금색 포인트
    display: block;
    font-size: 1.5rem;
    font-weight: 500;
    margin-bottom: 10px;
    letter-spacing: 5px;
  }
`;

// 게임 시작 버튼
export const StartButton = styled(Link)`
  margin-top: 3rem;
  padding: 1.2rem 3rem;
  font-size: 1.5rem;
  font-weight: bold;
  color: #000;
  background-color: #d4af37; // 금색 배경
  border-radius: 50px;
  text-decoration: none;
  box-shadow: 0 0 20px rgba(212, 175, 55, 0.6); // 버튼 빛남
  transition: all 0.3s ease;

  &:hover {
    background-color: #fff;
    transform: scale(1.1); // 커짐
    box-shadow: 0 0 40px rgba(255, 255, 255, 0.8);
  }
`;

// 설명 텍스트
export const Description = styled.p`
  color: #aaa;
  font-size: 1.1rem;
  max-width: 600px;
  line-height: 1.6;
`;