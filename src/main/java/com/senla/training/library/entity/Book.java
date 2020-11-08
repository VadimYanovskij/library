package com.senla.training.library.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "book", schema = "library")
public class Book {
    private Integer id;
    private Author author;
    private Publisher publisher;
    private Integer publicationYear;
    private Category category;
    private BookStatus bookStatus;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public Author getAuthor() {
        return author;
    }

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    public Publisher getPublisher() {
        return publisher;
    }

    @Basic
    @Column(name = "publication_year")
    public Integer getPublicationYear() {
        return publicationYear;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }

    @ManyToOne
    @JoinColumn(name = "book_status_id", referencedColumnName = "id")
    public BookStatus getBookStatus() {
        return bookStatus;
    }

}
