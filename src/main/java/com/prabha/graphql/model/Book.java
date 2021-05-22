package com.prabha.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String isn;

    private String title;

    private String publisher;

    @Column(name = "authors")
    private String author;

    @Column(name = "publisheddate")
    private String publishedDate;
}
