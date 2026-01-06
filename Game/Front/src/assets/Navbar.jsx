import React from 'react'
import Nav, { Logo, MenuGroup, MenuItem } from './Nav.style';

const Navbar = () => {
  return (
        <Nav>
            <Logo onClick={() => navigate('/')}>
                TEXT RPG
            </Logo>

            <MenuGroup>
                <MenuItem onClick={() => navigate('/')}>홈</MenuItem>
                <MenuItem onClick={() => navigate('/game')}>모험하기</MenuItem>
                <MenuItem onClick={() => navigate('/board')}>게시판</MenuItem>
                <MenuItem onClick={() => navigate('/login')}>로그인</MenuItem>
            </MenuGroup>
        </Nav>
    );
}

export default Navbar;