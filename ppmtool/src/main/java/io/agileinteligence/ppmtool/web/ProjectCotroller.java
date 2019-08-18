package io.agileinteligence.ppmtool.web;

import io.agileinteligence.ppmtool.domain.Project;
import io.agileinteligence.ppmtool.services.MapValidationErrorService;
import io.agileinteligence.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/project")
@CrossOrigin
public class ProjectCotroller {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService = new MapValidationErrorService();


    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {

        ResponseEntity<?> errMap = mapValidationErrorService.validate(bindingResult);
        if (errMap != null) {
            return errMap;
        }


        Project project1 = projectService.saveOrUpdateproject(project);

        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {

        Project project = projectService.findByIdentifier(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<Iterable<Project>>(projectService.findAllProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectId) {

        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String>("Project with ID " + projectId + " is deleted", HttpStatus.OK);
    }
}
