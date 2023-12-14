package dkgenser.advent_of_code_2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    private static final List<String> EXAMPLE_INPUT = List.of(
            "seeds: 79 14 55 13",
            "",
            "seed-to-soil map:",
            "50 98 2",
            "52 50 48",
            "",
            "soil-to-fertilizer map:",
            "0 15 37",
            "37 52 2",
            "39 0 15",
            "",
            "fertilizer-to-water map:",
            "49 53 8",
            "0 11 42",
            "42 0 7",
            "57 7 4",
            "",
            "water-to-light map:",
            "88 18 7",
            "18 25 70",
            "",
            "light-to-temperature map:",
            "45 77 23",
            "81 45 19",
            "68 64 13",
            "",
            "temperature-to-humidity map:",
            "0 69 1",
            "1 0 69",
            "",
            "humidity-to-location map:",
            "60 56 37",
            "56 93 4"
    );

    @ParameterizedTest
    @MethodSource("mapBuilderProvider")
    void mapBuilderTest(int ask, int expected, List<String> input) {
        GardenMap map = new GardenMap(input);
        assertEquals(expected, map.get(ask));
    }

    private static Stream<Arguments> mapBuilderProvider() {
        List<String> seedToSoilMap = List.of("50 98 2", "52 50 48");
        return Stream.of(
                Arguments.of(1, 1, seedToSoilMap),
                Arguments.of(10, 10, seedToSoilMap),
                Arguments.of(49, 49, seedToSoilMap),
                Arguments.of(50, 52, seedToSoilMap),
                Arguments.of(51, 53, seedToSoilMap),
                Arguments.of(96, 98, seedToSoilMap),
                Arguments.of(97, 99, seedToSoilMap),
                Arguments.of(98, 50, seedToSoilMap),
                Arguments.of(99, 51, seedToSoilMap),
                Arguments.of(100, 100, seedToSoilMap)
        );
    }

    @ParameterizedTest
    @MethodSource("mapBuilder2Provider")
    void mapBuilder2Test(int ask, int expected, String input) {
        GardenMap map = new GardenMap(input);
        assertEquals(expected, map.get(ask));
    }

    private static Stream<Arguments> mapBuilder2Provider() {
        String seedToSoilMap =
                "seed-to-soil map:\n" +
                        "50 98 2\n" +
                        "52 50 48\n";
        return Stream.of(
                Arguments.of(1, 1, seedToSoilMap),
                Arguments.of(10, 10, seedToSoilMap),
                Arguments.of(49, 49, seedToSoilMap),
                Arguments.of(50, 52, seedToSoilMap),
                Arguments.of(51, 53, seedToSoilMap),
                Arguments.of(96, 98, seedToSoilMap),
                Arguments.of(97, 99, seedToSoilMap),
                Arguments.of(98, 50, seedToSoilMap),
                Arguments.of(99, 51, seedToSoilMap),
                Arguments.of(100, 100, seedToSoilMap)
        );
    }
    @ParameterizedTest
    @MethodSource("partOneProvider")
    void partOneTest(int expected, List<String> input) {
        assertEquals(expected, Day05.solutionPartOne(input));
    }

    private static Stream<Arguments> partOneProvider() {
        return Stream.of(
                Arguments.of(35, EXAMPLE_INPUT)
        );
    }

    @ParameterizedTest
    @MethodSource("partTwoProvider")
    void partTwoTest(int expected, List<String> input) {
        assertEquals(expected, Day05.solutionPartTwo(input));
    }

    private static Stream<Arguments> partTwoProvider() {
        return Stream.of(
                Arguments.of(29, List.of("two1nine")),
                Arguments.of(83, List.of("eightwothree"))
        );
    }
}