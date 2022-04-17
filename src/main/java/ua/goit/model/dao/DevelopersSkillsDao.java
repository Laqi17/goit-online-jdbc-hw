package ua.goit.model.dao;

public class DevelopersSkillsDao {
    private Integer developerId;
    private Integer skillId;

    public DevelopersSkillsDao() {
    }

    public DevelopersSkillsDao(Integer developerId, Integer skillId) {
        this.developerId = developerId;
        this.skillId = skillId;
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }
}
