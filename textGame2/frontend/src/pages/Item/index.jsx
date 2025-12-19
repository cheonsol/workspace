import React, { useState, useEffect } from 'react';
import Header from '../../layout/Header';
import useItemStore from '../../store/item'; // 모든 아이템 목록 (상점)
import useGameStore from '../../store/user'; // 사용자 인벤토리 및 게임 액션
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
    ItemRarity, // ItemRarity는 ItemType으로 대체될 예정
    ButtonGroup,
    BuyButton,
    InventorySection,
    InventoryItem,
    ItemQuantity,
    UseButton,
    SellButton,
    EquipButton // 장착 버튼 추가
} from './Item.style';

const Item = () => {
    const [activeTab, setActiveTab] = useState('shop'); // 'shop' or 'inventory'

    // useItemStore에서 모든 아이템 (상점 판매용) 가져오기
    const { items: allItems, fetchItems } = useItemStore();

    // useGameStore에서 사용자 정보 및 아이템 관련 액션 가져오기
    const {
        currentUser,
        buyItem,
        useItem,
        sellItem,
        toggleEquipItem,
        loading: gameLoading,
        error: gameError
    } = useGameStore();

    // currentUser에서 인벤토리와 골드를 가져옴
    const inventory = currentUser?.inventory || [];
    const playerGold = currentUser?.gold || 0;

    useEffect(() => {
        fetchItems(); // 상점 아이템 목록 불러오기
    }, [fetchItems]);


    const handleBuyItem = async (itemId, price) => {
        if (!currentUser) {
            alert("로그인이 필요합니다.");
            return;
        }
        if (playerGold < price) {
            alert("골드가 부족합니다!");
            return;
        }
        if (window.confirm(`${price} 골드를 사용하여 아이템을 구매하시겠습니까?`)) {
            try {
                await buyItem(itemId, 1); // 수량은 일단 1로 고정
                alert('아이템을 구매했습니다!');
            } catch (error) {
                alert(`아이템 구매에 실패했습니다: ${error.message}`);
                console.error('Buy item failed:', error);
            }
        }
    };

    const handleUseItem = async (memberItemId, itemType) => {
        if (!currentUser) {
            alert("로그인이 필요합니다.");
            return;
        }
        if (itemType !== 'POTION' && itemType !== 'CONSUMABLE') {
            alert("사용할 수 없는 아이템입니다.");
            return;
        }
        if (window.confirm("아이템을 사용하시겠습니까?")) {
            try {
                await useItem(memberItemId);
                alert('아이템을 사용했습니다!');
            } catch (error) {
                alert(`아이템 사용에 실패했습니다: ${error.message}`);
                console.error('Use item failed:', error);
            }
        }
    };

    const handleSellItem = async (memberItemId, price) => {
        if (!currentUser) {
            alert("로그인이 필요합니다.");
            return;
        }
        if (window.confirm(`${price / 2} 골드를 받고 아이템을 판매하시겠습니까?`)) { // 판매가는 구매가의 절반으로 가정
            try {
                await sellItem(memberItemId, 1); // 수량은 일단 1로 고정
                alert('아이템을 판매했습니다!');
            } catch (error) {
                alert(`아이템 판매에 실패했습니다: ${error.message}`);
                console.error('Sell item failed:', error);
            }
        }
    };

    const handleToggleEquipItem = async (memberItemId, itemType, equipped) => {
        if (!currentUser) {
            alert("로그인이 필요합니다.");
            return;
        }
        if (itemType !== 'WEAPON' && itemType !== 'ARMOR') {
            alert("장착할 수 없는 아이템입니다.");
            return;
        }
        const action = equipped ? "해제" : "장착";
        if (window.confirm(`아이템을 ${action}하시겠습니까?`)) {
            try {
                await toggleEquipItem(memberItemId);
                alert(`아이템을 ${action}했습니다!`);
            } catch (error) {
                alert(`아이템 ${action}에 실패했습니다: ${error.message}`);
                console.error('Toggle equip item failed:', error);
            }
        }
    };

    // ItemType을 기반으로 색상 결정
    const getItemTypeColor = (type) => {
        switch (type) {
            case 'WEAPON': return '#e74c3c'; // Red
            case 'ARMOR': return '#3498db';  // Blue
            case 'POTION': return '#2ecc71'; // Green
            case 'CONSUMABLE': return '#f1c40f'; // Yellow
            case 'KEY': return '#9b59b6';    // Purple
            default: return '#bdc3c7';       // Gray
        }
    };

    // 로딩 중이거나 에러 발생 시 처리
    if (gameLoading) return <div>Loading...</div>;
    if (gameError) return <div>Error: {gameError}</div>;

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
                            🏪 상점 (내 골드: {playerGold})
                        </TabButton>
                        <TabButton
                            active={activeTab === 'inventory'}
                            onClick={() => setActiveTab('inventory')}
                        >
                            🎒 인벤토리 ({inventory.reduce((sum, item) => sum + item.quantity, 0)})
                        </TabButton>
                    </TabContainer>

                    <ContentArea>
                        {/* 상점 탭 */}
                        {activeTab === 'shop' && (
                            <ItemGrid>
                                {allItems.length === 0 ? (
                                    <p style={{ color: '#888', textAlign: 'center', padding: '40px', gridColumn: '1 / -1' }}>
                                        판매 중인 아이템이 없습니다.
                                    </p>
                                ) : (
                                    allItems.map((item) => (
                                        <ItemCard key={item.id}>
                                            <ItemIcon>{item.img}</ItemIcon> {/* icon -> img */}
                                            <ItemName>{item.name}</ItemName>
                                            <ItemDesc>{item.description}</ItemDesc>
                                            <ItemRarity color={getItemTypeColor(item.type)}> {/* rarity -> type, color prop으로 전달 */}
                                                {item.type}
                                            </ItemRarity>
                                            <ItemPrice>💰 {item.price}원</ItemPrice>
                                            <ButtonGroup>
                                                <BuyButton onClick={() => handleBuyItem(item.id, item.price)} disabled={!currentUser || playerGold < item.price}>
                                                    구매
                                                </BuyButton>
                                            </ButtonGroup>
                                        </ItemCard>
                                    ))
                                )}
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
                                        {inventory.map((memberItem) => ( // memberItem으로 변경
                                            <InventoryItem key={memberItem.id}>
                                                <ItemIcon>{memberItem.itemImg}</ItemIcon> {/* item.icon -> memberItem.itemImg */}
                                                <ItemName>{memberItem.itemName}</ItemName> {/* item.name -> memberItem.itemName */}
                                                <ItemDesc>{memberItem.itemDescription}</ItemDesc> {/* item.description -> memberItem.itemDescription */}
                                                <ItemRarity color={getItemTypeColor(memberItem.itemType)}>
                                                    {memberItem.itemType} {memberItem.equipped ? '(장착중)' : ''}
                                                </ItemRarity>
                                                <ItemQuantity>수량: {memberItem.quantity}</ItemQuantity>
                                                <ButtonGroup>
                                                    {(memberItem.itemType === 'POTION' || memberItem.itemType === 'CONSUMABLE') && (
                                                        <UseButton onClick={() => handleUseItem(memberItem.id, memberItem.itemType)} disabled={memberItem.quantity === 0}>
                                                            사용
                                                        </UseButton>
                                                    )}
                                                    {(memberItem.itemType === 'WEAPON' || memberItem.itemType === 'ARMOR') && (
                                                        <EquipButton
                                                            onClick={() => handleToggleEquipItem(memberItem.id, memberItem.itemType, memberItem.equipped)}
                                                            equipped={memberItem.equipped}
                                                        >
                                                            {memberItem.equipped ? '장착 해제' : '장착'}
                                                        </EquipButton>
                                                    )}
                                                    <SellButton onClick={() => handleSellItem(memberItem.id, memberItem.itemPrice)} disabled={memberItem.quantity === 0}>
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
