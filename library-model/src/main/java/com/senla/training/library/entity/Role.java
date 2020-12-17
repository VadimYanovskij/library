package com.senla.training.library.entity;

import com.senla.training.library.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "role"
 *
 * @author Vadim Yanovskij
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role", schema = "library")
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleName roleName;
}
