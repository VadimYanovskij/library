package com.senla.training.library.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user", schema = "library")
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private Set<Role> roles;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    @Basic
    @Column(name = "birthday")
    public LocalDate getBirthday() {
        return birthday;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    public Set<Role> getRoles() {
        return roles;
    }

}
