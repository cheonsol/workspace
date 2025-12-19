import React from 'react';
import {
  QuestItem,
  QuestBadge,
  QuestImage,
  QuestContent,
  QuestStats,
  QuestMeta
} from '../../pages/Board/Board.style';
// 게시판 리스트 아이템 포매팅 함수들
import {
  formatDate,   // 날짜 포매팅
  truncateText  // 텍스트 자르기
} from '../../utils/formatters';

// board, index, onClickItem props를 전달받음
// board는 어디서 가져오는거임?
const BoardListItem = ({ board, index, onClickItem }) => {
  return (
    <QuestItem onClick={() => onClickItem(board.id)}>
      <QuestBadge $index={index}>
        {/* index = 조회수? index가 0이면 hot, 3보다 작으면 new 혹은 board.id */}
        {/* index가 뭐임? */}
        {index === 0 ? '🔥 HOT' : index < 3 ? '⭐ NEW' : `#${board.id}`}
      </QuestBadge>

      {board.imageUrl && (
        <QuestImage>
          <img src={board.imageUrl} alt={board.title} />
        </QuestImage>
      )}

      <QuestContent>
        <div className="title">{board.title}</div>
        <div className="preview">{truncateText(board.contents, 60)}...</div>
      </QuestContent>

      <QuestStats>
        <span title="댓글 수">💬 {board.comments?.length || 0}</span>
      </QuestStats>

      <QuestMeta>
        <span className="writer">👤 {board.writer || '익명'}</span>
        <span className="date">📅 {formatDate(board.writeDate)}</span>
      </QuestMeta>
    </QuestItem>
  );
};

export default BoardListItem;
