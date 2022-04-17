package ua.goit.model.dto;

public class SkillsDto {
    private Integer id;
    private String description;
    private String level;

    public SkillsDto() {
    }

    public SkillsDto(Integer id, String description, String level) {
        this.id = id;
        this.description = description;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
