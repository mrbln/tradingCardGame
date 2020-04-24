package com.example.tradingcardgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TradingCardGameApplication implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(TradingCardGameApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TradingCardGameApplication.class, args);
    }

    @Override public void run(String... args) {
        log.info("Starting game!");
        Game.run();
    }

}
