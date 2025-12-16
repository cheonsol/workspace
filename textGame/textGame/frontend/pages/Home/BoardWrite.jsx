import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
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
  SubmitButton,
  ImageUploadArea,
  ImagePreview
} from '../style/BoardWrite.style';
// 게시판 글 작성 시 사용자 입력값 검증 함수들
import {
  validateBoardTitle,   // 게시판 제목 유효성 검사 (길이, 특수문자)
  validateBoardContent  // 게시판 내용 유효성 검사 (길이)
} from '../utils/validation';

const BoardWrite = () => {

    const navigator = useNavigate();
    
    // 게시판 스토어 (addBoard)
    const addBoard = useBoardStore((state) => state.addBoard);
    
    // 유저 스토어 (현재 로그인한 사람 확인)
    const currentUser = useGameStore((state) => state.currentUser);

    // 1. 초기값 오류 수정 (문자열 -> 객체)
    const [inputs, setInput] = useState({
        title: '',
        contents: '',
        imageUrl: ''
    });

    const { title, contents, imageUrl } = inputs;

    // 로그인 안 한 사람이 들어오면 쫓아내기 (선택 사항)
    useEffect(() => {
        if (!currentUser) {
            alert("의뢰를 등록하려면 로그인이 필요합니다.");
            navigator('/login');
        }
    }, [currentUser, navigator]);

    const handleChange = (e) => {
        const {name, value} = e.target;
        setInput({
            ...inputs,
            [name] : value,
        });
    };

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

    const handleSubmit = (e) => {
        e.preventDefault();

        // 빈 값 체크
        if (!title.trim() || !contents.trim()) {
            alert("제목과 내용을 모두 입력해주세요.");
            return;
        }

        // validateBoardTitle: 제목 유효성 검사 (5-50자, 특수문자 제한)
        const titleValidation = validateBoardTitle(title);
        if (!titleValidation.isValid) {
            alert(titleValidation.message);
            return;
        }

        // validateBoardContent: 내용 유효성 검사 (10-2000자)
        const contentValidation = validateBoardContent(contents);
        if (!contentValidation.isValid) {
            alert(contentValidation.message);
            return;
        }

        // 글 저장 (작성자 정보 포함)
        addBoard({
            ...inputs,
            writer: currentUser.nickname,// 작성자 이름 자동 입력
            // writerId: currentUser?.Uid // 필요하다면 ID도 저장
        });

        alert('의뢰가 길드 게시판에 등록되었습니다!');
        navigator('/board');
    }

    return (
        <>
            <Header /> {/* 헤더 추가 */}
            
            <PageWrapper>
                <WriteForm onSubmit={handleSubmit}>
                    <TitleHeader>📜 의뢰서 작성</TitleHeader>

                    <div>
                        <Label>의뢰 제목</Label>
                        <TitleInput 
                            type="text" 
                            name="title" 
                            value={title} // value 바인딩
                            onChange={handleChange} 
                            placeholder="어떤 의뢰인가요? (예: 슬라임 처치 팟 구함)"
                        />
                    </div>

                    <div>
                        <Label>상세 내용</Label>
                        <ContentInput 
                            name="contents" 
                            value={contents} // value 바인딩
                            onChange={handleChange}
                            placeholder="의뢰 내용을 상세히 적어주세요. (보상, 위치 등)"
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

                    <SubmitButton type="submit">의뢰 등록하기</SubmitButton>
                </WriteForm>
            </PageWrapper>
        </>
    )
}

export default BoardWrite;