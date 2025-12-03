import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import SignUp from './components/SignUp'
import Login from './components/Login';
import Home from './components/Home';
import Logout from './components/Logout';
import Mypage from './components/Mypage';
import Board from './components/Board';
import BoardDetail from './components/BoardDetail';
import BoardWrite from './components/BoardWrite';
import Game from './components/Game';

function App() {

  return (
      <BrowserRouter>
        <Routes>
          <Route path = '/' element = {<Home />} />
          <Route path = '/signUp' element = {<SignUp />} />
          <Route path = '/login' element = {<Login />} />
          <Route path = '/logout/:id' element = {<Logout />} />
          <Route path = '/mypage/:id' element = {<Mypage />} />
          <Route path = '/board' element = {<Board />} />
          <Route path = '/boardWrite' element = {<BoardWrite />} />
          <Route path = '/boardDetail/:boardId' element = {<BoardDetail />} />
          <Route path = '/game' element = {<Game />} />  
        </Routes>
      </BrowserRouter>
  )
}

export default App
