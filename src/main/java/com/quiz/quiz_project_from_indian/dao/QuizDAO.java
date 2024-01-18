package com.quiz.quiz_project_from_indian.dao;

import com.quiz.quiz_project_from_indian.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz, Integer> {
}
