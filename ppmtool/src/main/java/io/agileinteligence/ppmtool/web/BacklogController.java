package io.agileinteligence.ppmtool.web;

import io.agileinteligence.ppmtool.domain.ProjectTask;
import io.agileinteligence.ppmtool.services.MapValidationErrorService;
import io.agileinteligence.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;
    @Autowired
    MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTToBacklog(@Valid @RequestBody ProjectTask projectTask,
                                            BindingResult bindingResult,
                                            @PathVariable String backlog_id) {
        ResponseEntity<?> errMap = mapValidationErrorService.validate(bindingResult);
        if (errMap != null) {
            return errMap;
        }

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);

        return new ResponseEntity<>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {

        return projectTaskService.findBacklogById(backlog_id);
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id,
                                            @PathVariable String pt_id) {
        ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id, pt_id);

        return new ResponseEntity<>(projectTask, HttpStatus.OK);

    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult bindingResult,
                                               @PathVariable String backlog_id, @PathVariable String pt_id) {
        ResponseEntity<?> errorMap = mapValidationErrorService.validate(bindingResult);
        if (errorMap != null) return errorMap;

        ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask, backlog_id, pt_id);

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id) {
        projectTaskService.deletePTByProjectSequence(backlog_id, pt_id);

        return new ResponseEntity<>("Project Task '" + pt_id + "' was deleted sucessfully", HttpStatus.OK);
    }
}
