package com.kh.textGame.service;

import com.kh.textGame.dto.BattleTurnResultDto;
import com.kh.textGame.dto.GameEventDto;
import com.kh.textGame.dto.MemberDto;
import com.kh.textGame.dto.MonsterDto;
import com.kh.textGame.entity.Member;
import com.kh.textGame.entity.Monster;
import com.kh.textGame.repository.MemberRepository;
import com.kh.textGame.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {
    private final MemberRepository memberRepository;
    private final MonsterRepository monsterRepository;

    public BattleTurnResultDto handleAttack(Long memberId, Long monsterId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Monster monster = monsterRepository.findById(monsterId)
                .orElseThrow(() -> new RuntimeException("Monster not found"));

        List<GameEventDto> events = new ArrayList<>();
        boolean battleOver = false;
        boolean playerWon = false;

        // Player's turn
        boolean isCritical = new Random().nextInt(100) < member.getLuk();
        int playerDamage = member.getAtk() * (isCritical ? 2 : 1);
        events.add(new GameEventDto("PLAYER_ATTACK", "You attack for " + playerDamage + " damage" + (isCritical ? " (Critical!)" : ""), playerDamage, isCritical, 0, 0, 0, 0));

        int monsterCurrentHp = monster.getHp() - playerDamage;
        monster.setHp(monsterCurrentHp);

        if (monsterCurrentHp <= 0) {
            battleOver = true;
            playerWon = true;
            long expGained = monster.getExp();
            long goldGained = monster.getGold();

            member.setExp(member.getExp() + expGained);
            member.setGold(member.getGold() + goldGained);

            events.add(new GameEventDto("REWARD", "You defeated the monster! You gained " + expGained + " EXP and " + goldGained + " Gold.", 0, false, 0, expGained, goldGained, 0));
            memberRepository.save(member);
        } else {
            int monsterDamage = monster.getAtk();
            member.setCurrentHp(member.getCurrentHp() - monsterDamage);
            events.add(new GameEventDto("MONSTER_ATTACK", "The monster attacks for " + monsterDamage + " damage.", monsterDamage, false, 0, 0, 0, 0));
            if (member.getCurrentHp() <= 0) {
                battleOver = true;
                playerWon = false;
                member.setCurrentHp(0);
                events.add(new GameEventDto("GAME_OVER", "You have been defeated.", 0, false, 0, 0, 0, 0));
            }
            memberRepository.save(member);
        }

        MemberDto memberDto = new MemberDto(member.getId(), member.getUserId(), member.getNickname(), member.getLevel(), member.getExp(), member.getGold(), member.getFloor(), member.getStatPoints(), member.getMaxHp(), member.getCurrentHp(), member.getMaxMp(), member.getCurrentMp(), member.getAtk(), member.getDef(), member.getIntel(), member.getDex(), member.getLuk(), member.getBaseMaxHp(), member.getBaseMaxMp(), member.getBaseAtk(), member.getBaseDef(), member.getBaseDex(), member.getBaseLuk(), null, null);
        MonsterDto monsterDto = new MonsterDto(monster.getId(), monster.getName(), monsterCurrentHp, monster.getMaxHp(), monster.getMaxMp(), monster.getAtk(), monster.getDef(), monster.getDex(), monster.getLuk(), monster.getExp(), monster.getGold(), monster.getFloor(), monster.isBoss(), monster.getImg());

        return new BattleTurnResultDto(memberDto, monsterDto, events, battleOver, playerWon);
    }
}
