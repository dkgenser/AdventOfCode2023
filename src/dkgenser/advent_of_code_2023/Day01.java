package dkgenser.advent_of_code_2023;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day01 {

    private static final Map<String, Integer> digitAsWordMap;
    private static final Pattern digitPattern;
    private static final Pattern digitAndWordDigitPattern;
    static {
        digitAsWordMap = Map.of("one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9,
                "zero", 0);
        digitPattern = Pattern.compile("\\d");
        digitAndWordDigitPattern = Pattern.compile("\\d|one|two|three|four|five|six|seven|eight|nine");

    }


    private Day01() {}

    public static int solutionPartOne(List<String> args) {
        int sum = 0;
        for(String str : args) {
            sum += stringCalibrationValuePartOne(str);
        }
        return sum;
    }

    private static int stringCalibrationValuePartOne(String input) {
        Matcher m = digitPattern.matcher(input);
        m.find();
        int digitOne = input.charAt(m.start()) - '0';
        int digitTwo = digitOne;
        while(m.find()) {
            digitTwo = input.charAt(m.start()) - '0';
        }
        return digitOne * 10 + digitTwo;
    }

    public static int solutionPartTwo(List<String> args) {
        int sum = 0;
        for(String str : args) {
            sum += stringCalibrationValuePartTwo(str);
        }
        return sum;
    }

    private static int stringCalibrationValuePartTwo(String input) {
        Matcher m = digitAndWordDigitPattern.matcher(input);
        m.find();
        String digitOne = m.group();
        String digitTwo = digitOne;
        while(m.find(m.start() + 1)) {
            digitTwo = m.group();
        }
        return convertStringDigit(digitOne) * 10 + convertStringDigit(digitTwo);
    }

    private static int convertStringDigit(String digit) {
        if (1 == digit.length()) {
            return digit.charAt(0) - '0';
        } else {
            return digitAsWordMap.get(digit);
        }
    }
}
