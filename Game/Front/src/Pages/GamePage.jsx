import React, { useRef, useEffect, useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import {
    GameContainer,
    ScoreBoard,
    Overlay,
    GameOverTitle,
    FinalScore,
    NicknameInput,
    ButtonRow,
    ActionButton,
    StartMessage,
    SpeedIndicator
} from './GamePage.style';
import kiwiSprite from '../assets/player/kiwi_run.png';

// 게임 상수 (동적으로 계산됨)
const GRAVITY = 0.8;
const JUMP_FORCE = -14;  // 2단 블럭을 아슬아슬하게 넘는 정도
const BASE_SPEED = 8;

// 스프라이트 설정 (kiwi_run.png 기준)
const SPRITE = {
    frameWidth: 100,
    frameHeight: 120,  // 다리까지 포함
    runFrames: 5,
    rowY: 0            // 스프라이트 시작 Y 오프셋
};

const GamePage = () => {
    const navigate = useNavigate();
    const canvasRef = useRef(null);
    const containerRef = useRef(null);
    const animationRef = useRef(null);
    const dimensionsRef = useRef({ width: window.innerWidth, height: window.innerHeight });
    
    // 구름 상태를 ref로 관리 (부드러운 움직임)
    const cloudsRef = useRef([
        { x: 200, y: 80, speed: 0.3, size: 1 },
        { x: 500, y: 150, speed: 0.2, size: 1.2 },
        { x: 800, y: 60, speed: 0.25, size: 0.9 },
        { x: 1100, y: 120, speed: 0.15, size: 1.1 },
        { x: 1400, y: 90, speed: 0.22, size: 0.85 },
    ]);
    
    const getGroundY = () => dimensionsRef.current.height * 0.75;
    
    const gameStateRef = useRef({
        isRunning: false,
        isGameOver: false,
        score: 0,
        speed: BASE_SPEED,
        player: {
            x: 120,
            y: 0,
            width: 90,
            height: 110,
            velocityY: 0,
            isJumping: false,
            frameIndex: 0,
            frameTimer: 0
        },
        obstacles: [],
        obstacleTimer: 0,
        groundOffset: 0
    });

    const [score, setScore] = useState(0);
    const [currentSpeed, setCurrentSpeed] = useState(BASE_SPEED);
    const [isGameOver, setIsGameOver] = useState(false);
    const [isStarted, setIsStarted] = useState(false);
    const [nickname, setNickname] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);

    // 이미지 로드
    const kiwiImageRef = useRef(null);
    const imagesLoadedRef = useRef(false);

    // 캔버스 크기 조절
    const resizeCanvas = useCallback(() => {
        const canvas = canvasRef.current;
        if (!canvas) return;
        
        dimensionsRef.current = {
            width: window.innerWidth,
            height: window.innerHeight
        };
        
        canvas.width = dimensionsRef.current.width;
        canvas.height = dimensionsRef.current.height;
        
        // 플레이어 위치 업데이트 (발이 땅에 닿도록)
        const groundY = getGroundY();
        gameStateRef.current.player.y = groundY - gameStateRef.current.player.height + 5;
    }, []);

    useEffect(() => {
        const kiwiImg = new Image();
        kiwiImg.src = kiwiSprite;
        kiwiImageRef.current = kiwiImg;

        kiwiImg.onload = () => {
            imagesLoadedRef.current = true;
        };

        // 초기 캔버스 크기 설정
        resizeCanvas();
        window.addEventListener('resize', resizeCanvas);

        return () => {
            window.removeEventListener('resize', resizeCanvas);
            if (animationRef.current) {
                cancelAnimationFrame(animationRef.current);
            }
        };
    }, [resizeCanvas]);

    // 충돌 감지
    const checkCollision = useCallback((player, obstacle) => {
        const padding = 15;
        return (
            player.x + padding < obstacle.x + obstacle.width - padding &&
            player.x + player.width - padding > obstacle.x + padding &&
            player.y + padding < obstacle.y + obstacle.height &&
            player.y + player.height > obstacle.y + padding
        );
    }, []);

    // 게임 오버 처리
    const handleGameOver = useCallback(() => {
        gameStateRef.current.isRunning = false;
        gameStateRef.current.isGameOver = true;
        setIsGameOver(true);
        if (animationRef.current) {
            cancelAnimationFrame(animationRef.current);
        }
    }, []);

    // 점수 등록
    const submitScore = async () => {
        if (isSubmitting) return;
        setIsSubmitting(true);

        let guestId = localStorage.getItem('guestId');
        if (!guestId) {
            guestId = 'guest_' + Date.now() + '_' + Math.random().toString(36).substr(2, 9);
            localStorage.setItem('guestId', guestId);
        }

        const finalNickname = nickname.trim() || '익명의 키위';

        try {
            await fetch('http://localhost:8080/api/ranking', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    nickname: finalNickname,
                    score: gameStateRef.current.score,
                    guestId: guestId
                })
            });
            navigate('/ranking');
        } catch (error) {
            console.error('점수 등록 실패:', error);
            alert('점수 등록에 실패했습니다.');
        } finally {
            setIsSubmitting(false);
        }
    };

    // 게임 재시작
    const restartGame = useCallback(() => {
        const groundY = getGroundY();
        const playerHeight = 110;
        gameStateRef.current = {
            isRunning: true,
            isGameOver: false,
            score: 0,
            speed: BASE_SPEED,
            player: {
                x: 120,
                y: groundY - playerHeight + 5,
                width: 90,
                height: 110,
                velocityY: 0,
                isJumping: false,
                frameIndex: 0,
                frameTimer: 0
            },
            obstacles: [],
            obstacleTimer: 0,
            groundOffset: 0
        };
        setScore(0);
        setCurrentSpeed(BASE_SPEED);
        setIsGameOver(false);
        setNickname('');
        startGameLoop();
    }, []);

    // 게임 시작
    const startGame = useCallback(() => {
        const groundY = getGroundY();
        const playerHeight = gameStateRef.current.player.height;
        gameStateRef.current.player.y = groundY - playerHeight + 5;
        setIsStarted(true);
        gameStateRef.current.isRunning = true;
        startGameLoop();
    }, []);

    // 메인 게임 루프
    const startGameLoop = useCallback(() => {
        const canvas = canvasRef.current;
        if (!canvas) return;
        const ctx = canvas.getContext('2d');

        const gameLoop = () => {
            const state = gameStateRef.current;
            if (!state.isRunning) return;

            const { width, height } = dimensionsRef.current;
            const groundY = height * 0.75;

            // 화면 클리어
            ctx.clearRect(0, 0, width, height);

            // 배경 그라데이션
            const gradient = ctx.createLinearGradient(0, 0, width, height);
            gradient.addColorStop(0, '#667eea');
            gradient.addColorStop(0.5, '#764ba2');
            gradient.addColorStop(1, '#f093fb');
            ctx.fillStyle = gradient;
            ctx.fillRect(0, 0, width, height);

            // 구름 업데이트 및 그리기 (부드럽게)
            updateClouds(state.speed, width);
            drawClouds(ctx);

            // 땅 그리기
            drawGround(ctx, state, width, height, groundY);

            // 플레이어 업데이트 및 그리기
            updatePlayer(state, groundY);
            drawPlayer(ctx, state);

            // 장애물 업데이트 및 그리기
            updateObstacles(state, width, groundY);
            drawObstacles(ctx, state);

            // 충돌 감지
            for (const obstacle of state.obstacles) {
                if (checkCollision(state.player, obstacle)) {
                    handleGameOver();
                    return;
                }
            }

            // 점수 및 속도 업데이트
            state.score += 1;
            // 1000m 간격으로 증가율이 올라가는 방식
            const distance = state.score;
            const tier = Math.floor(distance / 1000); // 1000m마다 tier 증가
            // 천천히 증가하되, tier마다 증가율 50% 상승
            state.speed = BASE_SPEED + (distance * 0.002) * (1 + tier * 0.5);
            setScore(state.score);
            setCurrentSpeed(state.speed);

            // 땅 오프셋 업데이트
            state.groundOffset = (state.groundOffset + state.speed) % 80;

            animationRef.current = requestAnimationFrame(gameLoop);
        };

        animationRef.current = requestAnimationFrame(gameLoop);
    }, [checkCollision, handleGameOver]);

    // 구름 업데이트 (부드러운 패럴랙스)
    const updateClouds = (gameSpeed, width) => {
        cloudsRef.current.forEach(cloud => {
            // 부드럽게 왼쪽으로 이동
            cloud.x -= gameSpeed * cloud.speed;
            
            // 화면 밖으로 나가면 오른쪽에서 다시 시작
            if (cloud.x + 150 * cloud.size < 0) {
                cloud.x = width + Math.random() * 200;
                cloud.y = 50 + Math.random() * 150;
            }
        });
    };

    // 구름 그리기
    const drawClouds = (ctx) => {
        cloudsRef.current.forEach(cloud => {
            const size = cloud.size;
            ctx.fillStyle = 'rgba(255, 255, 255, 0.4)';
            ctx.beginPath();
            ctx.arc(cloud.x, cloud.y, 35 * size, 0, Math.PI * 2);
            ctx.arc(cloud.x + 45 * size, cloud.y - 12 * size, 45 * size, 0, Math.PI * 2);
            ctx.arc(cloud.x + 90 * size, cloud.y, 35 * size, 0, Math.PI * 2);
            ctx.fill();
        });
    };

    // 땅 그리기
    const drawGround = (ctx, state, width, height, groundY) => {
        // 메인 땅
        ctx.fillStyle = '#5D4E37';
        ctx.fillRect(0, groundY + 20, width, height - groundY);

        // 잔디
        ctx.fillStyle = '#7CB342';
        ctx.fillRect(0, groundY + 10, width, 20);

        // 잔디 하이라이트
        ctx.fillStyle = '#8BC34A';
        ctx.fillRect(0, groundY + 10, width, 8);

        // 땅 무늬
        ctx.strokeStyle = '#4A3F2F';
        ctx.lineWidth = 3;
        for (let i = -80; i < width + 80; i += 80) {
            const x = i - (state.groundOffset % 80);
            ctx.beginPath();
            ctx.moveTo(x, groundY + 50);
            ctx.lineTo(x + 30, groundY + 80);
            ctx.stroke();
        }
    };

    // 플레이어 업데이트
    const updatePlayer = (state, groundY) => {
        const player = state.player;
        const landingY = groundY - player.height + 5;

        // 중력 적용
        if (player.isJumping) {
            player.velocityY += GRAVITY;
            player.y += player.velocityY;

            // 착지
            if (player.y >= landingY) {
                player.y = landingY;
                player.velocityY = 0;
                player.isJumping = false;
            }
        }

        // 애니메이션 프레임 업데이트 (달리기만)
        if (!player.isJumping) {
            player.frameTimer++;
            if (player.frameTimer >= 5) {
                player.frameTimer = 0;
                player.frameIndex = (player.frameIndex + 1) % SPRITE.runFrames;
            }
        }
    };

    // 플레이어 그리기
    const drawPlayer = (ctx, state) => {
        if (!imagesLoadedRef.current || !kiwiImageRef.current) return;

        const player = state.player;
        // 점프 중에는 첫 번째 프레임 고정, 아니면 달리기 애니메이션
        const frameIndex = player.isJumping ? 0 : (player.frameIndex % SPRITE.runFrames);

        ctx.drawImage(
            kiwiImageRef.current,
            frameIndex * SPRITE.frameWidth,  // 소스 X
            SPRITE.rowY,                      // 소스 Y (첫 번째 row만 사용)
            SPRITE.frameWidth,                // 소스 너비
            SPRITE.frameHeight,               // 소스 높이
            player.x,                         // 목적지 X
            player.y,                         // 목적지 Y
            player.width,                     // 목적지 너비
            player.height                     // 목적지 높이
        );
    };

    // 장애물 업데이트
    const updateObstacles = (state, width, groundY) => {
        state.obstacleTimer++;
        // 난이도 상승: 장애물 생성 간격 감소 (최소 35프레임)
        const tier = Math.floor(state.score / 1000);
        const spawnInterval = Math.max(35, 80 - tier * 8);
        
        if (state.obstacleTimer >= spawnInterval) {
            state.obstacleTimer = 0;
            
            // 거리에 따라 2단 블럭 확률 증가
            const tier = Math.floor(state.score / 1000);
            const tallBlockChance = Math.min(0.1 + tier * 0.15, 0.7); // 최대 70%
            
            let size;
            if (Math.random() < tallBlockChance) {
                // 2단 블럭 (높은 장애물) - 아슬아슬하게 넘어야 함
                size = { width: 50, height: 85 };
            } else {
                // 1단 블럭 (낮은 장애물)
                const smallSizes = [
                    { width: 40, height: 45 },
                    { width: 50, height: 55 }
                ];
                size = smallSizes[Math.floor(Math.random() * smallSizes.length)];
            }
            
            state.obstacles.push({
                x: width,
                y: groundY + 10 - size.height,
                width: size.width,
                height: size.height
            });
        }

        state.obstacles.forEach(obstacle => {
            obstacle.x -= state.speed;
        });

        state.obstacles = state.obstacles.filter(obstacle => obstacle.x + obstacle.width > 0);
    };

    // 장애물 그리기 (네모)
    const drawObstacles = (ctx, state) => {
        state.obstacles.forEach(obstacle => {
            // 장애물 본체
            ctx.fillStyle = '#8B4513';
            ctx.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
            
            // 테두리
            ctx.strokeStyle = '#5D3A1A';
            ctx.lineWidth = 3;
            ctx.strokeRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
            
            // 하이라이트
            ctx.fillStyle = 'rgba(255, 255, 255, 0.2)';
            ctx.fillRect(obstacle.x + 3, obstacle.y + 3, obstacle.width / 3, obstacle.height - 6);
        });
    };

    // 점프 핸들러
    const handleJump = useCallback(() => {
        const state = gameStateRef.current;
        if (!state.isRunning || state.isGameOver) return;
        
        if (!state.player.isJumping) {
            state.player.isJumping = true;
            state.player.velocityY = JUMP_FORCE;
            state.player.frameIndex = 0;
        }
    }, []);

    // 마우스 클릭 이벤트 (게임 화면 전체)
    const handleGameClick = (e) => {
        // 버튼이나 입력창 클릭은 무시
        if (e.target.tagName === 'BUTTON' || e.target.tagName === 'INPUT') {
            return;
        }
        
        if (!isStarted) {
            startGame();
        } else if (!isGameOver) {
            handleJump();
        }
    };

    return (
        <GameContainer ref={containerRef} onClick={handleGameClick}>
            <canvas
                ref={canvasRef}
                style={{ 
                    display: 'block',
                    position: 'absolute',
                    top: 0,
                    left: 0,
                    width: '100%',
                    height: '100%'
                }}
            />
            
            {isStarted && !isGameOver && (
                <>
                    <ScoreBoard>
                        🥝 {score.toLocaleString()}m
                    </ScoreBoard>
                    <SpeedIndicator speed={currentSpeed}>
                        ⚡ x{currentSpeed.toFixed(1)}
                    </SpeedIndicator>
                </>
            )}

            {!isStarted && (
                <Overlay>
                    <StartMessage>
                        <h2>🥝 KIWI RUN 🥝</h2>
                        <p>화면을 클릭하여 시작!</p>
                        <p style={{ fontSize: '1rem', marginTop: '15px', opacity: 0.8 }}>
                            클릭으로 점프하여 장애물을 피하세요
                        </p>
                    </StartMessage>
                </Overlay>
            )}

            {isGameOver && (
                <Overlay>
                    <GameOverTitle>GAME OVER!</GameOverTitle>
                    <FinalScore>
                        🏃 {score.toLocaleString()}m 달성!
                    </FinalScore>
                    <NicknameInput
                        type="text"
                        placeholder="닉네임을 입력하세요 (비우면 익명)"
                        value={nickname}
                        onChange={(e) => setNickname(e.target.value)}
                        onClick={(e) => e.stopPropagation()}
                        maxLength={12}
                    />
                    <ButtonRow>
                        <ActionButton 
                            onClick={(e) => { e.stopPropagation(); submitScore(); }} 
                            disabled={isSubmitting} 
                            primary
                        >
                            {isSubmitting ? '등록 중...' : '랭킹 등록'}
                        </ActionButton>
                        <ActionButton onClick={(e) => { e.stopPropagation(); restartGame(); }}>
                            다시 하기
                        </ActionButton>
                    </ButtonRow>
                    <ActionButton 
                        onClick={(e) => { e.stopPropagation(); navigate('/'); }} 
                        style={{ marginTop: '10px', width: '100%' }}
                    >
                        메인으로
                    </ActionButton>
                </Overlay>
            )}
        </GameContainer>
    );
};

export default GamePage;
