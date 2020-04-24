package com.example.tradingcardgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static Logger log = LoggerFactory.getLogger(Player.class);

    private int hp;
    private int mana;
    private Deck playableDeck;
    private List<Card> activeDeck;
    private List<Card> discardDeck;

    public Player() {
        this.hp = 30;
        this.mana = 0;
        this.playableDeck = new Deck();
        this.activeDeck = new ArrayList<>();
        this.discardDeck = new ArrayList<>();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Deck getPlayableDeck() {
        return playableDeck;
    }

    public void setPlayableDeck(Deck playableDeck) {
        this.playableDeck = playableDeck;
    }

    public List<Card> getActiveDeck() {
        return activeDeck;
    }

    public void setActiveDeck(List<Card> activeDeck) {
        this.activeDeck = activeDeck;
    }

    public List<Card> getDiscardDeck() {
        return discardDeck;
    }

    public void setDiscardDeck(List<Card> discardDeck) {
        this.discardDeck = discardDeck;
    }

    public Card getHighManaCard(int mana) {
        Card tempCard = new Card(-1);
        for (Card card : this.activeDeck) {
            if (card.getCost() <= mana && card.getCost() > tempCard.getCost()) {
                tempCard = card;
            }
        }
        return tempCard;
    }

    public boolean hasZeroCard() {
        for (Card card : this.activeDeck) {
            if (card.getCost() == 0) {
                return true;
            }
        }
        return false;
    }

    public void playCard(Card card) {
        for (Card deckCard : this.activeDeck) {
            if (card.getCost() == deckCard.getCost()) {
                this.activeDeck.remove(deckCard);
                break;
            }
        }
    }

    public void showCards() {
        if (this.activeDeck.size() == 1) {
            log.info("Cards: {}", this.activeDeck.get(0).getCost());
        } else if (this.activeDeck.size() == 2) {
            log.info("Cards: {}, {}", this.activeDeck.get(0).getCost(),
                this.activeDeck.get(1).getCost());
        } else if (this.activeDeck.size() == 3) {
            log.info("Cards: {}, {}, {}", this.activeDeck.get(0).getCost(),
                this.activeDeck.get(1).getCost(), this.activeDeck.get(2).getCost());
        } else if (this.activeDeck.size() == 4) {
            log.info("Cards: {}, {}, {}, {}", this.activeDeck.get(0).getCost(),
                this.activeDeck.get(1).getCost(), this.activeDeck.get(2).getCost(),
                this.activeDeck.get(3).getCost());
        } else if (this.activeDeck.size() == 5) {
            log.info("Cards: {}, {}, {}, {}, {}", this.activeDeck.get(0).getCost(),
                this.activeDeck.get(1).getCost(), this.activeDeck.get(2).getCost(),
                this.activeDeck.get(3).getCost(), this.activeDeck.get(4).getCost());
        }
    }
}
