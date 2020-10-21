package com.thoughtworks.basicquiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private long userId;
    private String title;
    private long year;
    private String description;
}
