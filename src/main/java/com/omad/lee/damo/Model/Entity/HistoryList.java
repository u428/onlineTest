package com.omad.lee.damo.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "historylist")
public class HistoryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String questionid;

    @Column
    private String variantid;

    @ManyToOne
    @JoinColumn(name = "testing_id", referencedColumnName = "id")
    private Testings testings;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getVariantid() {
        return variantid;
    }

    public void setVariantid(String variantid) {
        this.variantid = variantid;
    }

    public Testings getTestings() {
        return testings;
    }

    public void setTestings(Testings testings) {
        this.testings = testings;
    }
}
