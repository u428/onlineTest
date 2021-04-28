package com.omad.lee.damo.Repository;

import com.omad.lee.damo.Model.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
    Questions findQuestionsByQuestionid(String questionid);


    @Query(value = "SELECT * FROM questions q ORDER BY RANDOM() LIMIT 30", nativeQuery = true)
    List<Questions> getRandomQuestions();
}