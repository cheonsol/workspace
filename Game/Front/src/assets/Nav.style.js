import styled from 'styled-components';

const Nav = styled.nav`
    width: 100%;
    height: 70px;
    background-color: #1a1a1a;
    color: #e0e0e0;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 40px;
    box-sizing: border-box;
    border-bottom: 2px solid #d4af37;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
    position: sticky;
    top: 0;
    z-index: 1000;
`;

export const Logo = styled.div`
    font-size: 1.5rem;
    font-weight: bold;
    color: #d4af37;
    cursor: pointer;
    letter-spacing: 2px;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 1);

    &:hover {
        color: #fff;
        transition: 0.3s;
    }
`;

export const MenuGroup = styled.div`
    display: flex;
    gap: 30px;
    align-items: center;
`;

export const MenuItem = styled.span`
    font-size: 1rem;
    font-weight: 500;
    cursor: pointer;
    position: relative;
    transition: 0.3s;

    &:hover {
        color: #d4af37;
    }

    /* 호버 시 아래 줄이 생기는 효과 */
    &:after {
        content: '';
        position: absolute;
        width: 0;
        height: 2px;
        bottom: -5px;
        left: 0;
        background-color: #d4af37;
        transition: 0.3s;
    }

    &:hover:after {
        width: 100%;
    }
`;
export default Nav;