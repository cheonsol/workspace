// JSDoc Quick Reference (개발자 안내)
// - 상수 파일에는 함수와 달리 `@param`이 없지만, 각 상수가 무엇을 의미하는지
//   상세히 주석으로 남기는 것이 중요합니다.
// - 사용 예: `import { GAME_RULES } from '../utils/constants';` 후
//   `GAME_RULES.KILL_COUNT_FOR_BOSS`로 접근합니다.
// - 상수 정의의 목적: 매직 넘버를 중앙에서 관리하여 일관된 변경과 재사용이
//   가능하도록 합니다.
//
// 게임 전역에서 사용하는 상수 모음
// 이 파일은 하드코딩된 '매직 넘버'를 한 곳에 모아 관리하기 위한 목적입니다.
// 프로젝트 전역에서 사용되는 규칙, 기본값, 텍스트 메시지, 색상 등을 모아두고
// 필요 시 이 파일만 수정해도 전체 동작이 일관되게 바뀌도록 설계합니다.

// -----------------------------
// 게임 규칙 (GAME_RULES)
// -----------------------------
// 게임 전반에 걸친 고정 규칙과 기본값을 정의합니다. 예: 보스 소환 조건,
// 초기 레벨/골드, 최대 층수 등. 게임 밸런스를 조정하려면 여기 값을 바꾸세요.
export const GAME_RULES = {
  KILL_COUNT_FOR_BOSS: 5,  // 보스 소환까지 처치해야 할 몬스터 수
  BASE_LEVEL: 1,           // 초기 레벨 (새 플레이어 시작 레벨)
  BASE_GOLD: 0,            // 초기 골드
  MAX_FLOORS: 3,           // 최대 층 수 (층 확장 시 값 증가)
};

// -----------------------------
// 스탯 관련 상수 (STATS)
// -----------------------------
// 캐릭터의 기본 스탯 및 스탯 포인트가 능력치에 미치는 기본 보정값을 정의.
// 예: 스탯 1 포인트당 HP가 얼마나 오르는지 등을 이곳에서 관리합니다.
export const STATS = {
  BASE_HP: 100,
  BASE_MP: 100,
  BASE_ATK: 10,
  BASE_DEF: 10,
  BASE_DEX: 10,
  BASE_LUK: 10,
  HP_PER_STAT: 10,  // 스탯 1당 HP 증가량
  MP_PER_STAT: 10,  // 스탯 1당 MP 증가량
};

// -----------------------------
// 스킬 관련 상수 (SKILLS)
// -----------------------------
// 스킬의 학습/사용 관련 기본 규칙을 모아둡니다. (예: 스킬 레벨 필요치,
// 스킬의 기본 데미지 배수 등)
export const SKILLS = {
  SKILL_LEVEL_MULTIPLIER: 10, // 스킬 ID * 10 = 필요 레벨 (간단한 예시)
  BASE_SKILL_DAMAGE_MULTIPLIER: 0.1, // 스킬 데미지에 적용되는 공격력 배수
};

// -----------------------------
// 아이템 관련 (ITEMS)
// -----------------------------
// 아이템 희귀도(등급) 등의 문자열 상수를 모아두어, 문자열 오타로 인한
// 버그를 줄입니다.
export const ITEMS = {
  RARITY_COMMON: 'common',
  RARITY_UNCOMMON: 'uncommon',
  RARITY_RARE: 'rare',
  RARITY_LEGENDARY: 'legendary',
};

// -----------------------------
// 게시판 관련 (BOARD)
// -----------------------------
// 게시판 UI/로직에서 사용하는 기본값(미리보기 길이, 배지 기준 등)을 정의
export const BOARD = {
  HOT_BADGE_INDEX: 0,      // HOT 배지 표시 인덱스
  NEW_BADGE_INDEX: 3,      // NEW 배지 표시 기한 (상위 3개까지 NEW 처리)
  PREVIEW_LENGTH: 60,      // 게시글 미리보기 길이
};

// -----------------------------
// HTTP 상태 코드 (미래 API 연동용)
// -----------------------------
// 서버 통신 시 코드 비교에 사용합니다. 숫자 대신 상수를 쓰면 가독성이 좋아집니다.
export const HTTP_STATUS = {
  OK: 200,
  CREATED: 201,
  BAD_REQUEST: 400,
  UNAUTHORIZED: 401,
  NOT_FOUND: 404,
  INTERNAL_ERROR: 500,
};

// -----------------------------
// 메시지 템플릿 (MESSAGES)
// -----------------------------
// 화면에 표시할 문구들을 모아둔 객체입니다. 함수형 메시지는 동적 값을
// 받아 문자열을 반환하도록 구성되어 있으며, 다국어(i18n) 적용 전 단계로
// 간단한 텍스트 출력을 위한 용도입니다.
export const MESSAGES = {
  // 인증 관련
  LOGIN_SUCCESS: '로그인에 성공했습니다!',
  LOGIN_FAILED: '아이디 또는 비밀번호가 잘못되었습니다.',
  LOGOUT_SUCCESS: '로그아웃했습니다.',
  SIGNUP_SUCCESS: '회원가입에 성공했습니다!',
  
  // 게임 관련
  MONSTER_APPEARED: (name) => `야생의 [${name}]이(가) 나타났다!`,
  MONSTER_DEFEATED: (name) => `🎉 ${name}을(를) 처치했다!`,
  BOSS_DEFEATED: '🎉 보스를 격파했습니다!',
  PLAYER_DIED: '터어어어어얼렸구나!',
  
  // 게시판 관련
  BOARD_CREATED: '의뢰가 길드 게시판에 등록되었습니다!',
  BOARD_UPDATED: '의뢰가 수정되었습니다!',
  BOARD_DELETED: '의뢰가 삭제되었습니다!',
  COMMENT_ADDED: '댓글이 등록되었습니다!',
  COMMENT_UPDATED: '댓글이 수정되었습니다!',
  COMMENT_DELETED: '댓글이 삭제되었습니다!',
};

// -----------------------------
// 색상 팔레트 (COLORS)
// -----------------------------
// Styled Components 등에서 재사용할 색상값을 모아둡니다. 디자인 변경 시
// 이곳만 바꾸면 전체 스타일에 적용할 수 있습니다.
export const COLORS = {
  PRIMARY: '#d4af37',     // 금색
  SECONDARY: '#60a5fa',   // 파랑
  SUCCESS: '#4ade80',     // 녹색
  DANGER: '#ff6b6b',      // 빨강
  WARNING: '#fbbf24',     // 주황
  DARK: '#121212',
  DARKER: '#0a0a0a',
};
