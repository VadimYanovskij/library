package com.senla.training.library.entity;

import com.senla.training.library.enums.BookStatusName;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_status", schema = "library")
public class BookStatus {

    private Integer id;
    private BookStatusName bookStatusName;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "book_status_name")
    public BookStatusName getBookStatusName() {
        return bookStatusName;
    }
}
