package ua.goit.model.convecter;

import ua.goit.model.dao.DevelopersSkillsDao;
import ua.goit.model.dto.DevelopersSkillsDto;

public class DevelopersSkillsConverter implements Converter<DevelopersSkillsDto, DevelopersSkillsDao> {
    @Override
    public DevelopersSkillsDto toDto(DevelopersSkillsDao dao) {
        DevelopersSkillsDto dto = new DevelopersSkillsDto();
        dto.setDeveloperId(dao.getDeveloperId());
        dto.setSkillId(dao.getSkillId());
        return dto;
    }

    @Override
    public DevelopersSkillsDao toDao(DevelopersSkillsDto dto) {
        DevelopersSkillsDao dao = new DevelopersSkillsDao();
        dao.setDeveloperId(dto.getDeveloperId());
        dao.setSkillId(dto.getSkillId());
        return dao;
    }
}
