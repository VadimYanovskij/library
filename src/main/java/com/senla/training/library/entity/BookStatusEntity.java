package com.senla.training.library.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "book_status", schema = "library", catalog = "")
public class BookStatusEntity {
    private Integer id;
    private String bookStatusName;
    private Collection<BookEntity> booksById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "book_status_name", nullable = false, length = 45)
    public String getBookStatusName() {
        return bookStatusName;
    }

    public void setBookStatusName(String bookStatusName) {
        this.bookStatusName = bookStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookStatusEntity that = (BookStatusEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bookStatusName != null ? !bookStatusName.equals(that.bookStatusName) : that.bookStatusName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bookStatusName != null ? bookStatusName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "bookStatusByBookStatusId")
    public Collection<BookEntity> getBooksById() {
        return booksById;
    }

    public void setBooksById(Collection<BookEntity> booksById) {
        this.booksById = booksById;
    }
}
