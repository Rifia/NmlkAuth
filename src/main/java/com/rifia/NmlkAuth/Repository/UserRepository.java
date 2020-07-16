package com.rifia.NmlkAuth.Repository;

import com.rifia.NmlkAuth.Entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByLogin(String login);

}
