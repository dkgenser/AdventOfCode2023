package dkgenser.advent_of_code_2023;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day04 {

    private static final Pattern numberPattern;
    private static final Pattern winningNumbersPattern;
    private static final Pattern scratchNumbersPattern;
    static {
        numberPattern = Pattern.compile("\\d+");
        winningNumbersPattern = Pattern.compile(":\\s+(?:\\d+\\s+)+\\|");
        scratchNumbersPattern = Pattern.compile("\\|(?:\\s+\\d+)+$");
    }


    private Day04() {}

    public static int solutionPartOne(List<String> args) {
        int sum = 0;
        for (String card : args) {
            Matcher wNumbers = winningNumbersPattern.matcher(card);
            wNumbers.find();
            Set<Integer> winningNumbers = findNumbers(wNumbers.group());
            //TODO: num check, I'm assuming no duplicates and since I'm using sets, ensure all sets have the same numbers across all cards
            Matcher scratchNums = scratchNumbersPattern.matcher(card);
            scratchNums.find();
            Set<Integer> numbersIHave = findNumbers(scratchNums.group());
            sum += Math.pow(2, numbersIHave.stream().filter(winningNumbers::contains).count() - 1);
        }
        return sum;
    }

    private static Set<Integer> findNumbers(String string) {
        Set<Integer> numbers = new HashSet<>();
        Matcher numberMatch = numberPattern.matcher(string);
        while (numberMatch.find()) {
            numbers.add(Integer.valueOf(numberMatch.group()));
        }
        return numbers;
    }

    public static int solutionPartTwo(List<String> args) {
        int[] winning = new int[args.size()];
        //count number of winning numbers per card, then do the crazy card math
        for (int i = 0; i < args.size(); i++) {
            String card = args.get(i);
            Matcher wNumbers = winningNumbersPattern.matcher(card);
            wNumbers.find();
            Set<Integer> winningNumbers = findNumbers(wNumbers.group());
            //TODO: num check, I'm assuming no duplicates and since I'm using sets, ensure all sets have the same numbers across all cards
            Matcher scratchNums = scratchNumbersPattern.matcher(card);
            scratchNums.find();
            Set<Integer> numbersIHave = findNumbers(scratchNums.group());
            winning[i] = (int) numbersIHave.stream().filter(winningNumbers::contains).count();
        }
        // now add up how many cards this ends up as
        return Arrays.stream(countCopies(winning)).sum();
    }

    static int[] countCopies(int[] winning) {
        int numCards = winning.length;
        int [] copies = new int[numCards];
        Arrays.fill(copies, 1);
        for (int i = 0; i < numCards; i++) {
            int copyCount = copies[i];
            int limit = Math.min(i + winning[i] + 1, numCards);
            for (int j = i + 1; j < limit; j++) {
                copies[j] = copies[j] + copyCount;
            }
        }
        return copies;
    }

}
