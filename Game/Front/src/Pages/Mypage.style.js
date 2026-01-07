import styled from 'styled-components';

export const MypageContainer = styled.div`
    position: relative;
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #121212;
    overflow: hidden;
`;

export const BackgroundImage = styled.img`
    position: absolute;
     top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    z-index: 0;
    filter: brightness(0.3) grayscale(0.5);
`;

export const ProfileCard = styled.div`
    position: relative;
    z-index: 10;
    width: 450px;
    padding: 40px;
    background-color: rgba(10, 10, 10, 0.9);
    border: 1px solid #d4af37;
    box-shadow: 0 0 30px rgba(0, 0, 0, 0.5), inset 0 0 15px rgba(212, 175, 55, 0.1);
`;

export const ProfileTitle = styled.h2`
    color: #d4af37;
    text-align: center;
    font-size: 1.5rem;
    letter-spacing: 5px;
    margin-bottom: 30px;
    border-bottom: 1px solid #d4af37;
    padding-bottom: 10px;
`;

export const InfoSection = styled.div`
    display: flex;
    flex-direction: column;
    gap: 20px;
    margin-bottom: 30px;
`;

export const InfoRow = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 5px 0;
    border-bottom: 1px thin rgba(212, 175, 55, 0.2);
`;

export const Label = styled.span`
    color: #888;
    font-size: 0.9rem;
`;

export const Value = styled.span`
    color: #fff;
    font-size: 1.1rem;
    font-family: 'serif';
`;

export const ButtonGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 10px;
`;

export const MenuButton = styled.button`
    padding: 12px;
    background: ${props => props.exit ? 'transparent' : 'rgba(212, 175, 55, 0.1)'};
    color: #d4af37;
    border: 1px solid #d4af37;
    cursor: pointer;
    font-size: 1rem;
    transition: all 0.3s;

    &:hover {
        background: #d4af37;
        color: #1a1a1a;
    }
`;