package hu.BlackJack.service;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import hu.BlackJack.event.DealEvent;
import hu.BlackJack.model.impl.Card;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Random;

@Service
public class DealerService {

    Card card;
    EventBus eventBus;

//    Random random=new Random();

    @Autowired
    public void setCard(Card card) {
        this.card = card;
    }

    @Autowired
    void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /*
    public void deal(){
        this.eventBus.post(new DealEvent());
    }
*/
    @PostConstruct
    void initialize(){
        this.eventBus.register(this);
    }


    public DealEvent deal(){
      return new DealEvent();
    }

    @Subscribe
    void dealCard(DealEvent dealEvent){
        this.card=dealEvent.getCard();
    }



}
