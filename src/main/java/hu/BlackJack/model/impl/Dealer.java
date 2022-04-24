package hu.BlackJack.model.impl;


import com.google.common.eventbus.EventBus;
import hu.BlackJack.event.DealEvent;
import hu.BlackJack.service.DealerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class Dealer implements hu.BlackJack.model.Dealer {
    @Getter
    ArrayList<Card> cards=new ArrayList<>();
    private EventBus eventBus;

    @Autowired
    DealerService dealerService = new DealerService();

    public Dealer(){ }

    @Autowired
    void setEventBus(EventBus eventBus){
        this.eventBus=eventBus;
    }

    @Override
    public void dealCards() {
      eventBus.post(dealerService.deal());
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
