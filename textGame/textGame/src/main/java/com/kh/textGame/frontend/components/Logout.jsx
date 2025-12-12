import React, { useEffect } from 'react'
import useGameStore from '../store/user'
import { useNavigate } from 'react-router-dom';

const Logout = () => {
    // 이동을 위한 navigator생성
    const navigator = useNavigate();
    // store에서 logout을 호출
    const logout = useGameStore(state => state.logout);
    
    // useEffect를 사용해 리렌더링 없이 작동하도록 함.
    useEffect(() => {
        // logout 작동
        logout();
        alert('로그아웃 되었습니다.');
        navigator('/');
    }, []) // []를 사용해 마운트 하자마자 즉시 발동 
    

  return null;
}

export default Logout