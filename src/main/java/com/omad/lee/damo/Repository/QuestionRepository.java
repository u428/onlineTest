package com.omad.lee.damo.Repository;

import com.omad.lee.damo.Model.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
    Questions findQuestionsByQuestionid(String questionid);
}
