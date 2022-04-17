package ua.goit.dl.relationshep;

import ua.goit.config.DatabaseManager;
import ua.goit.dl.repository.Repository;
import ua.goit.model.dao.DeveloperProjectsDao;
import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dao.ProjectsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DevelopersProjects implements Repository<DeveloperProjectsDao>, ManyToMany<DevelopersDao, ProjectsDao> {

    private static final String INSERT_DEVELOPER_PROJECT = "INSERT INTO developers_projects(developer_id, project_id) VALUES(?, ?);";

    private final DatabaseManager manager;

    public DevelopersProjects(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void addRelation(DevelopersDao first, ProjectsDao second) {
        try (Connection connection = manager.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_DEVELOPER_PROJECT)) {
            ps.setLong(1, first.getId());
            ps.setLong(2, second.getId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Optional<DeveloperProjectsDao> find(Integer id) {
        return Optional.empty();
    }


    @Override
    public List<DeveloperProjectsDao> findAll() {
        return null;
    }

    @Override
    public boolean save(DeveloperProjectsDao developerProjectsDao) {
        return false;
    }

    @Override
    public void remove(DeveloperProjectsDao developerProjectsDao) {

    }

    @Override
    public int update(DeveloperProjectsDao developerProjectsDao) {
        return 0;
    }
}
