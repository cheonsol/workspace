import React, { useCallback, useMemo, useState } from 'react'
import ViewResult from './ViewResult';


const UseCallbackTest = () => {
    const [num, setNum] = useState(0);
    const [dark, setDark] = useState(false);

    const theme = useMemo(() => ({
        backgroundColor: dark ? "#333" : "#fff",
        color: dark ? "#fff" : "#3333",
        padding: "12px"
    }), [dark])

    const onChangeNum = (ev) => {
        setNum(Number(ev.target.value));
    }

    const getResult = useCallback(() => {
        return num ? [num, num+1, num+2] : [0,0,0];
    }, [num]);
    
  return (
    <div style={theme}>
        <h2>useCallback 최적화 테스트</h2>

        <div>
            <input 
                type="number"
                placeholder='숫자를 입력하세요'
                value={num}
                onChange={onChangeNum} 
            />
        </div>

        <button onClick={() => setDark(prev => !prev)}>
            테마변경
        </button>

        <ViewResult getResult={getResult} />
    </div>
  )
}

export default UseCallbackTest