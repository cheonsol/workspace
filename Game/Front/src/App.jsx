import { useState } from 'react'
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import MainPage from './Pages/MainPage';
import Signup from './Pages/Signup';
import Login from './Pages/Login';


function App() {
  const [count, setCount] = useState(0)

  return (

  <BrowserRouter>
    <Routes>
       <Route path="/" element={<MainPage />} />
       <Route path="/login" element={<Login />} />
       <Route path="/signup" element={<Signup />} />
    </Routes>
  </BrowserRouter>
  )
}

export default App
