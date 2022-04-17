package ua.goit.model.convecter;

import ua.goit.model.dao.ProjectsDao;
import ua.goit.model.dto.ProjectsDto;

public class ProjectsConverter implements Converter<ProjectsDto, ProjectsDao> {
    @Override
    public ProjectsDto toDto(ProjectsDao dao) {
        ProjectsDto dto = new ProjectsDto();
        dto.setId(dao.getId());
        dto.setDescription(dao.getDescription());
        dto.setCost(dao.getCost());
        dto.setCompanyId(dao.getCompanyId());
        dto.setManagerName(dao.getManagerName());
        dto.setCustomerId(dao.getCustomerId());
        return dto;
    }

    @Override
    public ProjectsDao toDao(ProjectsDto dto) {
        ProjectsDao dao = new ProjectsDao();
        dao.setId(dto.getId());
        dao.setDescription(dto.getDescription());
        dao.setCost(dto.getCost());
        dao.setCompanyId(dto.getCompanyId());
        dao.setManagerName(dto.getManagerName());
        dao.setCustomerId(dto.getCustomerId());
        return dao;
    }
}
