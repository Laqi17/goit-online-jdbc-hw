package ua.goit.model.convecter;

import ua.goit.model.dao.DeveloperProjectsDao;
import ua.goit.model.dto.DeveloperProjectsDto;

public class DeveloperProjectsConverter implements Converter<DeveloperProjectsDto, DeveloperProjectsDao> {
    @Override
    public DeveloperProjectsDto toDto(DeveloperProjectsDao dao) {
        DeveloperProjectsDto dto = new DeveloperProjectsDto();
        dto.setDeveloperId(dao.getDeveloperId());
        dto.setProjectId(dao.getProjectId());
        return dto;
    }

    @Override
    public DeveloperProjectsDao toDao(DeveloperProjectsDto dto) {
        DeveloperProjectsDao dao = new DeveloperProjectsDao();
        dao.setDeveloperId(dto.getDeveloperId());
        dao.setProjectId(dto.getProjectId());
        return dao;
    }
}
