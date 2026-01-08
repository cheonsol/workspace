import { useState } from 'react'
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import MainPage from './Pages/MainPage';
import GamePage from './Pages/GamePage';
import Ranking from './Pages/Ranking';



function App() {
  const [count, setCount] = useState(0)

  return (

  <BrowserRouter>
    <Routes>
       <Route path="/" element={<MainPage />} />
       <Route path="/game" element={<GamePage />} />
       <Route path="/ranking" element={<Ranking />} />
    </Routes>
  </BrowserRouter>
  )
}

export default App
