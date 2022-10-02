package me.petrolingus.game.poker;

import me.petrolingus.game.poker.comparator.HandEvaluator;
import me.petrolingus.game.poker.core.Deck;
import me.petrolingus.game.poker.core.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Главный класс игры
 */
public class Main {

    /**
     * Количество игроков
     */
    private final static int NUMBER_OF_PLAYERS = 2;

    public static void main(String[] args) {

        // Создаем колоду карт
        Deck deck = new Deck();

        // Создаем руки игроков (игроков), в которых хранятся карты
        List<Hand> hands = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            hands.add(new Hand());
        }

        // Раздаем каждому игроку по 5 карт
        for (int i = 0; i < Hand.DEFAULT_SIZE; i++) {
            for (Hand hand : hands) {
                hand.addCard(deck.dealCard());
            }
        }

        // Выводим на экран все карты ( вскрываемся)) )
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            Hand hand = hands.get(i);
            System.out.println(i + ". " + hand.toString() + " - " + new HandEvaluator(hand).getCombination());
        }

//        // Находим победителя
//        Hand winnerHand = hands.stream().max(Hand::compareTo).orElse(null);
//        System.out.println("\nИгрок " + hands.indexOf(winnerHand) + " выиграл!");
    }
}
