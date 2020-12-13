package com.msc.mtalk.error.exception;

public class DuplicationException extends InvalidValueException {

    public DuplicationException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
