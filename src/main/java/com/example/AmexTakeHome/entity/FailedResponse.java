package com.example.AmexTakeHome.entity;

public class FailedResponse implements OrderResponse {
    // You could fill this with whatever makes sense
    private String message;

    public FailedResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
