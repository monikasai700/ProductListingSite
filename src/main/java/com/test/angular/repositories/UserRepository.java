package com.test.angular.repositories;

import com.test.angular.models.TestUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<TestUser, Long> {

}
