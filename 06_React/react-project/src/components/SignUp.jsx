import React, { useContext, useState } from 'react'
import { UserContext } from '../user/UserContext';
import { Button, CheckId, CheckNickName, Container, InputBox, InputContainer, LinkStyle } from '../layout/check';
import { useNavigate } from 'react-router-dom';

const SignUp = () => {
    
    const navigator = useNavigate();

    const { addUser, checkId, checkNickName } = useContext(UserContext);
    const [inputs, setInputs] = useState({
        id : '',
        password : '',
        nickname : '',
    });

    const { id, password, nickname } = inputs;
    
    const [isCheckedId, setIsCheckedId] = useState(false);
    const [ischeckedNickName, setIsCheckedNickName] = useState(false);



    const handleChange = (e) => {
        const { name, value } = e.target;
        setInputs({
            ...inputs,
            [name] : value,
        });

        

    const checkSignId = checkId(e.target.value);
    const checkSignNickName = checkNickName(e.target.value);
        
        
        if(name === 'id'){
            if(checkSignId){
                if(value.length === 0){
                    setIsCheckedId(false);
                    return;
                }
                setIsCheckedId(false);
            }else{
                setIsCheckedId(true);
            }
     }   
        
        if(name === 'nickname'){
            if(checkSignNickName){
                if(value.length === 0){
                    setIsCheckedNickName(false);
                    return;
                }
                setIsCheckedNickName(false);
            }else{
                setIsCheckedNickName(true);
            }
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const isAnyEmpty = Object.values(inputs).includes('');

        if(isAnyEmpty){
            alert('모든 항목을 입력해주십시오.')
            return;
        }

        if(!checkId || !checkNickName){
            alert('중복 확인이 필요합니다.')
            return;
        }

        addUser(inputs);

        alert('회원가입이 완료되었습니다.')
        navigator('/login');
    };

    return (
    <form onSubmit={handleSubmit}>
        
    <Container>
            <InputContainer><div>아이디</div> <div><InputBox type = 'text' onChange={handleChange} name = 'id' value = {id} placeholder='id를 입력하세요.'></InputBox></div><CheckId $isCheckedId={isCheckedId} /></InputContainer>
            <InputContainer><div>비밀번호</div> <div><InputBox type = 'password' onChange={handleChange} name = 'password' value = {password} placeholder='비밀번호를 입력하세요.'></InputBox></div></InputContainer>
            <InputContainer><div>닉네임 </div><div><InputBox type = 'text' onChange={handleChange} name = 'nickname' value = {nickname} placeholder='닉네임을 입력하세요.'></InputBox></div><CheckNickName $isCheckedNickName={ischeckedNickName} /></InputContainer>
            <InputContainer><Button>회원가입</Button></InputContainer>
            <InputContainer><LinkStyle to = '/login'>로그인</LinkStyle></InputContainer>
    </Container>
    </form>
    )
}

export default SignUp