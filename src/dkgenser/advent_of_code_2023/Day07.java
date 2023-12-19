package dkgenser.advent_of_code_2023;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Day 7
 */
public final class Day07 {

    private static final Pattern cardPattern;
    static {
        cardPattern = Pattern.compile("([AKQJT2-9]+)\\s+(\\d+)");
    }


    private Day07() {}

    public static long solutionPartOne(List<String> args) {
        List<CamelCard> cards = args.stream()
                .map(arg -> {
            Matcher m = cardPattern.matcher(arg);
            m.find();
            return new CamelCard(m.group(1), Integer.valueOf(m.group(2)));
        })
                .sorted(Comparator.comparing(CamelCard::getHand))
                .collect(Collectors.toList());
        long sum = 0;
        for (int i = 0; i < cards.size(); i++) {
            sum = Math.addExact(sum, (i + 1) * cards.get(i).getBid());
        }

        return sum;
    }

    public static int solutionPartTwo(List<String> args) {
        return 0;
    }

}
