import styled from 'styled-components';

export const PageWrapper = styled.div`
  background-color: #121212;
  min-height: calc(100vh - 70px);
  padding: 40px 20px;
`;

export const SkillContainer = styled.div`
  max-width: 1200px;
  margin: 0 auto;
`;

export const SkillTitle = styled.h1`
  color: #d4af37;
  font-size: 2rem;
  margin-bottom: 30px;
  text-align: center;
  font-weight: bold;
`;

export const SkillGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 40px;
`;

export const SkillCard = styled.div`
  background-color: #1e1e1e;
  border: 2px solid ${(props) => props.isLearned ? '#d4af37' : '#444'};
  border-radius: 12px;
  padding: 20px;
  text-align: center;
  transition: all 0.3s;
  cursor: pointer;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.5);

  &:hover {
    border-color: #d4af37;
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(212, 175, 55, 0.3);
  }
`;

export const SkillIcon = styled.div`
  font-size: 3rem;
  margin-bottom: 15px;
`;

export const SkillName = styled.h2`
  color: #d4af37;
  font-size: 1.3rem;
  margin-bottom: 10px;
`;

export const SkillDesc = styled.p`
  color: #aaa;
  font-size: 0.95rem;
  margin-bottom: 15px;
  line-height: 1.5;
  min-height: 40px;
`;

export const SkillStats = styled.div`
  background-color: rgba(0, 0, 0, 0.3);
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 15px;
`;

export const StatItem = styled.div`
  display: flex;
  justify-content: space-between;
  color: #ddd;
  font-size: 0.9rem;
  padding: 5px 0;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);

  &:last-child {
    border-bottom: none;
  }

  span:first-child {
    color: #aaa;
  }

  span:last-child {
    color: #4ade80;
    font-weight: bold;
  }
`;

export const LearnButton = styled.button`
  width: 100%;
  padding: 10px;
  background-color: #d4af37;
  color: #000;
  border: none;
  border-radius: 6px;
  font-weight: bold;
  cursor: pointer;
  transition: 0.2s;

  &:hover:not(:disabled) {
    background-color: #e5c158;
    transform: scale(1.02);
  }

  &:disabled {
    background-color: #666;
    color: #aaa;
    cursor: not-allowed;
  }
`;
