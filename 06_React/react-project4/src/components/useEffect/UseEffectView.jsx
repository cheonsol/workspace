import React, { useState } from 'react'
import UesEffectTest from './UesEffectTest';

const UseEffectView = () => {
    const [isView, setIsView] = useState(false);
  
    return (
        <div>
            <button onClick={() => setIsView(prev => !prev)}>
                화면출력
            </button>
            {
                isView &&
                <UesEffectTest />
            }
        </div>
    )
}

export default UseEffectView