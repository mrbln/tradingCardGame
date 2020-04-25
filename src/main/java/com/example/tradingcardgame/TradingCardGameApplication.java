package com.example.tradingcardgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class TradingCardGameApplication implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(TradingCardGameApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TradingCardGameApplication.class, args);
    }

    @Override public void run(String... args) throws IOException {
        log.info("Starting game!");
        log.info("Press 1 then ENTER if you want to play: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while (choice == 1) {
            Game.run();
            log.info("Press 1 then ENTER if you want to play: ");
            choice = scanner.nextInt();
        }
        log.info("Finishing game...");
    }

}
