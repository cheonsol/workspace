import { BrowserRouter, Route, Routes } from 'react-router-dom';
import React, { useEffect } from 'react'; // useEffect 추가
import useGameStore from './store/user'; // useGameStore 임포트
import './App.css';
import Home from './pages/Home';
import SignUp from './pages/SignUp';
import Login from './pages/Login';
import Logout from './components/Logout';
import Mypage from './pages/Mypage';
import Board from './pages/Board';
import BoardDetail from './pages/BoardDetail';
import BoardWrite from './pages/BoardWrite';
import BoardEdit from './pages/BoardEdit';
import Game from './pages/Game';
import Skill from './pages/Skill';
import Item from './pages/Item';
import PrivateRoute from './components/PrivateRoute';
import NotFound from './pages/NotFound';

function App() {
  const initializeAuth = useGameStore((state) => state.initializeAuth);

  useEffect(() => {
    initializeAuth();
  }, [initializeAuth]);

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/signUp' element={<SignUp />} />
        <Route path='/login' element={<Login />} />
        <Route path='/logout' element={<Logout />} />
        <Route element={<PrivateRoute />}>
          <Route path='/mypage/:id' element={<Mypage />} />
          <Route path='/board' element={<Board />} />
          <Route path='/boardWrite' element={<BoardWrite />} />
          <Route path='/boardDetail/:boardId' element={<BoardDetail />} />
          <Route path='/boardEdit/:boardId' element={<BoardEdit />} />
          <Route path='/skill' element={<Skill />} />
          <Route path='/item' element={<Item />} />
          <Route path='/game' element={<Game />} />
        </Route>
        {/* 404 Not Found Route */}
        <Route path='/*' element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
