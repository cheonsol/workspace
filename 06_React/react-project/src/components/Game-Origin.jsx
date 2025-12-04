import React, { useEffect, useState } from 'react'
import useGameStore from '../store/user'
import useMonsterStore from '../store/monster'
import { useNavigate } from 'react-router-dom';

const Game = () => {

  const user = useGameStore((state) => state.currentUser)
  const monster = useMonsterStore((state) => state.monsters)
  const currentMonster = monster.find(m => m.floor === user.floor)
  const [monsterHp,setMonsterHp] = useState(currentMonster.currentHp)
  const [userHp,setUserHp] = useState(user.currentHp)
  // user?는 undefinded인가 아닌가를 찾아 구분한다.
  const [floor,setFloor] = useState(Number(user?.floor || 0))
  const navigator = useNavigate();
  const [turn, setTurn] = useState(true);
  

  // useEffect = () => {
    

  // },[monster]

  const userDamage = (Math.random() * 100 < user.luk ? Math.floor(user.atk * 2) : Math.floor(user.atk))
  const monsterDamage = (Math.random() * 100 < currentMonster.luk ? Math.floor(currentMonster.atk * 2) : Math.floor(currentMonster.atk))
    

  const attck = () => {
      setMonsterHp(monsterHp - userDamage);
      setTurn(false);
      if(monsterHp <= 0){
        return(
        <div>{currentMonster.name}을 잡았다.</div>
        )
      } else
      return(
        <div>{user.nickname}의 공격! {userDamage}의 피해를 입혔다.</div>
      )
  }

  const monsterTurn = () => {
      setTurn(true);
      setUserHp(userHp - monsterDamage);
      return(
      <div>{currentMonster.name}의 공격! {monsterDamage}의 피해를 입혔다.</div>
      )
  }

  return (
    
    <div>
      {console.log(turn)}
          <div>
            <ul>
              <li>현재 층 : {floor}</li>
              <li>몬스터 : {currentMonster.name}</li>
              <li>몬스터 체력 : {monsterHp}</li>
              <li>내 체력 : {userHp}</li>
              <li>내 마나 : {user.currentMp}</li>
            </ul>
            <div>
            {turn ?
            <div>
              <p>무엇을 할까?</p>
              <button onClick={attck}>공격</button>
              <button onClick = {() => navigator('/')}>도망</button>
            </div>
            :
            monsterTurn()
            }
          </div></div>
    </div>
  )
} 

export default Game