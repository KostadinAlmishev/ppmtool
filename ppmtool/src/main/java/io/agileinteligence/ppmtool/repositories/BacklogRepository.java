package io.agileinteligence.ppmtool.repositories;

import io.agileinteligence.ppmtool.domain.Backlog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BacklogRepository extends CrudRepository<Backlog, Long> {

    @Query(value = "Select * from BACKLOG b where b.PROJECT_IDENTIFIER = ? and ROWNUM = 1", nativeQuery = true)
    Backlog findByProjectIdentifier(String identifier);

}
