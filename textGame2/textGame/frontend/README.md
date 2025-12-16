# 🎮 THE ADVENTURER'S GUILD - TEXT RPG PROJECT

모험가 길드에 가입하여 던전을 탐험하고 몬스터를 처치하는 **텍스트 RPG 게임**입니다.

![React](https://img.shields.io/badge/React-18+-blue)
![Zustand](https://img.shields.io/badge/Zustand-State%20Management-green)
![Styled Components](https://img.shields.io/badge/Styled%20Components-CSS--in--JS-pink)

---

## 📋 목차
- [주요 기능](#-주요-기능)
- [게임 시스템](#-게임-시스템)
- [설치 및 실행](#-설치-및-실행)
- [테스트 계정](#-테스트-계정)
- [프로젝트 구조](#-프로젝트-구조)

---

## ✨ 주요 기능

### 🔐 회원 시스템
- **회원가입**: 아이디, 비밀번호, 닉네임으로 계정 생성
- **로그인**: 안전한 로그인 및 세션 관리
- **프로필**: 캐릭터 정보, 스탯, 보유 골드 확인

### ⚔️ 게임 플레이
- **층별 던전**: 1층~3층의 다양한 던전 탐험
- **몬스터 전투**: 실시간 전투 시스템
- **보스 시스템**: 5마리 몬스터 처치 후 보스 출현
- **보스 처치 보상**: 경험치, 골드, 레어 아이템, 스킬북

### 🛍️ 상점 시스템
- **스킬 상점**: 레벨별 스킬 해금 (Lv 10/20/30/40)
- **아이템 상점**: 골드로 아이템 구매

### 🎒 인벤토리 & 장비 시스템
- **인벤토리**: 아이템 관리
- **착용 시스템**: 아이템 장착/해제

### 💬 게시판
- **의뢰 작성/조회/수정/삭제**
- **댓글 기능**: 작성자만 수정/삭제 가능

---

## 🎮 게임 시스템

### 📊 스탯 시스템
| 스탯 | 설명 |
|------|------|
| **LV** | 레벨 |
| **HP** | 체력 |
| **MP** | 마나 |
| **ATK** | 공격력 |
| **DEF** | 방어력 |
| **DEX** | 회피력 |
| **LUK** | 치명타 확률 (최대 100%) |

### ⚔️ 전투 시스템
```
플레이어 턴:
1. 공격 - 기본 데미지
2. 스킬 - 배운 스킬로 공격 (마나 소비)
3. 아이템 - 포션으로 회복
4. 도망 - 전투 종료
```

### 📈 몬스터 & 보스
| 층 | 일반 몬스터 | 보스 |
|---|-----------|------|
| **1층** | 고블린, 슬라임 | 👑 고블린 킹 |
| **2층** | 코볼트, 늑대 | 👑 늑대 왕 |
| **3층** | 오크, 트롤 | 👑 오크 왕 |

### 🎯 몬스터 처치 진행도
- 일반 몬스터: 5마리 처치 시 보스 출현
- 보스 처치: 경험치 50 + 골드 200 + 레어 아이템/스킬북 획득
- 보상: 50% 확률로 레어 아이템, 50% 확률로 스킬북

---

## 🚀 설치 및 실행

### 설치
```bash
cd react-project
npm install
```

### 개발 서버 시작
```bash
npm run dev
```

**접속**: http://localhost:5173

### 빌드
```bash
npm run build
```

---

## 🧪 테스트 계정
| 항목 | 값 |
|------|-----|
| **ID** | `testuser` |
| **비밀번호** | `1234` |
| **닉네임** | `최강의 테스터` |
| **레벨** | 100 |
| **모든 스탯** | 100 |
| **골드** | 999,999 |

---

## 📁 프로젝트 구조
```
react-project/
├── src/
│   ├── components/          # React 컴포넌트
│   │   ├── Board.jsx        # 게시판 목록
│   │   ├── BoardDetail.jsx  # 게시글 상세
│   │   ├── BoardWrite.jsx   # 게시글 작성
│   │   ├── BoardEdit.jsx    # 게시글 수정
│   │   ├── Game.jsx         # 게임 화면
│   │   ├── Home.jsx         # 홈 화면
│   │   ├── Item.jsx         # 아이템 상점
│   │   ├── Login.jsx        # 로그인
│   │   ├── Logout.jsx       # 로그아웃
│   │   ├── Mypage.jsx       # 마이페이지
│   │   ├── PrivateRoute.jsx # 보호된 라우트
│   │   ├── SignUp.jsx       # 회원가입
│   │   ├── Skill.jsx        # 스킬 상점
│   │   └── Game-Origin.jsx  # 게임 원본 백업
│   ├── store/               # Zustand 상태 관리
│   │   ├── ~~~~user.js          # 사용자 상태
│   │   ├── monster.js       # 몬스터 상태
│   │   ├── skill.js         # 스킬 상태
│   │   ├── item.js          # 아이템 상태
│   │   └── board.js         # 게시판 상태
│   ├── style/               # Styled Components
│   │   ├── Auth.style.js
│   │   ├── Board.style.js
│   │   ├── BoardDetail.style.js
│   │   ├── BoardWrite.style.js
│   │   ├── Game.style.js
│   │   ├── Header.style.js
│   │   ├── Home.style.js
│   │   ├── Item.style.js
│   │   ├── Mypage.style.js
│   │   ├── Skill.style.js
│   │   └── check.js
│   ├── layout/              # 레이아웃 컴포넌트
│   │   └── Header.jsx
│   ├── user/                # 사용자 관련 유틸
│   │   ├── UserContext.jsx
│   │   └── UseUser.jsx
│   ├── App.jsx              # 라우팅 설정
│   ├── main.jsx             # 진입점
│   ├── App.css
│   └── index.css
├── public/
├── package.json
├── vite.config.js
├── eslint.config.js
└── README.md
```

---

## 🛠️ 사용 기술 스택

### 프론트엔드
- **React 18** - UI 라이브러리
- **React Router** - 페이지 라우팅
- **Zustand** - 경량 상태 관리
- **Styled Components** - CSS-in-JS 스타일링

### 개발 도구
- **Vite** - 모던 번들러

### 저장소
- **localStorage** - 브라우저 로컬 데이터 저장소

---

## 🎯 게임 플레이 흐름

### 1️⃣ 게임 시작
```
로그인 → 홈 화면 → 게임 시작 → 1층 던전 진입
```

### 2️⃣ 전투
```
몬스터 출현 → 공격/스킬/아이템/도망 선택 → 
몬스터 공격 → 턴 반복 → 승패 결정
```

### 3️⃣ 보상
```
일반 몬스터 처치: 경험치 + 골드 → 
5마리 처치 → 보스 출현 → 보스 처치 → 
다음 층으로 이동
```

### 4️⃣ 성장
```
마이페이지 → 스킬/아이템 상점 → 
레벨 달성 시 스킬 해금 → 
골드로 아이템 구매 → 능력 강화
```

---

## 💰 상점 정보

### 🔮 스킬 상점
| ID | 스킬 | 필요 레벨 | 마나 | 설명 |
|---|------|---------|------|------|
| 1 | 파이어볼 | Lv 10 | 20 | 화염 공격 |
| 2 | 아이스샤드 | Lv 20 | 20 | 냉동 공격 |
| 3 | 라이트닝 | Lv 30 | 30 | 번개 공격 |
| 4 | 힐 | Lv 40 | 15 | 체력 회복 |

### 🎁 아이템 상점
| ID | 아이템 | 등급 | 가격 | 효과 |
|---|-------|------|------|------|
| 1 | 체력 물약 | 일반 | 50 | HP +30 |
| 2 | 마나 물약 | 일반 | 40 | MP +20 |
| 3 | 철 검 | 일반 | 100 | ATK +10 |
| 4 | 가죽 갑옷 | 일반 | 150 | DEF +10 |
| 5 | 로브 | 일반 | 120 | INT +10 |
| 6 | 반지 | 일반 | 80 | LUK +5 |
| 7 | 드래곤 슬레이어 | 전설 | 500 | ATK +35 |
| 8 | 불사조 갑옷 | 전설 | 600 | DEF +50, MaxHP +100 |
| 9 | 마법사 왕관 | 전설 | 550 | MaxMP +100 |

---

## ❓ FAQ

### Q. 스킬은 어떻게 배우나요?
**A.** 마이페이지 → 상점 탭 → 스킬 상점에서 필요 레벨 도달 시 배울 수 있습니다.

### Q. 아이템은 어디서 구매하나요?
**A.** 마이페이지 → 상점 탭 → 아이템 상점에서 골드로 구매합니다.

### Q. 층을 어떻게 올라가나요?
**A.** 각 층에서 5마리 처치 → 보스 출현 → 보스 격파 시 자동으로 다음 층으로 이동합니다.

### Q. 죽으면 어떻게 되나요?
**A.** "터어어어어얼렸구나!"라는 메시지가 나오며 2초 후 홈으로 이동합니다.

### Q. 아이템을 어디서 장착하나요?
**A.** 마이페이지 → 착용 아이템 탭에서 인벤토리의 아이템을 장착/해제할 수 있습니다.

### Q. 게시판의 댓글을 수정/삭제할 수 있나요?
**A.** 본인이 작성한 댓글만 수정/삭제 가능합니다.

### Q. 데이터는 어디에 저장되나요?
**A.** 브라우저의 localStorage에 저장되므로 브라우저 데이터를 삭제하면 초기화됩니다.

---

## 📝 개발 가이드

### 새로운 스킬 추가
`src/store/skill.js`에서 `allSkills` 배열에 스킬 객체를 추가합니다.
```javascript
{
  id: 5,
  name: "새로운 스킬",
  damage: 40,
  manaCost: 25,
  requiredLevel: 50,
  icon: "⚡"
}
```

### 새로운 아이템 추가
`src/store/item.js`에서 `allItems` 배열에 아이템 객체를 추가합니다.
```javascript
{
  id: 10,
  name: "새 아이템",
  type: "weapon",
  price: 200,
  effect: { atk: 15 },
  rarity: "uncommon",
  icon: "🗡️"
}
```

### 새로운 몬스터 추가
`src/store/monster.js`에서 `monsters` 배열에 몬스터 객체를 추가합니다.
```javascript
{
  id: 10,
  name: "새 몬스터",
  floor: 1,
  exp: 20,
  gold: 100,
  hp: 30,
  atk: 8,
  isBoss: false
}
```

---

## 🔄 상태 관리 아키텍처

### Zustand Stores
- **useGameStore** (`src/store/user.jsx`)
  - 사용자 정보, 통계, 보유 아이템 관리
- **useMonsterStore** (`src/store/monster.jsx`)
  - 몬스터 데이터, 층 정보, 처치 카운터
- **useSkillStore** (`src/store/skill.jsx`)
  - 스킬 카탈로그, 배운 스킬 관리
- **useItemStore** (`src/store/item.jsx`)
  - 아이템 카탈로그, 인벤토리 관리
- **useBoardStore** (`src/store/board.jsx`)
  - 게시글, 댓글 관리

### localStorage Persistence
모든 스토어는 `persist` 미들웨어로 자동 저장됩니다.

---

## 🚀 성능 최적화
- React Router를 사용한 SPA 구조
- Zustand를 통한 효율적인 상태 관리
- localStorage를 활용한 빠른 데이터 로드

---

## 📝 라이선스

이 프로젝트는 학습 목적으로 작성되었습니다.

---

**즐거운 모험되세요! 🗡️⚔️🛡️**
