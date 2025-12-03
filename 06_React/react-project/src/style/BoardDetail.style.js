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