import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from '../../layout/Header';
import useBoardStore from '../../store/board';
import useGameStore from '../../store/user';

import {
    PageWrapper,
    WriteForm,
    TitleHeader,
    Label,
    TitleInput,
    ContentInput,
    SubmitButton,
    ImageUploadArea,
    ImagePreview
} from '../BoardWrite/BoardWrite.style';

const BoardEdit = () => {
    const { boardId } = useParams();
    const navigate = useNavigate();

    // Store
    const { fetchBoardById, updateBoard, loading, error } = useBoardStore();
    const currentUser = useGameStore((state) => state.currentUser);

    // State
    const [inputs, setInput] = useState({
        title: '',
        contents: '',
        imageUrl: ''
    });
    const [board, setBoard] = useState(null); // 단일 게시글 상태

    const { title, contents, imageUrl } = inputs;

    // 게시글 조회 및 초기값 설정, 권한 확인
    useEffect(() => {
        const loadBoard = async () => {
            try {
                const fetchedBoard = await fetchBoardById(Number(boardId));
                setBoard(fetchedBoard); // 상태에 게시글 저장

                if (fetchedBoard.writer !== currentUser?.nickname) { // currentUser?.nickname 으로 수정
                    alert("자신의 의뢰서만 수정할 수 있습니다.");
                    navigate(`/boardDetail/${boardId}`);
                    return;
                }

                setInput({
                    title: fetchedBoard.title,
                    contents: fetchedBoard.contents,
                    imageUrl: fetchedBoard.imageUrl || ''
                });
            } catch (err) {
                console.error("Failed to load board for editing:", err);
                alert("존재하지 않거나 접근할 수 없는 의뢰서입니다.");
                navigate('/board');
            }
        };

        if (currentUser) { // currentUser가 로드된 후에만 게시글 로드 시도
            loadBoard();
        } else {
            // currentUser가 없으면 (로그인 안됨) 로그인 페이지로 리다이렉트
            alert("로그인이 필요합니다.");
            navigate('/login');
        }
    }, [boardId, currentUser, fetchBoardById, navigate]);


    // 이미지 업로드 처리
    const handleImageUpload = (e) => {
        const file = e.target.files?.[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = (event) => {
                setInput({
                    ...inputs,
                    imageUrl: event.target?.result
                });
            };
            reader.readAsDataURL(file);
        }
    };

    // 이미지 제거
    const handleRemoveImage = () => {
        setInput({
            ...inputs,
            imageUrl: ''
        });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setInput({
            ...inputs,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => { // async 함수로 변경
        e.preventDefault();

        if (!title.trim() || !contents.trim()) {
            alert("제목과 내용을 모두 입력해주세요.");
            return;
        }

        try {
            await updateBoard(Number(boardId), {
                title,
                contents,
                imageUrl,
            });

            alert('의뢰서가 수정되었습니다!');
            navigate(`/boardDetail/${boardId}`);
        } catch (err) {
            alert('의뢰서 수정에 실패했습니다.');
            console.error('Failed to update board:', err);
        }
    };

    if (loading) return <div>Loading board for editing...</div>;
    // board가 없거나 권한 확인 중이라면 폼을 렌더링하지 않음
    if (!board || board.writer !== currentUser?.nickname) { // currentUser?.nickname 으로 수정
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

                    <div>
                        <Label>의뢰 이미지 (선택사항)</Label>
                        <ImageUploadArea>
                            {imageUrl ? (
                                <ImagePreview>
                                    <img src={imageUrl} alt="의뢰 이미지" />
                                    <button
                                        type="button"
                                        onClick={handleRemoveImage}
                                        style={{
                                            position: 'absolute',
                                            top: '10px',
                                            right: '10px',
                                            padding: '5px 10px',
                                            backgroundColor: '#ff6b6b',
                                            color: 'white',
                                            border: 'none',
                                            borderRadius: '4px',
                                            cursor: 'pointer'
                                        }}
                                    >
                                        ❌ 제거
                                    </button>
                                </ImagePreview>
                            ) : (
                                <label style={{ cursor: 'pointer', width: '100%', height: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
                                    <div style={{ fontSize: '2.5rem', marginBottom: '10px' }}>🖼️</div>
                                    <div style={{ fontSize: '1rem', color: '#aaa' }}>클릭하여 이미지를 업로드하세요</div>
                                    <div style={{ fontSize: '0.85rem', color: '#666', marginTop: '5px' }}>PNG, JPG 형식 지원</div>
                                    <input
                                        type="file"
                                        accept="image/*"
                                        onChange={handleImageUpload}
                                        style={{ display: 'none' }}
                                    />
                                </label>
                            )}
                        </ImageUploadArea>
                    </div>

                    <SubmitButton type="submit">수정 완료</SubmitButton>
                </WriteForm>
            </PageWrapper>
        </>
    );
};

export default BoardEdit;
