package me.petrolingus.game.poker.comparator;

import me.petrolingus.game.poker.core.Card;
import me.petrolingus.game.poker.core.Combination;
import me.petrolingus.game.poker.core.Hand;

import java.util.*;
import java.util.stream.Collectors;

import static me.petrolingus.game.poker.core.Card.*;

public class HandEvaluator {

    private final List<Card> cards;
    private final Map<Rank, Integer> ranksMap;

    public HandEvaluator(Hand hand) {
        this.cards = hand.getSortedCards();
        if (cards.size() != 5) {
            throw new RuntimeException();
        }
        this.ranksMap = getAlikeRanksCountsMap(cards);
    }

    /**
     * Возвдращает мапу (ранг, количество)
     */
    private Map<Rank, Integer> getAlikeRanksCountsMap(List<Card> cards) {
        Map<Rank, Integer> rankMap = new HashMap<>();
        for (Card card : cards) {
            if (rankMap.containsKey(card.rank)) {
                rankMap.put(card.rank, rankMap.get(card.rank) + 1);
            } else {
                rankMap.put(card.rank, 1);
            }
        }
        return rankMap;
    }

    /**
     * Возвдращает комбинацию руки
     */
    public Combination getCombination() {
        if (isFlush(cards)) {
            if (isStraight(cards)) {
                if (cards.get(0).rank == Rank.Ten) {
                    return Combination.RoyalFlush;
                }
                return Combination.StraightFlush;
            }
            return Combination.Flush;
        }
        if (isStraight(cards)) {
            return Combination.Straight;
        }
        if (isHighCard()) return Combination.HighCard;
        if (isPair()) return Combination.Pair;
        if (isTwoPairs()) return Combination.TwoPairs;
        if (isThreeOfAKind()) return Combination.ThreeOfAKind;
        if (isFourOfAKind()) return Combination.FourOfAKind;
        if (isFullHouse()) return Combination.FullHouse;
        return Combination.Flush;
    }

    private boolean isHighCard() {
        return ranksMap.values().size() == 5;
    }

    private boolean isPair() {
        return ranksMap.values().size() == 4;
    }

    private boolean isTwoPairs() {
        return ranksMap.containsValue(2) && ranksMap.values().size() == 3;
    }

    private boolean isFullHouse() {
        return ranksMap.containsValue(3) && ranksMap.containsValue(2);
    }

    private boolean isThreeOfAKind() {
        return ranksMap.containsValue(3) && ranksMap.containsValue(1);
    }

    private boolean isFourOfAKind() {
        return ranksMap.containsValue(4);
    }

    private boolean isStraight(List<Card> cards) {
        return (cards.get(0).rank.value == cards.get(1).rank.value - 1 &&
                cards.get(1).rank.value == cards.get(2).rank.value - 1 &&
                cards.get(2).rank.value == cards.get(3).rank.value - 1 &&
                cards.get(3).rank.value == cards.get(4).rank.value - 1) ||

                (cards.get(0).rank == Rank.Two &&
                        cards.get(1).rank == Rank.Three &&
                        cards.get(2).rank == Rank.Four &&
                        cards.get(3).rank == Rank.Five &&
                        cards.get(4).rank == Rank.Ace);
    }

    private boolean isFlush(List<Card> cards) {
        Suit suit = cards.get(0).suit;

        for (Card card : cards) {
            if (card.suit != suit) {
                return false;
            }
        }

        return true;
    }

    /**
     * Возвращает отсортированную по значению ранга коллекцию карт
     */
    public List<Rank> getHandRanksSortedByValue() {
        List<RankCount> rankCountList = new ArrayList<>();
        for (Rank rank : ranksMap.keySet()) {
            int count = ranksMap.get(rank);
            rankCountList.add(new RankCount(rank, count));
        }
        rankCountList.sort((rankCount1, rankCount2) -> {
            if (rankCount1.count == rankCount2.count) {
                return rankCount1.rank.compareTo(rankCount2.rank);
            }
            return Integer.compare(rankCount1.count, rankCount2.count);
        });
        Collections.reverse(rankCountList);
        return rankCountList.stream().map(rankCount -> rankCount.rank).collect(Collectors.toList());
    }

    /**
     * Реализацию пары (ранг, количество)
     */
    static class RankCount {
        public Rank rank;
        public int count;

        public RankCount(Rank rank, int count) {
            this.count = count;
            this.rank = rank;
        }
    }
}
