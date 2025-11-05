package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import com.example.demo.model.QuizSession;
import com.example.demo.model.GetQuestion;
import com.example.demo.model.questions.QuestionTrueFalse;
import com.example.demo.model.Greeting;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }

    // Show first question OR continue quiz
    @GetMapping({"/get_question", "/get-question"})
    public String questionForm(HttpSession session, Model model) {
        QuizSession quiz = (QuizSession) session.getAttribute("quiz");
        if (quiz == null) {
            quiz = new QuizSession();
            session.setAttribute("quiz", quiz);
        }

        GetQuestion getQuestion = new GetQuestion();
        QuestionTrueFalse q = getQuestion.nextQuestion(quiz.getCurrentIndex());

        model.addAttribute("questionText", q.getQuestion());
        model.addAttribute("questionNumber", quiz.getCurrentIndex() + 1);
        model.addAttribute("score", quiz.getScore());
        model.addAttribute("feedback", "");

        return "question";
    }

    // Process submitted answer OR start over
    @PostMapping({"/get_question", "/get-question"})
    public String questionFormPOST(
            @RequestParam(name = "answer", required = false) String answer,
            HttpSession session,
            Model model) {

        // Handle "Start Over" button
        if ("reset".equals(answer)) {
            session.invalidate();
            return "redirect:get_question"; // relative redirect works in Codespaces
        }

        QuizSession quiz = (QuizSession) session.getAttribute("quiz");
        if (quiz == null) {
            quiz = new QuizSession();
            session.setAttribute("quiz", quiz);
        }

        GetQuestion getQuestion = new GetQuestion();
        QuestionTrueFalse current = getQuestion.nextQuestion(quiz.getCurrentIndex());

        boolean answerBool = Boolean.parseBoolean(answer != null ? answer : "false");
        String feedback;

        if (answer != null && answerBool == current.getAnswer()) {
            quiz.incrementScore();
            feedback = "Correct!";
        } else {
            feedback = "Wrong!";
        }

        quiz.incrementIndex();

        QuestionTrueFalse next = getQuestion.nextQuestion(quiz.getCurrentIndex());

        model.addAttribute("questionText", next.getQuestion());
        model.addAttribute("questionNumber", quiz.getCurrentIndex() + 1);
        model.addAttribute("score", quiz.getScore());
        model.addAttribute("feedback", feedback);

        return "question";
    }
}
