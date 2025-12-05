import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from '../layout/Header';
import useBoardStore from '../store/board';
import useGameStore from '../store/user';

import { 
  PageWrapper, 
  WriteForm, 
  TitleHeader, 
  Label, 
  TitleInput, 
  ContentInput, 
  SubmitButton 
} from '../style/BoardWrite.style';

const BoardEdit = () => {
  const { boardId } = useParams();
  const navigator = useNavigate();
  
  // Store
  const boards = useBoardStore((state) => state.boards);
  const updateBoard = useBoardStore((state) => state.updateBoard);
  const currentUser = useGameStore((state) => state.currentUser);

  // State
  const [inputs, setInput] = useState({
    title: '',
    contents: ''
  });

  const { title, contents } = inputs;

  // 게시글 조회
  const board = boards.find((b) => b.id === Number(boardId));

  // 초기값 설정 및 권한 확인
  useEffect(() => {
    if (!board) {
      alert("존재하지 않는 의뢰서입니다.");
      navigator('/board');
      return;
    }

    if (board.writer !== currentUser.nickname) {
      alert("자신의 의뢰서만 수정할 수 있습니다.");
      navigator(`/boardDetail/${boardId}`);
      return;
    }

    // 기존 값 불러오기
    setInput({
      title: board.title,
      contents: board.contents
    });
  }, [board, currentUser, boardId, navigator]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setInput({
      ...inputs,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // 유효성 검사
    if (!title.trim() || !contents.trim()) {
      alert("제목과 내용을 모두 입력해주세요.");
      return;
    }

    // 게시글 수정
    updateBoard({
      id: Number(boardId),
      ...inputs
    });

    alert('의뢰서가 수정되었습니다!');
    navigator(`/boardDetail/${boardId}`);
  };

  if (!board) {
    return null;
  }

  return (
    <>
      <Header />
      
      <PageWrapper>
        <WriteForm onSubmit={handleSubmit}>
          <TitleHeader>📜 의뢰서 수정</TitleHeader>

          <div>
            <Label>의뢰 제목</Label>
            <TitleInput 
              type="text" 
              name="title" 
              value={title}
              onChange={handleChange} 
              placeholder="어떤 의뢰인가요?"
            />
          </div>

          <div>
            <Label>상세 내용</Label>
            <ContentInput 
              name="contents" 
              value={contents}
              onChange={handleChange}
              placeholder="의뢰 내용을 상세히 적어주세요."
            />
          </div>

          <SubmitButton type="submit">수정 완료</SubmitButton>
        </WriteForm>
      </PageWrapper>
    </>
  );
};

export default BoardEdit;
