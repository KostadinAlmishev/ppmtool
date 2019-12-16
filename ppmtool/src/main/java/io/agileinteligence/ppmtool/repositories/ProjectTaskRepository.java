package io.agileinteligence.ppmtool.repositories;

import io.agileinteligence.ppmtool.domain.ProjectTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    @Query(value = "Select * from PROJECTTASK pt where pt.PROJECT_IDENTIFIER = ? ORDER BY pt.PRIORITY", nativeQuery = true)
    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

    @Query(value = "Select * from PROJECTTASK pt where pt.PROJECT_SEQUENCE = ? and ROWNUM = 1", nativeQuery = true)
    ProjectTask findByProjectSequence(String sequence);
}
