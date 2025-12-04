import React, { useState, useEffect } from 'react';
import useBoardStore from '../store/board';
import useGameStore from '../store/user'; // 작성자 정보용
import { useNavigate } from 'react-router-dom';
import Header from '../layout/Header';

// 스타일 불러오기
import { 
  PageWrapper, 
  WriteForm, 
  TitleHeader, 
  Label, 
  TitleInput, 
  ContentInput, 
  SubmitButton 
} from '../style/BoardWrite.style';

const BoardWrite = () => {

    const navigator = useNavigate();
    
    // 게시판 스토어 (addBoard)
    const addBoard = useBoardStore((state) => state.addBoard);
    
    // 유저 스토어 (현재 로그인한 사람 확인)
    const currentUser = useGameStore((state) => state.currentUser);

    // 1. 초기값 오류 수정 (문자열 -> 객체)
    const [inputs, setInput] = useState({
        title: '',
        contents: ''
    });

    const { title, contents } = inputs;

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

    const handleSubmit = (e) => {
        e.preventDefault();

        // 2. 유효성 검사
        if (!title.trim() || !contents.trim()) {
            alert("제목과 내용을 모두 입력해주세요.");
            return;
        }

        // 3. 글 저장 (작성자 정보 포함)
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

                    <SubmitButton type="submit">의뢰 등록하기</SubmitButton>
                </WriteForm>
            </PageWrapper>
        </>
    )
}

export default BoardWrite;