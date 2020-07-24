package com.example.gitapi.event;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * Created by alireza on 7/11/20.
 */

public class PostMethodCallEvent extends ApplicationEvent {

    String message;

    public PostMethodCallEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
