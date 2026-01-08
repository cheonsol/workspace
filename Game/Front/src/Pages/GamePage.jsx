import React, { useRef, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { GameContainer, CanvasWrapper, ScoreBoard, Overlay } from './GamePage.style';

const GamePage = () => {
    const canvasRef = useRef(null);
    const navigate = useNavigate();
    const [score, setScore] = useState(0);
    // 진입하자마자 게임이 시작되도록 초기값을 'PLAYING'으로 설정
    const [gameState, setGameState] = useState('PLAYING');

    const saveScore = async (finalScore) => {
        const currentScore = Math.floor(finalScore / 10);
        
        const bestScore = localStorage.getItem('highScore') || 0;
        if (currentScore > bestScore) {
            localStorage.setItem('highScore', currentScore);
        }

        try {
            await fetch('http://localhost:8080/api/ranking', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ 
                    nickname: '익명의 키위',
                    score: currentScore 
                })
            });
        } catch (error) {
            console.error("점수 전송 실패");
        }
    };

    useEffect(() => {
        if (gameState !== 'PLAYING') return;

        const canvas = canvasRef.current;
        const ctx = canvas.getContext('2d');
        let animationId;
        let frameCount = 0;
        let gameSpeed = 5;
        let currentScore = 0;
        let obstacles = [];

        const kiwi = {
            x: 100,
            y: 300,
            width: 50,
            height: 50,
            velocityY: 0,
            jumpPower: -12,
            gravity: 0.6,
            isJumping: false,
            draw() {
                ctx.fillStyle = '#8b4513';
                ctx.fillRect(this.x, this.y, this.width, this.height);
            },
            update() {
                this.velocityY += this.gravity;
                this.y += this.velocityY;
                if (this.y + this.height > 350) {
                    this.y = 350 - this.height;
                    this.isJumping = false;
                }
                this.draw();
            }
        };

        const handleKeyDown = (e) => {
            if (e.code === 'Space' && !kiwi.isJumping) {
                kiwi.velocityY = kiwi.jumpPower;
                kiwi.isJumping = true;
            }
        };

        const animate = () => {
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            
            // 바닥 그리기
            ctx.fillStyle = '#90EE90';
            ctx.fillRect(0, 350, canvas.width, 50);

            kiwi.update();

            frameCount++;
            if (frameCount % 100 === 0) {
                obstacles.push({
                    x: canvas.width,
                    y: 310,
                    width: 40,
                    height: 40
                });
            }

            obstacles.forEach((obs) => {
                obs.x -= gameSpeed;
                ctx.fillStyle = '#5d4037';
                ctx.fillRect(obs.x, obs.y, obs.width, obs.height);

                if (kiwi.x < obs.x + obs.width &&
                    kiwi.x + kiwi.width > obs.x &&
                    kiwi.y < obs.y + obs.height &&
                    kiwi.y + kiwi.height > obs.y) {
                    setGameState('GAMEOVER');
                    saveScore(currentScore);
                    cancelAnimationFrame(animationId);
                }
            });

            obstacles = obstacles.filter(obs => obs.x + obs.width > 0);
            currentScore++;
            setScore(Math.floor(currentScore / 10));
            gameSpeed += 0.001;
            animationId = requestAnimationFrame(animate);
        };

        window.addEventListener('keydown', handleKeyDown);
        animate();

        return () => {
            window.removeEventListener('keydown', handleKeyDown);
            cancelAnimationFrame(animationId);
        };
    }, [gameState]);

    return (
        <GameContainer>
            <ScoreBoard>SCORE: {score} PTS</ScoreBoard>
            <CanvasWrapper>
                <canvas ref={canvasRef} width={800} height={400} />
            </CanvasWrapper>

            {gameState === 'GAMEOVER' && (
                <Overlay>
                    <h1 style={{color: '#8b4513'}}>GAME OVER</h1>
                    <p style={{fontSize: '1.5rem', fontWeight: 'bold'}}>SCORE: {score} PTS</p>
                    
                    <input 
                        type="text" 
                        placeholder="닉네임을 입력하세요"
                        id="nicknameInput"
                        style={{padding: '10px', borderRadius: '10px', border: '2px solid #8b4513', marginBottom: '10px'}}
                    />

                    <button onClick={async () => {
                        const nickname = document.getElementById('nicknameInput').value || '익명의 키위';
                        await fetch('http://localhost:8080/api/ranking', {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify({ nickname, score })
                        });
                        navigate('/ranking');
                    }}>
                        SCORE SUBMIT
                    </button>
                    <button onClick={() => {
                        setScore(0);
                        setGameState('PLAYING');
                    }}>RETRY</button>
                    <button onClick={() => navigate('/')}>TITLE</button>
                </Overlay>
            )}
        </GameContainer>
    );
};

export default GamePage;