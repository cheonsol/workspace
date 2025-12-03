import React from 'react';
import Header from '../layout/Header';
import useGameStore from '../store/store';
import { HeroSection, HeroTitle, StartButton, Description } from '../style/Home.style'; // 추가한 스타일 import

const Home = () => {
  const currentUser = useGameStore((state) => state.currentUser);

  return (
    <>
      <Header />
      
      <HeroSection>
        <HeroTitle>
          <span>TEXT RPG PROJECT</span>
          THE ADVENTURER'S<br />GUILD
        </HeroTitle>

        <Description>
          어둠이 깔린 던전, 당신을 기다리는 몬스터들.<br />
          지금 바로 모험가 길드에 가입하여 전설이 되어보세요.<br />
          당신의 선택이 운명을 결정합니다.
        </Description>

        {/* 로그인 여부에 따라 버튼 이동 경로 다르게 */}
        {currentUser ? (
             <StartButton to="/game">모험 계속하기 ▶</StartButton>
        ) : (
             <StartButton to="/login">모험 시작하기 (로그인) ▶</StartButton>
        )}
      </HeroSection>
    </>
  );
};

export default Home;