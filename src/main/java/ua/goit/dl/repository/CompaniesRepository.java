package ua.goit.dl.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.CompaniesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompaniesRepository implements Repository<CompaniesDao>{

    private static final String FIND_BY_ID = "SELECT * FROM companies c WHERE c.company_id = ?;";
    private static final String FIND_BY_NAME = "SELECT * FROM companies c WHERE c.company_name = ?;";
    private static final String FIND_ALL = "SELECT * FROM companies;";
    private static final String INSERT = "INSERT INTO companies (company_id, company_name, company_location) VALUES (?, ?, ?)";
    private static final String REMOVE_BY_ID = "DELETE FROM companies WHERE company_id = ?";
    private static final String UPDATE = "UPDATE companies SET company_name = ?, company_location = ? WHERE company_id = ?";

    private final DatabaseManager connector;

    public CompaniesRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public Optional<CompaniesDao> find(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCompaniesDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<CompaniesDao> find(String name) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCompaniesDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CompaniesDao> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public boolean save(CompaniesDao companiesDao) {
        if (find(companiesDao.getId()).isEmpty()) {
            try (Connection connection = connector.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                preparedStatement.setInt(1, companiesDao.getId());
                preparedStatement.setString(2, companiesDao.getName());
                preparedStatement.setString(3, companiesDao.getLocation());
                preparedStatement.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void remove(CompaniesDao companiesDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setInt(1, companiesDao.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(CompaniesDao companiesDao) {
        int columnsUpdated = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, companiesDao.getName());
            preparedStatement.setString(2, companiesDao.getLocation());
            preparedStatement.setInt(3, companiesDao.getId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    private Optional<CompaniesDao> mapToCompaniesDao(ResultSet resultSet) throws SQLException {
        CompaniesDao companiesDao = null;
        while (resultSet.next()) {
            companiesDao = new CompaniesDao();
            companiesDao.setId(resultSet.getInt("company_id"));
            companiesDao.setName(resultSet.getString("company_name"));
            companiesDao.setLocation(resultSet.getString("company_location"));
        }
        return Optional.ofNullable(companiesDao);
    }

    private List<CompaniesDao> mapToList(ResultSet resultSet) throws SQLException {
        List<CompaniesDao> companies = new ArrayList<>();
        while (resultSet.next()) {
            CompaniesDao company = new CompaniesDao();
            company.setId(resultSet.getInt("company_id"));
            company.setName(resultSet.getString("company_name"));
            company.setLocation(resultSet.getString("company_location"));
            companies.add(company);
        }
        return companies;
    }
}
