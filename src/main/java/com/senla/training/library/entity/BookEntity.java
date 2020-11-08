package com.senla.training.library.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "book", schema = "library", catalog = "")
public class BookEntity {
    private Integer id;
    private Integer authorId;
    private Integer publisherId;
    private Object publicationYear;
    private Integer categoryId;
    private Integer bookStatusId;
    private AuthorEntity authorByAuthorId;
    private PublisherEntity publisherByPublisherId;
    private CategoryEntity categoryByCategoryId;
    private BookStatusEntity bookStatusByBookStatusId;
    private Collection<BorrowEntity> borrowsById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "author_id", nullable = true)
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "publisher_id", nullable = true)
    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    @Basic
    @Column(name = "publication_year", nullable = false)
    public Object getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Object publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Basic
    @Column(name = "category_id", nullable = true)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "book_status_id", nullable = true)
    public Integer getBookStatusId() {
        return bookStatusId;
    }

    public void setBookStatusId(Integer bookStatusId) {
        this.bookStatusId = bookStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (authorId != null ? !authorId.equals(that.authorId) : that.authorId != null) return false;
        if (publisherId != null ? !publisherId.equals(that.publisherId) : that.publisherId != null) return false;
        if (publicationYear != null ? !publicationYear.equals(that.publicationYear) : that.publicationYear != null)
            return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (bookStatusId != null ? !bookStatusId.equals(that.bookStatusId) : that.bookStatusId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        result = 31 * result + (publisherId != null ? publisherId.hashCode() : 0);
        result = 31 * result + (publicationYear != null ? publicationYear.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (bookStatusId != null ? bookStatusId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public AuthorEntity getAuthorByAuthorId() {
        return authorByAuthorId;
    }

    public void setAuthorByAuthorId(AuthorEntity authorByAuthorId) {
        this.authorByAuthorId = authorByAuthorId;
    }

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    public PublisherEntity getPublisherByPublisherId() {
        return publisherByPublisherId;
    }

    public void setPublisherByPublisherId(PublisherEntity publisherByPublisherId) {
        this.publisherByPublisherId = publisherByPublisherId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "book_status_id", referencedColumnName = "id")
    public BookStatusEntity getBookStatusByBookStatusId() {
        return bookStatusByBookStatusId;
    }

    public void setBookStatusByBookStatusId(BookStatusEntity bookStatusByBookStatusId) {
        this.bookStatusByBookStatusId = bookStatusByBookStatusId;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<BorrowEntity> getBorrowsById() {
        return borrowsById;
    }

    public void setBorrowsById(Collection<BorrowEntity> borrowsById) {
        this.borrowsById = borrowsById;
    }
}
