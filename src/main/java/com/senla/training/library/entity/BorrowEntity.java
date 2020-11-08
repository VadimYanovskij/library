package com.senla.training.library.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "borrow", schema = "library", catalog = "")
public class BorrowEntity {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Date borrowDate;
    private Integer period;
    private Date returnDate;
    private UserEntity userByUserId;
    private BookEntity bookByBookId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "book_id", nullable = true)
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "borrow_date", nullable = false)
    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Basic
    @Column(name = "period", nullable = false)
    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    @Basic
    @Column(name = "return_date", nullable = false)
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowEntity that = (BorrowEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (bookId != null ? !bookId.equals(that.bookId) : that.bookId != null) return false;
        if (borrowDate != null ? !borrowDate.equals(that.borrowDate) : that.borrowDate != null) return false;
        if (period != null ? !period.equals(that.period) : that.period != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        result = 31 * result + (borrowDate != null ? borrowDate.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }
}
