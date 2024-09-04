package com.example.Ingress_lab.exception;

import lombok.Getter;

@Getter
public class GuideBusyException extends RuntimeException{
    private String code;

    public GuideBusyException(String message, String code) {
        super(message);
        this.code = code;
    }
}
