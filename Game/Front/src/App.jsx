import { useState } from 'react'
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import MainPage from './Pages/MainPage';
import Signup from './Pages/Signup';
import Login from './Pages/Login';
import Mypage from './Pages/Mypage';


function App() {
  const [count, setCount] = useState(0)

  return (

  <BrowserRouter>
    <Routes>
       <Route path="/" element={<MainPage />} />
       <Route path="/login" element={<Login />} />
       <Route path="/signup" element={<Signup />} />
       <Route path="/mypage" element={<Mypage />} />
    </Routes>
  </BrowserRouter>
  )
}

export default App
