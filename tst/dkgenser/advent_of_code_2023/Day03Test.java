package dkgenser.advent_of_code_2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {
    @ParameterizedTest
    @MethodSource("partOneProvider")
    void partOneTest(int expected, List<String> input) {
        assertEquals(expected, Day03.solutionPartOne(input));
    }

    private static Stream<Arguments> partOneProvider() {
        return Stream.of(
                Arguments.of(4361, List.of(
                        "467..114..",
                        "...*......",
                        "..35..633.",
                        "......#...",
                        "617*......",
                        ".....+.58.",
                        "..592.....",
                        "......755.",
                        "...$.*....",
                        ".664.598..")),
                Arguments.of(0, List.of("467..114..")),
                Arguments.of(467, List.of("467..114..",
                        "...*......")),
                Arguments.of(633, List.of("..35..633.",
                        "......#...")),
                Arguments.of(668, List.of("...*......",
                        "..35..633.",
                        "......#...")),
                Arguments.of(35, List.of("...*......",
                        "..35..633.")),
                Arguments.of(1262, List.of("...$.*...." ,
                        ".664.598..")),
                Arguments.of(617, List.of("*617......")),
                Arguments.of(617, List.of("617#......")),
                Arguments.of(617, List.of("617&......")),
                Arguments.of(617, List.of("617=......")),
                Arguments.of(617, List.of("617%......")),
                Arguments.of(617, List.of("617/......")),
                Arguments.of(617, List.of("617@......")),
                Arguments.of(617, List.of("617-......")),
                Arguments.of(617, List.of("617$......")),
                Arguments.of(617, List.of("617+......")),
                Arguments.of(0, List.of("617?......"))
        );
    }

    @ParameterizedTest
    @MethodSource("partTwoProvider")
    void partTwoTest(int expected, List<String> input) {
        assertEquals(expected, Day03.solutionPartTwo(input));
    }

    private static Stream<Arguments> partTwoProvider() {
        return Stream.of(
                //example gear 1
                Arguments.of(16345, List.of(
                        "467..114..",
                        "...*......",
                        "..35..633.")),
                // example gear 2
                Arguments.of(451490, List.of(
                        "......755.",
                        "...$.*....",
                        ".664.598..")),
                // full example
                Arguments.of(467835, List.of(
                        "467..114..",
                        "...*......",
                        "..35..633.",
                        "......#...",
                        "617*......",
                        ".....+.58.",
                        "..592.....",
                        "......755.",
                        "...$.*....",
                        ".664.598..")),
                // no symbol
                Arguments.of(0, List.of("467..114..")),
                // only one part adjacent
                Arguments.of(0, List.of("2*......")),
                // only one part above
                Arguments.of(0, List.of(
                        "467..114..",
                        "...*......")),
                // only one part below
                Arguments.of(0, List.of(
                        "...*......",
                        "..35..633.",
                        "......#...")),
                Arguments.of(0, List.of(
                        "...*......",
                        "..35..633.")),
                // both above
                Arguments.of(16345, List.of(
                        "467.35...",
                        "...*......")),
                //both below
                Arguments.of(16345, List.of(
                        "...*......",
                        "467.35...")),
                //symbol checks
                Arguments.of(12, List.of("2*6......")),
                Arguments.of(0, List.of("2%6......")),
                Arguments.of(0, List.of("2=6......")),
                Arguments.of(0, List.of("2&6......")),
                Arguments.of(0, List.of("2/6......")),
                Arguments.of(0, List.of("2@6......")),
                Arguments.of(0, List.of("2-6......")),
                Arguments.of(0, List.of("2$6......")),
                Arguments.of(0, List.of("2+6......"))
        );
    }
}