package com.seahere.backend.outgoing.exception;

import com.seahere.backend.common.exception.SeaHereException;

public class OutgoingReqNotFoundException extends SeaHereException {
    private static String MESSAGE = "존재하는 출고 요청이 없습니다.";

    public OutgoingReqNotFoundException() {
        super(MESSAGE);
    }

    public OutgoingReqNotFoundException(String message) {
        super(message);
    }

    public OutgoingReqNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 403;
    }
}
