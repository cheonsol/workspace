// JSDoc Quick Reference (개발자 안내)
// - `@param {Type} name` : 매개변수의 타입과 이름을 문서화합니다.
// - 기본값/선택 매개변수: `@param {Type} [name=default]` 또는 `@param {Type} [name]`를 사용
//   합니다.
// - `@returns {Type}` : 반환 타입과 반환 값의 의미를 적습니다.
// - 예시/사용처를 함께 적으면 호출자가 빠르게 이해할 수 있습니다.
//
// 데이터 포맷팅 관련 유틸함수
// 이 파일의 함수들은 UI에 표시할 때 가독성을 높이기 위해 값을 보기 좋은
// 문자열로 바꿔주는 헬퍼입니다. 입력이 null/undefined/비어있는 문자열일
// 경우 빈 문자열을 반환하도록 안전 처리를 해두었습니다.

/**
 * 날짜 포맷팅
 *
 * 설명:
 *  - `date` 파라미터로 전달된 값(Date 객체 또는 날짜 문자열)을
 *    사람이 읽기 편한 로케일 형식으로 변환합니다. 내부적으로 `new Date()`를
 *    사용하므로 브라우저가 해석할 수 있는 표준 형식을 전달하세요.
 *
 * JSDoc 가이드 (@param 예시 설명):
 *  - `@param {string|Date} date` : 이 태그는 개발자에게 'date'가 문자열이거나
 *    Date 객체가 되어야 함을 알려줍니다. 타입 힌트는 자동완성 및 문서화에
 *    유용합니다.
 *  - `@returns {string}` : 반환 형식(문자열)을 명시합니다.
 *
 * 적용 및 사용 예:
 *  - 게시글 작성 시간, 유저 프로필의 가입일자 등 UI에 사용합니다.
 *  - 예: `formatDate(post.writeDate)`
 *
 * 엣지 케이스:
 *  - `new Date()`가 `Invalid Date`를 반환하면 빈 문자열을 반환하도록
 *    안전 처리되어 있습니다.
 *
 * @param {string|Date} date - 포맷할 날짜
 * @returns {string} 포맷된 날짜 (로케일별 표기)
 */
export const formatDate = (date) => {
  if (!date) return '';
  const d = new Date(date);
  if (isNaN(d.getTime())) return '';
  return d.toLocaleDateString('ko-KR');
};

/**
 * 시간 포맷팅
 *
 * 설명:
 *  - 시각 정보를 읽기 좋은 형태(시:분:초)로 변환합니다. 시간대 및
 *    로케일에 따라 표시 형식이 달라질 수 있으니 UI 요구사항에 따라
 *    추가 포맷을 적용하세요.
 *
 * JSDoc 가이드:
 *  - `@param {string|Date} date` : Date 또는 날짜 문자열을 허용합니다.
 *  - `@returns {string}` : 시각 문자열 반환
 *
 * 적용 예:
 *  - 채팅 메시지의 보낸 시간, 로그 타임스탬프 등에서 사용합니다.
 *
 * @param {string|Date} date - 포맷할 날짜
 * @returns {string} 포맷된 시간
 */
export const formatTime = (date) => {
  if (!date) return '';
  const d = new Date(date);
  if (isNaN(d.getTime())) return '';
  return d.toLocaleTimeString('ko-KR');
};

/**
 * 숫자 포맷팅 (천단위 구분)
 *
 * 설명:
 *  - 숫자 또는 숫자로 변환 가능한 값을 받아 `toLocaleString`으로
 *    천 단위 구분자를 넣은 문자열을 반환합니다. 반환값은 프론트엔드
 *    표시용으로 적합합니다.
 *
 * JSDoc 가이드:
 *  - `@param {number} num` : 입력은 숫자 또는 숫자형으로 변환 가능한 값
 *  - `@returns {string}` : 천 단위 구분이 적용된 문자열
 *
 * 적용 예:
 *  - 골드, 경험치, 가격 등 통화/숫자 표시에 사용합니다.
 *  - 프로젝트 내 사용처: `src/components/Mypage.jsx` 등
 *
 * 엣지 케이스 처리:
 *  - null/undefined 또는 숫자로 변환 불가한 값은 빈 문자열을 반환합니다.
 *
 * @param {number} num - 포맷할 숫자
 * @returns {string} 포맷된 숫자
 */
export const formatNumber = (num) => {
  if (num === null || num === undefined || Number.isNaN(Number(num))) return '';
  return Number(num).toLocaleString('ko-KR');
};

/**
 * 골드 포맷팅
 *
 * 설명:
 *  - 숫자 포맷팅 결과에 ' G' 접미사를 붙여 골드 단위로 표시합니다.
 *
 * JSDoc:
 *  - `@param {number} gold` : 골드 값
 *  - `@returns {string}` : 포맷된 골드 문자열
 *
 * 적용 예:
 *  - 인벤토리, 상점, 보상 화면 등에서 사용됩니다.
 *
 * @param {number} gold - 포맷할 골드
 * @returns {string} 포맷된 골드 (예: "1,500 G")
 */
export const formatGold = (gold) => {
  return `${formatNumber(gold)} G`;
};

/**
 * 경험치 포맷팅
 *
 * 설명:
 *  - 숫자 포맷팅 후 ' EXP' 접미사를 붙여 경험치를 표시합니다.
 *
 * @param {number} exp - 포맷할 경험치
 * @returns {string} 포맷된 경험치 (예: "1,500 EXP")
 */
export const formatExp = (exp) => {
  return `${formatNumber(exp)} EXP`;
};

/**
 * 텍스트 자르기 (줄임표 추가)
 *
 * 설명:
 *  - 긴 텍스트를 지정한 길이로 잘라서 미리보기용으로 사용합니다. 잘린
 *    경우 끝에 '...'을 붙입니다.
 *
 * JSDoc 가이드:
 *  - `@param {string} text` : 자를 문자열
 *  - `@param {number} length` : 최대 길이(기본값 60)
 *  - `@returns {string}` : 잘린 결과 문자열
 *
 * 적용 예:
 *  - 게시글 목록, 미리보기 카드 등에서 사용됩니다.
 *
 * 주의사항:
 *  - 유니코드(이모지 등)가 포함된 문자열에서 글자 단위로 정확히 자르려면
 *    추가 처리가 필요할 수 있습니다.
 *
 * @param {string} text - 자를 텍스트
 * @param {number} length - 최대 길이
 * @returns {string} 자른 텍스트
 */
export const truncateText = (text, length = 60) => {
  if (!text) return '';
  return text.length > length ? text.substring(0, length) + '...' : text;
};
