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

        Player player1 = new Player("First player");
        Player player2 = new Player("Second player");
        int activeMana;

        takeStarterThreeCards(player1, player2);

        int turn = coinToss(player1, player2);

        while (isGameEnd(player1, player2)) {
            showGameStatus(player1, player2);

            log.info("Starting new turn..");
            if (turn % 2 == 0) {
                log.info("{} 's mana raises +1 {} -> {}", player1.getName(), player1.getMana(), player1.getMana() + 1);

                activeMana = gainMana(player1);

                checkPlayableDeckSize(player1);

                log.info("{} 's next card is: {}", player1.getName(), player1.getNextCard().getCost());

                play(player1, player2, activeMana);
            } else {
                log.info("{} 's mana raises +1 {} -> {}", player2.getName(), player2.getMana(), player2.getMana() + 1);
                activeMana = gainMana(player2);

                checkPlayableDeckSize(player2);

                log.info("{} 's next card is: {}", player2.getName(), player2.getNextCard().getCost());

                play(player2, player1, activeMana);
            }

            turn++;
            log.info("---------------------------------------------------------------------------");
        }

        logWinner(player1, player2);
    }

    private static void logWinner(Player player1, Player player2) {
        if (player1.getHp() > 0) {
            log.info("{} WON!", player1.getName());
        } else {
            log.info("{} WON!", player2.getName());
        }
    }

    private static boolean isGameEnd(Player player1, Player player2) {
        return player1.getHp() > 0 && player2.getHp() > 0;
    }

    private static void showGameStatus(Player player1, Player player2) {
        log.info("{} health point is: {}, mana is: {}, playable deck cards count: {}", player1.getName(), player1.getHp(),
            player1.getMana(), player1.getPlayableDeck().getCards().size());
        player1.showCards();
        log.info("{} health point is: {}, mana is: {}, playable deck cards count: {}", player2.getName(), player2.getHp(),
            player2.getMana(), player2.getPlayableDeck().getCards().size());
        player2.showCards();
    }

    private static void play(Player activePlayer, Player otherPlayer, int tempMana) {
        if (!activePlayer.isActiveDeckFull()) {
            log.info("{} adding card {} to deck..", activePlayer.getName(), activePlayer.getNextCard().getCost());
            activePlayer.addCardToHand();
            log.info("Showing {} cards", activePlayer.getName());
            activePlayer.showCards();
        } else {
            log.info("Showing {} cards", activePlayer.getName());
            activePlayer.showCards();

            log.info("Looking if {} has zero value or a playable card..", activePlayer.getName());
            if (activePlayer.hasZeroCard()) {
                activePlayer.playZeroCard();
                if (!activePlayer.isPlayableDeckEmpty()) {
                    log.info("{} adding card to deck: {}", activePlayer.getName(), activePlayer.getNextCard().getCost());
                    activePlayer.addCardToHand();
                }
            } else if (activePlayer.hasPlayableCard(tempMana)) {
                Card card = activePlayer.getHighManaCard(activePlayer.getMana());
                activePlayer.playCard(card);
                log.info("{} plays card: {}", activePlayer.getName(), card.getCost());
                otherPlayer.setHp((otherPlayer.getHp() - card.getCost()));
                log.info("{} loses hp: {}, player2 new hp: {}", otherPlayer.getName(), card.getCost(), otherPlayer.getHp());
                tempMana = tempMana - card.getCost();
                if (!activePlayer.isPlayableDeckEmpty()) {
                    log.info("{} adding card to deck: {}", activePlayer.getName(), activePlayer.getNextCard().getCost());
                    activePlayer.addCardToHand();
                }
            } else {
                log.info("{} doesn't have any zero value or playable card", activePlayer.getName());
                if (!activePlayer.isPlayableDeckEmpty()) {
                    activePlayer.discardCard();
                }
            }
        }

        while (tempMana >= 0) {
            if (activePlayer.hasPlayableCard(tempMana)) {
                Card card = activePlayer.getHighManaCard(tempMana);
                activePlayer.playCard(card);
                log.info("{} plays card: {}", activePlayer.getName(), card.getCost());
                if (card.getCost() > 0) {
                    otherPlayer.setHp((otherPlayer.getHp() - card.getCost()));
                    log.info("{} loses hp: {}, player new hp: {}", otherPlayer.getName(), card.getCost(), otherPlayer.getHp());
                }
                tempMana = tempMana - card.getCost();
            } else {
                log.info("{} doesn't have any card to play!", activePlayer.getName());
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

    private static int coinToss(Player player1, Player player2) {
        int turn = new Random().nextInt(2);

        if (turn == 0) {
            log.info("{} starts the game!", player1.getName());
        } else {
            log.info("{} starts the game!", player2.getName());
        }

        return turn;
    }

    private static void takeStarterThreeCards(Player player1, Player player2) {
        player1.takeStarterThreeCards();
        player2.takeStarterThreeCards();
    }
}
