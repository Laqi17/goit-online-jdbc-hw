package ua.goit;


import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertyUtil;
import ua.goit.dl.repository.CompaniesRepository;
import ua.goit.dl.repository.Repository;
import ua.goit.model.dao.CompaniesDao;

public class Main {
    public static void main(String[] args) {
        PropertyUtil propertyUtil = new PropertyUtil();
        DatabaseManager databaseManager = new PostgresHikariProvider(propertyUtil.getHostName(), propertyUtil.getPort(),
                propertyUtil.getSchema(), propertyUtil.getUser(), propertyUtil.getPassword());

        Repository<CompaniesDao> companyRepository = new CompaniesRepository(databaseManager);
        System.out.println(companyRepository.find(2));
        System.out.println(companyRepository.findAll());
        companyRepository.save(new CompaniesDao(6, "Nix", "Kharkov"));
    }
}
