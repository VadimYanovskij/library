package com.senla.training.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Persistent class for entity stored in table "borrow"
 *
 * @author Vadim Yanovskij
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "borrow", schema = "library")
public class Borrow {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Basic
    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Basic
    @Column(name = "repayment_date")
    private LocalDate repaymentDate;

    @Basic
    @Column(name = "return_date")
    private LocalDate returnDate;
}
