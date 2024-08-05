package com.seahere.backend.inventory.exception;

import com.seahere.backend.common.exception.SeaHereException;

public class InventoryNotFoundException extends SeaHereException {
    private final static String MESSAGE = "해당하는 재고가 없습니다.";

    public InventoryNotFoundException() {
        super(MESSAGE);
    }

    public InventoryNotFoundException(String message) {
        super(message);
    }

    public InventoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}