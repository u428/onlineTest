package com.omad.lee.damo.Model.Entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable=false)
    private String userId;
    @NotNull
    @Column(nullable = false, length = 50)
    private String firstName;
    @NotNull
    @Column(nullable = false, length = 50)
    private String lastName;
    @NotNull
    @Column(nullable = false, length = 120)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String encryptedPassword;
    @NotNull
    @Column()
    private String emailVerificationToken;
    @NotNull
    @Column()
    private Boolean emailVerificationStatus;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
//    @JoinTable(name = "users_role",
//            joinColumns        = { @JoinColumn(name = "users_id", referencedColumnName = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
//    )
    private Role role;

    @NotNull
    @OneToOne(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private History history;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
