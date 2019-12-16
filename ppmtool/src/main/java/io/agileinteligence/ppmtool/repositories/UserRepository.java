package io.agileinteligence.ppmtool.repositories;

import io.agileinteligence.ppmtool.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "Select * from USERR u where u.USERNAME = ? and ROWNUM = 1", nativeQuery = true)
    User findByUsername(String username);
    @Query(value = "Select * from USERR u where u.ID = ? and ROWNUM = 1", nativeQuery = true)
    User getById(Long id);

}
