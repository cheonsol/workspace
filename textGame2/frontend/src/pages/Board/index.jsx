import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../../layout/Header';
import useBoardStore from '../../store/board';
import { 
  BoardContainer, 
  BoardTitle, 
  BoardSubtitle,
  QuestList, 
  ButtonGroup, 
  WriteButton,
  EmptyMessage,
  TotalCount
} from './Board.style';
import BoardListItem from '../../components/Board/BoardListItem';

const Board = () => {
  const { boards, fetchBoards, loading, error } = useBoardStore();

  const navigator = useNavigate();

  useEffect(() => {
    fetchBoards();
  }, [fetchBoards]);

  const handleItemClick = (id) => {
    navigator(`/boardDetail/${id}`);
  };

  const onClickWrite = () => {
    navigator('/boardwrite');
  };

  if (loading) return <div>Loading...</div>
  if (error) return <div>Error: {error}</div>
  
  // filter : 매개변수로 보낸 값이 true인 것들만 모아 새로운 배열 생성
  // boards를 b라는 객체로 저장 후, b.show가 true인 것들만 필터링
  // sort : 정렬 함수, a-b 오름차순, b-a 내림차순
  // id는 점점 커지기 때문에 b.id - a.id는 내림차순 정렬
  // 내림차순 : 10, 9 ,8,...
  const activeBoards = boards.sort((a, b) => b.id - a.id);

  return (
    <>
      <Header /> {/* 상단 헤더 */}
      
      <BoardContainer>
        <BoardTitle>🛡️ 길드 의뢰소</BoardTitle>
        <BoardSubtitle>모험가들을 위한 다양한 의뢰가 대기 중입니다</BoardSubtitle>
        
        {activeBoards.length > 0 ? (
          <>
            <TotalCount>등록된 의뢰: {activeBoards.length}개</TotalCount>
            <QuestList>
              {/*  index : 가상의 숫자로 0부터 시작하여 1씩 증가 */}
              {activeBoards.map((b, index) => (
                <BoardListItem
                key={b.id}
                board={b}
                index={index}
                onClickItem={handleItemClick} />
              ))}
            </QuestList>
          </>
        ) : (
          // 게시글이 하나도 없을 때
          <QuestList>
            <EmptyMessage>
              🏰 현재 등록된 의뢰가 없습니다.<br />
              <span>첫 번째 의뢰를 등록해보세요!</span>
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