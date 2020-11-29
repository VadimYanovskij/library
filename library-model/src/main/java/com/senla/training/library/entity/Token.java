package com.senla.training.library.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "blocked_token", schema = "library")
public class Token {
    private String id;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

}
