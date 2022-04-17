package ua.goit.model.convecter;

import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dto.DevelopersDto;

public class DevelopersConverter implements Converter<DevelopersDto, DevelopersDao> {
    @Override
    public DevelopersDto toDto(DevelopersDao dao) {
        DevelopersDto dto = new DevelopersDto();
        dto.setId(dao.getId());
        dto.setFirstName(dao.getFirstName());
        dto.setLastName(dao.getLastName());
        dto.setAge(dao.getAge());
        dto.setSex(dao.getSex());
        dto.setSalary(dao.getSalary());
        dto.setCompanyId(dao.getCompanyId());
        return dto;
    }

    @Override
    public DevelopersDao toDao(DevelopersDto dto) {
        DevelopersDao dao = new DevelopersDao();
        dao.setId(dto.getId());
        dao.setFirstName(dto.getFirstName());
        dao.setLastName(dto.getLastName());
        dao.setAge(dto.getAge());
        dao.setSex(dto.getSex());
        dao.setSalary(dto.getSalary());
        dao.setCompanyId(dto.getCompanyId());
        return dao;

    }
}
