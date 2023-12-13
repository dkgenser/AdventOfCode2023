package dkgenser.advent_of_code_2023;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day03 {

    private static final Pattern numberPattern;
    private static final List<String> PartSymbols = Arrays.asList("#", "$", "%", "&", "*", "+", "-", "/", "=", "@");
    private static final String GEAR_SYMBOL = "*";
    private static final Pattern gearPattern;
    static {
        numberPattern = Pattern.compile("(\\d+)");
        gearPattern = Pattern.compile("\\" + GEAR_SYMBOL);
    }


    private Day03() {}

    public static int solutionPartOne(List<String> args) {
        int sum = 0;
        //numbers with position from previous line
        Optional<String> previousLine = Optional.empty();
        for (int i = 0; i < args.size() ; i++){
            String line = args.get(i);
            Optional<String> nextLine = (i < args.size() - 1 ? Optional.of(args.get(i + 1)) : Optional.empty());

            //each number, check for symbols above and adjacent, save to compare otherwise
            Matcher numberMatcher = numberPattern.matcher(line);
            while (numberMatcher.find()) {
                int position = numberMatcher.start();
                String strNum = numberMatcher.group();
                if (checkForParts(previousLine, line, nextLine, position - 1, position + strNum.length() + 1)) {
                    sum += Integer.valueOf(strNum);
                }
            }

            // current -> previous
            previousLine = Optional.of(line);
        }
        return sum;
    }

    private static boolean checkForParts(Optional<String> prev, String current, Optional<String> next, int lowerBound, int higherBound){
        int finalLowerBound = (lowerBound > 0 ? lowerBound : 0);
        int finalHigherBound = (higherBound < current.length() ? higherBound : current.length() - 1);
        if (prev.isPresent()){
            if (checkForParts(prev.get(), finalLowerBound, finalHigherBound)) {
                return true;
            }
        }
        if (checkForParts(current, finalLowerBound, finalHigherBound)) {
            return true;
        }
        if (next.isPresent()){
            if (checkForParts(next.get(), finalLowerBound, finalHigherBound)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkForParts(String str, int lowerBound, int higherBound) {
        String compare = str.substring(lowerBound, higherBound);
        return PartSymbols.stream().anyMatch(compare::contains);
    }

    public static int solutionPartTwo(List<String> args) {
        int sum = 0;

        Optional<String> previousLine = Optional.empty();
        for (int i = 0; i < args.size() ; i++){
            String line = args.get(i);
            Optional<String> nextLine = (i < args.size() - 1 ? Optional.of(args.get(i + 1)) : Optional.empty());

            //each gear, find adjacent numbers if any
            Matcher gearMatcher = gearPattern.matcher(line);
            while (gearMatcher.find()) {
                int position = gearMatcher.start();
                sum += findGearRatio(position, previousLine, line, nextLine);
            }

            // current -> previous
            previousLine = Optional.of(line);
        }
        return sum;
    }

    // returns the ratio, or zero if it's not actually a gear
    private static int findGearRatio(int position, Optional<String> prev, String curr, Optional<String> next) {
        int partOneNum = 0;

        List<String> lines = new ArrayList<>();
        if (prev.isPresent()) lines.add(prev.get());
        lines.add(curr);
        if (next.isPresent()) lines.add(next.get());

        for (String line : lines) {
            Matcher matcher = numberPattern.matcher(line);
            while(matcher.find()) {
                String partNumStr = matcher.group();
                if(adjacent(position, matcher.start(), partNumStr)) {
                    int partNum = Integer.valueOf(partNumStr);
                    if (partOneNum == 0) {
                        partOneNum = partNum;
                    } else {
                        return partOneNum * partNum;
                    }
                }
            }
        }

        return 0;
    }

    private static boolean adjacent(int gearPosition, int startPosition, String part) {
        int endPosition = startPosition + part.length() - 1;
        return (startPosition <= gearPosition + 1 && gearPosition - 1 <= endPosition);
    }

}
