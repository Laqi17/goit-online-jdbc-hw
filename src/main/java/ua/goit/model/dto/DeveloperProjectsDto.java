package ua.goit.model.dto;

public class DeveloperProjectsDto {
    private Integer developerId;
    private Integer projectId;

    public DeveloperProjectsDto() {
    }

    public DeveloperProjectsDto(Integer developerId, Integer projectId) {
        this.developerId = developerId;
        this.projectId = projectId;
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
