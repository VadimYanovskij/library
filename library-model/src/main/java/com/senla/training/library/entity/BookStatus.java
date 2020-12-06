package com.senla.training.library.entity;

import com.senla.training.library.enums.BookStatusName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_status", schema = "library")
public class BookStatus {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "book_status_name")
    private BookStatusName bookStatusName;
}
