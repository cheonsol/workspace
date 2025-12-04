import React, { useState } from 'react'; // useState 추가
import { useParams, useNavigate } from 'react-router-dom';
import useBoardStore from '../store/board';
import Header from '../layout/Header';
import useGameStore from '../store/user';

// 스타일 import (아래에서 새로 만든 스타일 컴포넌트들도 추가해야 합니다)
import { 
  DetailWrapper, DetailHeader, DetailContent, ButtonArea, ActionButton,
  // 👇 새로 추가된 스타일 컴포넌트들
  CommentArea, CommentHeader, CommentForm, CommentInput, SubmitButton, 
  CommentList, CommentItem, CommentInfo, CommentText, DeleteText, EditText
} from '../style/BoardDetail.style';

const BoardDetail = () => {
  const { boardId } = useParams();
  const currentUser = useGameStore((state) => state.currentUser);
  const navigator = useNavigate();
  const boards = useBoardStore((state) => state.boards);
  const addComment = useBoardStore((state) => state.addComment);
  const deleteComment = useBoardStore((state) => state.deleteComment);
  const updateComment = useBoardStore((state) => state.updateComment);

  // --- 댓글 관련 State ---
  const [comment, setComment] = useState(""); // 입력된 댓글
  const [editingId, setEditingId] = useState(null); // 수정 중인 댓글 ID
  const [editingContent, setEditingContent] = useState(""); // 수정 중인 내용

  const board = boards.find((b) => b.id === Number(boardId));

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
  const isWriter = (writer === currentUser?.nickname);

  // --- 댓글 등록 핸들러 ---
  const handleCommentSubmit = () => {
    if (!currentUser) return alert("로그인이 필요합니다.");
    if (!comment.trim()) return alert("댓글 내용을 입력해주세요.");

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
    if (window.confirm("댓글을 삭제하시겠습니까?")) {
      deleteComment(Number(boardId), id);
    }
  };

  // --- 댓글 수정 시작 ---
  const handleEditStart = (id, content) => {
    setEditingId(id);
    setEditingContent(content);
  };

  // --- 댓글 수정 완료 ---
  const handleEditComplete = () => {
    if (!editingContent.trim()) {
      alert("댓글 내용을 입력해주세요.");
      return;
    }
    updateComment(Number(boardId), editingId, editingContent);
    setEditingId(null);
    setEditingContent("");
  };

  // --- 댓글 수정 취소 ---
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
             <span>📅 {writeDate ? writeDate.toString().slice(0, 21) : 'Unknown'}</span>
          </div>
        </DetailHeader>

        {/* 게시글 내용 */}
        <DetailContent>
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
                    <span>{c.date}</span>
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