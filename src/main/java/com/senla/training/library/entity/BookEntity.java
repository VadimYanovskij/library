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
@Table(name = "book", schema = "library")
public class BookEntity {
    private Integer id;
    private AuthorEntity authorByAuthorId;
    private PublisherEntity publisherByPublisherId;
    private Integer publicationYear;
    private CategoryEntity categoryByCategoryId;
    private BookStatusEntity bookStatusByBookStatusId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public AuthorEntity getAuthorByAuthorId() {
        return authorByAuthorId;
    }

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    public PublisherEntity getPublisherByPublisherId() {
        return publisherByPublisherId;
    }

    @Basic
    @Column(name = "publication_year")
    public Integer getPublicationYear() {
        return publicationYear;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "book_status_id", referencedColumnName = "id")
    public BookStatusEntity getBookStatusByBookStatusId() {
        return bookStatusByBookStatusId;
    }

}
