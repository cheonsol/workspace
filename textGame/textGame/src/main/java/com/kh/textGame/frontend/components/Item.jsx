import React, { useState } from 'react';
import Header from '../layout/Header';
import useItemStore from '../store/item';
import { 
  PageWrapper, 
  Container,
  Title,
  TabContainer,
  TabButton,
  ContentArea,
  ItemGrid,
  ItemCard,
  ItemIcon,
  ItemName,
  ItemDesc,
  ItemPrice,
  ItemRarity,
  ButtonGroup,
  BuyButton,
  InventorySection,
  InventoryItem,
  ItemQuantity,
  UseButton,
  SellButton
} from '../style/Item.style';

const Item = () => {
  const [activeTab, setActiveTab] = useState('shop'); // 'shop' or 'inventory'
  const allItems = useItemStore((state) => state.allItems);
  const inventory = useItemStore((state) => state.inventory);
  const buyItem = useItemStore((state) => state.buyItem);
  const useItem = useItemStore((state) => state.useItem);
  const sellItem = useItemStore((state) => state.sellItem);

  const handleBuyItem = (itemId) => {
    buyItem(itemId, 1);
    alert('아이템을 구매했습니다!');
  };

  const handleUseItem = (itemId) => {
    useItem(itemId);
    alert('아이템을 사용했습니다!');
  };

  const handleSellItem = (itemId) => {
    sellItem(itemId, 1);
    alert('아이템을 판매했습니다!');
  };

  const getRarityColor = (rarity) => {
    const colors = {
      common: '#888',
      uncommon: '#4ade80',
      rare: '#60a5fa',
      legendary: '#d4af37'
    };
    return colors[rarity] || '#888';
  };

  return (
    <>
      <Header />
      <PageWrapper>
        <Container>
          <Title>🎁 아이템</Title>

          <TabContainer>
            <TabButton 
              active={activeTab === 'shop'}
              onClick={() => setActiveTab('shop')}
            >
              🏪 상점
            </TabButton>
            <TabButton 
              active={activeTab === 'inventory'}
              onClick={() => setActiveTab('inventory')}
            >
              🎒 인벤토리 ({inventory.length})
            </TabButton>
          </TabContainer>

          <ContentArea>
            {/* 상점 탭 */}
            {activeTab === 'shop' && (
              <ItemGrid>
                {allItems.map((item) => (
                  <ItemCard key={item.id}>
                    <ItemIcon>{item.icon}</ItemIcon>
                    <ItemName>{item.name}</ItemName>
                    <ItemDesc>{item.description}</ItemDesc>
                    <ItemRarity rarity={item.rarity}>
                      {item.rarity.toUpperCase()}
                    </ItemRarity>
                    <ItemPrice>💰 {item.price}원</ItemPrice>
                    <ButtonGroup>
                      <BuyButton onClick={() => handleBuyItem(item.id)}>
                        구매
                      </BuyButton>
                    </ButtonGroup>
                  </ItemCard>
                ))}
              </ItemGrid>
            )}

            {/* 인벤토리 탭 */}
            {activeTab === 'inventory' && (
              <InventorySection>
                {inventory.length === 0 ? (
                  <p style={{ color: '#888', textAlign: 'center', padding: '40px' }}>
                    아이템이 없습니다. 상점에서 구매해보세요!
                  </p>
                ) : (
                  <ItemGrid>
                    {inventory.map((item) => (
                      <InventoryItem key={item.id}>
                        <ItemIcon>{item.icon}</ItemIcon>
                        <ItemName>{item.name}</ItemName>
                        <ItemDesc>{item.description}</ItemDesc>
                        <ItemQuantity>수량: {item.quantity}</ItemQuantity>
                        <ButtonGroup>
                          {item.type === 'potion' && (
                            <UseButton onClick={() => handleUseItem(item.id)}>
                              사용
                            </UseButton>
                          )}
                          <SellButton onClick={() => handleSellItem(item.id)}>
                            판매
                          </SellButton>
                        </ButtonGroup>
                      </InventoryItem>
                    ))}
                  </ItemGrid>
                )}
              </InventorySection>
            )}
          </ContentArea>
        </Container>
      </PageWrapper>
    </>
  );
};

export default Item;
