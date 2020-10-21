package com.thoughtworks.basicquiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private long userId;

    @NotEmpty
    @Size(min = 1, max = 256, message = "title长度需为1-128位")
    private String title;

    @NotEmpty
    private long year;

    @NotEmpty
    @Size(min = 1, max = 4096, message = "description长度需为1-128位")
    private String description;
}
