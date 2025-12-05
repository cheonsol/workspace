import React from 'react';
import styled from 'styled-components';

const NotFoundContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background: #222;
  color: #fff;
`;

const Title = styled.h1`
  font-size: 3rem;
  margin-bottom: 1rem;
`;

const Message = styled.p`
  font-size: 1.2rem;
  margin-bottom: 2rem;
`;

const NotFound = () => (
  <NotFoundContainer>
    <Title>404</Title>
    <Message>페이지를 찾을 수 없습니다.</Message>
    <a href="/" style={{ color: '#61dafb', fontSize: '1.1rem' }}>홈으로 돌아가기</a>
  </NotFoundContainer>
);

export default NotFound;
