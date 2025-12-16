// JSDoc Quick Reference (개발자 안내)
// - `@param {Type} name` : 함수 매개변수의 타입과 이름을 문서화합니다.
//    예: `@param {string} id` 는 id가 문자열임을 나타냅니다.
// - 선택적 매개변수 표기: `@param {Type} [name]` 또는 `@param {Type} [name=default]`
//    를 사용하면 매개변수가 선택사항임을 명시할 수 있습니다.
// - `@returns {Type}` : 함수의 반환 타입과 의미를 설명합니다.
// - 주석은 IDE의 자동완성, 타입 힌트, 자동 문서화에 도움됩니다.
//
// JSDoc을 작성하는 이유:
// - 협업 시 함수의 입력/출력을 명확히 하여 오용을 줄입니다.
// - 자동화 도구(문서 생성기, 타입체크, LSP)가 정보를 활용합니다.
// - 코드 읽기가 쉬워지고 유지보수성이 좋아집니다.
//
// 이 파일의 함수들은 입력값이 사용자의 입력 폼(회원가입, 게시글 작성 등)에
// 적절한지 검사하는 단순한 헬퍼입니다. 모든 함수는 입력을 먼저 trim 처리
// 해서 앞뒤 공백을 무시하고 길이/패턴을 검사합니다.

/**
 * 아이디 유효성 검사
 *
 * 설명:
 *  - 사용자 입력으로 들어오는 아이디(ID)가 서비스 규칙을 만족하는지 검사합니다.
 *  - 이 함수는 단순 패턴 검사기이며 '형식'만 검사합니다.
 *
 * 파라미터:
 *  - id (string): 확인할 아이디
 *
 * 반환값:
 *  - (object) { isValid: boolean, message: string }
 *    isValid가 true면 형식 규칙을 만족함
 *    false면 message에 오류 사유가 담김
 *
 * 호출 예시:
 *  - const result = validateId(form.uid);
 *  - if (!result.isValid) { alert(result.message); }
 *
 * 적용 위치:
 *  - 회원가입/로그인 폼에서 입력값 검증
 *  - src/components/Auth/SignUp.jsx, src/components/Auth/Login.jsx
 *
 * @param {string} id - 확인할 아이디 (문자열로 전달)
 * @returns {object} { isValid: boolean, message: string } 검사 결과
 */
export const validateId = (id) => {
  if (typeof id !== 'string') {
    return { isValid: false, message: '아이디는 문자열이어야 합니다.' };
  }
  const trimmed = id.trim();
  if (trimmed.length < 4) {
    return { isValid: false, message: '아이디는 4자 이상이어야 합니다.' };
  }
  if (!/^[a-zA-Z0-9_]+$/.test(trimmed)) {
    return { isValid: false, message: '아이디는 영문, 숫자, 언더스코어(_)만 사용 가능합니다.' };
  }
  return { isValid: true, message: '' };
};

/**
 * 비밀번호 유효성 검사
 *
 * 설명:
 *  - 현재 구현은 최소 길이(8자) 검증합니다. 필요하면 정규식으로
 *    복잡도(대문자/소문자/숫자/특수문자 포함)를 추가하세요.
 *
 * JSDoc 태그 안내:
 *  - `@param {string} password` : password 매개변수는 문자열이어야 함을
 *    문서화합니다.
 *  - `@returns {object}` : { isValid: boolean, message: string }을 반환합니다.
 *
 * 적용 방법:
 *  - 폼 제출 전에 `validatePassword(password)`로 검사하고, isValid가 false면
 *    사용자에게 message를 안내하세요.
 *  - 프로젝트 내 사용처 예시: `src/components/Auth/SignUp.jsx`
 *
 * 사용 예:
 *  validatePassword('pass1234') // { isValid: true, message: '' }
 *  validatePassword('a') // { isValid: false, message: '...' }
 *
 * @param {string} password - 확인할 비밀번호
 * @returns {object} { isValid: boolean, message: string } 검사 결과
 */
export const validatePassword = (password) => {
  if (typeof password !== 'string') {
    return { isValid: false, message: '비밀번호는 문자열이어야 합니다.' };
  }
  const trimmed = password.trim();
  if (trimmed.length < 8) {
    return { isValid: false, message: '비밀번호는 8자 이상이어야 합니다.' };
  }
  return { isValid: true, message: '' };
};

/**
 * 닉네임 유효성 검사
 *
 * 설명:
 *  - 화면에 표시되는 닉네임의 최소/최대 길이를 검사합니다. 특수문자,
 *    이모지 허용 여부는 서비스 정책에 따라 변경하세요.
 *
 * JSDoc 태그 안내 및 사용법:
 *  - `@param {string} nickname` : nickname 매개변수는 문자열로 전달되어야
 *    함을 명시합니다. 이 정보는 IDE의 자동완성/타입 힌트에 도움 됩니다.
 *  - `@returns {object}` : { isValid: boolean, message: string }을 반환합니다.
 *
 * 적용 예시:
 *  - 회원 프로필 편집, 회원가입 폼에서 사용합니다.
 *  - 프로젝트 내 사용처 예시: `src/components/Mypage.jsx`,
 *    `src/components/Auth/SignUp.jsx`
 *
 * 사용 예:
 *  validateNickname('용사') // { isValid: true, message: '' }
 *
 * @param {string} nickname - 확인할 닉네임
 * @returns {object} { isValid: boolean, message: string } 검사 결과
 */
export const validateNickname = (nickname) => {
  if (typeof nickname !== 'string') {
    return { isValid: false, message: '닉네임은 문자열이어야 합니다.' };
  }
  const t = nickname.trim();
  if (t.length < 2) {
    return { isValid: false, message: '닉네임은 2자 이상이어야 합니다.' };
  }
  if (t.length > 20) {
    return { isValid: false, message: '닉네임은 20자 이하여야 합니다.' };
  }
  return { isValid: true, message: '' };
};

/**
 * 게시글 제목 유효성 검사
 *
 * 설명:
 *  - 게시글 제목의 최소/최대 길이를 검사합니다. 제목에 금지어 필터,
 *    HTML 태그 제거 등이 필요하면 추가 전처리를 권장합니다.
 *
 * JSDoc 설명 (개발자 가이드):
 *  - `@param {string} title` : 전달되는 값은 문자열이며, 함수는 앞뒤
 *    공백을 제거한 뒤 길이를 검사합니다.
 *  - `@returns {object}` : { isValid: boolean, message: string }을 반환합니다.
 *
 * 적용 위치:
 *  - 게시글 작성/수정 폼에서 사용합니다.
 *  - 프로젝트 내 사용처 예시: `src/components/BoardWrite.jsx`,
 *    `src/components/BoardEdit.jsx`
 *
 * 사용 예:
 *  validateBoardTitle('의뢰: 고블린 토벌') // { isValid: true, message: '' }
 *
 * @param {string} title - 확인할 제목
 * @returns {object} { isValid: boolean, message: string } 검사 결과
 */
export const validateBoardTitle = (title) => {
  if (typeof title !== 'string') {
    return { isValid: false, message: '제목은 문자열이어야 합니다.' };
  }
  const t = title.trim();
  if (t.length < 1) {
    return { isValid: false, message: '제목은 1자 이상이어야 합니다.' };
  }
  if (t.length > 100) {
    return { isValid: false, message: '제목은 100자 이하여야 합니다.' };
  }
  return { isValid: true, message: '' };
};

/**
 * 게시글 내용 유효성 검사
 *
 * 설명:
 *  - 게시글 본문이 일정 길이 이상인지 검사하여 불완전한 글 등록을
 *    방지합니다. 필요하면 마크다운/HTML 허용 여부를 판단하는 전처리를
 *    추가하세요.
 *
 * JSDoc 및 사용법:
 *  - `@param {string} content` : 게시글 본문 문자열
 *  - `@returns {object}` : { isValid: boolean, message: string }을 반환합니다.
 *
 * 적용 위치:
 *  - `src/components/BoardWrite.jsx`, `src/components/BoardEdit.jsx`
 *
 * 사용 예:
 *  validateBoardContent('고블린 서식지 근처에서 의뢰합니다...') // { isValid: true, message: '' }
 *
 * @param {string} content - 확인할 내용
 * @returns {object} { isValid: boolean, message: string } 검사 결과
 */
export const validateBoardContent = (content) => {
  if (typeof content !== 'string') {
    return { isValid: false, message: '내용은 문자열이어야 합니다.' };
  }
  const t = content.trim();
  if (t.length < 1) {
    return { isValid: false, message: '내용은 1자 이상이어야 합니다.' };
  }
  if (t.length > 2000) {
    return { isValid: false, message: '내용은 2000자 이하여야 합니다.' };
  }
  return { isValid: true, message: '' };
};

/**
 * 댓글 유효성 검사
 *
 * 설명:
 *  - 댓글의 최소/최대 길이를 검사합니다. 댓글은 간결해야 하므로 상한을
 *    설정해 지나친 데이터 사용을 막습니다.
 *
 * JSDoc 태그 설명:
 *  - `@param {string} comment` : 댓글 텍스트
 *  - `@returns {object}` : { isValid: boolean, message: string }을 반환합니다.
 *
 * 적용 위치:
 *  - 게시글 상세의 댓글 등록 폼 등 (`src/components/BoardDetail.jsx`)
 *
 * 사용 예:
 *  validateComment('좋은 의뢰입니다!') // { isValid: true, message: '' }
 *
 * @param {string} comment - 확인할 댓글
 * @returns {object} { isValid: boolean, message: string } 검사 결과
 */
export const validateComment = (comment) => {
  if (typeof comment !== 'string') {
    return { isValid: false, message: '댓글은 문자열이어야 합니다.' };
  }
  const t = comment.trim();
  if (t.length < 1) {
    return { isValid: false, message: '댓글을 입력해주세요.' };
  }
  if (t.length > 500) {
    return { isValid: false, message: '댓글은 500자 이하여야 합니다.' };
  }
  return { isValid: true, message: '' };
};
