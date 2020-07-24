package com.example.gitapi.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by alireza on 7/11/20.
 */
@Component
public class PostMethodCallReceiver {

    @EventListener
    public void handleEvent(PostMethodCallEvent postMethodCallEvent){
        System.out.println("hahahahahaha");
    }

}
