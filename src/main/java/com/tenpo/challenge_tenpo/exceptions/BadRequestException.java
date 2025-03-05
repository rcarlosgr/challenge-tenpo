package com.tenpo.challenge_tenpo.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final int code;

    public BadRequestException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BadRequestException(String message) {
        super(message);
        this.code = 0;
    }

}
