import React, { useState } from 'react'

/*
    state : 컴포넌트의 상태값
    useState : 컴포넌트의 상태를 생성하고 관리할 수 있게 해주는 react hook
    -> 컴포넌트는 state값이 변경되면 이를 확인하고 컴포넌트를 리렌더링 해준다.

    const [변수명, set변수명] = useState(초기값);

    React에서 setState와 같은 상태 업데이트 함수는 비동기적으로 작동한다.
    -> 상태변경시 리렌더링이 되기 때문에 즉시 상태를 변경하면 반복적인 리렌더링이 발생할 수 있다. 하여,
       리엑트는 이를 모아서 한번에 반영해준다.

    setNum(prevNum => prevNum - 1)
    setNum(num - 1)

    function setNum = (value) => {
        if(typeof value === 'function'){
            prevNum = value(prevNum);
        } else {
            prevNum = value;
        }
    }
*/

const UseStateTest = () => {
    //const num = 0;
    const [num, setNum] = useState(0);

    const onClickPlus = () => {
        // num++; 현재 상태를 직접 사용해서 수정하는 것은 어차피 state변경인식이 안됨! -> 리렌더링x
        setNum(num + 1);//1
        setNum(num + 1);//2
        //위처럼 여러번 state값을 초기화 해 주었을 때 예상하는 것처럼 즉시 값의 변화가 반영되지 않는다.
        //이유는 즉시 state값을 변경하게되면 리렌더링이 그때그때 발생하여 한번의 동작으로 여러번의 리렌더링이 일어날 수 있어,
        //배치작업을 통해 값변경 내용을 모아서 한번에 처리해 준다.
    }

    const onClickMinus = () => {
        setNum((prevNum) => prevNum - 1);
        setNum((prevNum) => {
            console.log("이전 상태 : ", prevNum);
            return prevNum - 1;
        });

        console.log("상태 변경 : ", num);
        //상태가 이전 상태에 의존하는 경우에는
        //항상 상태 업데이트 함수에 콜백을 사용하는 방식((prevNum) => prevNum - 1)을 사용해야 한다.
    }

    return (
        <div>
            <div>COUNT : {num}</div>
            <button onClick={onClickPlus}> + </button>
            <button onClick={onClickMinus}> - </button>
        </div>
    )
}

export default UseStateTest