package com.crazystone.me.customview.test;

/**
 * Created by crazy_stone on 17-7-24.
 */

public class Event {

    private String message;

    public Event(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Event setMessage(String message) {
        this.message = message;
        return this;
    }
}
