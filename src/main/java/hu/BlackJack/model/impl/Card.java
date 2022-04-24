package hu.BlackJack.model.impl;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class Card {
    @Setter @Getter
    int type;

    @Getter @Setter
    String name;
    @Getter @Setter
    int value;


    public Card(int type){
        this.type=type;
        switch(this.type){
            case 1:
                this.name="ACE";
                this.value=1;
                break;
            case 2:
                this.name="TWO";
                this.value=2;
                break;
            case 3:
                this.name="THREE";
                this.value=3;
                break;
            case 4:
                this.name="FOUR";
                this.value=4;
                break;
            case 5:
                this.name="FIVE";
                this.value=5;
                break;
            case 6:
                this.name="SIX";
                this.value=6;
                break;
            case 7:
                this.name="SEVEN";
                this.value=7;
                break;
            case 8:
                this.name="EIGHT";
                this.value=8;
                break;
            case 9:
                this.name="NINE";
                this.value=9;
                break;
            case 10:
                this.name="TEN";
                this.value=10;
                break;
            case 11:
                this.name="JACK";
                this.value=10;
                break;
            case 12:
                this.name="QUEEN";
                this.value=10;
                break;
            case 13:
                this.name="KING";
                this.value=10;
                break;
                /*
            default:
                this.name="JOKER";
                break;*/
        }
    }
    public Card(){

    }

    @PostConstruct
    void initialize( ){

    }

    /*
    void setCardType(int type){

    }
*/



}
