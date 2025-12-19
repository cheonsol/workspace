import React, { useState, useEffect } from 'react'; // useEffect 추가
import { useParams, useNavigate } from 'react-router-dom';
import Header from '../../layout/Header';
import useBoardStore from '../../store/board';
import useGameStore from '../../store/user';
import {
    DetailWrapper, DetailHeader, DetailContent, ButtonArea, ActionButton,
    CommentArea, CommentHeader, CommentForm, CommentInput, SubmitButton,
    CommentList, CommentItem, CommentInfo, CommentText, DeleteText, EditText
} from './BoardDetail.style';
import {
    formatDate,
    truncateText
} from '../../utils/formatters';

const BoardDetail = () => {
    const { boardId } = useParams();
    const currentUser = useGameStore((state) => state.currentUser);
    const navigate = useNavigate();

    const {
        boards,
        fetchBoardById,
        fetchComments,
        addComment,
        deleteComment,
        updateComment,
        deleteBoard: deleteBoardApi, // 충돌을 피하기 위해 이름 변경
        loading,
        error
    } = useBoardStore();

    const [board, setBoard] = useState(null); // 단일 게시글 상태 관리
    const [comments, setComments] = useState([]); // 댓글 상태 관리
    const [commentInput, setCommentInput] = useState("");
    const [editingId, setEditingId] = useState(null);
    const [editingContent, setEditingContent] = useState("");

    useEffect(() => {
        const loadBoardAndComments = async () => {
            try {
                // 특정 게시글 불러오기
                const fetchedBoard = await fetchBoardById(Number(boardId));
                setBoard(fetchedBoard);

                // 해당 게시글의 댓글 불러오기
                const fetchedComments = await fetchComments(Number(boardId));
                setComments(fetchedComments);
            } catch (err) {
                console.error("Failed to load board or comments:", err);
                // 에러 발생 시 404 페이지로 리다이렉트 또는 에러 메시지 표시
                navigate('/notFound'); // 예시: NotFound 페이지로 이동
            }
        };

        loadBoardAndComments();
    }, [boardId, fetchBoardById, fetchComments, navigate]); // 의존성 배열에 navigate 추가

    if (loading) return <div>Loading...</div>;
    // error는 컴포넌트에서 직접 처리할 수도 있고, navigate('/notFound')를 통해 처리할 수도 있습니다.
    // 여기서는 로딩 스피너만 보여주고, 에러 발생 시 navigate('/notFound')로 처리했습니다.


    if (!board) {
        return (
            <>
                <Header />
                <div style={{ textAlign: 'center', marginTop: '100px', color: 'white' }}>
                    <h2>❌ 존재하지 않는 의뢰서입니다.</h2>
                    <button onClick={() => navigate('/board')} style={{ marginTop: '20px' }}>목록으로 돌아가기</button>
                </div>
            </>
        );
    }

    const { title, contents, writer, writeDate } = board;
    const isWriter = (writer === currentUser?.nickname);

    const handleCommentSubmit = async () => {
        if (!commentInput.trim()) return alert("댓글 내용을 입력해주세요.");
        if (!currentUser) return alert("로그인이 필요합니다.");

        try {
            const newComment = await addComment(Number(boardId), {
                content: commentInput,
            });
            setComments((prev) => [...prev, newComment]); // 로컬 댓글 상태 업데이트
            setCommentInput("");
        } catch (err) {
            alert('댓글 등록에 실패했습니다.');
            console.error('Failed to add comment:', err);
        }
    };

    const handleDeleteComment = async (id) => {
        if (window.confirm("댓글을 삭제하시겠습니까?")) {
            try {
                await deleteComment(id);
                setComments((prev) => prev.filter((c) => c.id !== id)); // 로컬 댓글 상태 업데이트
                alert('댓글이 삭제되었습니다.');
            } catch (err) {
                alert('댓글 삭제에 실패했습니다.');
                console.error('Failed to delete comment:', err);
            }
        }
    };

    const handleEditStart = (id, content) => {
        setEditingId(id);
        setEditingContent(content);
    };

    const handleEditComplete = async () => {
        if (!editingContent.trim()) {
            alert("댓글 내용을 입력해주세요.");
            return;
        }
        try {
            const updatedComment = await updateComment(editingId, { content: editingContent });
            setComments((prev) =>
                prev.map((c) => (c.id === editingId ? updatedComment : c))
            ); // 로컬 댓글 상태 업데이트
            setEditingId(null);
            setEditingContent("");
            alert('댓글이 수정되었습니다.');
        } catch (err) {
            alert('댓글 수정에 실패했습니다.');
            console.error('Failed to update comment:', err);
        }
    };

    const handleEditCancel = () => {
        setEditingId(null);
        setEditingContent("");
    };

    const handleDeleteBoard = async () => {
        if (window.confirm("정말 의뢰서를 삭제하시겠습니까?")) {
            try {
                await deleteBoardApi(Number(boardId));
                alert("의뢰서가 삭제되었습니다.");
                navigate('/board');
            } catch (err) {
                alert('의뢰서 삭제에 실패했습니다.');
                console.error('Failed to delete board:', err);
            }
        }
    };


    return (
        <>
            <Header />

            <DetailWrapper>
                <DetailHeader>
                    <h2>{title}</h2>
                    <div className="info">
                        <span>No. {board.id}</span>
                        <span>👤 의뢰인: {writer}</span>
                        <span>📅 {formatDate(writeDate) || 'Unknown'}</span>
                    </div>
                </DetailHeader>

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

                <ButtonArea>
                    <ActionButton onClick={() => navigate('/board')}>목록으로</ActionButton>
                    {isWriter && (
                        <>
                            <ActionButton onClick={() => navigate(`/boardEdit/${board.id}`)}>수정</ActionButton>
                            <ActionButton onClick={handleDeleteBoard}>삭제</ActionButton>
                        </>
                    )}
                </ButtonArea>

                <CommentArea>
                    <CommentHeader>💬 댓글 ({comments?.length || 0})</CommentHeader>

                    <CommentForm>
                        <CommentInput
                            placeholder={currentUser ? "댓글을 입력하세요..." : "로그인이 필요합니다."}
                            value={commentInput}
                            onChange={(e) => setCommentInput(e.target.value)}
                            disabled={!currentUser}
                        />
                        <SubmitButton onClick={handleCommentSubmit} disabled={!currentUser}>
                            등록
                        </SubmitButton>
                    </CommentForm>

                    <CommentList>
                        {!comments || comments.length === 0 ? (
                            <p className="empty-msg">첫 번째 댓글을 남겨보세요!</p>
                        ) : (
                            comments.map((c) => (
                                <CommentItem key={c.id}>
                                    <CommentInfo>
                                        <strong>{c.writer}</strong>
                                        <span>{formatDate(c.writeDate)}</span> {/* date -> writeDate로 변경 */}
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
            </DetailWrapper>
        </>
    );
};

export default BoardDetail;