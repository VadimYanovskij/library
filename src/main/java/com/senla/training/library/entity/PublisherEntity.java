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
@Table(name = "publisher", schema = "library")
public class PublisherEntity {
    private Integer id;
    private String publisherName;
    private String publisherCity;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "publisher_name")
    public String getPublisherName() {
        return publisherName;
    }

    @Basic
    @Column(name = "publisher_city")
    public String getPublisherCity() {
        return publisherCity;
    }

}
