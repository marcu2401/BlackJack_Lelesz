package hu.BlackJack.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class PlayEvent {

     @EventListener
     @Async
    public void play(){
         System.out.println("Játék elkezdve!");
     }
}
