package com.example.web_quiz_engine.businessLayer.users.completedQuizzes;

import com.example.web_quiz_engine.persistenceLayer.CompletedQuizPaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CompletedQuizPaginationService {
    private final CompletedQuizPaginationRepository completedQuizPaginationRepository;

    @Autowired
    public CompletedQuizPaginationService(CompletedQuizPaginationRepository completedQuizPaginationRepository) {
        this.completedQuizPaginationRepository = completedQuizPaginationRepository;
    }

    public Page<CompletedQuiz> getPageOfCompletedQuizzes(Integer pageNo, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "completedAt"));
        String solvedBy = getCurrentUserName();
        return completedQuizPaginationRepository.findAllBySolvedBy(solvedBy, pageRequest);
    }

    public void addCompletedQuiz(Integer quizId) {
        String solvedBy = getCurrentUserName();
        CompletedQuiz completedQuiz = new CompletedQuiz(solvedBy, quizId);
        completedQuizPaginationRepository.save(completedQuiz);
    }

    private String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
