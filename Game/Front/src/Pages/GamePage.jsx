import React, { useRef, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { GameContainer, CanvasWrapper, ScoreBoard, Overlay } from './GamePage.style';
import kiwiRunSrc from '../assets/player/kiwi_run.png'; 

const GamePage = () => {
    const canvasRef = useRef(null);
    const navigate = useNavigate();
    const scoreRef = useRef(0);
    const [displayScore, setDisplayScore] = useState(0);
    const [gameState, setGameState] = useState('PLAYING');
    const [bestScore, setBestScore] = useState(0);

    useEffect(() => {
        let guestId = localStorage.getItem('guestId');
        if (!guestId) {
            guestId = crypto.randomUUID(); 
            localStorage.setItem('guestId', guestId);
        }
        fetch(`http://localhost:8080/api/ranking/my-best/${guestId}`)
            .then(res => res.json())
            .then(data => setBestScore(data))
            .catch(err => console.error(err));
    }, []);

    const handleSubmit = async () => {
        const nickname = document.getElementById('nicknameInput')?.value || '익명의 키위';
        const guestId = localStorage.getItem('guestId');
        try {
            const response = await fetch('http://localhost:8080/api/ranking', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ nickname, score: scoreRef.current, guestId })
            });
            if (response.ok) navigate('/ranking');
        } catch (error) {
            console.error(error);
        }
    };
    
    useEffect(() => {
        if (gameState !== 'PLAYING') return;

        const canvas = canvasRef.current;
        const ctx = canvas.getContext('2d');
        window.focus(); 

        let animationId;
        let frameCount = 0;
        let gameSpeed = 5;
        let obstacles = [];

        const kiwiImg = new Image();
        kiwiImg.src = kiwiRunSrc;

        const IMAGE_WIDTH = 500;
        const IMAGE_HEIGHT = 500;
        const MAX_COLS = 5;
        const ROWS = 3;
        const FRAME_WIDTH = 100;
        const FRAME_HEIGHT = 166.6;

        const SPRITE = {
            RUN:  { row: 0, frames: 5, speed: 6 }, 
            JUMP: { row: 1, frames: 3, speed: 8 }, 
            FALL: { row: 2, frames: 2, speed: 10 }
        };

        const groundY = 350;
        
        const kiwi = {
            x: 100,
            y: 0,
            width: 80,
            height: 80,
            footOffset: 22,
            velocityY: 0,
            jumpPower: -16, // 점프 힘
            gravity: 0.8,   
            isJumping: false,
            jumpDelayTimer: 0, 
            state: 'RUN',
            frameIndex: 0,
            frameTimer: 0,

            // kiwi 객체의 update 함수 부분만 아래와 같이 교체해 보세요.

update() {
    const groundPos = groundY - this.height + this.footOffset;
    if (this.y === 0) this.y = groundPos;

    // 1. 점프 딜레이 (땅에서 기 모으기)
    if (this.jumpDelayTimer > 0) {
        this.jumpDelayTimer--;
        this.state = 'JUMP';
        this.frameIndex = 0; // 무조건 웅크린 자세 고정
        
        if (this.jumpDelayTimer === 0) {
            this.velocityY = this.jumpPower; 
            this.y += this.velocityY; // 즉시 위치 이동으로 바닥 탈출
            this.frameIndex = 1; // 도약 자세로 변경
        }
        this.draw();
        return; // 딜레이 중에는 아래 로직 실행 안 함
    }

    // 2. 물리 엔진 적용 (공중 상태)
    if (this.y < groundPos || this.velocityY !== 0) {
        this.velocityY += this.gravity;
        this.y += this.velocityY;
    }

    // 3. 상태 및 애니메이션 결정 (우선순위 중요)
    if (this.y >= groundPos) {
        // [바닥 상태]
        this.y = groundPos;
        this.velocityY = 0;
        if (this.isJumping) {
            this.isJumping = false;
            this.state = 'RUN';
            this.frameIndex = 0;
            this.frameTimer = 0;
        }
    } else {
        // [공중 상태]
        if (this.velocityY < -2) { 
            // 확실히 상승 중일 때 (수치 조정 가능)
            this.state = 'JUMP';
            this.frameIndex = 1; 
        } else if (this.velocityY >= -2 && this.velocityY <= 2) {
            // 점프 정점 (Apex)
            this.state = 'JUMP';
            this.frameIndex = 2; // 만약 점프 행에 3번째 프레임이 있다면 사용
        } else {
            // 하강 중
            this.state = 'FALL';
        }
    }

    // 4. 자동 애니메이션 (RUN과 FALL 상태에서만 프레임 순환)
    if (this.state === 'RUN' || this.state === 'FALL') {
        const sprite = SPRITE[this.state];
        this.frameTimer += (gameSpeed / 5);
        if (this.frameTimer >= sprite.speed) {
            this.frameIndex = (this.frameIndex + 1) % sprite.frames;
            this.frameTimer = 0;
        }
    }
    
    this.draw();
},

            draw() {
                const sprite = SPRITE[this.state];
                const sourceX = this.frameIndex * FRAME_WIDTH;
                const sourceY = Math.floor(sprite.row * FRAME_HEIGHT);

                ctx.imageSmoothingEnabled = false;
                ctx.drawImage(
                    kiwiImg,
                    sourceX, sourceY, FRAME_WIDTH, Math.floor(FRAME_HEIGHT),
                    this.x, this.y, this.width, this.height
                );
            }
        };

        const animate = () => {
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.fillStyle = '#90EE90';
            ctx.fillRect(0, groundY, canvas.width, 50);

            kiwi.update();
            kiwi.draw();

            frameCount++;
            if (frameCount % 120 === 0) {
                obstacles.push({ x: canvas.width, y: 310, width: 60, height: 40 });
            }

            for (let i = 0; i < obstacles.length; i++) {
                const obs = obstacles[i];
                obs.x -= gameSpeed;
                ctx.fillStyle = '#8B4513';
                ctx.fillRect(obs.x, obs.y, obs.width, obs.height);

                const kHitbox = {
                    x: kiwi.x + 25,
                    y: kiwi.y + 20,
                    w: kiwi.width - 50,
                    h: kiwi.height - 30
                };

                if (
                    kHitbox.x < obs.x + obs.width &&
                    kHitbox.x + kHitbox.w > obs.x &&
                    kHitbox.y < obs.y + obs.height &&
                    kHitbox.y + kHitbox.h > obs.y
                ) {
                    setGameState('GAMEOVER');
                    cancelAnimationFrame(animationId);
                    return; 
                }
            }

            obstacles = obstacles.filter(obs => obs.x + obs.width > 0);
            scoreRef.current += 1;
            if (frameCount % 10 === 0) {
                setDisplayScore(Math.floor(scoreRef.current / 10));
            }

            gameSpeed += 0.001;
            animationId = requestAnimationFrame(animate);
        };

        kiwiImg.onload = () => animate();

        const handleKeyDown = (e) => {
            if (e.code === 'Space') {
                e.preventDefault(); 
                // 점프 중이 아닐 때만 점프 시작
                if (!kiwi.isJumping && kiwi.jumpDelayTimer === 0 && gameState === 'PLAYING') {
                    kiwi.isJumping = true;
                    kiwi.jumpDelayTimer = 10; // 10프레임 동안 땅에서 대기 (딜레이 상향)
                }
            }
        };

        window.addEventListener('keydown', handleKeyDown);
        return () => {
            window.removeEventListener('keydown', handleKeyDown);
            cancelAnimationFrame(animationId);
        };
    }, [gameState]);

    return (
        <GameContainer>
            <ScoreBoard>SCORE: {displayScore} | BEST: {bestScore}</ScoreBoard>
            <CanvasWrapper>
                <canvas ref={canvasRef} width={800} height={400} />
            </CanvasWrapper>
            {gameState === 'GAMEOVER' && (
                <Overlay>
                    <h1>GAME OVER</h1>
                    <p>SCORE: {displayScore}</p>
                    <input id="nicknameInput" type="text" placeholder="닉네임 입력" />
                    <button onClick={handleSubmit}>SCORE SUBMIT</button>
                    <button onClick={() => window.location.reload()}>RETRY</button>
                </Overlay>
            )}
        </GameContainer>
    );
};

export default GamePage;