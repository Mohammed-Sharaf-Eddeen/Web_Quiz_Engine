package com.example.web_quiz_engine.businessLayer.quizzes;

import com.example.web_quiz_engine.persistenceLayer.QuizPaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class QuizPaginationService {
    private final QuizPaginationRepository quizPaginationRepository;

    @Autowired
    public QuizPaginationService(QuizPaginationRepository quizPaginationRepository) {
        this.quizPaginationRepository = quizPaginationRepository;
    }

    public Page<Quiz> getPageOfQuizzes(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return quizPaginationRepository.findAll(pageRequest);
    }
}
