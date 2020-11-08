package com.senla.training.library.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "publisher", schema = "library", catalog = "")
public class PublisherEntity {
    private Integer id;
    private String publisherName;
    private String publisherCity;
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
    @Column(name = "publisher_name", nullable = false, length = 45)
    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Basic
    @Column(name = "publisher_city", nullable = false, length = 45)
    public String getPublisherCity() {
        return publisherCity;
    }

    public void setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublisherEntity that = (PublisherEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (publisherName != null ? !publisherName.equals(that.publisherName) : that.publisherName != null)
            return false;
        if (publisherCity != null ? !publisherCity.equals(that.publisherCity) : that.publisherCity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (publisherName != null ? publisherName.hashCode() : 0);
        result = 31 * result + (publisherCity != null ? publisherCity.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "publisherByPublisherId")
    public Collection<BookEntity> getBooksById() {
        return booksById;
    }

    public void setBooksById(Collection<BookEntity> booksById) {
        this.booksById = booksById;
    }
}
