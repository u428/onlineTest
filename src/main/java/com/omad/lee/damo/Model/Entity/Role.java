package com.omad.lee.damo.Model.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "role_name")
    private String roleName;

    @OneToOne(mappedBy = "role")
    private UserEntity userEntity;


    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "role_priviliges",
//            joinColumns        = { @JoinColumn(name = "role_id",       referencedColumnName = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "priviliges_id", referencedColumnName = "id") }
//    )
    private Set<Priviliges> priviliges;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Priviliges> getPriviliges() {
        return priviliges;
    }

    public void setPriviliges(Set<Priviliges> priviliges) {
        this.priviliges = priviliges;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
