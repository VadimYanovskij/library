package com.senla.training.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publisher", schema = "library")
public class Publisher {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "publisher_name")
    private String publisherName;

    @Basic
    @Column(name = "publisher_city")
    private String publisherCity;
}
