package com.example.web_quiz_engine.businessLayer.quizzes;

import java.util.ArrayList;
import java.util.List;

public class UserAnswer {
    private List<Integer> answer = new ArrayList<>();

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
