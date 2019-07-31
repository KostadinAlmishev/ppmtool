package io.agileinteligence.ppmtool.services;

import io.agileinteligence.ppmtool.domain.Project;
import io.agileinteligence.ppmtool.exceptions.ProjectIdException;
import io.agileinteligence.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateproject(Project project) {

        try {
            // make projectIdentifier upper case
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " 'already exists'");
        }

    }

    public Iterable<Project> findAllProjects() {

        Iterable<Project> projects = projectRepository.findAll();

        return projects;

    }

    public Project findByIdentifier(String projectId) {
        Project project =  projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID " + projectId + " 'does not exists'");
        }

        return project;
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null) {
            throw new ProjectIdException("Project ID " + projectId + " 'does not exists'");
        }

        projectRepository.delete(project);

    }

}
