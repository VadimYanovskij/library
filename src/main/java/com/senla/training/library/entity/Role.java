package com.senla.training.library.entity;

import com.senla.training.library.enums.RoleName;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "role", schema = "library")
public class Role {
    private Integer id;
    private RoleName roleName;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    public RoleName getRoleName() {
        return roleName;
    }

}
