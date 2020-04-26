package com.example.tradingcardgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static Logger log = LoggerFactory.getLogger(Player.class);

    private String name;
    private int hp;
    private int mana;
    private Deck playableDeck;
    private List<Card> activeDeck;
    private List<Card> discardDeck;

    public Player(String name) {
        this.name = name;
        this.hp = 30;
        this.mana = 0;
        this.playableDeck = new Deck();
        this.activeDeck = new ArrayList<>();
        this.discardDeck = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                return;
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
        } else {
            log.info("{} doesn't have any cards in active deck.", this.name);
        }
    }

    public Card takeCard() {
        return this.playableDeck.getCards().remove(this.playableDeck.getCards().size()-1);
    }

    public void takeStarterThreeCards() {
        for (int activeCardCount = 0; activeCardCount < 3; activeCardCount++) {
            this.activeDeck.add(takeCard());
        }
    }

    public boolean isActiveDeckFull() {
        return this.activeDeck.size() >= 5;
    }

    public boolean isPlayableDeckEmpty() {
        return this.playableDeck.getCards().isEmpty();
    }

    public void addCardToHand() {
        this.activeDeck.add(takeCard());
    }

    public void playZeroCard() {
        for (Card card : this.activeDeck) {
            if (card.getCost() == 0) {
                this.activeDeck.remove(card);
                log.info("{} playing zero value card: {}", this.name, card.getCost());
                break;
            }
        }
    }

    public void discardCard() {
        Card discardedCard = takeCard();
        this.discardDeck.add(discardedCard);
    }

    public boolean hasPlayableCard(int mana) {
        for (Card deckCard : this.activeDeck) {
            if (deckCard.getCost() <= mana) {
                return true;
            }
        }
        return false;
    }

    public Card getNextCard() {
        return this.playableDeck.getCards().get(this.playableDeck.getCards().size()-1);
    }
}
