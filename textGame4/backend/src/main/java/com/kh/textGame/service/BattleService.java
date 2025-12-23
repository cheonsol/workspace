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

        Monster monster = monsterRepository.findById(monsterId).orElseThrow();
        Member member = memberRepository.findById(playerId).orElseThrow();


        int damage = Math.max(0, member.getAtk() - monster.getDef());


        monster.takeDamage(damage);


        return BattleResultDto.builder()
                .damage(damage)
                .monsterHp(monster.getCurrentHp())
                .isMonsterDead(monster.getCurrentHp() <= 0)
                .message(member.getNickname() + "이(가) " + monster.getName() + "에게 " + damage + "의 데미지를 입혔습니다!")
                .build();
    }
}