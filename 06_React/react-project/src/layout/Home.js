import styled from "styled-components";
import { Link } from "react-router-dom";


export const Nav = styled.ul`
    top: 0;
    width : 100%;
    flex-direction: row;
    list-style: none;
    align-items: center;
    justify-content: center;
    gap : 30px;
    z-index: 1000;
    margin : 0px;
    padding : 0px;
    left : 0px;
    position : fixed;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    gap : 30px;
    background-color: #cacacaff;
    height: 50px;
`

export const LinkStyle = styled(Link)`
    text-decoration: none;
    color: black;
    font-weight: 500;
`

export const Container = styled.div`
    padding-top : 50px;
`