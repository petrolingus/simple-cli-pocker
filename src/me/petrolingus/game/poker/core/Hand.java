package me.petrolingus.game.poker.core;

import me.petrolingus.game.poker.comparator.HandComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Реализация коллекции карт на руках у игрока
 */
public class Hand implements Comparable<Hand> {

    /**
     * Максимальное количество карт на руках игрока
     */
    public static final int DEFAULT_SIZE = 5;

    /**
     * Коллекция карт игрока
     */
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Добавляет карту в руки игрока
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Возвращает отсортированную коллекцию карт игрока
     */
    public List<Card> getSortedCards() {
        cards.sort(Comparator.comparing(card -> card.rank));
        return cards.subList(0, cards.size());
    }

    /**
     * Возвращает коллекцию карт игрока в виде строки
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : cards) {
            result.append(card).append(" ");
        }
        return result.toString();
    }

    /**
     * Сравнивает карты двух игроков
     */
    @Override
    public int compareTo(Hand o) {
        return new HandComparator().compare(this, o);
    }
}
