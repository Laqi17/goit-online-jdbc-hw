package ua.goit.model.dto;

public class DevelopersSkillsDto {
    private Integer developerId;
    private Integer skillId;

    public DevelopersSkillsDto() {
    }

    public DevelopersSkillsDto(Integer developerId, Integer skillId) {
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
