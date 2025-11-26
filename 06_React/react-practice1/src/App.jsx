import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ProfileCard from './components/ProfileCard'

const profile = [{
  id : 1,
  name : "박민수",
  age : 19,
  isOnline : true,
},
{
  id : 2,
  name : "김철수",
  age : 22,
  isOnline : true,
},
{
  id : 3,
  name : "강수현",
  age : 30,
  isOnline : false,
}]
function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      {profile.map(p => <ProfileCard key={p.id} p={p} />)}
    </>
  )
}

export default App
