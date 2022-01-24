package com.example.web_quiz_engine.businessLayer.users.completedQuizzes;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CompletedQuiz {
    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;

    //username of the user that solved the quiz
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String solvedBy;

    //not id for the completedQuiz, it is the id of the original quiz
    @JsonProperty(value = "id")
    private Integer originalQuizId;

    private String completedAt;

    public CompletedQuiz(String solvedBy, Integer quizId) {
        this.solvedBy = solvedBy;
        this.originalQuizId = quizId;
        this.completedAt = String.valueOf(java.time.LocalDateTime.now());
    }

    public CompletedQuiz() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSolvedBy() {
        return solvedBy;
    }

    public void setSolvedBy(String solvedBy) {
        this.solvedBy = solvedBy;
    }

    public Integer getOriginalQuizId() {
        return originalQuizId;
    }

    public void setOriginalQuizId(Integer originalQuizId) {
        this.originalQuizId = originalQuizId;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }
}
