package ua.goit.dl.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.ProjectsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectsRepository implements Repository<ProjectsDao> {

    private final static String FIND_BY_ID = "SELECT * FROM projects p WHERE p.project_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM projects;";
    private static final String INSERT = "INSERT INTO projects (project_id, description, manger_name, company_id, customer_id, cost) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_BY_ID = "DELETE FROM projects WHERE project_id = ?";
    private static final String UPDATE = "UPDATE projects SET description = ?, manger_name = ?, company_id = ?, customer_id = ?, cost = ? WHERE project_id = ?";


    private final DatabaseManager connector;

    public ProjectsRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public Optional<ProjectsDao> find(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProjectDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectsDao> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProjectList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public boolean save(ProjectsDao projectsDao) {
        if (find(projectsDao.getId()).isEmpty()) {
            try (Connection connection = connector.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                preparedStatement.setInt(1, projectsDao.getId());
                preparedStatement.setString(2, projectsDao.getDescription());
                preparedStatement.setString(3, projectsDao.getManagerName());
                preparedStatement.setInt(4, projectsDao.getCompanyId());
                preparedStatement.setInt(5, projectsDao.getCustomerId());
                preparedStatement.setInt(6, projectsDao.getCost());
                preparedStatement.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void remove(ProjectsDao projectsDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setInt(1, projectsDao.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(ProjectsDao projectsDao) {
        int columnsUpdated = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, projectsDao.getDescription());
            preparedStatement.setString(2, projectsDao.getManagerName());
            preparedStatement.setInt(3, projectsDao.getCompanyId());
            preparedStatement.setInt(4, projectsDao.getCustomerId());
            preparedStatement.setInt(5, projectsDao.getCost());
            preparedStatement.setInt(6, projectsDao.getId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    private Optional<ProjectsDao> mapToProjectDao(ResultSet resultSet) throws SQLException {
        ProjectsDao dao = null;
        while (resultSet.next()){
            dao = new ProjectsDao();
            dao.setId(resultSet.getInt("project_id"));
            dao.setDescription(resultSet.getString("description"));
            dao.setManagerName(resultSet.getString("manger_name"));
            dao.setCompanyId(resultSet.getInt("company_id"));
            dao.setCustomerId(resultSet.getInt("customer_id"));
            dao.setCost(resultSet.getInt("cost"));
        }
        return Optional.ofNullable(dao);
    }

    private List<ProjectsDao> mapToProjectList(ResultSet resultSet) throws SQLException {
        List<ProjectsDao> list = new ArrayList<>();
        while (resultSet.next()){
            ProjectsDao dao = new ProjectsDao();
            dao.setId(resultSet.getInt("project_id"));
            dao.setDescription(resultSet.getString("description"));
            dao.setManagerName(resultSet.getString("manger_name"));
            dao.setCompanyId(resultSet.getInt("company_id"));
            dao.setCustomerId(resultSet.getInt("customer_id"));
            dao.setCost(resultSet.getInt("cost"));
            list.add(dao);
        }
        return list;
    }
}
