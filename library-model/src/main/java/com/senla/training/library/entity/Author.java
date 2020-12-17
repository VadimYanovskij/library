package com.senla.training.library.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "author"
 *
 * @author Vadim Yanovskij
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author", schema = "library")
public class Author {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "firstname")
    private String firstname;

    @Basic
    @Column(name = "lastname")
    private String lastname;
}
