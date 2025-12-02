import React, { createContext, useState } from 'react'
import { date } from './../../node_modules/zod/src/v4/mini/schemas';

export const UserContext = createContext(null);

export const UserProvider = ({ children }) => {
    const [users, setUser] = useState([{
        id : '',
        Uid : '',
        password : '',
        nickname : '',
        LV : 1,
        exp : 0,
        maxHp : 100,
        currentHp : 100,
        maxMp : 100,
        currentMp : 100,
        atk : 10,
        def : 10,
        dex : 10,
        luk : 10,
    }]);

    const addUser = ({id, password, nickname}) => {
        const newUser = [{
            id : date.now,
            Uid : {id},
            password : {password},
            nickname : {nickname},
            LV : 1,
            exp : 0,
            maxHp : 100,
            currentHp : 100,
            maxMp : 100,
            currentMp : 100,
            atk : 10,
            def : 10,
            dex : 10,
            luk : 10
        }]

        setUser(prevUser => [...prevUser, newUser])
    }

    const checkId = (id) => {
        return (
            users.some(user => user.Uid === id)
        )
    }

      const checkNickName = (nickname) => {
        return (
            users.some(user => user.nickname === nickname)
        )
      }

    return (
        <UserContext.Provider value = {{users, setUser, addUser,checkId, checkNickName}}>
            {children} 
        </UserContext.Provider>
    );
}