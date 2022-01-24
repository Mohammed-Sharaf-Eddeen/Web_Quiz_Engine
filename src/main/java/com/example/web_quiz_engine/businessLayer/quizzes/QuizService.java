package com.example.web_quiz_engine.businessLayer.quizzes;

import com.example.web_quiz_engine.persistenceLayer.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz findQuizByID(Integer id) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if (optionalQuiz.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no quiz at the specified location");
        }
        return optionalQuiz.get();
    }


    public Quiz saveQuiz(Quiz quiz) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        quiz.setOwnerUserName(userName);
        return quizRepository.save(quiz);
    }

    public ResponseEntity<Void> deleteQuiz(Integer id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        if (quizOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Quiz quiz = quizOptional.get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!Objects.equals(quiz.getOwnerUserName(), auth.getName())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        quizRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
