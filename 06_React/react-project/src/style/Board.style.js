import styled from 'styled-components';

// 전체 레이아웃 (중앙 정렬)
export const BoardContainer = styled.div`
  max-width: 900px;
  margin: 0 auto;
  padding: 40px 20px;
  color: #e0e0e0;
`;

// 페이지 제목 (의뢰소)
export const BoardTitle = styled.h2`
  text-align: center;
  font-size: 2.5rem;
  color: #d4af37; /* 금색 */
  margin-bottom: 30px;
  text-shadow: 0 0 10px rgba(212, 175, 55, 0.3);
  font-weight: 900;
  letter-spacing: 2px;

  &::after {
    content: '';
    display: block;
    width: 60px;
    height: 3px;
    background-color: #d4af37;
    margin: 15px auto 0;
  }
`;

// 게시글 목록 틀 (종이/보드 느낌)
export const QuestList = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0;
  background-color: #1e1e1e;
  border: 1px solid #333;
  border-radius: 12px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
  overflow: hidden; /* 모서리 둥글게 유지 */
`;

// 개별 게시글 (퀘스트 아이템)
export const QuestItem = styled.li`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 25px;
  border-bottom: 1px solid #333;
  transition: all 0.2s ease;
  cursor: pointer;

  &:last-child {
    border-bottom: none;
  }

  &:hover {
    background-color: #2a2a2a;
    padding-left: 35px; /* 호버 시 오른쪽으로 살짝 이동하는 효과 */
    
    .title {
      color: #d4af37; /* 제목 금색으로 변경 */
    }
  }

  /* 글 번호 (No.) */
  .id {
    font-size: 0.9rem;
    color: #666;
    margin-right: 15px;
    min-width: 30px;
  }

  /* 글 제목 */
  .title {
    flex: 1; /* 남은 공간 차지 */
    font-size: 1.1rem;
    font-weight: 500;
    color: #fff;
    transition: color 0.2s;
  }

  /* 작성자 및 날짜 */
  .info {
    font-size: 0.85rem;
    color: #888;
    text-align: right;

    span {
      display: inline-block;
      margin-left: 10px;
    }
  }
`;

// 글쓰기 버튼 영역
export const ButtonGroup = styled.div`
  display: flex;
  justify-content: flex-end; /* 오른쪽 정렬 */
  margin-top: 20px;
`;

// 글쓰기 버튼 (금색)
export const WriteButton = styled.button`
  padding: 12px 25px;
  background-color: #d4af37;
  color: #000;
  font-weight: bold;
  font-size: 1rem;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  box-shadow: 0 4px 15px rgba(212, 175, 55, 0.4);
  transition: transform 0.2s, background-color 0.2s;

  &:hover {
    background-color: #fff;
    transform: translateY(-2px);
  }
`;

// 게시글이 없을 때 메시지
export const EmptyMessage = styled.div`
  text-align: center;
  padding: 50px;
  color: #666;
  font-size: 1.1rem;
`;