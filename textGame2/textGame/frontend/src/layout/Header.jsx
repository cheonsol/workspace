import React from 'react';
import useGameStore from '../store/user';
import { HeaderWrapper, Logo, NavList, StyledLink } from '../style/Header.style';
// 방금 만든 스타일 컴포넌트들 import


const Header = () => {

  const currentUser = useGameStore((state) => state.currentUser);

  return (
    <HeaderWrapper>
        {/* 1. 왼쪽: 로고 (클릭하면 홈으로) */}
        <Logo to="/">
            ADVENTURER <span>GUILD</span>
        </Logo>

        {/* 2. 오른쪽: 네비게이션 메뉴 */}
        <nav>
            <NavList>
                <li><StyledLink to='/'>홈</StyledLink></li>
                <li><StyledLink to='/game'>던전</StyledLink></li>
                <li><StyledLink to='/board'>의뢰소(게시판)</StyledLink></li>

                {/* 로그인 상태에 따른 메뉴 변화 */}
                {currentUser ? (
                    <>
                        {/* 로그인 했을 때 */}
                        <li>
                            {/* 마이페이지: ID보다는 Uid를 주로 쓰지만, 작성하신 id 그대로 유지 */}
                            <StyledLink to={`/mypage/${currentUser.Uid}`}>내 정보</StyledLink>
                        </li>
                        <li>
                            <StyledLink to={`/Logout`}>로그아웃</StyledLink>
                        </li>
                    </>
                ) : (
                    <>
                        {/* 로그인 안 했을 때 */}
                        <li><StyledLink to='/signUp'>등록(가입)</StyledLink></li>
                        <li><StyledLink to='/Login'>로그인</StyledLink></li>
                    </>
                )}
            </NavList>
        </nav>
    </HeaderWrapper>
  )
}

export default Header;