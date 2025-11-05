package com.example.demo.model;

public class QuizSession {
    private int currentIndex = 0;
    private int score = 0;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void incrementIndex() {
        currentIndex++;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}
