package com.example.tradingcardgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

public final class Game {
    private static Logger log = LoggerFactory.getLogger(Game.class);

    private Game() {
    }

    public static void run() {
        log.info("hello gamers!");

        Player player1 = new Player();
        Player player2 = new Player();

        for (int activeCardCount = 0; activeCardCount < 3; activeCardCount++) {
            player1.getActiveDeck().add(player1.getPlayableDeck().getCard());
            player2.getActiveDeck().add(player2.getPlayableDeck().getCard());
        }

        log.info("player1 cards: ");
        player1.showCards();
        log.info("--------------------");
        log.info("player2 cards: ");
        player2.showCards();
        log.info("--------------------");

        int turn = 0;

        while (player1.getHp() > 0 && player2.getHp() > 0) {
            log.info("New turn!!!");
            if (turn % 2 == 0) {
                if (player1.getMana() < 10) {
                    log.info("player1 mana raises +1 {} -> {}", player1.getMana(),
                        player1.getMana() + 1);
                    player1.setMana(player1.getMana() + 1);
                    log.info("player1 mana is now: {}", player1.getMana());
                }

                if (CollectionUtils
                    .isEmpty(Collections.singleton(player1.getPlayableDeck().getCards().size()))) {
                    player1.setHp(player1.getHp() - 1);
                }

                log.info("player1 active deck size: {}", player1.getActiveDeck().size());
                if (player1.getActiveDeck().size() < 5) {
                    log.info("player1 taking a new card from deck: {}",
                        player1.getPlayableDeck().getCards()
                            .get(player1.getPlayableDeck().getCards().size() - 1).getCost());
                    player1.getActiveDeck().add(player1.getPlayableDeck().getCard());
                    log.info("Showing player1 cards");
                    player1.showCards();
                } else {
                    player1.showCards();
                    log.info("Looking if player has zero value card..");
                    if (player1.hasZeroCard()) {
                        for (Card card : player1.getActiveDeck()) {
                            if (card.getCost() == 0) {
                                player1.getActiveDeck().remove(card);
                                log.info("player1 playing zero value card: {}", card.getCost());
                                if (!CollectionUtils.isEmpty(Collections
                                    .singleton(player1.getPlayableDeck().getCards().size()))) {
                                    player1.getActiveDeck()
                                        .add(player1.getPlayableDeck().getCard());
                                }
                            }
                        }
                    } else {
                        if (!CollectionUtils.isEmpty(
                            Collections.singleton(player1.getPlayableDeck().getCards().size()))) {
                            Card discardedCard = player1.getPlayableDeck().getCard();
                            player1.getDiscardDeck().add(discardedCard);
                            player1.getPlayableDeck().getCards().remove(discardedCard);
                        }
                    }
                }

                int tempMana = player1.getMana();
                while (tempMana >= 0) {
                    if (player1.getHighManaCard(tempMana).getCost() >= 0) {
                        Card card = player1.getHighManaCard(tempMana);
                        log.info("player1 plays card: {}",
                            player1.getHighManaCard(tempMana).getCost());
                        player2
                            .setHp((player2.getHp() - player1.getHighManaCard(tempMana).getCost()));
                        log.info("player2 loses hp: {}, player2 new hp: {}",
                            player1.getHighManaCard(tempMana).getCost(), player2.getHp());
                        player1.playCard(player1.getHighManaCard(tempMana));
                        tempMana = tempMana - card.getCost();
                    } else {
                        log.info("player1 doesn't have a card to play!");
                        break;
                    }
                }

                log.info("player1 health point is: {}, mana is: {}, playable deck cards count: {}",
                    player1.getHp(), player1.getMana(),
                    player1.getPlayableDeck().getCards().size());
                player1.showCards();
                log.info("player2 health point is: {}, mana is: {}, playable deck cards count: {}",
                    player2.getHp(), player2.getMana(),
                    player2.getPlayableDeck().getCards().size());
                player2.showCards();
            } else {
                if (player2.getMana() < 10) {
                    log.info("player2 mana raises +1 {} -> {}", player2.getMana(),
                        player2.getMana() + 1);
                    player2.setMana(player2.getMana() + 1);
                    log.info("player2 mana is now: {}", player2.getMana());
                }

                if (CollectionUtils
                    .isEmpty(Collections.singleton(player2.getPlayableDeck().getCards().size()))) {
                    player2.setHp(player2.getHp() - 1);
                }

                log.info("player2 active deck size: {}", player2.getActiveDeck().size());
                if (player2.getActiveDeck().size() < 5) {
                    log.info("player2 taking a new card from deck: {}",
                        player2.getPlayableDeck().getCards()
                            .get(player2.getPlayableDeck().getCards().size() - 1).getCost());
                    player2.getActiveDeck().add(player2.getPlayableDeck().getCard());
                    log.info("Showing player2 cards");
                    player2.showCards();
                } else {
                    log.info("Showing player2 cards");
                    player2.showCards();
                    log.info("Looking if player has zero value card..");
                    if (player2.hasZeroCard()) {
                        for (Card card : player2.getActiveDeck()) {
                            if (card.getCost() == 0) {
                                player2.getActiveDeck().remove(card);
                                log.info("player2 playing zero value card: {}", card.getCost());
                                if (!CollectionUtils.isEmpty(Collections
                                    .singleton(player2.getPlayableDeck().getCards().size()))) {
                                    player2.getActiveDeck()
                                        .add(player2.getPlayableDeck().getCard());
                                }
                            }
                        }
                    } else {
                        if (!CollectionUtils.isEmpty(
                            Collections.singleton(player2.getPlayableDeck().getCards().size()))) {
                            Card discardedCard = player2.getPlayableDeck().getCard();
                            player2.getDiscardDeck().add(discardedCard);
                            player2.getPlayableDeck().getCards().remove(discardedCard);
                        }
                    }
                }

                int tempMana = player2.getMana();
                while (tempMana >= 0) {
                    if (player2.getHighManaCard(tempMana).getCost() >= 0) {
                        Card card = player2.getHighManaCard(tempMana);
                        log.info("player2 plays card: {}",
                            player2.getHighManaCard(tempMana).getCost());
                        player1
                            .setHp((player1.getHp() - player2.getHighManaCard(tempMana).getCost()));
                        log.info("player1 loses hp: {}, player1 new hp: {}",
                            player2.getHighManaCard(tempMana).getCost(), player1.getHp());
                        player2.playCard(player2.getHighManaCard(tempMana));
                        tempMana = tempMana - card.getCost();
                    } else {
                        log.info("player2 doesn't have a card to play!");
                        break;
                    }
                }

                log.info("player1 health point is: {}, mana is: {}, playable deck cards count: {}",
                    player1.getHp(), player1.getMana(),
                    player1.getPlayableDeck().getCards().size());
                player1.showCards();
                log.info("player2 health point is: {}, mana is: {}, playable deck cards count: {}",
                    player2.getHp(), player2.getMana(),
                    player2.getPlayableDeck().getCards().size());
                player2.showCards();
            }
            turn++;
            log.info("---------------------------------------------------------------------------");
        }

        if (player1.getHp() > 0) {
            log.info("PLAYER 1 WON!");
        } else {
            log.info("PLAYER 2 WON!");
        }
    }
}
