package com.example.tradingcardgame;

import com.example.tradingcardgame.common.constants.GameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class DeckTest {
    Deck deck;

    @BeforeEach
    void setup() {
        deck = new Deck();
    }

    @Test
    void whenCreateDeck_thenReturnsListOfCards() {
        // Given

        // When
        List<Card> playableCards = deck.createDeck();

        // Then
        assertNotNull(playableCards);
        assertEquals(GameConstants.DECK_SIZE, playableCards.size());
    }

}
