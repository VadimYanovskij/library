package com.senla.training.library.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "borrow", schema = "library")
public class Borrow {
    private Integer id;
    private User user;
    private Book book;
    private Date borrowDate;
    private Integer period;
    private Date returnDate;

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
    public Date getBorrowDate() {
        return borrowDate;
    }

    @Basic
    @Column(name = "period")
    public Integer getPeriod() {
        return period;
    }

    @Basic
    @Column(name = "return_date")
    public Date getReturnDate() {
        return returnDate;
    }

}
