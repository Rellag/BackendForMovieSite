package com.quiz.quiz_project_from_indian.service;

import com.quiz.quiz_project_from_indian.dao.QuestionDAO;
import com.quiz.quiz_project_from_indian.dao.QuizDAO;
import com.quiz.quiz_project_from_indian.model.Question;
import com.quiz.quiz_project_from_indian.model.QuestionWrapper;
import com.quiz.quiz_project_from_indian.model.Quiz;
import com.quiz.quiz_project_from_indian.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int numberOfQuestions, String title) {
        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, numberOfQuestions);

        Quiz quiz = new Quiz();
        quiz.setQuestions(questions);
        quiz.setTitle(title);
        quizDAO.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q: questions){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle()
                    , q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
