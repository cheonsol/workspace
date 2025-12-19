# 프로젝트 구조 가이드

## 📁 디렉토리 구조

```
src/
├── components/          # React 컴포넌트
│   ├── Auth/           # 인증 관련 컴포넌트 (로그인, 회원가입)
│   ├── Board/          # 게시판 관련 컴포넌트
│   │   ├── BoardListItem.jsx   # 게시글 리스트 아이템
│   │   └── index.js
│   ├── Game/           # 게임 관련 컴포넌트
│   │   ├── GameStatus.jsx      # 게임 상태 표시
│   │   ├── GameActions.jsx     # 게임 액션 버튼
│   │   ├── GameDialogue.jsx    # 게임 대화창
│   │   └── index.js
│   ├── Home.jsx
│   ├── Mypage.jsx
│   ├── Skill.jsx
│   ├── Item.jsx
│   ├── Board.jsx
│   ├── BoardDetail.jsx
│   ├── BoardWrite.jsx
│   ├── BoardEdit.jsx
│   └── ...
│
├── store/              # Zustand 상태 관리
│   ├── user.js         # 사용자 정보
│   ├── monster.js      # 몬스터 데이터
│   ├── skill.js        # 스킬 데이터
│   ├── item.js         # 아이템 데이터
│   └── board.js        # 게시판 데이터
│
├── style/              # Styled Components
│   ├── Game.style.js
│   ├── Board.style.js
│   ├── Auth.style.js
│   └── ...
│
├── layout/             # 레이아웃 컴포넌트
│   └── Header.jsx
│
├── hooks/              # 커스텀 훅
│   ├── useGame.js      # 게임 관련 훅
│   └── index.js
│
├── utils/              # 유틸함수
│   ├── gameUtils.js    # 게임 로직 함수
│   ├── validation.js   # 유효성 검사 함수
│   ├── formatters.js   # 데이터 포맷팅 함수
│   ├── constants.js    # 게임 상수
│   └── index.js
│
├── assets/             # 정적 리소스
├── App.jsx
├── main.jsx
└── index.css
```

## 🎯 컴포넌트 분류

### Game Components (`components/Game/`)
- **GameStatus.jsx**: 플레이어/몬스터 HP, MP 표시
- **GameActions.jsx**: 공격, 스킬, 아이템, 도망 버튼 관리
- **GameDialogue.jsx**: 게임 메시지 출력

### Board Components (`components/Board/`)
- **BoardListItem.jsx**: 게시글 리스트의 개별 아이템

### Auth Components (`components/Auth/`)
- Login, SignUp 관련 컴포넌트 (향후 이동)

## 🔧 Hooks 사용법

### useGameBattle
```javascript
import { useGameBattle } from '../hooks';

const { handleMonsterDefeated, monsters, killCount } = useGameBattle();
handleMonsterDefeated(monster, currentFloor);
```

### useFloor
```javascript
import { useFloor } from '../hooks';

const { currentFloor, canAdvanceFloor, moveToNextFloor } = useFloor();
```

### useStatManagement
```javascript
import { useStatManagement } from '../hooks';

const { stats, statPoints, allocateStat, addStatPoints } = useStatManagement();
```

## 📚 Utils 사용법

### gameUtils
```javascript
import { calculateDamage, isCritical, calculateSkillDamage } from '../utils';

const damage = calculateDamage(10, 1);
const isCrit = isCritical(20);
const skillDmg = calculateSkillDamage(skill, atk);
```

### validation
```javascript
import { validateId, validatePassword, validateNickname } from '../utils';

if (!validateId(userId)) alert('유효한 아이디가 아닙니다.');
```

### formatters
```javascript
import { formatDate, formatNumber, formatGold, truncateText } from '../utils';

console.log(formatDate(new Date())); // YYYY-MM-DD
console.log(formatGold(1500)); // 1,500 G
console.log(truncateText(longText, 50)); // 길이 50 자르고 ... 추가
```

### constants
```javascript
import { GAME_RULES, STATS, COLORS, MESSAGES } from '../utils';

console.log(GAME_RULES.KILL_COUNT_FOR_BOSS); // 5
console.log(MESSAGES.MONSTER_APPEARED('고블린')); // 야생의 [고블린]이(가) 나타났다!
```

## 🚀 최적화 점검 사항

- ✅ 컴포넌트 분리: 큰 컴포넌트를 작은 컴포넌트로 분리
- ✅ 폴더 구조: 기능별로 폴더 정리
- ✅ 유틸함수: 공통 로직을 별도 파일로 분리
- ✅ 커스텀 훅: 상태 관리 로직 재사용 가능하게 구성
- ✅ 상수 관리: 매직넘버를 constants.js로 통합
- ✅ 명확한 네이밍: 폴더명/파일명으로 역할 명시

## 💡 향후 개선 사항

1. **컴포넌트 추가 분리 가능**
   - Mypage.jsx: 프로필/스탯/장비 탭 분리
   - Board.jsx: 게시글 목록/헤더/푸터 분리

2. **폴더 확장**
   - `types/` - TypeScript 타입 정의 (향후 TS 도입 시)
   - `config/` - 설정 파일
   - `services/` - API 서비스 (백엔드 연동 시)

3. **성능 최적화**
   - React.memo 적용
   - useMemo, useCallback 활용
   - 번들 크기 최적화

## 📝 개발 시 주의사항

1. 새 컴포넌트는 해당 폴더에 추가
2. 공통 로직은 utils/hooks로 분리
3. 상수는 utils/constants.js에 추가
4. 폴더의 index.js에서 export 관리
