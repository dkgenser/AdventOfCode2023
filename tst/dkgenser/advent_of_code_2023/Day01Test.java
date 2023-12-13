package dkgenser.advent_of_code_2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {
    @ParameterizedTest
    @MethodSource("partOneProvider")
    void partOneTest(int expected, List<String> input) {
        assertEquals(expected, Day01.solutionPartOne(input));
    }

    private static Stream<Arguments> partOneProvider() {
        return Stream.of(
                Arguments.of(12, List.of("1abc2")),
                Arguments.of(24, List.of("1abc2", "1abc2")),
                Arguments.of(38, List.of("pqr3stu8vwx")),
                Arguments.of(15, List.of("a1b2c3d4e5f")),
                Arguments.of(22, List.of("2")),
                Arguments.of(77, List.of("treb7uchet")),
                Arguments.of(142, List.of("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"))
        );
    }

    @ParameterizedTest
    @MethodSource("partTwoProvider")
    void partTwoTest(int expected, List<String> input) {
        assertEquals(expected, Day01.solutionPartTwo(input));
    }

    private static Stream<Arguments> partTwoProvider() {
        return Stream.of(
                Arguments.of(29, List.of("two1nine")),
                Arguments.of(83, List.of("eightwothree")),
                Arguments.of(13, List.of("abcone2threexyz")),
                Arguments.of(24, List.of("xtwone3four")),
                Arguments.of(42, List.of("4nineeightseven2")),
                Arguments.of(14, List.of("zoneight234")),
                Arguments.of(76, List.of("7pqrstsixteen")),
                Arguments.of(68, List.of("65oneightpln")), //overlapping words
                Arguments.of(281, List.of("two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen"))
        );
    }
}