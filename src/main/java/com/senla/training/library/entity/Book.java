package com.senla.training.library.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "book", schema = "library")
public class Book {
    private Integer id;
    private Set<Author> authors;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "author_book",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
    public Set<Author> getAuthors() {
        return authors;
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
