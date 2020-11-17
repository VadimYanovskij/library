package com.senla.training.library.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "role", schema = "library")
public class Role {
    private Integer id;
    private String roleName;
//    private Set<User> users;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

//    @ManyToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    public Set<User> getUsers() {
//        return users;
//    }
}
