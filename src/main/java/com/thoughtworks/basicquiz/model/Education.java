package com.thoughtworks.basicquiz.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private long year;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
