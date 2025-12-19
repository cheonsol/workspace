// JSDoc Quick Reference (개발자 안내)
// - `@param {Type} name` : 매개변수의 타입과 이름을 문서화합니다.
// - 선택 매개변수 표기: `@param {Type} [name]` 또는 기본값 표기 `@param {Type} [name=default]`.
// - `@returns {Type}` : 반환값의 타입과 의미를 설명하세요.
// - 함수 설명, 사용 예, 엣지 케이스를 함께 적으면 유지보수가 쉬워집니다.
//
// 게임 전투 관련 유틸함수
// 이 파일의 함수들은 전투에서 데미지/치명타/레벨업 계산 등 핵심 수치 연산을
// 담당합니다. 게임 밸런스 조정이 필요할 때는 여기의 수식만 조정하면 됩니다.

/**
 * 데미지 계산 함수
 *
 * 설명:
 *  - 전투에서 기본 공격력에 배수를 곱해 실제 데미지를 계산합니다.
 *  - 스킬 효과나 버프/디버프 적용 시 배수를 조정하면 됩니다.
 *
 * 파라미터:
 *  - baseAttack (number): 기본 공격력 (예: 플레이어의 공격력)
 *  - multiplier (number, 기본값 1): 데미지 배수
 *    스킬은 1보다 큰 값을, 디버프는 1보다 작은 값을 가질 수 있습니다.
 *
 * 반환값:
 *  - (number) 계산된 정수형 데미지
 *
 * 호출 예시:
 *  - 단일 공격: `const dmg = calculateDamage(player.atk);`
 *  - 스킬 적용: `const dmg = calculateDamage(player.atk, skill.multiplier);`
 *
 * 적용 위치:
 *  - `src/components/Game.jsx`, `src/hooks/useGame.js` 등 전투 로직
 *
 * 주의사항:
 *  - 음수 입력이 들어오면 음수 데미지가 나올 수 있으므로 호출 전에 값 검증을 권장합니다.
 *
 * @param {number} baseAttack - 기본 공격력
 * @param {number} [multiplier=1] - 배수 (기본값 1)
 * @returns {number} 계산된 데미지 (정수)
 */
export const calculateDamage = (baseAttack, multiplier = 1) => {
  const a = Number(baseAttack) || 0;
  const m = Number(multiplier) || 1;
  return Math.floor(a * m);
};

/**
 * 치명타 확률 계산
 *
 * 설명:
 *  - 전달된 행운도(luck) 값을 퍼센트(0~100)로 보고 랜덤시드와 비교하여
 *    치명타 여부를 결정합니다.
 *  - 내부적으로 Math.random()을 사용합니다.
 *
 * 파라미터:
 *  - luck (number): 치명타 확률(퍼센트). 정수나 소수 가능.
 *
 * 반환값:
 *  - (boolean) 치명타이면 true, 아니면 false
 *
 * 적용 위치:
 *  - 전투 로직에서 치명타 판정에 사용됩니다.
 *  - 예: if (isCritical(player.luk)) dmg *= 2;
 *
 * @param {number} luck - 치명타 확률(퍼센트)
 * @returns {boolean} 치명타 여부
 */
export const isCritical = (luck) => {
  const l = Number(luck) || 0;
  return Math.random() * 100 < l;
};

/**
 * 스킬 데미지 계산
 *
 * 설명:
 *  - 각 스킬은 고정 베이스 데미지를 가지며, 플레이어의 공격력(atk)에 비례한
 *    보정을 받아 최종 스킬 데미지를 계산합니다.
 *  - 현재 수식: floor(skillDamage * (1 + atk / 10))
 *
 * 파라미터:
 *  - skillDamage (number): 스킬의 기본 데미지
 *  - atk (number): 플레이어의 공격력
 *
 * 반환값:
 *  - (number) 계산된 정수형 스킬 데미지
 *
 * 호출 예시:
 *  - const damage = calculateSkillDamage(50, player.atk);
 *
 * 적용 위치:
 *  - 스킬 사용 로직 (src/components/Game.jsx, src/hooks/useGame.js)
 *
 * @param {number} skillDamage - 스킬의 기본 데미지
 * @param {number} atk - 플레이어의 공격력
 * @returns {number} 스킬 데미지(정수)
 */
export const calculateSkillDamage = (skillDamage, atk) => {
  const base = Number(skillDamage) || 0;
  const a = Number(atk) || 0;
  return Math.floor(base * (1 + a / 10));
};

/**
 * 몬스터 데미지 계산
 *
 * 설명:
 *  - 몬스터의 공격력(atk)과 행운(luk)을 이용해 입히는 데미지를 계산합니다.
 *  - 크리티컬 여부만 고려하며, 크리티컬이면 atk * 2를 반환합니다.
 *
 * 파라미터:
 *  - monster (object): 최소 atk와 luk 프로퍼티를 가진 객체
 *    예: { atk: 10, luk: 5 }
 *
 * 반환값:
 *  - (number) 몬스터가 입히는 데미지(정수)
 *
 * 적용 위치:
 *  - 전투 시 몬스터가 플레이어에게 공격할 때 사용됩니다.
 *  - src/components/Game.jsx의 반격 로직
 *
 * @param {object} monster - 몬스터 객체 (예: { atk: 10, luk: 5 })
 * @returns {number} 몬스터가 입히는 데미지(정수)
 */
export const calculateMonsterDamage = (monster) => {
  const atk = Number(monster?.atk) || 0;
  const luk = Number(monster?.luk) || 0;
  return isCritical(luk) ? Math.floor(atk * 2) : Math.floor(atk);
};

/**
 * 레벨별 필요 경험치 계산
 *
 * 설명:
 *  - 현재는 선형 공식(100 * level)을 사용합니다.
 *  - 더 복잡한 성장곡선이 필요하면 이 함수만 수정하면 전역에서 적용됩니다.
 *
 * 파라미터:
 *  - level (number): 현재 레벨
 *
 * 반환값:
 *  - (number) 해당 레벨에서 다음 레벨로 가기 위해 필요한 경험치
 *
 * 호출 예시:
 *  - const nextExp = getRequiredExp(player.level);
 *
 * 적용 위치:
 *  - 레벨업 로직, 레벨 표시 UI 등에서 사용됩니다.
 *  - src/hooks/useGame.js
 *
 * @param {number} level - 레벨
 * @returns {number} 필요 경험치
 */
export const getRequiredExp = (level) => {
  const l = Number(level) || 1;
  return 100 * l;
};

/**
 * 레벨 업 여부 확인
 *
 * 설명:
 *  - 현재 경험치가 해당 레벨의 필요 경험치 이상이면 레벨업이 가능한지를
 *    판단해 관련 정보를 반환합니다.
 *
 * 파라미터:
 *  - currentExp (number): 현재 경험치
 *  - currentLevel (number): 현재 레벨
 *
 * 반환값:
 *  - (object) { canLevelUp: boolean, nextLevelExp: number }
 *    canLevelUp: 레벨업 가능 여부
 *    nextLevelExp: 필요한 경험치
 *
 * 호출 예시:
 *  - const result = checkLevelUp(player.exp, player.level);
 *  - if (result.canLevelUp) { levelUp(); }
 *
 * 적용 위치:
 *  - 전투 종료 처리 후 경험치 획득
 *  - src/components/Game.jsx, src/hooks/useGame.js
 *
 * @param {number} currentExp - 현재 경험치
 * @param {number} currentLevel - 현재 레벨
 * @returns {object} { canLevelUp, nextLevelExp }
 */
export const checkLevelUp = (currentExp, currentLevel) => {
  const req = getRequiredExp(currentLevel);
  const exp = Number(currentExp) || 0;
  return {
    canLevelUp: exp >= req,
    nextLevelExp: req,
  };
};
