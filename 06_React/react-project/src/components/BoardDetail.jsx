import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import useBoardStore from '../store/board';
import Header from '../layout/Header';
// 스타일 import
import { DetailWrapper, DetailHeader, DetailContent, ButtonArea, ActionButton } from '../style/BoardDetail.style';
import useGameStore from '../store/store';

const BoardDetail = () => {
  // 1. URL에서 id 가져오기 (문자열 형태임)
  const { boardId } = useParams();
  const currentUser = useGameStore((state) => state.currentUser)
  const navigator = useNavigate();
  const boards = useBoardStore((state) => state.boards);

  // 2. 게시글 찾기
  // 중요: id는 문자열이므로 Number(id)로 숫자로 바꿔서 비교해야 함!
  const board = boards.find((b) => b.id === Number(boardId));

  // 3. 게시글이 없을 경우 (잘못된 주소 등) 처리
  if (!board) {
    return (
      <>
        <Header />
        <div style={{ textAlign: 'center', marginTop: '100px', color: 'white' }}>
          <h2>❌ 존재하지 않는 의뢰서입니다.</h2>
          <button onClick={() => navigator('/board')} style={{ marginTop: '20px' }}>목록으로 돌아가기</button>
        </div>
      </>
    );
  }

  // 4. 데이터 구조분해 할당
  const { title, contents, writer, writeDate } = board;
  const checkWriter = (writer === currentUser.nickname);

  return (
    <>
      <Header />
      
      <DetailWrapper>
        <DetailHeader>
          <h2>{title}</h2>
          <div className="info">
             <span>No. {board.id}</span>
             <span>👤 의뢰인: {writer}</span>
             {/* 날짜가 있으면 표시 */}
             <span>📅 {writeDate ? writeDate.toString().slice(0, 21) : 'Unknown'}</span>
          </div>
        </DetailHeader>

        <DetailContent>
          {contents}
        </DetailContent>
        {checkWriter ?
        <ButtonArea>
          <ActionButton onClick={() => navigator('/board')}>목록으로</ActionButton>
          <ActionButton>수정</ActionButton>
          <ActionButton>삭제</ActionButton>
        </ButtonArea>
          : 
        <ButtonArea>
          <ActionButton onClick={() => navigator('/board')}>목록으로</ActionButton>
        </ButtonArea>
        }
           
        
      </DetailWrapper>
    </>
  );
};

export default BoardDetail;