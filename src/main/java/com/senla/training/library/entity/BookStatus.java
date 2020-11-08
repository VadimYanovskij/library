package com.senla.training.library.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "book_status", schema = "library")
public class BookStatus {
    private Integer id;
    private String bookStatusName;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "book_status_name")
    public String getBookStatusName() {
        return bookStatusName;
    }

}
