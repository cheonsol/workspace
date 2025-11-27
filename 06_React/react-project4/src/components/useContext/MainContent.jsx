import React, { useContext } from 'react'
import ThemeContext from './ThemeContext'

const MainContent = () => {
    const {theme, toggleTheme} = useContext(ThemeContext);

    const containerStyle = {
        width: "100vw",
        height: "100vh",
        padding: "12px",
        background: theme,
        color: theme === "white" ? "black" : "white"
    }
  return (
    <div style={containerStyle}>
        <p>안녕하세요, 테마변경 테스트 중입니다.</p>
        <button onClick={toggleTheme}>테마변경</button>
    </div>
  )
}

export default MainContent