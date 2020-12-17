package com.senla.training.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent class for entity stored in table "blocked_token"
 *
 * @author Vadim Yanovskij
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blocked_token", schema = "library")
public class Token {

    @Id
    @Column(name = "id")
    private String id;
}
