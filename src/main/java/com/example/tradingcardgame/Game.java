package com.example.tradingcardgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Random;

public final class Game {
    private static Logger log = LoggerFactory.getLogger(Game.class);

    private Game() {
    }

    public static void run() {
        log.info("Game is about to start!");

        Player player1 = new Player();
        Player player2 = new Player();
        int activeMana;

        takeStarterThreeCards(player1, player2);

        showGameStatus(player1, player2);

        int turn = coinToss();

        if (turn == 0) {
            log.info("player1 starts the game!");
        } else {
            log.info("player2 starts the game!");
        }

        while (player1.getHp() > 0 && player2.getHp() > 0) {
            log.info("New turn!!!");
            if (turn % 2 == 0) {
                log.info("player1 mana raises +1 {} -> {}", player1.getMana(), player1.getMana() + 1);

                activeMana = gainMana(player1);

                checkPlayableDeckSize(player1);

                log.info("player1's next card is: {}", player1.getNextCard().getCost());

                play(player1, player2, activeMana);
            } else {
                log.info("player2 mana raises +1 {} -> {}", player2.getMana(), player2.getMana() + 1);
                activeMana = gainMana(player2);

                checkPlayableDeckSize(player2);

                log.info("player2's next card is: {}", player2.getNextCard().getCost());

                play(player2, player1, activeMana);
            }

            log.info("player1 health point is: {}, mana is: {}, playable deck cards count: {}", player1.getHp(),
                player1.getMana(), player1.getPlayableDeck().getCards().size());
            player1.showCards();
            log.info("player2 health point is: {}, mana is: {}, playable deck cards count: {}", player2.getHp(),
                player2.getMana(), player2.getPlayableDeck().getCards().size());
            player2.showCards();

            turn++;
            log.info("---------------------------------------------------------------------------");
        }

        if (player1.getHp() > 0) {
            log.info("PLAYER 1 WON!");
        } else {
            log.info("PLAYER 2 WON!");
        }
    }

    private static void play(Player activePlayer, Player otherPlayer, int tempMana) {
        if (!activePlayer.isActiveDeckFull()) {
            log.info("player adding card to deck: {}", activePlayer.getNextCard().getCost());
            activePlayer.addCardToHand();
            log.info("Showing player cards");
            activePlayer.showCards();
        } else {
            log.info("Showing player cards");
            activePlayer.showCards();

            log.info("Looking if player has zero value or a playable card..");
            if (activePlayer.hasZeroCard()) {
                activePlayer.playZeroCard();
                if (!activePlayer.isPlayableDeckEmpty()) {
                    log.info("player adding card to deck: {}", activePlayer.getNextCard().getCost());
                    activePlayer.addCardToHand();
                }
            } else if (activePlayer.hasPlayableCard(tempMana)) {
                Card card = activePlayer.getHighManaCard(activePlayer.getMana());
                activePlayer.playCard(card);
                log.info("player plays card: {}", card.getCost());
                otherPlayer.setHp((otherPlayer.getHp() - card.getCost()));
                log.info("player loses hp: {}, player2 new hp: {}", card.getCost(), otherPlayer.getHp());
                tempMana = tempMana - card.getCost();
                if (!activePlayer.isPlayableDeckEmpty()) {
                    log.info("player adding card to deck: {}", activePlayer.getNextCard().getCost());
                    activePlayer.addCardToHand();
                }
            } else {
                log.info("player doesn't have any zero value or playable card");
                if (!activePlayer.isPlayableDeckEmpty()) {
                    activePlayer.discardCard();
                }
            }
        }

        while (tempMana >= 0) {
            if (activePlayer.hasPlayableCard(tempMana)) {
                Card card = activePlayer.getHighManaCard(tempMana);
                activePlayer.playCard(card);
                log.info("player plays card: {}", card.getCost());
                if (card.getCost() > 0) {
                    otherPlayer.setHp((otherPlayer.getHp() - card.getCost()));
                    log.info("player2 loses hp: {}, player2 new hp: {}", card.getCost(), otherPlayer.getHp());
                }
                tempMana = tempMana - card.getCost();
            } else {
                log.info("player doesn't have any card to play!");
                break;
            }
        }
    }

    private static void checkPlayableDeckSize(Player player) {
        if (CollectionUtils.isEmpty(Collections.singleton(player.getPlayableDeck().getCards().size()))) {
            player.setHp(player.getHp() - 1);
        }
    }

    private static int gainMana(Player player) {
        if (player.getMana() < 10) {
            player.setMana(player.getMana() + 1);
        }

        return player.getMana();
    }

    private static int coinToss() {
        return new Random().nextInt(2);
    }

    private static void showGameStatus(Player player1, Player player2) {
        log.info("player1 cards: ");
        player1.showCards();
        log.info("--------------------");
        log.info("player2 cards: ");
        player2.showCards();
        log.info("--------------------");
    }

    private static void takeStarterThreeCards(Player player1, Player player2) {
        player1.takeStarterThreeCards();
        player2.takeStarterThreeCards();
    }
}
