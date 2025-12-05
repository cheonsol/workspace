import styled from 'styled-components';

// 전체 화면 배경
export const PageWrapper = styled.div`
  background-color: #121212;
  min-height: calc(100vh - 70px); // 헤더 높이 제외
  display: flex;
  justify-content: center;
  padding-top: 50px;
`;

// 글쓰기 폼 컨테이너 (종이/보드 느낌)
export const WriteForm = styled.form`
  width: 100%;
  max-width: 800px; // 좀 더 넓게
  height: fit-content;
  background-color: #1e1e1e;
  padding: 40px;
  border-radius: 12px;
  border: 1px solid #333;
  border-top: 3px solid #d4af37; // 금색 포인트
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
`;

// 제목 헤더
export const TitleHeader = styled.h2`
  color: #d4af37;
  font-size: 1.8rem;
  margin-bottom: 30px;
  text-align: center;
  font-weight: bold;
  letter-spacing: 1px;
`;

// 입력 라벨
export const Label = styled.label`
  display: block;
  color: #aaa;
  margin-bottom: 10px;
  font-size: 1rem;
  font-weight: 500;
`;

// 제목 입력창
export const TitleInput = styled.input`
  width: 100%;
  padding: 15px;
  background-color: #2a2a2a;
  border: 1px solid #444;
  border-radius: 8px;
  color: #fff;
  font-size: 1.1rem;
  margin-bottom: 25px;
  outline: none;
  transition: border-color 0.2s;

  &:focus {
    border-color: #d4af37;
  }
`;

// 내용 입력창 (Textarea)
export const ContentInput = styled.textarea`
  width: 100%;
  min-height: 300px; /* 길게 설정 */
  padding: 15px;
  background-color: #2a2a2a;
  border: 1px solid #444;
  border-radius: 8px;
  color: #fff;
  font-size: 1rem;
  line-height: 1.6;
  resize: vertical; /* 세로 크기 조절만 허용 */
  outline: none;
  margin-bottom: 30px;
  font-family: inherit; /* 폰트 상속 */

  &:focus {
    border-color: #d4af37;
  }
`;

// 저장 버튼
export const SubmitButton = styled.button`
  width: 100%;
  padding: 15px;
  background-color: #d4af37;
  color: #000;
  font-size: 1.2rem;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.2s;

  &:hover {
    background-color: #fff;
    transform: translateY(-2px);
    box-shadow: 0 0 15px rgba(212, 175, 55, 0.4);
  }
`;

// 이미지 업로드 영역
export const ImageUploadArea = styled.div`
  width: 100%;
  height: 250px;
  border: 2px dashed #d4af37;
  border-radius: 8px;
  background-color: rgba(212, 175, 55, 0.05);
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 25px;
  transition: all 0.3s;
  cursor: pointer;

  &:hover {
    background-color: rgba(212, 175, 55, 0.1);
    border-color: #fff;
  }
`;

// 이미지 미리보기
export const ImagePreview = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  border-radius: 6px;
  overflow: hidden;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;