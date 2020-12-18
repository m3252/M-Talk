package com.msc.mtalk.error.exception;

public class DuplicateException extends InvalidValueException {

    private static final long serialVersionUID = 7997903205234411456L;

    public DuplicateException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
