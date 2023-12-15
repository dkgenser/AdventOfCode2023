package dkgenser.advent_of_code_2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    private final static List<String> EXAMPLE_RACES = List.of(
            "Time:      7  15   30",
            "Distance:  9  40  200");

    @ParameterizedTest
    @MethodSource("partOneProvider")
    void partOneTest(int expected, List<String> input) {
        assertEquals(expected, Day06.solutionPartOne(input));
    }

    private static Stream<Arguments> partOneProvider() {
        return Stream.of(
                Arguments.of(4, List.of("Time: 7", "Distance: 9")),
                Arguments.of(8, List.of("Time: 15", "Distance: 40")),
                Arguments.of(9, List.of("Time: 30", "Distance: 200")),
                Arguments.of(288, EXAMPLE_RACES)
        );
    }

    @ParameterizedTest
    @MethodSource("waysToWinProvider")
    void waysToWinTest(int expected, int time, long distanceRecord) {
        assertEquals(expected, Day06.calculateWaysToWin(time, distanceRecord));
    }

    private static Stream<Arguments> waysToWinProvider() {
        return Stream.of(
                Arguments.of(4, 7, 9),
                Arguments.of(8, 15, 40),
                Arguments.of(9, 30, 200),
                Arguments.of(71503, 71530, 940200)
                //Arguments.of(X, 45977295, 305106211101695l) this is the puzzle input

        );
    }
}