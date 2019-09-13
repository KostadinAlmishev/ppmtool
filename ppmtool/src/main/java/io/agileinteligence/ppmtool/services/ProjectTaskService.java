package io.agileinteligence.ppmtool.services;

import io.agileinteligence.ppmtool.domain.Backlog;
import io.agileinteligence.ppmtool.domain.Project;
import io.agileinteligence.ppmtool.domain.ProjectTask;
import io.agileinteligence.ppmtool.exceptions.ProjectNotFoundException;
import io.agileinteligence.ppmtool.repositories.BacklogRepository;
import io.agileinteligence.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        // Exceptions: Project not found

        // PTs to be added to a specific project, project != null
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        // set the bl to pt
        projectTask.setBacklog(backlog);
        projectTask.setProjectIdentifier(projectIdentifier);

        // we want our sequence to be like this IDPRO-1 IDPRO-2 IDPRO-3 ...
        Integer backlogSequence = backlog.getPTSequence();
        // Update the BL SEQUENCE
        backlogSequence++;
        backlog.setPTSequence(backlogSequence);

        // Add sequence to project task
        projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backlogSequence);

        // INITIAL priority when priority is null
        if (projectTask.getPriority() == null ||
                projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }

        // INITIAL status when status is null
        if (projectTask.getStatus() == null ||
            projectTask.getStatus().isEmpty()) {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {

        // make sure we are searching on the right backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if (backlog == null) {
            throw new ProjectNotFoundException("Project with ID: '" + backlog_id + "' does not exist");
        }

        // make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' not found");
        }

        // make sure that the baklog/project id in the path corresponds to the right project
        if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' does not exists in project: '" + backlog_id + "'");
        }

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }

    public void deletePTByProjectSequence(String backlog_id, String pt_id) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTaskRepository.delete(projectTask);
    }


    // Update project task

    // find existing project task

    // replace it with updated task

    // save update
}
