import React from 'react'

const Scene = ({scene}) => {
    const {text, select} = scene;
 
    return (
        <> 
            {console.log(text)}
            <p>{text}</p>
            <p>어떻게 하시겠습니까?</p>
            <p>{select}</p>
            <button value ='1'>1</button>
            <button value ='2'>2</button>    
        </>      
    )
}

export default Scene