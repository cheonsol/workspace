import React from 'react'

const Status = ({status}) => {
    const {currentHp, hungry, temperature} = status;

  return (
    <>
        {console.log(status)}
        <p>체력 : {currentHp}</p>
        <p>배고픔 : {hungry}</p>
        <p>체온 : {temperature}</p>
    </>   
  )
}

export default Status