package com.omad.lee.damo.Model.Entity;

import javax.persistence.*;



@Entity(name = "variants")
public class Variants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Questions questions;

    @ManyToMany
    @JoinColumn(name = "variant_id")
    private History history;

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

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
