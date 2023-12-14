package dkgenser.advent_of_code_2023;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day04Test {
    @ParameterizedTest
    @MethodSource("partOneProvider")
    void partOneTest(int expected, List<String> input) {
        assertEquals(expected, Day04.solutionPartOne(input));
    }

    private static Stream<Arguments> partOneProvider() {
        return Stream.of(
                Arguments.of(8, List.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")),
                Arguments.of(2, List.of("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19")),
                Arguments.of(2, List.of("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1")),
                Arguments.of(1, List.of("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83")),
                Arguments.of(0, List.of("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36")),
                Arguments.of(0, List.of("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")),
                Arguments.of(13, List.of(
                    "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                    "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                    "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                    "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                    "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                    "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("partTwoProvider")
    void partTwoTest(int expected, List<String> input) {
        assertEquals(expected, Day04.solutionPartTwo(input));
    }

    private static Stream<Arguments> partTwoProvider() {
        return Stream.of(
                Arguments.of(1, List.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")),
                Arguments.of(1, List.of("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19")),
                Arguments.of(3, List.of(
                        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"
                )),
                Arguments.of(30, List.of(
                        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
                ))
        );
    }

    @Test
    void countCopiesTest() {
        assertTrue(Arrays.equals(new int[]{1, 2, 4, 8, 14, 1}, Day04.countCopies(new int[] {4, 2, 2, 1, 0, 0})));
    }

}