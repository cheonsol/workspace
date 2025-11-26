import React, { useEffect, useState } from 'react'

const ViewResult = ({getResult}) => {

    const [items, setItems] = useState([]);

    //컴포넌트가 마운트될 때 실행되는 함수
    useEffect(()=> {
        console.log("ViewResult 랜더링 / getResult가 변경됨")
        setItems(getResult());
    },[getResult])
  return (
    <div>
        <h4>계산된 아이템 목록</h4>
        <ul>
            {items.map((item, index) => 
                <li key={index}>{item}</li>
            )}
        </ul>
    </div>
  )
}

export default ViewResult