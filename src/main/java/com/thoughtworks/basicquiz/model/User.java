package com.thoughtworks.basicquiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;

    @NotEmpty(message = "username不能为空")
    // TODO GTB-3: - 题目要求的是长度范围是1 - 128 bytes。而@Size是标记字符的长度。
    @Size(min = 1, max = 128, message = "username长度需为1-128位")
    private String name;

    @NotNull(message = "age不能为空")
    @Min(17)
    private long age;

    @NotEmpty(message = "avatar不能为空")
    @Size(min = 8, max = 512, message = "avatar长度需为8-512位")
    private String avatar;

    @Size(max = 1024, message = "description长度需为0-1024位")
    private String description;
}
