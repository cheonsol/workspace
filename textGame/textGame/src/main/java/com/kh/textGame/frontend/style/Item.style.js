import styled from 'styled-components';

export const PageWrapper = styled.div`
  background-color: #121212;
  min-height: calc(100vh - 70px);
  padding: 40px 20px;
`;

export const Container = styled.div`
  max-width: 1200px;
  margin: 0 auto;
`;

export const Title = styled.h1`
  color: #d4af37;
  font-size: 2rem;
  margin-bottom: 30px;
  text-align: center;
  font-weight: bold;
`;

export const TabContainer = styled.div`
  display: flex;
  gap: 10px;
  margin-bottom: 30px;
  border-bottom: 2px solid #444;
`;

export const TabButton = styled.button`
  padding: 12px 25px;
  background-color: ${(props) => props.active ? '#d4af37' : 'transparent'};
  color: ${(props) => props.active ? '#000' : '#aaa'};
  border: none;
  border-bottom: 3px solid ${(props) => props.active ? '#d4af37' : 'transparent'};
  cursor: pointer;
  font-weight: bold;
  font-size: 1rem;
  transition: all 0.2s;

  &:hover {
    color: #d4af37;
  }
`;

export const ContentArea = styled.div`
  min-height: 500px;
`;

export const ItemGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
`;

export const ItemCard = styled.div`
  background-color: #1e1e1e;
  border: 1px solid #444;
  border-radius: 10px;
  padding: 15px;
  text-align: center;
  transition: all 0.3s;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.5);

  &:hover {
    border-color: #d4af37;
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(212, 175, 55, 0.2);
  }
`;

export const ItemIcon = styled.div`
  font-size: 2.5rem;
  margin-bottom: 10px;
`;

export const ItemName = styled.h3`
  color: #d4af37;
  font-size: 1.1rem;
  margin-bottom: 8px;
`;

export const ItemDesc = styled.p`
  color: #aaa;
  font-size: 0.85rem;
  margin-bottom: 10px;
  min-height: 30px;
  line-height: 1.4;
`;

export const ItemPrice = styled.div`
  color: #4ade80;
  font-weight: bold;
  margin-bottom: 10px;
`;

export const ItemRarity = styled.span`
  display: inline-block;
  color: ${(props) => {
    const colors = {
      common: '#888',
      uncommon: '#4ade80',
      rare: '#60a5fa',
      legendary: '#d4af37'
    };
    return colors[props.rarity] || '#888';
  }};
  font-size: 0.8rem;
  font-weight: bold;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 4px 10px;
  border-radius: 4px;
  margin-bottom: 10px;
`;

export const ButtonGroup = styled.div`
  display: flex;
  gap: 8px;
  margin-top: 10px;
`;

export const BuyButton = styled.button`
  flex: 1;
  padding: 8px 12px;
  background-color: #d4af37;
  color: #000;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  transition: all 0.2s;

  &:hover {
    background-color: #e5c158;
  }
`;

export const UseButton = styled.button`
  flex: 1;
  padding: 8px 12px;
  background-color: #60a5fa;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  transition: all 0.2s;

  &:hover {
    background-color: #3b82f6;
  }
`;

export const SellButton = styled.button`
  flex: 1;
  padding: 8px 12px;
  background-color: #ef4444;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  transition: all 0.2s;

  &:hover {
    background-color: #dc2626;
  }
`;

export const InventorySection = styled.div`
  width: 100%;
`;

export const InventoryItem = styled.div`
  background-color: #1e1e1e;
  border: 2px solid #4ade80;
  border-radius: 10px;
  padding: 15px;
  text-align: center;
  transition: all 0.3s;
  box-shadow: 0 4px 15px rgba(74, 222, 128, 0.2);

  &:hover {
    transform: translateY(-3px);
    box-shadow: 0 6px 20px rgba(74, 222, 128, 0.3);
  }
`;

export const ItemQuantity = styled.div`
  color: #4ade80;
  font-weight: bold;
  margin-bottom: 10px;
  font-size: 0.95rem;
`;
