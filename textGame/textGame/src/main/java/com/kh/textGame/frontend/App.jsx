import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Home from './components/Home'
import SignUp from './components/Auth/SignUp'
import Login from './components/Auth/Login'
import Logout from './components/Logout'
import Mypage from './components/Mypage'
import Board from './components/Board'
import BoardDetail from './components/BoardDetail'
import BoardWrite from './components/BoardWrite'
import BoardEdit from './components/BoardEdit'
import Game from './components/Game'
import Skill from './components/Skill'
import Item from './components/Item'
import PrivateRoute from './components/PrivateRoute'
import NotFound from './components/NotFound'

function App() {

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
  )
}

export default App
