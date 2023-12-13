package dkgenser.advent_of_code_2023;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day02 {

    private static final Pattern gamePattern;
    private static final Pattern bluePattern;
    private static final int BLUE_LIMIT = 14;
    private static final Pattern greenPattern;
    private static final int GREEN_LIMIT = 13;
    private static final Pattern redPattern;
    private static final int RED_LIMIT = 12;
    static {
        gamePattern = Pattern.compile("Game (\\d+):");
        bluePattern = Pattern.compile("(\\d+) blue");
        greenPattern = Pattern.compile("(\\d+) green");
        redPattern = Pattern.compile("(\\d+) red");
    }


    private Day02() {}

    public static int solutionPartOne(List<String> args) {
        int sum = 0;
        for (String game: args) {
            if (isGamePossible(game)) {
                Matcher m = gamePattern.matcher(game);
                m.find();
                sum += Integer.valueOf(game.substring(m.start(1), m.end(1)));
            }
        }
        return sum;
    }

    private static boolean isGamePossible(String game) {
        Matcher blue = bluePattern.matcher(game);
        if (!checkColor(BLUE_LIMIT, blue)) {
            return false;
        }
        Matcher green = greenPattern.matcher(game);
        if (!checkColor(GREEN_LIMIT, green)) {
            return false;
        }
        Matcher red = redPattern.matcher(game);
        if (!checkColor(RED_LIMIT, red)) {
            return false;
        }
        return true;
    }

    private static boolean checkColor(int limit, Matcher m) {
        while(m.find()) {
            if (limit < Integer.valueOf(m.group(1))) {
                return false;
            }
        }
        return true;
    }
    public static int solutionPartTwo(List<String> args) {
        int sum = 0;
        for (String game : args) {
            sum += calculatePowerofGame(game);
        }
        return sum;
    }

    private static int calculatePowerofGame(String game) {
        Matcher blue = bluePattern.matcher(game);
        int blueMax = calculateMaxColor(blue);
        Matcher green = greenPattern.matcher(game);
        int greenMax = calculateMaxColor(green);
        Matcher red = redPattern.matcher(game);
        int redMax = calculateMaxColor(red);

        return (blueMax > 0 ? blueMax : 1) * (greenMax > 0 ? greenMax : 1) * (redMax > 0 ? redMax: 1);
    }

    private static int calculateMaxColor(Matcher m) {
        int max = 0;
        while(m.find()) {
            max = Math.max(max, Integer.valueOf(m.group(1)));
        }
        return max;
    }
}
