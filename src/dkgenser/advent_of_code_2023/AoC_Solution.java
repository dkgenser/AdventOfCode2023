package dkgenser.advent_of_code_2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AoC_Solution {

    public static void main(String[] args) {
        String dayNumber = "07";
        String fileName = "src/dkgenser/advent_of_code_2023/resources/aoc_day" + dayNumber;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> input = stream.collect(Collectors.toList());

            System.out.println("Part One Answer: " + Day07.solutionPartOne(input));
            //System.out.println("Part Two Answer: " + Day06.solutionPartTwo(input));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
