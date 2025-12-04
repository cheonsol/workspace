import styled from 'styled-components';

export const DetailWrapper = styled.div`
  max-width: 800px;
  margin: 40px auto;
  padding: 40px;
  background-color: #1e1e1e; /* 어두운 배경 */
  border: 1px solid #444;
  border-radius: 10px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.8);
  color: #e0e0e0;
  position: relative;
  
  /* 상단 금색 포인트 */
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 3px;
    background: linear-gradient(90deg, transparent, #d4af37, transparent);
  }
`;

export const DetailHeader = styled.div`
  border-bottom: 1px solid #333;
  padding-bottom: 20px;
  margin-bottom: 30px;

  h2 {
    font-size: 2rem;
    color: #d4af37; /* 제목 금색 */
    margin-bottom: 10px;
  }

  .info {
    font-size: 0.9rem;
    color: #888;
    display: flex;
    gap: 15px;
  }
`;

export const DetailContent = styled.div`
  min-height: 300px;
  font-size: 1.1rem;
  line-height: 1.8;
  color: #ddd;
  white-space: pre-wrap; /* 줄바꿈 적용 */
`;

export const ButtonArea = styled.div`
  margin-top: 40px;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
`;

export const ActionButton = styled.button`
  padding: 10px 20px;
  border: 1px solid #d4af37;
  background: transparent;
  color: #d4af37;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  transition: all 0.2s;

  &:hover {
    background: #d4af37;
    color: #000;
  }
`;

// ... (기존 스타일 코드 아래에 이어 붙이세요)

// --- 댓글 영역 스타일 ---

export const CommentArea = styled.div`
  margin-top: 40px;
  padding-top: 20px;
  border-top: 2px dashed rgba(255, 255, 255, 0.2);
  width: 100%;
`;

export const CommentHeader = styled.h3`
  font-size: 1.2rem;
  color: #eee;
  margin-bottom: 15px;
`;

export const CommentForm = styled.div`
  display: flex;
  gap: 10px;
  margin-bottom: 30px;
`;

export const CommentInput = styled.input`
  flex: 1;
  padding: 12px 15px;
  border-radius: 8px;
  border: 1px solid #555;
  background-color: rgba(0, 0, 0, 0.3);
  color: white;
  font-size: 1rem;

  &:focus {
    outline: none;
    border-color: #aaa;
  }
`;

export const SubmitButton = styled.button`
  padding: 0 20px;
  background-color: #555;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: bold;
  transition: 0.2s;

  &:hover {
    background-color: #777;
  }
  &:disabled {
    background-color: #333;
    cursor: not-allowed;
  }
`;

export const CommentList = styled.ul`
  list-style: none;
  padding: 0;
  
  .empty-msg {
    color: #888;
    text-align: center;
    padding: 20px;
  }
`;

export const CommentItem = styled.li`
  background-color: rgba(255, 255, 255, 0.05);
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 10px;
  text-align: left;
`;

export const CommentInfo = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 8px;
  font-size: 0.9rem;
  justify-content: flex-start;

  strong {
    color: #4ade80; /* 유저 닉네임 색상 */
  }

  span {
    color: #aaa;
    font-size: 0.8rem;
  }
`;
export const DeleteText = styled.span`
  cursor: pointer;
  color: #ff6b6b !important;
  margin-left: 5px;
  
  &:hover {
    text-decoration: underline;
  }
`;

export const EditText = styled.span`
  cursor: pointer;
  color: #60a5fa !important;
  margin-left: 5px;
  
  &:hover {
    text-decoration: underline;
  }
`;

export const CommentText = styled.p`
  color: #ddd;
  line-height: 1.4;
  white-space: pre-wrap; /* 줄바꿈 허용 */
  margin: 0;
  padding-left: 0;
  white-space: pre-wrap; /* 줄바꿈 허용 */
`;