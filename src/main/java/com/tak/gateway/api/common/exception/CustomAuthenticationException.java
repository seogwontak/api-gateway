package com.tak.gateway.api.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    @Getter
    private String errorCode;

    @Getter
    @Setter
    private Object authentication;

    public CustomAuthenticationException(String msg) {
        super(msg);
    }

    public CustomAuthenticationException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
