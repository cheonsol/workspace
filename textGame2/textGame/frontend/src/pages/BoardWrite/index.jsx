import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
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
  SubmitButton
} from './BoardWrite.style';

const BoardWrite = () => {
    const navigator = useNavigate();
    const addBoard = useBoardStore((state) => state.addBoard);
    const currentUser = useGameStore((state) => state.currentUser);

    const [inputs, setInput] = useState({
        title: '',
        contents: '',
    });

    const { title, contents } = inputs;

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

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!title.trim() || !contents.trim()) {
            alert("제목과 내용을 모두 입력해주세요.");
            return;
        }

        try {
            await addBoard({
                title,
                contents,
                writer: currentUser.nickname,
            });

            alert('의뢰가 길드 게시판에 등록되었습니다!');
            navigator('/board');
        } catch (error) {
            alert('의뢰 등록에 실패했습니다.');
            console.error(error);
        }
    }

    return (
        <>
            <Header />
            <PageWrapper>
                <WriteForm onSubmit={handleSubmit}>
                    <TitleHeader>📜 의뢰서 작성</TitleHeader>
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
                    <SubmitButton type="submit">의뢰 등록하기</SubmitButton>
                </WriteForm>
            </PageWrapper>
        </>
    )
}

export default BoardWrite;