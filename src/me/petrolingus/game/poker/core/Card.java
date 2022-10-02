package me.petrolingus.game.poker.core;

/**
 * Реализация игральной карты
 */
public class Card {

    /**
     * Масть карты (Червы, Трефы, Пики, Бубны)
     */
    public final Suit suit;

    /**
     * Достоинства карты (A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K)
     */
    public final Rank rank;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Возвращает название карты в виде строки
     */
    @Override
    public String toString() {
        return String.format("%s%s", rank.name, suit.symbol);
    }

    /**
     * Перечисление достоинств
     */
    public enum Rank {
        Two("2", 2),
        Three("3", 3),
        Four("4", 4),
        Five("5", 5),
        Six("6", 6),
        Seven("7", 7),
        Eight("8", 8),
        Nine("9", 9),
        Ten("10", 10),
        Jack("J", 11),
        Queen("Q", 12),
        King("K", 13),
        Ace("A", 14);

        public final String name;
        public final int value;

        Rank(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

    /**
     * Перечисление мастей
     */
    public enum Suit {
        Hearts('♥'),
        Clubs('♣'),
        Spades('♠'),
        Diamonds('♦');

        public final char symbol;

        Suit(char symbol) {
            this.symbol = symbol;
        }
    }
}
