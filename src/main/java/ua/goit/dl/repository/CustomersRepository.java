package ua.goit.dl.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.CustomersDao;

import javax.xml.stream.events.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomersRepository implements Repository<CustomersDao>{

    private final static String FIND_BY_ID = "SELECT * FROM customers c WHERE c.customer_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM customers;";
    private static final String INSERT = "INSERT INTO customers (customer_id, customer_name, customer_location) VALUES (?, ?, ?)";
    private static final String REMOVE_BY_ID = "DELETE FROM customers WHERE customer_id = ?";
    private static final String UPDATE = "UPDATE customers SET customer_name = ?, customer_location = ? WHERE customer_id = ?";

    private final DatabaseManager connector;

    public CustomersRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public Optional<CustomersDao> find(Integer id) {
        try (Connection connection = connector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCustomerDao(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CustomersDao> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)
        ){
            ResultSet resultSet =  preparedStatement.executeQuery();
            return mapToListCustomers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public boolean save(CustomersDao customersDao) {
        if (find(customersDao.getId()).isEmpty()) {
            try (Connection connection = connector.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                preparedStatement.setInt(1, customersDao.getId());
                preparedStatement.setString(2, customersDao.getName());
                preparedStatement.setString(3, customersDao.getLocation());
                preparedStatement.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void remove(CustomersDao customersDao) {
        try (Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
                preparedStatement.setInt(1, customersDao.getId());
                preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        }

    @Override
    public int update(CustomersDao customersDao) {
        try (Connection connection = connector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, customersDao.getLocation());
            preparedStatement.setString(2, customersDao.getName());
            preparedStatement.setInt(3, customersDao.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private Optional<CustomersDao> mapToCustomerDao (ResultSet resultSet) throws SQLException {
        CustomersDao customersDao = null;
        while(resultSet.next()) {
            customersDao = new CustomersDao();
            customersDao.setId(resultSet.getInt("customer_id"));
            customersDao.setName(resultSet.getString("customer_name"));
            customersDao.setLocation(resultSet.getString("customer_location"));
        }
        return Optional.ofNullable(customersDao);
    }

    private List<CustomersDao> mapToListCustomers (ResultSet resultSet) throws SQLException {
        List<CustomersDao> list = new ArrayList<>();
        while (resultSet.next()) {
            CustomersDao customersDao = new CustomersDao();
            customersDao.setId(resultSet.getInt("customer_id"));
            customersDao.setName(resultSet.getString("customer_name"));
            customersDao.setLocation(resultSet.getString("customer_location"));
            list.add(customersDao);
        }
        return list;
    }
}
