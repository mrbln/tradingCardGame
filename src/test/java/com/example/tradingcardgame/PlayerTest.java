package com.example.tradingcardgame;

import com.example.tradingcardgame.common.constants.GameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class PlayerTest {

    private Player player;

    private int mana = GameConstants.EIGHTMANA_CARD_COUNT;

    private List<Card> activeDeck;

    @Mock
    Card card;

    @BeforeEach
    void setup() {
        player = new Player("player");
    }

    @Test
    void givenPlayer_whenGetHighManaCard_thenReturnsCardWithHighMana() {
        // Given

        // When
        Card highManaCard = player.getHighManaCard(mana);

        // Then
        assertNotNull(highManaCard);
        assertEquals(GameConstants.NON_EXIST_CARD, highManaCard.getCost());
    }

    @Test
    void givenPlayer_whenHasZeroCard_thenReturnFalse() {
        // Given

        // When
        boolean hasZeroCard = player.hasZeroCard();

        // Then
        assertFalse(hasZeroCard);
    }

    @Test
    void givenPlayerAndActiveDeckWithZeroCard_whenHasZeroCard_thenReturnTrue() {
        // Given
        activeDeck = new ArrayList<>();
        activeDeck.add(new Card(0));
        player.setActiveDeck(activeDeck);

        // When
        boolean hasZeroCard = player.hasZeroCard();

        // Then
        assertTrue(hasZeroCard);
    }

    @Test
    void givenPlayerAndActiveDeckWithCard_whenPlayCard_thenRemoveCardFromDeck() {
        // Given
        activeDeck = new ArrayList<>();
        activeDeck.add(card);
        player.setActiveDeck(activeDeck);

        // When
        player.playCard(card);

        // Then
        assertEquals(0, activeDeck.size());
    }

    @Test
    void givenPlayerAndActiveDeckWithSameCards_whenPlayCard_thenRemoveCardFromDeck() {
        // Given
        activeDeck = new ArrayList<>();
        activeDeck.add(card);
        activeDeck.add(card);
        player.setActiveDeck(activeDeck);

        // When
        player.playCard(card);

        // Then
        assertEquals(1, activeDeck.size());
    }

    @Test
    void givenPlayer_whenTakeCard_thenRemoveCardFromPlayableDeck() {
        // Given

        // When
        Card card = player.takeCard();

        // Then
        assertEquals(19, player.getPlayableDeck().getCards().size());
    }

    @Test
    void givenPlayer_whenTakeStarterCards_thenRemoveCardFromPlayableDeckAndAddCardsToActiveDeck() {
        // Given
        activeDeck = new ArrayList<>();
        player.setActiveDeck(activeDeck);

        // When
        player.takeStarterThreeCards();

        // Then
        assertEquals(3, activeDeck.size());
        assertEquals(17, player.getPlayableDeck().getCards().size());
    }

    @Test
    void givenPlayer_whenIsActiveDeckFull_thenReturnFalse() {
        // Given
        activeDeck = new ArrayList<>();
        player.setActiveDeck(activeDeck);

        // When
        boolean isActiveDeckFull = player.isActiveDeckFull();

        // Then
        assertFalse(isActiveDeckFull);
    }

    @Test
    void givenPlayerAndActiveDeckWithFiveCards_whenIsActiveDeckFull_thenReturnTrue() {
        // Given
        activeDeck = new ArrayList<>();
        activeDeck.add(card);
        activeDeck.add(card);
        activeDeck.add(card);
        activeDeck.add(card);
        activeDeck.add(card);
        player.setActiveDeck(activeDeck);

        // When
        boolean isActiveDeckFull = player.isActiveDeckFull();

        // Then
        assertTrue(isActiveDeckFull);
    }

    @Test
    void givenPlayer_whenIsPlayableDeckEmpty_thenReturnFalse() {
        // Given

        // When
        boolean isPlayableDeckEmpty = player.isPlayableDeckEmpty();

        // Then
        assertFalse(isPlayableDeckEmpty);
    }

    @Test
    void givenPlayerAndActiveDeck_whenAddCardToHand_thenAddCardToActiveDeckAndRemoveFromPlayableDeck() {
        // Given
        activeDeck = new ArrayList<>();
        player.setActiveDeck(activeDeck);

        // When
        player.addCardToHand();

        // Then
        assertEquals(1, activeDeck.size());
        assertEquals(19, player.getPlayableDeck().getCards().size());
    }

    @Test
    void givenPlayerAndActiveDeckWithZeroCard_whenPlayZeroCard_thenRemoveCardFromActiveDeck() {
        // Given
        activeDeck = new ArrayList<>();
        activeDeck.add(new Card(0));
        player.setActiveDeck(activeDeck);

        // When
        player.playZeroCard();

        // Then
        assertEquals(0, activeDeck.size());
    }

    @Test
    void givenPlayer_whenDiscardCard_thenRemoveCardFromPlayableDeck() {
        // Given

        // When
        player.discardCard();

        // Then
        assertEquals(19, player.getPlayableDeck().getCards().size());
    }

    @Test
    void givenPlayerAndActiveDeckWithCard_whenHasPlayableCard_thenReturnTrue() {
        // Given
        activeDeck = new ArrayList<>();
        activeDeck.add(new Card(0));
        player.setActiveDeck(activeDeck);

        // When
        boolean hasPlayableCard = player.hasPlayableCard(mana);

        // Then
        assertTrue(hasPlayableCard);
    }

    @Test
    void givenPlayerAndActiveDeckWithCard_whenGetNextCard_thenReturnsCard() {
        // Given

        // When
        Card card = player.getNextCard();

        // Then
        assertEquals(card.getCost(), player.getPlayableDeck().getCards().get(player.getPlayableDeck().getCards().size()-1).getCost());
    }
}
