import React, {useEffect, useRef, useState} from 'react'

const UseRefTest = () => {
     const [name, setName] = useState("");
     const [gender, setGender] = useState("man");

     const useInput = useRef(null);

 
     const handleChangeName = (ev) => {
         setName(ev.target.value);
     }
 
     const handleChangeGender = (ev) => {
         setGender(ev.target.value);
     }
 
     const handleSubmit = (ev) =>{
         ev.preventDefault(); 
     
         alert(`이름 : ${name}, 성별 : ${gender}`);
     }

     const handelReset = () =>{
        setName("");
        setGender("man");
        // const userInput = document.getElementById('name-input');
        // userInput.focus();
        //이처럼 직접 DOM을 건들면 React가 UI를 렌더링하는 흐름이 예측하기 어려워진다.
        //타이밍상 DOM이 아직 그려지지 않았을 때 getElementById()가 실행될 수 있다.
        //id속성은 컴포넌트와 무관하다. -> id를 사용하면 다른 컴포넌트의 id를 가져올 수 있고 이렇게되면 코드 결합도가 증가한다.
        
        useInput.current?.focus();
        //DOM 직접 탐색없이 React가 input을 참조하게 한다.
    }

 
     return (
         <form onSubmit={handleSubmit}>
             <label>
                이름 : 
                <input 
                    id='name-input'
                    type='text' 
                    value={name} 
                    onChange={handleChangeName} 
                    ref={useInput}
                />
             </label>
             <br /><br />
             <label>
                 성별 : 
                 <select value={gender} onChange={handleChangeGender}>
                     <option value="man">남자</option>
                     <option value="woman">여자</option>
                 </select>
             </label>
             <br /><br />
             <button type='submit'>제출</button>
             <button type='button' onClick={handelReset}>초기화</button>
         </form>
     )
}

export default UseRefTest