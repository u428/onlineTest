package com.omad.lee.damo.Model.Entity;


import javax.persistence.*;
import java.util.List;

@Entity(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String questionid;

    @Column()
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Variants> variants;

    @OneToMany(mappedBy = "questions",cascade = CascadeType.ALL)
    private List<History> history;

    public Questions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Variants> getVariants() {
        return variants;
    }

    public void setVariants(List<Variants> variants) {
        this.variants = variants;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }
}
