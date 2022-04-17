package ua.goit.model.convecter;

import ua.goit.model.dao.SkillsDao;
import ua.goit.model.dto.SkillsDto;

public class SkillsConverter implements Converter<SkillsDto, SkillsDao> {
    @Override
    public SkillsDto toDto(SkillsDao dao) {
        SkillsDto dto = new SkillsDto();
        dto.setId(dao.getId());
        dto.setDescription(dao.getDescription());
        dto.setLevel(dao.getLevel());
        return dto;
    }

    @Override
    public SkillsDao toDao(SkillsDto dto) {
        SkillsDao dao = new SkillsDao();
        dao.setId(dto.getId());
        dao.setDescription(dto.getDescription());
        dao.setLevel(dto.getLevel());
        return dao;
    }
}
