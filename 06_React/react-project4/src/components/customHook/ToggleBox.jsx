import React from 'react'
import useToggle from './useToggle'

const ToggleBox = () => {
  const [isView, toggleIsView] = useToggle();

  return (
    <div>
        <button onClick={toggleIsView}>
            {isView ? "숨기기" : "보이기"}
        </button>
        {isView && <div>컨텐츠 영역입니다. 토글로 보이는 영역</div>}
    </div>
  )
}

export default ToggleBox