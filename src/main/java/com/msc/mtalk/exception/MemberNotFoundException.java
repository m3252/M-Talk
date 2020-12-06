package com.msc.mtalk.exception;

import java.util.function.Supplier;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String message) {
        super(message);
    }

}
