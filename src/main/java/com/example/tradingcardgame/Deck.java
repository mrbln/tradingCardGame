package com.example.tradingcardgame;

import com.example.tradingcardgame.common.constants.GameConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
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
        this.cards = new ArrayList<>(GameConstants.DECK_SIZE);

        for (int manaCount = 0; manaCount <= GameConstants.MAX_MANA_COUNT; manaCount++) {
            if (manaCount == 0) {
                addZeroManaCards(manaCount);
            } else if (manaCount == 1) {
                addOneManaCards(manaCount);
            } else if (manaCount == 2) {
                addTwoManaCards(manaCount);
            } else if (manaCount == 3) {
                addThreeManaCards(manaCount);
            } else if (manaCount == 4) {
                addFourManaCards(manaCount);
            } else if (manaCount == 5) {
                addFiveManaCards(manaCount);
            } else if (manaCount == 6) {
                addSixManaCards(manaCount);
            } else if (manaCount == 7) {
                addSevenManaCards(manaCount);
            } else {
                addEightManaCards(manaCount);
            }
        }

        Collections.shuffle(this.cards);
    }

    private void addEightManaCards(int manaCount) {
        for (int cardCount = 0; cardCount < GameConstants.EIGHTMANA_CARD_COUNT; cardCount++) {
            this.cards.add(new Card(manaCount));
        }
    }

    private void addSevenManaCards(int manaCount) {
        for (int cardCount = 0; cardCount < GameConstants.SEVENMANA_CARD_COUNT; cardCount++) {
            this.cards.add(new Card(manaCount));
        }
    }

    private void addSixManaCards(int manaCount) {
        for (int cardCount = 0; cardCount < GameConstants.SIXMANA_CARD_COUNT; cardCount++) {
            this.cards.add(new Card(manaCount));
        }
    }

    private void addFiveManaCards(int manaCount) {
        for (int cardCount = 0; cardCount < GameConstants.FIVEMANA_CARD_COUNT; cardCount++) {
            this.cards.add(new Card(manaCount));
        }
    }

    private void addFourManaCards(int manaCount) {
        for (int cardCount = 0; cardCount < GameConstants.FOURMANA_CARD_COUNT; cardCount++) {
            this.cards.add(new Card(manaCount));
        }
    }

    private void addThreeManaCards(int manaCount) {
        for (int cardCount = 0; cardCount < GameConstants.THREEMANA_CARD_COUNT; cardCount++) {
            this.cards.add(new Card(manaCount));
        }
    }

    private void addTwoManaCards(int manaCount) {
        for (int cardCount = 0; cardCount < GameConstants.TWOMANA_CARD_COUNT; cardCount++) {
            this.cards.add(new Card(manaCount));
        }
    }

    private void addOneManaCards(int manaCount) {
        for (int cardCount = 0; cardCount < GameConstants.ONEMANA_CARD_COUNT; cardCount++) {
            this.cards.add(new Card(manaCount));
        }
    }

    private void addZeroManaCards(int manaCount) {
        for (int cardCount = 0; cardCount < GameConstants.ZEROMANA_CARD_COUNT; cardCount++) {
            this.cards.add(new Card(manaCount));
        }
    }
}
