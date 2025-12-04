import React from 'react';
import useSkillStore from '../store/skill';
import Header from '../layout/Header';
import { 
  PageWrapper, 
  SkillContainer, 
  SkillTitle,
  SkillGrid,
  SkillCard,
  SkillIcon,
  SkillName,
  SkillDesc,
  SkillStats,
  StatItem,
  LearnButton
} from '../style/Skill.style';

const Skill = () => {
  const skills = useSkillStore((state) => state.skills);
  const playerSkills = useSkillStore((state) => state.playerSkills);
  const learnSkill = useSkillStore((state) => state.learnSkill);

  const handleLearnSkill = (skillId) => {
    learnSkill(skillId);
    alert('스킬을 배웠습니다!');
  };

  const isSkillLearned = (skillId) => {
    return playerSkills.some(ps => ps.id === skillId);
  };

  return (
    <>
      <Header />
      <PageWrapper>
        <SkillContainer>
          <SkillTitle>⚔️ 스킬 목록</SkillTitle>
          
          <SkillGrid>
            {skills.map((skill) => (
              <SkillCard key={skill.id} isLearned={isSkillLearned(skill.id)}>
                <SkillIcon>{skill.icon}</SkillIcon>
                <SkillName>{skill.name}</SkillName>
                <SkillDesc>{skill.description}</SkillDesc>
                
                <SkillStats>
                  {skill.damage > 0 && (
                    <StatItem>
                      <span>⚔️ 공격력</span>
                      <span>{skill.damage}</span>
                    </StatItem>
                  )}
                  {skill.isHealing && (
                    <StatItem>
                      <span>💚 회복량</span>
                      <span>{skill.healAmount}</span>
                    </StatItem>
                  )}
                  <StatItem>
                    <span>💙 마나</span>
                    <span>{skill.manaCost}</span>
                  </StatItem>
                  <StatItem>
                    <span>⏱️ 쿨타임</span>
                    <span>{skill.cooldown}초</span>
                  </StatItem>
                </SkillStats>

                <LearnButton 
                  onClick={() => handleLearnSkill(skill.id)}
                  disabled={isSkillLearned(skill.id)}
                >
                  {isSkillLearned(skill.id) ? '✓ 배운 스킬' : '배우기'}
                </LearnButton>
              </SkillCard>
            ))}
          </SkillGrid>

          {playerSkills.length > 0 && (
            <>
              <SkillTitle style={{ marginTop: '50px' }}>📚 배운 스킬 ({playerSkills.length})</SkillTitle>
              <SkillGrid>
                {playerSkills.map((skill) => (
                  <SkillCard key={skill.id} isLearned>
                    <SkillIcon>{skill.icon}</SkillIcon>
                    <SkillName>{skill.name}</SkillName>
                  </SkillCard>
                ))}
              </SkillGrid>
            </>
          )}
        </SkillContainer>
      </PageWrapper>
    </>
  );
};

export default Skill;
