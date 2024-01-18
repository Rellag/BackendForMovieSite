package com.quiz.quiz_project_from_indian.service;

import com.quiz.quiz_project_from_indian.model.Question;
import com.quiz.quiz_project_from_indian.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);


    }
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category){
        try {
            return new ResponseEntity<>(questionDAO.findQuestionsByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDAO.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
}
