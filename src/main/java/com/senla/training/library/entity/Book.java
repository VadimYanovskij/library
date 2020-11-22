package com.senla.training.library.entity;

import com.senla.training.library.enums.BookStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "book", schema = "library")
public class Book {
    private Integer id;
    private String name;
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

    @Basic
    @Column(name = "book_name")
    public String getName() {
        return name;
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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "book_status_id")
    public BookStatus getBookStatus() {
        return bookStatus;
    }
}
