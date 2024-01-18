package com.quiz.quiz_project_from_indian.controller;

import com.quiz.quiz_project_from_indian.model.Question;
import com.quiz.quiz_project_from_indian.model.QuestionWrapper;
import com.quiz.quiz_project_from_indian.model.Quiz;
import com.quiz.quiz_project_from_indian.model.Response;
import com.quiz.quiz_project_from_indian.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category
            ,@RequestParam int numberOfQuestions, @RequestParam String title ){

        return quizService.createQuiz(category, numberOfQuestions, title);
    }


    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){

        return quizService.calculateResult(id, responses);

    }
}
