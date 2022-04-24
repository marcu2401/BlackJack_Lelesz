package hu.BlackJack.service;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import hu.BlackJack.event.DealEvent;
import hu.BlackJack.model.impl.Card;
import hu.BlackJack.model.impl.Dealer;
import hu.BlackJack.model.impl.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@Service
public class GameService {

    //Card card;

    Player player;
    Dealer dealer;

    int bet;

    EventBus eventBus;

    Scanner sc=new Scanner(System.in);

    Random random=new Random();

    @Autowired
    void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Autowired
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Autowired
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    Card randomCard(){
        return new Card(this.random.nextInt(13)+1);
    }

 /*  public void  setCard(){
        this.card=new Card(this.random.nextInt(13)+1);
    }*/

    @PostConstruct
    void initialize(){
        this.eventBus.register(this);
    }


    public void play(){
        player.setMoney(100);
        System.out.println("100 egység pénzed van! Jó szórakozást!" );

        while (player.getMoney()>0 ){
            System.out.println();
            System.out.println("Új játék sok sikert! Jelenlegi pénzed: "+player.getMoney());

            System.out.println("Mennyi tétet szeretnél tenni?");
            int tét=sc.nextInt();
            bet=tét;

            player.cleanCards();
            dealer.cleanCards();

            for (int i = 0; i <2 ; i++) {
                dealer.pushCardToCards(randomCard());
                player.pushCardToCards(randomCard());
            }

            setAceValue(dealer.getCards());
            setAceValue(player.getCards());

            System.out.println("Az osztó lapja:"+dealer.getOneCardFromCards(0).getName());
            System.out.println("Lapjaid:"+player.getAllCardNameFromCards());


            while(player.getTotalValueFromCards()<=21 ){
                System.out.println("Mit szeretnél tenni? (1)HIT 2(STAND)  ");
                int todo= sc.nextInt();
                if(todo<1 || todo>2){
                    System.out.println("Kérlek a megfelelő számot add meg! (1)HIT 2(STAND)");
                }
                if(todo==1){
                    player.pushCardToCards(randomCard());
                    setAceValue(player.getCards());
                    System.out.println("Lapjaid:"+player.getAllCardNameFromCards());
                    System.out.println("Lapjaid értéke: "+player.getTotalValueFromCards());
                    if(player.getTotalValueFromCards()>21 ){
                        System.out.println("Vesztettél a lapjaid összege: "+player.getTotalValueFromCards()+", azaz nagyobb minnt 21!");
                        player.setMoney(player.getMoney()-bet);
                        break;
                    }
                    continue;
                }
                if(todo==2){
                    break;
                }
            }

            System.out.println("Az osztó lapjai: "+dealer.getAllCardNameFromCards());
            System.out.println("Az osztó lapjainak összértéke: "+dealer.getTotalValueFromCards());

            if(player.getTotalValueFromCards()>dealer.getTotalValueFromCards() && player.getTotalValueFromCards()<=21){
                System.out.println("Gratulálok nyertél!");
                player.setMoney(player.getMoney()+rateCalculate(bet, 1,2));
            }
            if(dealer.getTotalValueFromCards()> player.getTotalValueFromCards() && dealer.getTotalValueFromCards()<=21){
                System.out.println("Az osztó nyert! :(");
                player.setMoney(player.getMoney()-bet);
            }
            if(dealer.getTotalValueFromCards()==player.getTotalValueFromCards()){
                System.out.println("Döntetlen!");
            }


            if(player.getMoney()>0)continue;

            if(player.getMoney()<=0){
                System.out.println("Sajnos vesztettél, elfogyott a pénzed!");
                break;
            }
        }
    }

    void setAceValue(ArrayList<Card> cards){
        int totalValue=0;
        for (Card card: cards) {
            totalValue+=card.getValue();
            if(card.getName()=="ACE" && totalValue<21){
                card.setValue(11);
                totalValue+=10;

                if(totalValue>21){
                    totalValue-=10;
                    card.setValue(1);
                }
            }
        }
    }

    private  int rateCalculate(int money, int a, int b){
        money=(money/(a+b))*a;
        return money;
    }

    Card  generateRandomCard(DealEvent dealEvent){
        return dealEvent.getCard();
    };

    @Subscribe
    void getRadomCard(DealEvent dealEvent) {
        generateRandomCard(dealEvent);
    }
}
