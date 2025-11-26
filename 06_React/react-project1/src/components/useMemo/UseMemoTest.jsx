import React, { useState } from 'react'
import ViewState from './ViewState';

const UseMemoTest = () => {
    const [text, setText] = useState('');
    const [num, setNum] = useState(0);

    const increaseNum = () => setNum(prev => prev + 1);
    const decreaseNum = () => setNum(prev => prev - 1);


    const onChangeText = (ev) => {
        setText(ev.target.value);
    }
  return (
    <div>
        <h2>useMemo 최적화 테스트</h2>

        <div>
            <button onClick={increaseNum}>+</button>
            <button onClick={decreaseNum}>-</button>
            <br />
            <input 
                type="text"
                placeholder='글자를 입력하세요'
                value={text}
                onChange={onChangeText} 
            />
        </div>

        <ViewState num={num} />
    </div>
  )
}

export default UseMemoTest