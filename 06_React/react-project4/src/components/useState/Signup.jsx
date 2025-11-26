import React, { useState } from 'react'


//이름과 성별을 입력받는 창을 만들고
//submit버튼 클릭시 이름: ~ 성별 : ~ 를 출력하자!
const Signup = () => {
    const [name, setName] = useState("");
    const [gender, setGender] = useState("man");

    const handleChangeName = (ev) => {
        setName(ev.target.value);
    }

    const handleChangeGender = (ev) => {
        setGender(ev.target.value);
    }

    const handleSubmit = (ev) =>{
        ev.preventDefault(); // a태그나 sumbit같은 고유동작을 가진 태그에 이벤트를 중단해주기 위한 함수
        //onSubmit 자체에 서버로 데이터를 전송하고 페이지를 이동하는 기능이 있지만 react에서는
        //form태그 자체의 표준적인 구조는 사용하지만 이벤트기능은 SPA과는 맞지않아 사용하지 않음.
    
        alert(`이름 : ${name}, 성별 : ${gender}`);
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>
                이름 : <input type='text' value={name} onChange={handleChangeName} />
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
        </form>
    )
}

export default Signup