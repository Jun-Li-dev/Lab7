package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuizSessionTest {

    @Test
    void testInitialValues() {
        QuizSession quiz = new QuizSession();
        assertEquals(0, quiz.getCurrentIndex(), "Initial index should be 0");
        assertEquals(0, quiz.getScore(), "Initial score should be 0");
    }

    @Test
    void testIncrementIndex() {
        QuizSession quiz = new QuizSession();
        quiz.incrementIndex();
        assertEquals(1, quiz.getCurrentIndex(), "Index should increment by 1");
    }

    @Test
    void testIncrementScore() {
        QuizSession quiz = new QuizSession();
        quiz.incrementScore();
        assertEquals(1, quiz.getScore(), "Score should increment by 1");
    }
}
