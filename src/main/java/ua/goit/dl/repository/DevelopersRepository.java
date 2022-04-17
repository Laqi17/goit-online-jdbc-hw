package ua.goit.dl.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.DevelopersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevelopersRepository implements Repository<DevelopersDao>{

    private final static String FIND_BY_ID = "SELECT * FROM developers d WHERE d.developer_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM developers;";
    private static final String INSERT = "INSERT INTO developers (developer_id, first_name, last_name, age, sex, company_id, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_BY_ID = "DELETE FROM developers WHERE developer_id = ?";
    private static final String UPDATE = "UPDATE developers SET first_name = ?, last_name = ?, developer_age = ?, developer_sex = ?, company_id = ?, developer_salary = ? WHERE developer_id = ?";


    private final DatabaseManager connector;

    public DevelopersRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public Optional<DevelopersDao> find(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<DevelopersDao> findAll() {
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
    public boolean save(DevelopersDao developersDao) {
        if (find(developersDao.getId()).isEmpty()) {
            try (Connection connection = connector.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                preparedStatement.setInt(1, developersDao.getId());
                preparedStatement.setString(2, developersDao.getFirstName());
                preparedStatement.setString(3, developersDao.getLastName());
                preparedStatement.setInt(4, developersDao.getAge());
                preparedStatement.setString(5, developersDao.getSex());
                preparedStatement.setInt(6, developersDao.getCompanyId());
                preparedStatement.setInt(7, developersDao.getSalary());
                preparedStatement.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void remove(DevelopersDao developersDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setInt(1, developersDao.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(DevelopersDao developersDao) {
        int columnsUpdated = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, developersDao.getFirstName());
            preparedStatement.setString(2, developersDao.getLastName());
            preparedStatement.setInt(3, developersDao.getAge());
            preparedStatement.setString(4, developersDao.getSex());
            preparedStatement.setInt(5, developersDao.getCompanyId());
            preparedStatement.setInt(6, developersDao.getSalary());
            preparedStatement.setInt(7, developersDao.getId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    private Optional<DevelopersDao> mapToDevelopersDao(ResultSet resultSet) throws SQLException {
        DevelopersDao developersDao = null;
        while (resultSet.next()) {
            developersDao = new DevelopersDao();
            developersDao.setId(resultSet.getInt("developer_id"));
            developersDao.setFirstName(resultSet.getString("first_name"));
            developersDao.setLastName(resultSet.getString("last_name"));
            developersDao.setAge(resultSet.getInt("age"));
            developersDao.setSex(resultSet.getString("sex"));
            developersDao.setCompanyId(resultSet.getInt("company_id"));
            developersDao.setSalary(resultSet.getInt("salary"));
        }
        return Optional.ofNullable(developersDao);
    }

    private List<DevelopersDao> mapToList(ResultSet resultSet) throws SQLException {
        List<DevelopersDao> developers = new ArrayList<>();
        while (resultSet.next()) {
            DevelopersDao developersDao = new DevelopersDao();
            developersDao.setId(resultSet.getInt("developer_id"));
            developersDao.setFirstName(resultSet.getString("first_name"));
            developersDao.setLastName(resultSet.getString("last_name"));
            developersDao.setAge(resultSet.getInt("age"));
            developersDao.setSex(resultSet.getString("sex"));
            developersDao.setCompanyId(resultSet.getInt("company_id"));
            developersDao.setSalary(resultSet.getInt("salary"));
            developers.add(developersDao);
        }
        return developers;
    }
}

