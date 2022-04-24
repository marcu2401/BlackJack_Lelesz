package hu.BlackJack;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import hu.BlackJack.event.DealEvent;
import hu.BlackJack.event.PlayEvent;
import hu.BlackJack.model.impl.Card;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.Scanner;


@Configuration
@SpringBootApplication
public class BlackJackApplication {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);

		System.out.println("Black Jack játék!");

		ConfigurableApplicationContext context =SpringApplication.run(BlackJackApplication.class, args);
		EventBus eventBus = context.getBean(EventBus.class);

		System.out.println("Ha szeretnéd elkezdeni a játékot nyomj 1- es!");
		int kezdoszam= sc.nextInt();
		if(kezdoszam==1){
			eventBus.post(new PlayEvent());
		}



	}

}
