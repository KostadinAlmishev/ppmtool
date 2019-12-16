package io.agileinteligence.ppmtool.repositories;

import io.agileinteligence.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    @Query(value = "select * from PROJECT", nativeQuery = true)
    Iterable<Project> findAll();

    @Query(value = "select * from Project p where p.PROJECT_IDENTIFIER = ?", nativeQuery = true)
    Project findByProjectIdentifier(String projectId);

    @Query(value = "Select * from kostadin.PROJECT p where p.project_leader = ?", nativeQuery = true)
    Iterable<Project> findAllByProjectLeader(String username);
}
