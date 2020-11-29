package com.senla.training.library.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "borrow", schema = "library")
public class Borrow {
    private Integer id;
    private User user;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate repaymentDate;
    private LocalDate returnDate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    public Book getBook() {
        return book;
    }

    @Basic
    @Column(name = "borrow_date")
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    @Basic
    @Column(name = "repayment_date")
    public LocalDate getRepaymentDate() {
        return repaymentDate;
    }

    @Basic
    @Column(name = "return_date")
    public LocalDate getReturnDate() {
        return returnDate;
    }

}
