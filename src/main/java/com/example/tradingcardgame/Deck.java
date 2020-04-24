package com.example.tradingcardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int DECK_SIZE = 20;
    private List<Card> cards;

    public Deck() {
        createDeck();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void createDeck() {
        this.cards = new ArrayList<>(DECK_SIZE);

        for (int manaCount=0; manaCount <= 8; manaCount++) {
            if (manaCount == 0) {
                for (int cardCount=0; cardCount < 2; cardCount++) {
                    this.cards.add(new Card(manaCount));
                }
            } else if (manaCount == 1) {
                for (int cardCount=0; cardCount < 2; cardCount++) {
                    this.cards.add(new Card(manaCount));
                }
            } else if (manaCount == 2) {
                for (int cardCount=0; cardCount < 3; cardCount++) {
                    this.cards.add(new Card(manaCount));
                }
            } else if (manaCount == 3) {
                for (int cardCount=0; cardCount < 4; cardCount++) {
                    this.cards.add(new Card(manaCount));
                }
            } else if (manaCount == 4) {
                for (int cardCount=0; cardCount < 3; cardCount++) {
                    this.cards.add(new Card(manaCount));
                }
            } else if (manaCount == 5) {
                for (int cardCount=0; cardCount < 2; cardCount++) {
                    this.cards.add(new Card(manaCount));
                }
            } else if (manaCount == 6) {
                for (int cardCount=0; cardCount < 2; cardCount++) {
                    this.cards.add(new Card(manaCount));
                }
            } else if (manaCount == 7) {
                for (int cardCount=0; cardCount < 1; cardCount++) {
                    this.cards.add(new Card(manaCount));
                }
            } else {
                for (int cardCount=0; cardCount < 1; cardCount++) {
                    this.cards.add(new Card(manaCount));
                }
            }
        }

        Collections.shuffle(this.cards);
    }

    public Card getCard() {
        return this.cards.remove(this.cards.size()-1);
    }
}
