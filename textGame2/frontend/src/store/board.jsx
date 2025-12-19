import { create } from "zustand";
import { fetchBoards as apiFetchBoards, fetchBoard as apiFetchBoard, createBoard as apiCreateBoard, updateBoard as apiUpdateBoard, deleteBoard as apiDeleteBoard } from '../api/boardApi';
import { createComment as apiCreateComment, deleteComment as apiDeleteComment, updateComment as apiUpdateComment, fetchCommentsByBoardId as apiFetchCommentsByBoardId } from '../api/commentApi'; // commentApi 임포트

const useBoardStore = create((set) => ({
    boards: [],
    loading: false,
    error: null,

    fetchBoards: async () => {
        set({ loading: true, error: null });
        try {
            const boards = await apiFetchBoards();
            set({ boards, loading: false });
        } catch (error) {
            set({ error: error.message, loading: false });
        }
    },

    // 단일 게시글 조회 추가
    fetchBoardById: async (boardId) => {
        set({ loading: true, error: null });
        try {
            const board = await apiFetchBoard(boardId);
            set((state) => ({
                boards: state.boards.some(b => b.id === board.id)
                    ? state.boards.map(b => b.id === board.id ? board : b)
                    : [...state.boards, board],
                loading: false,
            }));
            return board;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error; // 컴포넌트에서 에러를 잡을 수 있도록 다시 던짐
        }
    },

    addBoard: async (newBoard) => {
        set({ loading: true, error: null });
        try {
            const createdBoard = await apiCreateBoard(newBoard);
            set((state) => ({
                boards: [...state.boards, createdBoard],
                loading: false,
            }));
        } catch (error) {
            set({ error: error.message, loading: false });
        }
    },

    updateBoard: async (id, targetBoard) => {
        set({ loading: true, error: null });
        try {
            const updatedBoard = await apiUpdateBoard(id, targetBoard);
            set((state) => ({
                boards: state.boards.map((board) =>
                    board.id === id ? updatedBoard : board
                ),
                loading: false,
            }));
        } catch (error) {
            set({ error: error.message, loading: false });
        }
    },

    deleteBoard: async (boardId) => {
        set({ loading: true, error: null });
        try {
            await apiDeleteBoard(boardId);
            set((state) => ({
                boards: state.boards.filter((board) => board.id !== boardId),
                loading: false,
            }));
        } catch (error) {
            set({ error: error.message, loading: false });
        }
    },

    // --- 댓글 관련 액션 (백엔드 연동) ---
    // 특정 게시글의 댓글 불러오기
    fetchComments: async (boardId) => {
        try {
            const comments = await apiFetchCommentsByBoardId(boardId);
            // 해당 게시글에 댓글을 업데이트하는 로직이 필요.
            // BoardDto에 comments 필드를 추가하고, 여기에서 업데이트하도록 할 수 있습니다.
            // 현재 BoardDto에 comments 필드가 없으므로, 이 데이터를 어떻게 처리할지 결정해야 합니다.
            // 임시로 해당 게시글의 comments 필드를 업데이트하는 것으로 가정합니다.
            set((state) => ({
                boards: state.boards.map(board =>
                    board.id === boardId ? { ...board, comments: comments } : board
                )
            }));
            return comments;
        } catch (error) {
            console.error("Failed to fetch comments:", error);
            // 에러 처리를 위한 상태 업데이트 추가 가능
            return [];
        }
    },

    addComment: async (boardId, newCommentData) => {
        set({ loading: true, error: null });
        try {
            const createdComment = await apiCreateComment(boardId, newCommentData);
            set((state) => ({
                boards: state.boards.map((board) =>
                    board.id === boardId
                        ? { ...board, comments: [...(board.comments || []), createdComment] }
                        : board
                ),
                loading: false,
            }));
            return createdComment;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error; // 에러를 호출자에게 다시 던져 컴포넌트에서 처리할 수 있도록 함
        }
    },

    updateComment: async (commentId, updatedCommentData) => {
        set({ loading: true, error: null });
        try {
            const updatedComment = await apiUpdateComment(commentId, updatedCommentData);
            set((state) => ({
                boards: state.boards.map((board) => ({
                    ...board,
                    comments: (board.comments || []).map((comment) =>
                        comment.id === commentId ? updatedComment : comment
                    ),
                })),
                loading: false,
            }));
            return updatedComment;
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },

    deleteComment: async (commentId) => {
        set({ loading: true, error: null });
        try {
            await apiDeleteComment(commentId);
            set((state) => ({
                boards: state.boards.map((board) => ({
                    ...board,
                    comments: (board.comments || []).filter((comment) => comment.id !== commentId),
                })),
                loading: false,
            }));
            return { success: true };
        } catch (error) {
            set({ error: error.message, loading: false });
            throw error;
        }
    },
}));

export default useBoardStore;