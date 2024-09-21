package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Entity // ADded so Spring Data knows what it is
@Table(name = "stations") // Name the table in our database
public class Station {

    @Id // tells SPring Data its an ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generates the number for us
    @Column // It's a column in the table.
    private Integer id;

    @Column // It's a column in the table.
    private String name;

    @Column // It's a column in the table.
    private Integer platformCount;

    @Column // It's a column in the table.
    private LocalDate dateOpened;
}
