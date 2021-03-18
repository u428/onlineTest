package com.omad.lee.damo.Model.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;

    @ManyToMany
    private Questions questions;

    @ManyToMany
    private Variants variants;

    @Column()
    private Date datebegin;

    @Column()
    private Date dateend;

    public History() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public Date getDatebegin() {
        return datebegin;
    }

    public void setDatebegin(Date datebegin) {
        this.datebegin = datebegin;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }
}
