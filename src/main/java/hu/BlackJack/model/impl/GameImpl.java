package hu.BlackJack.model.impl;

import com.google.common.eventbus.EventBus;
import hu.BlackJack.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class GameImpl {
    //private Random random;
    @Autowired
    GameService gameService;
    private EventBus eventBus;

    public GameImpl(){

    }


    @Autowired
    void setEventBus(EventBus eventBus){
        this.eventBus=eventBus;
    }

    @Autowired
    Player player;

    @Autowired
    Dealer dealer;


    @PostConstruct
    public void startGame(){
        gameService.play();
    }





}
