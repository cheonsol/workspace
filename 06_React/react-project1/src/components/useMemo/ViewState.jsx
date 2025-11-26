import React, { useMemo } from 'react'

const ViewState = ({num}) => {

    const getHeavyResult = (value) => {
        console.log("getHeavyResult 연산실행")
        let i = 0;
        while(i<100000000) i++;
        return value + i;
    }

    //useMemo를 통해서 연산결과를 캐싱하고 재사용한다.
    //의존성배열에있는 num이 변경될 때만 다시 함수를 호출하여 값을 캐싱한다.
    const heavyResult = useMemo(() => getHeavyResult(num), [num]) 
        
        
  return (
    <div>
        <p>현재 숫자 : {num}</p>
        <p>계산된 값 : {heavyResult}</p>
    </div>
  )
}

export default ViewState;