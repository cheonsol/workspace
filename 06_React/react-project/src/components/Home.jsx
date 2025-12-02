import React from 'react'
import { Container, LinkStyle, Nav } from './../layout/Home';

const Home = () => {
  return (
    <div>
        <Nav>
                <li><LinkStyle to = '/'>홈</LinkStyle></li>
                <li><LinkStyle to = '/game'>게임</LinkStyle></li>
                <li><LinkStyle to = '/board'>게시판</LinkStyle></li>
                <li><LinkStyle to = '/signUp'>회원가입</LinkStyle></li>
                <li><LinkStyle to = '/login'>로그인</LinkStyle></li>
        </Nav>
        <Container>
            123
        </Container>
    </div>
  )
}

export default Home