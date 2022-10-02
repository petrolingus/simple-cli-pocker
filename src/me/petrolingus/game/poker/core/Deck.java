package me.petrolingus.game.poker.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static me.petrolingus.game.poker.core.Card.*;

/**
 * Реализация колоды карт
 */
public class Deck {

    /**
     * Коллекция колоды карт
     */
    private final List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
        initDeck();
    }

    /**
     * Генерирует колоду карт и сохраняет ее в коллекцию
     */
    private void initDeck() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Выдает случайную карту из колоды
     */
    public Card dealCard() throws RuntimeException {
        if (deck.size() > 0) {
            int number = ThreadLocalRandom.current().nextInt(deck.size());
            return deck.remove(number);
        }
        throw new RuntimeException("Deck is empty!");
    }

}
