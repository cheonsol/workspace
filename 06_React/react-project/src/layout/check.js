import { Link } from "react-router-dom";
import styled from "styled-components";

export const Container = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap : 20px;
    border-radius: 24px;
    background-color: gray;
    min-width : 400px;
    min-height : 300px;
`
export const InputContainer = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: start;
    gap : 10px;
`

export const LinkStyle = styled(Link)`
    text-decoration: none;
    color: white;
    font-size: 12px;
    font-weight: 400;

    &:hover{
        color : red;
    }
`

export const InputBox = styled.input`
    border-style: none;
`

export const Button = styled.button`
    border-style: none;
    border : 1px solid black;
`

export const CheckId = styled.div`
    width : 10px;
    height : 10px;
    background-color : ${(props) => props.$isCheckedId ? '#21ff3eff' : 'red'};
    border : 1px solid black;
    border-radius : 50%;
    display: inline-block;
    vertical-align: middle;
`
export const CheckNickName = styled.div`
    background-color : ${(props) => props.$isCheckedNickName ? '#21ff3eff' : 'red'};
    width : 10px;
    height : 10px;
    border : 1px solid black;
    border-radius : 50%;
    display: inline-block;
    vertical-align: middle;
 `