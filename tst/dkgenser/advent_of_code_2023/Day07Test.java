package dkgenser.advent_of_code_2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {

    private static final String FIVE_OF_A_KIND = "QQQQQ";
    private static final String FOUR_OF_A_KIND = "QQ3QQ";
    private static final String FULL_HOUSE = "AA222";
    private static final String THREE_OF_A_KIND = "AAA23";
    private static final String TWO_PAIR = "AAKK8";
    private static final String ONE_PAIR = "AAKQJ";
    private static final String HIGH_CARD = "A9876";

    @ParameterizedTest
    @MethodSource("partOneProvider")
    void partOneTest(int expected, List<String> input) {
        assertEquals(expected, Day07.solutionPartOne(input));
    }

    private static Stream<Arguments> partOneProvider() {
        return Stream.of(
                Arguments.of(765, List.of("32T3K 765")),
                Arguments.of(483, List.of("QQQJA 483")),
                Arguments.of(6440, List.of("32T3K 765",
                        "T55J5 684",
                        "KK677 28",
                        "KTJJT 220",
                        "QQQJA 483"))
        );
    }

    @ParameterizedTest
    @MethodSource("cardOrderingProvider")
    void cardOrderingTest(int expected, String cardOne, String cardTwo) {
        CamelCard.CardHand handOne = new CamelCard.CardHand(cardOne);
        CamelCard.CardHand handTwo = new CamelCard.CardHand(cardTwo);
        assertEquals(expected, Integer.signum(handOne.compareTo(handTwo)));
        assertEquals(handOne.compareTo(handTwo), -handTwo.compareTo(handOne));
    }

    private static Stream<Arguments> cardOrderingProvider() {
        return Stream.of(
                //same cards
                Arguments.of(0, "KK677", "KK677"),
                /* same type, different cards - ranking depends on the order of the cards */
                // 5 of a kind
                Arguments.of(1, "QQQQQ", "22222"),
                // 4 of a kind
                Arguments.of(-1, "33333", "AAAAA"),
                Arguments.of(1, "QQQQ5", "QQQQ2"),
                Arguments.of(-1, "5QQQQ", "QQQQ2"),
                Arguments.of(-1, "33338", "AAAA7"),
                // full
                Arguments.of(1, "QQQ22", "222QQ"),
                Arguments.of(-1, "333AA", "AAA44"),
                // 3 of a kind
                Arguments.of(1, "Q3Q2Q", "2K242"),
                Arguments.of(-1, "Q3Q2Q", "K2242"),
                Arguments.of(-1, "233A3", "A45AA"),
                Arguments.of(1, "45QQQ", "222AK"),
                // 2 pairs
                Arguments.of(-1, "KTJJT", "KK677"),
                // 1 pair
                Arguments.of(1, "KK234", "234KK"),
                // high card
                Arguments.of(1, "AKQJT", "TJQKA"),
                Arguments.of(-1, "23456", "65432"),
                /* different type */
                Arguments.of(1, FIVE_OF_A_KIND, FOUR_OF_A_KIND),
                Arguments.of(1, FIVE_OF_A_KIND, FULL_HOUSE),
                Arguments.of(1, FOUR_OF_A_KIND, FULL_HOUSE),
                Arguments.of(1, FOUR_OF_A_KIND, THREE_OF_A_KIND),
                Arguments.of(1, FULL_HOUSE, THREE_OF_A_KIND),
                Arguments.of(1, FULL_HOUSE, TWO_PAIR),
                Arguments.of(1, THREE_OF_A_KIND, TWO_PAIR),
                Arguments.of(1, THREE_OF_A_KIND, ONE_PAIR),
                Arguments.of(1, TWO_PAIR, ONE_PAIR),
                Arguments.of(1, TWO_PAIR, HIGH_CARD),
                Arguments.of(1, ONE_PAIR, HIGH_CARD),
                /* From the given example */
                // each compared to the next in the expected order
                Arguments.of(-1, "T55J5", "QQQJA"),
                Arguments.of(-1, "KK677", "T55J5"),
                Arguments.of(-1, "KTJJT","KK677"),
                Arguments.of(-1, "32T3K", "KTJJT"),
                // each compared to the lowest
                Arguments.of(1, "QQQJA", "32T3K"),
                Arguments.of(1, "T55J5", "32T3K"),
                Arguments.of(1, "KK677", "32T3K"),
                Arguments.of(1, "KTJJT", "32T3K"),
                Arguments.of(1, "32T3K", "22T3K")
        );
    }

    @ParameterizedTest
    @MethodSource("cardTypeProvider")
    void cardTypeTest(CamelCard.HandType expected, String card) {
        CamelCard.CardHand hand = new CamelCard.CardHand(card);
        assertEquals(expected, hand.type);
    }

    private static Stream<Arguments> cardTypeProvider() {
        return Stream.of(
                Arguments.of(CamelCard.HandType.FIVE_OF_A_KIND, "QQQQQ"),
                Arguments.of(CamelCard.HandType.FOUR_OF_A_KIND, "QQQQ2"),
                Arguments.of(CamelCard.HandType.FULL_HOUSE, "44QQQ"),
                Arguments.of(CamelCard.HandType.THREE_OF_A_KIND, "QQQJA"),
                Arguments.of(CamelCard.HandType.THREE_OF_A_KIND, "T55J5"),
                Arguments.of(CamelCard.HandType.TWO_PAIR, "KK677"),
                Arguments.of(CamelCard.HandType.TWO_PAIR, "KTJJT"),
                Arguments.of(CamelCard.HandType.ONE_PAIR, "K6JJT"),
                Arguments.of(CamelCard.HandType.HIGH_CARD, "32T6K")
        );
    }

    @ParameterizedTest
    @MethodSource("partTwoProvider")
    void partTwoTest(int expected, List<String> input) {
        assertEquals(expected, Day07.solutionPartTwo(input));
    }

    private static Stream<Arguments> partTwoProvider() {
        return Stream.of(
        );
    }
}