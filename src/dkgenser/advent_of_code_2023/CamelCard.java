package dkgenser.advent_of_code_2023;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class CamelCard {

    enum HandType {
        // from highest to lowest
        FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD
    };

    static class CardHand implements Comparable<CardHand>{
        private static final String CARD_ORDER = "AKQJT98765432";
        HandType type;
        String cards;

        @Override
        public int compareTo(CardHand o) {
            //reverse ordinal (listed from highest to lowest)
            int typeCompare = o.type.compareTo(this.type);
            if (0 != typeCompare) {
                return typeCompare;
            }
            for (int i = 0; i < cards.length(); i++) {
                int cardCompare = CARD_ORDER.indexOf(o.cards.charAt(i)) - CARD_ORDER.indexOf(cards.charAt(i));
                if (cardCompare != 0) {
                    return cardCompare;
                }
            }
            return 0;
        }

        CardHand(String cards) {
            this.cards = cards;
            List<Long> counts = cards.codePoints()
                    .mapToObj(i -> (char) i)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .values().stream()
                    .sorted(Collections.reverseOrder())
                    .collect(Collectors.toList());
            long highestCount = counts.get(0);
            if (5 == highestCount) {
                type = HandType.FIVE_OF_A_KIND;
            } else if (4 == highestCount) {
                type = HandType.FOUR_OF_A_KIND;
            } else if (3 == highestCount && 2 == counts.get(1)) {
                type = HandType.FULL_HOUSE;
            } else if (3 == highestCount) {
                type = HandType.THREE_OF_A_KIND;
            } else if (2 == highestCount && 2 == counts.get(1)) {
                type = HandType.TWO_PAIR;
            } else if (2 == highestCount) {
                type = HandType.ONE_PAIR;
            } else {
                type = HandType.HIGH_CARD;
            }
        }
    }

    CardHand hand;
    int bid;

    CamelCard(String cards, int bid) {
        hand = new CardHand(cards);
        this.bid = bid;
    }

    CardHand getHand() {
        return hand;
    }

    int getBid() {
        return bid;
    }
}
