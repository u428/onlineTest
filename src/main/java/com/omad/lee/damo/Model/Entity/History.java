package com.omad.lee.damo.Model.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "history",cascade = CascadeType.ALL)
    private List<Testings> testings;

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

    public List<Testings> getTests() {
        return testings;
    }

    public void setTests(List<Testings> testings) {
        this.testings = testings;
    }

}
