package ua.goit.model.convecter;

import ua.goit.model.dao.CustomersDao;
import ua.goit.model.dto.CustomersDto;

public class CustomerConverter implements Converter<CustomersDto, CustomersDao>{

    @Override
    public CustomersDto toDto(CustomersDao dao) {
        CustomersDto dto = new CustomersDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setLocation(dao.getLocation());
        return dto;
    }

    @Override
    public CustomersDao toDao(CustomersDto dto) {
        CustomersDao dao = new CustomersDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setLocation(dto.getLocation());
        return dao;
    }
}
