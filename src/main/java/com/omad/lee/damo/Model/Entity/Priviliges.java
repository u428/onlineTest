package com.omad.lee.damo.Model.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "priviliges")
public class Priviliges {

    static Logger logger = LoggerFactory.getLogger(Priviliges.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @ManyToOne
//    @JoinTable(name = "role_priviliges",
//            joinColumns        = { @JoinColumn(name = "priviliges_id",       referencedColumnName = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
//    )
    private Role role;

    public Priviliges() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
