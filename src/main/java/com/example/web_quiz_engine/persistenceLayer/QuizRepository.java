package com.example.web_quiz_engine.persistenceLayer;

import com.example.web_quiz_engine.businessLayer.quizzes.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepository extends CrudRepository<Quiz, Integer> {
}
