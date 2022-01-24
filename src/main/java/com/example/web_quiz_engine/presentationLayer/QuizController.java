package com.example.web_quiz_engine.presentationLayer;

import com.example.web_quiz_engine.businessLayer.quizzes.*;
import com.example.web_quiz_engine.businessLayer.users.completedQuizzes.CompletedQuiz;
import com.example.web_quiz_engine.businessLayer.users.completedQuizzes.CompletedQuizPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class QuizController {
    private final QuizService quizService;
    private final QuizPaginationService quizPaginationService;
    private final CompletedQuizPaginationService completedQuizPaginationService;

    @Autowired
    public QuizController(QuizService quizService, QuizPaginationService quizPaginationService, CompletedQuizPaginationService completedQuizPaginationService) {
        this.quizService = quizService;
        this.quizPaginationService = quizPaginationService;
        this.completedQuizPaginationService = completedQuizPaginationService;
    }


    @GetMapping("/api/quizzes/{id}")
    public Quiz getOneQuiz(@PathVariable int id) {
        return quizService.findQuizByID(id);
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<Void> deleteOneQuiz(@PathVariable int id) {
        return quizService.deleteQuiz(id);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public AnswerResult answerQuiz(@PathVariable int id, @RequestBody UserAnswer userAnswer) {
        AnswerResult answerResult = quizService.findQuizByID(id).checkAnswer(userAnswer);
        if (answerResult.isSuccess()) {
            completedQuizPaginationService.addCompletedQuiz(id);
        }
        return answerResult;
    }

    @PostMapping("/api/quizzes")
    public Quiz postQuiz(@Valid @RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }

    @GetMapping("/api/quizzes")
    public Page<Quiz> getPageOfQuizzes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return quizPaginationService.getPageOfQuizzes(page, pageSize, sortBy);
    }

    @GetMapping("/api/quizzes/completed")
    public Page<CompletedQuiz> getPageOfCompletedQuizzes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        return completedQuizPaginationService.getPageOfCompletedQuizzes(page, pageSize);
    }

}
