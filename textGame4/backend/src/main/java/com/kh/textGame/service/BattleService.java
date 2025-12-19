package com.kh.textGame.service;

import com.kh.textGame.dto.BattleResultDto;
import com.kh.textGame.entity.Member;
import com.kh.textGame.entity.Monster;
import com.kh.textGame.repository.MemberRepository;
import com.kh.textGame.repository.MonsterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattleService {

    private final MonsterRepository monsterRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BattleResultDto attack(Long monsterId, Long playerId) {
        // 1. 데이터 조회
        Monster monster = monsterRepository.findById(monsterId).orElseThrow();
        Member member = memberRepository.findById(playerId).orElseThrow();

        // 2. 데미지 계산 로직 (백엔드에서 엄격하게 관리)
        // 공식 예시: $$Damage = \max(0, \text{PlayerAtk} - \text{MonsterDef})$$
        int damage = Math.max(0, member.getAtk() - monster.getDef());

        // 3. 상태 변경 (JPA Dirty Checking에 의해 자동 DB 반영)
        monster.takeDamage(damage);

        // 4. 결과 생성
        return BattleResultDto.builder()
                .damage(damage)
                .monsterHp(monster.getCurrentHp())
                .isMonsterDead(monster.getCurrentHp() <= 0)
                .message(member.getNickname() + "이(가) " + monster.getName() + "에게 " + damage + "의 데미지를 입혔습니다!")
                .build();
    }
}