package com.kh.textGame.service;

import com.kh.textGame.dto.MonsterDto;
import com.kh.textGame.entity.Monster;
import com.kh.textGame.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;


    public MonsterDto createMonster(MonsterDto monsterDto) {
        Monster monster = Monster.builder()
                .name(monsterDto.getName())
                .img(monsterDto.getImg())
                .floor(monsterDto.getFloor())
                .isBoss(monsterDto.isBoss())
                .exp(monsterDto.getExp())
                .gold(monsterDto.getGold())
                .maxHp(monsterDto.getMaxHp())
                .maxMp(monsterDto.getMaxMp())
                .atk(monsterDto.getAtk())
                .def(monsterDto.getDef())
                .dex(monsterDto.getDex())
                .luk(monsterDto.getLuk())
                .build();
        Monster savedMonster = monsterRepository.save(monster);
        return convertEntityToDto(savedMonster);
    }


    @Transactional(readOnly = true)
    public List<MonsterDto> getAllMonsters() {
        return monsterRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public MonsterDto getMonsterById(Long id) {
        return monsterRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElse(null);
    }


    public MonsterDto updateMonster(Long id, MonsterDto monsterDto) {
        Monster monster = monsterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid monster Id:" + id));


        monster.setName(monsterDto.getName());
        monster.setImg(monsterDto.getImg());
        monster.setFloor(monsterDto.getFloor());
        monster.setBoss(monsterDto.isBoss());
        monster.setExp(monsterDto.getExp());
        monster.setGold(monsterDto.getGold());
        monster.setMaxHp(monsterDto.getMaxHp());
        monster.setMaxMp(monsterDto.getMaxMp());
        monster.setAtk(monsterDto.getAtk());
        monster.setDef(monsterDto.getDef());
        monster.setDex(monsterDto.getDex());
        monster.setLuk(monsterDto.getLuk());
        
        Monster updatedMonster = monsterRepository.save(monster);
        return convertEntityToDto(updatedMonster);
    }


    public void deleteMonster(Long id) {
        monsterRepository.deleteById(id);
    }

    private MonsterDto convertEntityToDto(Monster monster) {
        MonsterDto monsterDto = new MonsterDto();
        monsterDto.setId(monster.getId());
        monsterDto.setName(monster.getName());
        monsterDto.setImg(monster.getImg());
        monsterDto.setFloor(monster.getFloor());
        monsterDto.setBoss(monster.isBoss());
        monsterDto.setExp(monster.getExp());
        monsterDto.setGold(monster.getGold());
        monsterDto.setMaxHp(monster.getMaxHp());
        monsterDto.setMaxMp(monster.getMaxMp());
        monsterDto.setAtk(monster.getAtk());
        monsterDto.setDef(monster.getDef());
        monsterDto.setDex(monster.getDex());
        monsterDto.setLuk(monster.getLuk());
        return monsterDto;
    }
}
