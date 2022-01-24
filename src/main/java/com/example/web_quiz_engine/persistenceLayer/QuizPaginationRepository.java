package com.example.web_quiz_engine.persistenceLayer;

import com.example.web_quiz_engine.businessLayer.quizzes.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface QuizPaginationRepository extends PagingAndSortingRepository<Quiz, Integer> {
}
