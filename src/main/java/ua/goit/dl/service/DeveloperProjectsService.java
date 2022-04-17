package ua.goit.dl.service;

import ua.goit.dl.relationshep.DevelopersProjects;
import ua.goit.dl.repository.DevelopersRepository;
import ua.goit.dl.repository.ProjectsRepository;
import ua.goit.model.convecter.DevelopersConverter;
import ua.goit.model.convecter.ProjectsConverter;
import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dao.ProjectsDao;
import ua.goit.model.dto.DevelopersDto;

public class DeveloperProjectsService {
    DevelopersRepository developersRepository;
    ProjectsRepository projectsRepository;
    DevelopersProjects developersProjects;
    DevelopersConverter developersConverter;
    ProjectsConverter projectsConverter;

    public DeveloperProjectsService(DevelopersRepository developersRepository, ProjectsRepository projectsRepository,
                                    DevelopersProjects developersProjects, DevelopersConverter developersConverter, ProjectsConverter projectsConverter) {
        this.developersRepository = developersRepository;
        this.projectsRepository = projectsRepository;
        this.developersProjects = developersProjects;
        this.developersConverter = developersConverter;
        this.projectsConverter = projectsConverter;
    }

    public void addDeveloperToProject (DevelopersDao developerDao, ProjectsDao projectsDao) {
        if(developersRepository.find(developerDao.getId()).isPresent() && projectsRepository.find(projectsDao.getId()).isPresent()) {
            developersProjects.addRelation(developerDao, projectsDao);
        }
        else throw new IllegalArgumentException("there are no such Developer or Project");
    }
}
