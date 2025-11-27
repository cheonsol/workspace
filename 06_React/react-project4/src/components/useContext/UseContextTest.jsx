import React, { useEffect, useState } from 'react'
import ThemeContext from './ThemeContext';
import MainContent from './MainContent';

const UseContextTest = () => {
    const [theme, setTheme] = useState("white");

    const toggleTheme = () =>{
        console.log("??")
         setTheme(theme === "white" ? "black" : "white");
    };
    
    return (
        <ThemeContext.Provider value={{theme, toggleTheme}}>
            <MainContent />
        </ThemeContext.Provider>
    )
}

export default UseContextTest