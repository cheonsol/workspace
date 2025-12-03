import React from 'react';
import useBoardStore from '../store/board';
import { useNavigate } from 'react-router-dom';
import Header from '../layout/Header'; // 헤더 추가

// 스타일 컴포넌트 불러오기
import { 
  BoardContainer, 
  BoardTitle, 
  QuestList, 
  QuestItem, 
  ButtonGroup, 
  WriteButton,
  EmptyMessage 
} from '../style/Board.style';

const Board = () => {

  // 게시글 목록 가져오기 (최신순 정렬을 위해 reverse 사용 가능)
  const boards = useBoardStore((state) => state.boards);
  const navigator = useNavigate();

  // 상세 페이지 이동 핸들러 (나중에 구현)
  const handleItemClick = (id) => {
    navigator(`/boardDetail/${id}`);
  };

  const onClickWrite = () => {
    navigator('/boardwrite');
  };
  
  // show가 true인 게시글만 필터링
  const activeBoards = boards.filter(b => b.show);

  return (
    <>
      <Header /> {/* 상단 헤더 */}
      
      <BoardContainer>
        <BoardTitle>🛡️ 길드 의뢰소</BoardTitle>
        
        {activeBoards.length > 0 ? (
          <QuestList>
            {activeBoards.map((b) => (
              <QuestItem key={b.id} onClick={() => handleItemClick(b.id)}>
                
                {/* 1. 번호 */}
                <span className="id">#{b.id}</span>
                
                {/* 2. 제목 */}
                <span className="title">{b.title}</span>
                
                {/* 3. 작성자 및 정보 (스토어에 있다면 표시) */}
                <div className="info">
                   {/* 작성자 정보가 있으면 보여주고, 없으면 '익명' */}
                   <span>👤 {b.writer || '익명'}</span>
                   {/* 날짜 정보가 있으면 보여줌 (앞부분 10자리만 자르기 예시) */}
                   {b.writeDate && <span>📅 {b.writeDate.slice(2)}</span>}
                </div>

              </QuestItem>
            ))}
          </QuestList>
        ) : (
          // 게시글이 하나도 없을 때
          <QuestList>
            <EmptyMessage>
              현재 등록된 의뢰가 없습니다.<br />
              첫 번째 의뢰를 등록해보세요!
            </EmptyMessage>
          </QuestList>
        )}

        <ButtonGroup>
          <WriteButton onClick={onClickWrite}>
            ✏️ 의뢰 등록하기
          </WriteButton>
        </ButtonGroup>

      </BoardContainer>
    </>
  )
}

export default Board;