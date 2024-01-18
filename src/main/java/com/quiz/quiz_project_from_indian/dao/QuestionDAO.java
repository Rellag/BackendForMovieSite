package com.quiz.quiz_project_from_indian.dao;

import com.quiz.quiz_project_from_indian.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    public List<Question> findQuestionsByCategory(String category);


    @Query(value ="SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numberOfQuestions" ,nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numberOfQuestions);
}
