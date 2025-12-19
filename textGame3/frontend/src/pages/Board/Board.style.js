import styled from 'styled-components';

// 전체 레이아웃 (중앙 정렬)
export const BoardContainer = styled.div`
  max-width: 1000px;
  margin: 0 auto;
  padding: 40px 20px 60px;
  color: #e0e0e0;
`;

// 페이지 제목 (의뢰소)
export const BoardTitle = styled.h2`
  text-align: center;
  font-size: 2.8rem;
  color: #d4af37; /* 금색 */
  margin-bottom: 10px;
  text-shadow: 0 0 15px rgba(212, 175, 55, 0.4), 0 0 30px rgba(212, 175, 55, 0.2);
  font-weight: 900;
  letter-spacing: 3px;

  &::after {
    content: '';
    display: block;
    width: 80px;
    height: 4px;
    background: linear-gradient(90deg, transparent, #d4af37, transparent);
    margin: 15px auto 0;
  }
`;

// 부제목
export const BoardSubtitle = styled.p`
  text-align: center;
  font-size: 1rem;
  color: #aaa;
  margin-bottom: 20px;
  font-style: italic;
`;

// 의뢰 개수 표시
export const TotalCount = styled.div`
  text-align: right;
  font-size: 0.95rem;
  color: #d4af37;
  margin-bottom: 15px;
  font-weight: 600;
`;

// 게시글 목록 틀 (종이/보드 느낌)
export const QuestList = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0 0 30px 0;
  background: linear-gradient(135deg, #1a1a1a 0%, #1e1e1e 100%);
  border: 2px solid #d4af37;
  border-radius: 15px;
  box-shadow: 
    0 0 20px rgba(212, 175, 55, 0.2),
    0 10px 40px rgba(0, 0, 0, 0.6),
    inset 0 1px 0 rgba(255, 255, 255, 0.05);
  overflow: hidden; /* 모서리 둥글게 유지 */
`;

// 개별 게시글 (퀘스트 아이템)
export const QuestItem = styled.li`
  display: grid;
  grid-template-columns: 80px 80px 1fr 120px 200px;
  gap: 15px;
  align-items: center;
  padding: 20px 25px;
  border-bottom: 1px solid #2a2a2a;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  cursor: pointer;
  position: relative;

  &:last-child {
    border-bottom: none;
  }

  &:hover {
    background: linear-gradient(90deg, rgba(212, 175, 55, 0.1) 0%, transparent 100%);
    padding-left: 35px;
    transform: translateX(5px);
    
    .title {
      color: #d4af37;
      text-shadow: 0 0 10px rgba(212, 175, 55, 0.5);
    }
  }

  /* 반응형 */
  @media (max-width: 768px) {
    grid-template-columns: 1fr;
    gap: 10px;
  }
`;

// 배지 (HOT, NEW, ID)
export const QuestBadge = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 8px 12px;
  background: ${props => {
    if (props.$index === 0) return 'linear-gradient(135deg, #ff6b6b 0%, #ff5252 100%)'; // HOT - 빨강
    if (props.$index < 3) return 'linear-gradient(135deg, #4ecdc4 0%, #44a89b 100%)'; // NEW - 청록
    return 'linear-gradient(135deg, #a8a8a8 0%, #6d6d6d 100%)'; // ID - 회색
  }};
  color: white;
  font-weight: 700;
  border-radius: 8px;
  font-size: 0.85rem;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
  min-width: 60px;
  text-align: center;
  white-space: nowrap;
`;

// 제목과 미리보기
export const QuestContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 5px;

  .title {
    font-size: 1.15rem;
    font-weight: 600;
    color: #fff;
    transition: all 0.3s;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .preview {
    font-size: 0.9rem;
    color: #999;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    max-width: 400px;
  }
`;

// 이미지 미리보기
export const QuestImage = styled.div`
  width: 80px;
  height: 80px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;

// 통계 (댓글 수 등)
export const QuestStats = styled.div`
  display: flex;
  gap: 12px;
  justify-content: center;
  font-size: 0.95rem;
  color: #aaa;

  span {
    display: inline-flex;
    align-items: center;
    gap: 4px;
    padding: 5px 10px;
    background-color: rgba(212, 175, 55, 0.1);
    border-radius: 6px;
    border-left: 2px solid #d4af37;
  }
`;

// 메타 정보 (작성자, 날짜)
export const QuestMeta = styled.div`
  display: flex;
  flex-direction: column;
  gap: 5px;
  text-align: right;
  font-size: 0.85rem;
  color: #888;

  .writer {
    font-weight: 500;
    color: #aaa;
  }

  .date {
    color: #666;
    font-size: 0.8rem;
  }
`;

// 글쓰기 버튼 영역
export const ButtonGroup = styled.div`
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 20px;
`;

// 글쓰기 버튼 (금색)
export const WriteButton = styled.button`
  padding: 14px 32px;
  background: linear-gradient(135deg, #d4af37 0%, #f4d03f 100%);
  color: #000;
  font-weight: 700;
  font-size: 1.05rem;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 
    0 6px 20px rgba(212, 175, 55, 0.4),
    inset 0 1px 0 rgba(255, 255, 255, 0.3);
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  position: relative;
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: -100%;
    width: 100%;
    height: 100%;
    background: rgba(255, 255, 255, 0.3);
    transition: left 0.3s;
  }

  &:hover {
    background: linear-gradient(135deg, #fff 0%, #f4d03f 100%);
    transform: translateY(-3px);
    box-shadow: 
      0 8px 30px rgba(212, 175, 55, 0.6),
      inset 0 1px 0 rgba(255, 255, 255, 0.5);

    &::before {
      left: 100%;
    }
  }

  &:active {
    transform: translateY(-1px);
    box-shadow: 
      0 4px 15px rgba(212, 175, 55, 0.4),
      inset 0 1px 0 rgba(255, 255, 255, 0.3);
  }
`;

// 게시글이 없을 때 메시지
export const EmptyMessage = styled.div`
  text-align: center;
  padding: 60px 20px;
  color: #666;
  font-size: 1.2rem;
  
  span {
    display: block;
    margin-top: 10px;
    font-size: 1rem;
    color: #555;
  }
`;