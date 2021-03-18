package com.omad.lee.damo.Model.Entity;


import javax.persistence.*;

@Entity(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
//    private String questionid;

    @Column()
    private String question;

    @ManyToMany(cascade = CascadeType.ALL)
    private Variants variants;

    @ManyToMany
    @JoinColumn(name = "history_id")
    private History history;

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

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
