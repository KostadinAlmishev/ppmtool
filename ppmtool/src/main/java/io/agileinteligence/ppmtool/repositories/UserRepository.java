package io.agileinteligence.ppmtool.repositories;

import io.agileinteligence.ppmtool.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User getById(Long id);

}
