import { useState } from 'react'
import './App.css'
import Scene from './components/Scene'
import Status from './components/Status'

function App() {

  const [turn, setTurn] = useState(1);
  const [history, setHistory] = useState(1);

    const status = [{
        id : 1,
        maxHp : 100,
        currentHp : 100,
        hungry : 0,
        temperature : 36.5,
    }]

    const scene = [{
        id : 1,
        text : `눈을 떠보니 어둠이 가득합니다.`,
        select : `1 : 주변을 둘러본다. 2 : 잠을 잔다.`,
    },
     {
        id : 2,
        text : `눅눅한 습기가 느껴지고 오돌토돌한 감촉이 느껴집니다. 동굴인 듯 합니다.`,
        select : `1 : 잠을 잔다. 2 : 주변을 돌아다닌다.`,
    },{
        id : 3,
        text : `당신은 드러누워 잠을 청했습니다. 바닥이 불편하게 느껴집니다. 얼마나 지났을까요? 온 몸에 소름이 돋으며 당신은 황급히 눈을 뜹니다. 눈앞에 괴물이 보입니다.`,
        select : `1 : 싸운다. 2 : 도망친다.`,
    }]

    const currentScene = scene.find(s => s.id === history);
    const currentStatus = status.find(h => h.id === turn);
  
    return (
    <>
     <Scene scene = {currentScene} />
     <Status status = {currentStatus}/>
    </>
  )
}

export default App
