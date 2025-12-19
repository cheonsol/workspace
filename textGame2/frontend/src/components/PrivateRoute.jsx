import React from 'react'
import useGameStore from '../store/user'
import { Navigate, Outlet } from 'react-router-dom';

const PrivateRoute = () => {
    const user = useGameStore((state) => state.currentUser);
    // const notUser = () => {
    //     alert('로그인이 필요한 서비스입니다.');
        
    // }

    
  return user ? <Outlet /> : <Navigate to ="/login" replace />
    //Outlet = 하위의 컴포넌트로 이동
    
}

export default PrivateRoute