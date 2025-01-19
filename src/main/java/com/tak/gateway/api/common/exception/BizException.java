package com.tak.gateway.api.common.exception;

import lombok.Getter;
import lombok.Setter;

public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String errorCode;

    @Getter
    @Setter
    private String displayMessage;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
