import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import SignUp from './components/SignUp'
import { UserProvider } from './user/UserContext'
import Login from './components/Login';
import Home from './components/Home';


function App() {

  return (
    <UserProvider>
      <BrowserRouter>
        <Routes>
          <Route path = '/' element = {<Home />} />
          <Route path = '/signUp' element = {<SignUp />} />
          <Route path = '/login' element = {<Login />} />
        </Routes>
      </BrowserRouter>
    </UserProvider>
  )
}

export default App
