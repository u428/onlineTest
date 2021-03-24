package com.omad.lee.damo.Model.Entity;

import javax.persistence.*;
import java.util.List;


@Entity(name = "variants")
public class Variants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String variantid;

    @Column()
    private String name;

    @Column
    private boolean answer;

    @ManyToOne()
    @JoinColumn(name = "questionid")
    private Questions questions;

    @OneToMany(mappedBy = "variants",cascade = CascadeType.ALL)
    private List<History> history;

    public Variants() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
