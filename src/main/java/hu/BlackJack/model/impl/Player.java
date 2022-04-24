package hu.BlackJack.model.impl;

import com.google.common.eventbus.EventBus;
import hu.BlackJack.event.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Player implements hu.BlackJack.model.Player {
    @Setter @Getter
    int money;
    @Getter
    ArrayList<Card> cards=new ArrayList<>();
    private EventBus eventBus;

    public Player(int money){
        this.money=money;
    }

    public Player(){  }

    @Autowired
    void setEventBus(EventBus eventBus){
        this.eventBus=eventBus;
    }

    @Override
    public void hit() {
        this.eventBus.post(new HitEvent());
    }

    @Override
    public void stay() {
        this.eventBus.post(new StayEvent());
    }

    @Override
    public void bust() {
        this.eventBus.post(new BustEvent());
    }

    @Override
    public void push() {
        this.eventBus.post(new PushEvent());
    }

    public void pushCardToCards(Card card) {
        this.cards.add(card);
    }

    public Card getOneCardFromCards(int szam ) {
      return   this.cards.get(szam);
    }

    public int getTotalValueFromCards(){
        int value=0;
        for(Card card: cards ){
            value+=card.getValue();
        }
        return value;
    }

    public void cleanCards(){
        cards.clear();
    }

    public String  getAllCardNameFromCards(){
        String CardNames="";
        for (int i = 0; i < cards.size(); i++) {
            CardNames+=cards.get(i).getName()+", ";
        }
        return CardNames;
    }
}

