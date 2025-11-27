import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import UseStateTest from './components/useState/UseStateTest'
import Signup from './components/useState/Signup'
import LandingPage from './components/useState/LandingPage'
import UseRefTest from './components/useRef/UseRefTest'
import UseRefScroll from './components/useRef/UseRefScroll'
import UseMemoTest from './components/useMemo/UseMemoTest'
import UseCallbackTest from './components/useCallback/UseCallbackTest'
import UseEffectView from './components/useEffect/UseEffectView'
import UseContextTest from './components/useContext/UseContextTest'
import MyInfo from './components/customHook/MyInfo'
import ToggleBox from './components/customHook/ToggleBox'
import { UserProvider } from './components/useContext/UserContext'
import Header from './components/useContext/Header'

function App() {

  return (
    <>
      {/* <UseStateTest /> */}
      {/* <Signup /> */}
      {/* <LandingPage /> */}
      {/* <UseRefTest /> */}
      {/* <UseRefScroll /> */}
      {/* <UseMemoTest /> */}
      {/* <UseCallbackTest /> */}
      {/* <UseEffectView /> */}
      {/* <UseContextTest /> */}
      {/* <MyInfo /> */}
      {/* <ToggleBox /> */}
      <UserProvider>
        <Header />
      </UserProvider>

    </> 
  )
}

export default App
