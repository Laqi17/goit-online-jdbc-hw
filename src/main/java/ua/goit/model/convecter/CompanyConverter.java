package ua.goit.model.convecter;

import ua.goit.model.dao.CompaniesDao;
import ua.goit.model.dto.CompaniesDto;

public class CompanyConverter implements Converter<CompaniesDto, CompaniesDao>{

    @Override
    public CompaniesDto toDto(CompaniesDao dao) {
        CompaniesDto dto = new CompaniesDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setLocation(dao.getLocation());
        return dto;
    }

    @Override
    public CompaniesDao toDao(CompaniesDto dto) {
        CompaniesDao dao = new CompaniesDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setLocation(dto.getLocation());
        return dao;
    }
}
