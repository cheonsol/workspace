import { useState } from "react"

const useToggle = (init = false) => {
    const [vlaue, setValue] = useState(init);
    
    const toggle = () => setValue(prev => !prev);

    return [vlaue, toggle];
}

export default useToggle;