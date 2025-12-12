import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from '../layout/Header';
import useBoardStore from '../store/board';
import useGameStore from '../store/user';
import { 
  DetailWrapper, DetailHeader, DetailContent, ButtonArea, ActionButton,
  CommentArea, CommentHeader, CommentForm, CommentInput, SubmitButton, 
  CommentList, CommentItem, CommentInfo, CommentText, DeleteText, EditText
} from '../style/BoardDetail.style';
// 게시판 내용 표시 시 데이터 포매팅 함수들
import {
  formatDate,  // 날짜를 읽기 좋은 형식으로 변환
  truncateText // 긴 텍스트를 지정된 길이로 자름
} from '../utils/formatters';

const BoardDetail = () => {
  // URL 파라미터에서 boardId 추출
  const { boardId } = useParams();
  // currentUser 정보를 store에서 가져옴
  const currentUser = useGameStore((state) => state.currentUser);
  const navigator = useNavigate();
  // 게시글 정보를 store에서 가져옴
  const boards = useBoardStore((state) => state.boards);
  // 댓글관련 함수들을 store에서 가져옴
  const addComment = useBoardStore((state) => state.addComment);
  const deleteComment = useBoardStore((state) => state.deleteComment);
  const updateComment = useBoardStore((state) => state.updateComment);

  // 댓글 관련 state
  const [comment, setComment] = useState(""); // 입력된 댓글
  const [editingId, setEditingId] = useState(null); // 수정 중인 댓글 ID
  const [editingContent, setEditingContent] = useState(""); // 수정 중인 내용

  // 게시글 조회. useParams로 가져온 boardId를 숫자로 변환하여 비교
  const board = boards.find((b) => b.id === Number(boardId));

  // id 조회 후, 게시글이 없을 경우. 나중에 404 페이지를 만들어 nagator할 생각.
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

  const { title, contents, writer, writeDate } = board;
  // 수정, 삭제 기능을 위한 작성자 확인
  const isWriter = (writer === currentUser?.nickname);

  const handleCommentSubmit = () => {
    // 공백을 제거하고 댓글 내용이 비어있는지 확인
    if (!comment.trim()) return alert("댓글 내용을 입력해주세요.");
    // 새로운 댓글 객체
    const newComment = {
      id: Date.now(), // 고유 ID (임시)
      writer: currentUser.nickname,
      content: comment,
      date: new Date().toLocaleString(), // 현재 시간
    };

    addComment(Number(boardId), newComment);
    setComment(""); // 입력창 초기화
  };

  // --- 댓글 삭제 핸들러 ---
  const handleDeleteComment = (id) => {
    // alert는 확인만 가능. confirm은 확인/취소 가능.
    if (window.confirm("댓글을 삭제하시겠습니까?")) {
      deleteComment(Number(boardId), id);
    }
  };

  // --- 댓글 수정 시작 ---
  const handleEditStart = (id, content) => {
    setEditingId(id);
    setEditingContent(content);
  };

  const handleEditComplete = () => {
    if (!editingContent.trim()) {
      alert("댓글 내용을 입력해주세요.");
      return;
    }
    updateComment(Number(boardId), editingId, editingContent);
    setEditingId(null);
    setEditingContent("");
  };

  const handleEditCancel = () => {
    setEditingId(null);
    setEditingContent("");
  };

  return (
    <>
      <Header />
      
      <DetailWrapper>
        {/* 게시글 헤더 */}
        <DetailHeader>
          <h2>{title}</h2>
          <div className="info">
             <span>No. {board.id}</span>
             <span>👤 의뢰인: {writer}</span>
             <span>📅 {formatDate(writeDate) || 'Unknown'}</span>
          </div>
        </DetailHeader>

        {/* 게시글 내용 */}
        <DetailContent>
          {board.imageUrl && (
            <div style={{ marginBottom: '20px', textAlign: 'center' }}>
              <img 
                src={board.imageUrl} 
                alt="의뢰 이미지" 
                style={{
                  maxWidth: '100%',
                  maxHeight: '400px',
                  borderRadius: '8px',
                  boxShadow: '0 4px 12px rgba(0, 0, 0, 0.3)'
                }} 
              />
            </div>
          )}
          {contents}
        </DetailContent>

        {/* 버튼 영역 */}
        <ButtonArea>
          <ActionButton onClick={() => navigator('/board')}>목록으로</ActionButton>
          {isWriter && (
            <>
              <ActionButton onClick={() => navigator(`/boardEdit/${board.id}`)}>수정</ActionButton>
              <ActionButton>삭제</ActionButton>
            </>
          )}
        </ButtonArea>

        {/* 👇 --- 댓글 영역 시작 --- */}
        <CommentArea>
          <CommentHeader>💬 댓글 ({board.comments?.length || 0})</CommentHeader>
          
          {/* 댓글 입력창 */}
          <CommentForm>
            <CommentInput 
              placeholder={currentUser ? "댓글을 입력하세요..." : "로그인이 필요합니다."}
              value={comment}
              onChange={(e) => setComment(e.target.value)}
              disabled={!currentUser}
            />
            <SubmitButton onClick={handleCommentSubmit} disabled={!currentUser}>
              등록
            </SubmitButton>
          </CommentForm>

          {/* 댓글 리스트 */}
          <CommentList>
            {!board.comments || board.comments.length === 0 ? (
              <p className="empty-msg">첫 번째 댓글을 남겨보세요!</p>
            ) : (
              board.comments.map((c) => (
                <CommentItem key={c.id}>
                  <CommentInfo>
                    <strong>{c.writer}</strong>
                    <span>{formatDate(c.date)}</span>
                    {/* 내가 쓴 댓글이면 삭제, 수정 버튼 표시 */}
                    {currentUser?.nickname === c.writer && (
                      <>
                        {editingId === c.id ? (
                          <>
                            <EditText onClick={handleEditComplete}>저장</EditText>
                            <DeleteText onClick={handleEditCancel}>취소</DeleteText>
                          </>
                        ) : (
                          <>
                            <EditText onClick={() => handleEditStart(c.id, c.content)}>수정</EditText>
                            <DeleteText onClick={() => handleDeleteComment(c.id)}>삭제</DeleteText>
                          </>
                        )}
                      </>
                    )}
                  </CommentInfo>
                  {editingId === c.id ? (
                    <CommentInput 
                      value={editingContent}
                      onChange={(e) => setEditingContent(e.target.value)}
                      autoFocus
                    />
                  ) : (
                    <CommentText>{c.content}</CommentText>
                  )}
                </CommentItem>
              ))
            )}
          </CommentList>
        </CommentArea>
        {/* 👆 --- 댓글 영역 끝 --- */}

      </DetailWrapper>
    </>
  );
};

export default BoardDetail;