package com.example.web_quiz_engine.persistenceLayer;

import com.example.web_quiz_engine.businessLayer.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
