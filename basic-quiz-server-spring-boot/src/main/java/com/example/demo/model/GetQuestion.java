package com.example.demo.model;

import com.example.demo.model.questions.ArrayQuestionsTrueFalse;
import com.example.demo.model.questions.QuestionTrueFalse;

public class GetQuestion {
    private static ArrayQuestionsTrueFalse arrayListQuestionsTF = new ArrayQuestionsTrueFalse();

    public GetQuestion() {}

    public QuestionTrueFalse nextQuestion(int index) {
        return arrayListQuestionsTF.nextQuestion(index);
    }
}
