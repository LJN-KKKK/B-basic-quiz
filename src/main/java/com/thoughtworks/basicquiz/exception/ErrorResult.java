package com.thoughtworks.basicquiz.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;

}
