import styled, { keyframes } from 'styled-components';

// 깜빡임 애니메이션 정의
const blink = keyframes`
  0%, 100% { opacity: 1; }
  50% { opacity: 0; }
`;

// 전체 게임 컨테이너
export const GameContainer = styled.div`
  padding: 20px;
  max-width: 600px;
  margin: 0 auto;
  font-family: 'Courier New', monospace; /* 고전 게임 느낌 폰트 */
`;

// 상태창 (몬스터/유저 정보)
export const StatusWindow = styled.div`
  border: 4px double #333;
  padding: 15px;
  margin-bottom: 20px;
  background: #f8f8f8;
  box-shadow: 4px 4px 0px rgba(0,0,0,0.1);
`;

// 상태창 상단 (이름, 체력바 배치용)
export const StatusHeader = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  align-items: flex-end;
`;

// 개별 캐릭터 정보 (정렬 방향 선택 가능)
export const CharacterInfo = styled.div`
  text-align: ${props => props.$align === 'right' ? 'right' : 'left'};
  width: 48%;
  
  strong {
    font-size: 1.1rem;
    display: block;
    margin-bottom: 4px;
  }
  
  small {
    font-size: 0.8rem;
    color: #666;
  }
`;

// 체력바 틀
export const HpBarFrame = styled.div`
  width: 100%;
  max-width: 150px;
  height: 12px;
  background: #ddd;
  border: 2px solid #333;
  border-radius: 6px;
  overflow: hidden;
  margin-top: 5px;
  /* 오른쪽 정렬일 경우 마진 auto로 우측 붙이기 */
  margin-left: ${props => props.$align === 'right' ? 'auto' : '0'};
`;

// 체력바 내부 (채워지는 부분)
export const HpBarFill = styled.div`
  width: ${props => props.$width}%;
  background: ${props => props.$color || '#ff4d4d'};
  height: 100%;
  transition: width 0.3s cubic-bezier(0.4, 0, 0.2, 1);
`;

// 몬스터 이미지 영역
export const MonsterImage = styled.div`
  text-align: center;
  font-size: 6rem;
  margin: 30px 0;
  filter: drop-shadow(0 4px 2px rgba(0,0,0,0.2));
  
  /* 피격 시 흔들림 효과 추가 가능 */
  transition: transform 0.1s;
`;

// 대화창 (로그 박스)
export const DialogueBox = styled.div`
  height: 120px;
  border: 4px solid #333;
  border-radius: 12px;
  background: #fff;
  padding: 20px;
  margin-bottom: 20px;
  color: #333;
  font-size: 1.2rem;
  font-weight: bold;
  line-height: 1.5;
  cursor: ${props => props.$isProcessing ? 'pointer' : 'default'};
  position: relative;
  display: flex;
  align-items: center;
  user-select: none;
  box-shadow: 4px 4px 0px rgba(0,0,0,0.1);

  /* 마우스 올렸을 때 효과 (진행 중일 때만) */
  &:hover {
    background: ${props => props.$isProcessing ? '#fafafa' : '#fff'};
  }
`;

// 대화 넘김 화살표 (▼)
export const NextArrow = styled.div`
  position: absolute;
  bottom: 15px;
  right: 20px;
  color: #ff4757;
  font-size: 1.2rem;
  animation: ${blink} 1s infinite;
`;

// 버튼 영역 그리드
export const ButtonGrid = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 15px;
`;

// 액션 버튼
export const ActionButton = styled.button`
  padding: 16px;
  background: #f1f2f6;
  border: 3px solid #333;
  border-radius: 8px;
  font-size: 1.1rem;
  font-weight: 800;
  cursor: pointer;
  color: #333;
  transition: all 0.1s;
  box-shadow: 0 4px 0 #333;
  font-family: inherit;

  &:active {
    transform: translateY(4px);
    box-shadow: 0 0 0 #333;
  }

  &:hover {
    background: #e2e6ea;
  }
`;