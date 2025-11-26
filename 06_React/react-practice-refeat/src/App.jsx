import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Profile from './components/Profile'

const profile = [{
  id : 1,
  name : "박민수",
  age : 19,
  isOnline : true,
},
{
  id : 2,
  name : "김철수",
  age : 30,
  isOnline : false,
},
{
  id : 3,
  name : "유재식",
  age : 22,
  isOnline : false,
}]

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      {profile.map(p => <Profile key = {p.id} p = {p}/>)}
    </>
  )
}

export default App
