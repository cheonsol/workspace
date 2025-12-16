import { create } from "zustand";
import { persist } from "zustand/middleware";

// Store 생성
const useBoardStore = create(
    // 저장
    persist(
        // 설정
        set => ({
            // 보드 배열 생성
            boards : [],
            // 보드 생성 함수 작성 : (새로운 보드를 받는다) => 설정한다.(원래 있던 값) => 
                //스프레드 + 새로운 보드 스프레드 + 입력받은 값.
            addBoard : (newBoard) => set((state) =>({
                boards : [...state.boards, {
                    
                // 이미 있는 형식에 새로운 보드를 더해야 함.
                id : state.boards.length + 1,
                title : '',
                contents : '',
                writer : '',
                writeDate : new Date().toLocaleString(),
                show : true,
                comments : [],
                imageUrl : '',

                ...newBoard,
            }]
            })),

             updateBoard : (targetBoard) => set((state) =>({
                boards : state.boards.map((board) => board.id === targetBoard.id ? {...board, ...targetBoard} : board)
                
            })),
             deleteBoard : (boardId) => set((state) =>({
                boards : state.boards.map((board) => board.id === boardId ? {...board , show : false} : board) 
            })),

            // 댓글 추가
            addComment : (boardId, newComment) => set((state) =>({
                boards : state.boards.map((board) => 
                    board.id === boardId 
                        ? {...board, comments: [...(board.comments || []), newComment]}
                        : board
                )
            })),

            // 댓글 삭제
            deleteComment : (boardId, commentId) => set((state) =>({
                boards : state.boards.map((board) => 
                    board.id === boardId 
                        ? {...board, comments: board.comments.filter(c => c.id !== commentId)}
                        : board
                )
            })),

            // 댓글 수정
            updateComment : (boardId, commentId, updatedContent) => set((state) =>({
                boards : state.boards.map((board) => 
                    board.id === boardId 
                        ? {...board, comments: board.comments.map(c => 
                            c.id === commentId 
                                ? {...c, content: updatedContent, date: new Date().toLocaleString()}
                                : c
                          )}
                        : board
                )
            })),
        }),
            {
                name : 'board-storage',
            }
              
            
       
    )
)

export default useBoardStore