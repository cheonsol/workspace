import React from 'react';
import Header from '../layout/Header';
import useGameStore from '../store/user';
import { HeroSection, HeroTitle, StartButton, Description } from '../style/Home.style'; // 추가한 스타일 import

const Home = () => {
  const currentUser = useGameStore((state) => state.currentUser);

  // localStorage 초기화 함수 (개발자용)
  const clearStorage = () => {
    localStorage.clear();
    alert('localStorage가 초기화되었습니다. 페이지를 새로고침해주세요.');
    window.location.reload();
  };

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

        {/* 개발자 용 버튼 */}
        <button 
          onClick={clearStorage}
          style={{
            marginTop: '40px',
            padding: '10px 20px',
            backgroundColor: '#666',
            color: '#fff',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
            fontSize: '0.9rem'
          }}
        >
          🔧 localStorage 초기화 (테스트용)
        </button>
      </HeroSection>
    </>
  );
};

export default Home;