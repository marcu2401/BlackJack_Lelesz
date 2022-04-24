package hu.BlackJack.event;

import com.google.common.eventbus.EventBus;
import hu.BlackJack.model.impl.Card;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;


@Component
public class DealEvent{

    @Getter
    Card card;

    Random random=new Random();

    public DealEvent(){

    }

    @Autowired
    public void setCard(){
        this.card=new Card(this.random.nextInt(13)+1);
    }


}
