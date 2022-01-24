package com.example.web_quiz_engine.persistenceLayer;

import com.example.web_quiz_engine.businessLayer.users.completedQuizzes.CompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedQuizPaginationRepository extends PagingAndSortingRepository<CompletedQuiz, Integer> {

    Page<CompletedQuiz> findAllBySolvedBy(String solvedBy, Pageable pageable);

}
