package me.petrolingus.game.poker.comparator;

import me.petrolingus.game.poker.core.Combination;
import me.petrolingus.game.poker.core.Hand;

import java.util.Comparator;
import java.util.List;

import static me.petrolingus.game.poker.core.Card.Rank;

/**
 * Сравнивает руки
 */
public class HandComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand hand1, Hand hand2) {

        HandEvaluator evaluator1 = new HandEvaluator(hand1);
        HandEvaluator evaluator2 = new HandEvaluator(hand2);

        Combination combination1 = evaluator1.getCombination();
        Combination combination2 = evaluator2.getCombination();

        // Если комбинации одинаковые, то надодим более сильную комбинацию
        if (combination1 == combination2) {
            if (combination1 == Combination.Straight || combination1 == Combination.StraightFlush) {
                if (smallestStraight(hand1)) {
                    return smallestStraight(hand2) ? 0 : -1;
                }
                if (smallestStraight(hand2)) {
                    return 1;
                }
            }

            List<Rank> hand1Ranks = evaluator1.getHandRanksSortedByValue();
            List<Rank> hand2Ranks = evaluator2.getHandRanksSortedByValue();

            for (int i = 0; i < hand1Ranks.size(); i++) {
                Rank rank1 = hand1Ranks.get(i);
                Rank rank2 = hand2Ranks.get(i);
                if (rank1 != rank2) {
                    return rank1.compareTo(rank2);
                }
            }
        }

        // Иначе просто сравниваем комбинации на "кто сильнее"
        return combination1.compareTo(combination2);
    }

    /**
     * Вспомагательный метод для сравнения
     */
    private boolean smallestStraight(Hand hand) {
        return hand.getSortedCards().get(0).rank == Rank.Two && hand.getSortedCards().get(4).rank == Rank.Ace;
    }
}
