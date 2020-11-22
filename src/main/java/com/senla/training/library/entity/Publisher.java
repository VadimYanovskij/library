package com.senla.training.library.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "publisher", schema = "library")
public class Publisher {
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
