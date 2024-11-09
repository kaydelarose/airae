package com.kaydelarose.airae.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpError
{
    private int code;
    private String error;
    private String message;

    public HttpError(HttpStatus httpStatus, String message) {
    }
}