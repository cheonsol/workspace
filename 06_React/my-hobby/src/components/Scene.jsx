import React from 'react'

const Scene = ({scene, onClick}) => {
    
    const {text, select} = scene;

    return (
        <> 
            <p>{text}</p>
            <p>어떻게 하시겠습니까?</p>
            <button value ='1' onClick = {onClick}>{select[0]}</button> <br/>
            <button value ='2' onClick = {onClick}>{select[1]}</button> 
        </>      
    )
}

export default Scene