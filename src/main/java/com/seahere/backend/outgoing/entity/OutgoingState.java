package com.seahere.backend.outgoing.entity;

public enum OutgoingState {
    pending("출고요청"),ready("출고대기"),complete("출고완료"),reject("출고거절");

    private String text;

    OutgoingState(String text) {
        this.text = text;
    }

    public String printState(){
        return this.text;
    }
}
