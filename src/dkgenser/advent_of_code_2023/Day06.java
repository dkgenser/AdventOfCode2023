package dkgenser.advent_of_code_2023;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day 6
 */
public final class Day06 {

    class RaceTuple {
        int time;
        int distRecord;
    }

    private static final Pattern numberPattern;
    static {
        numberPattern = Pattern.compile("\\d+");
    }


    private Day06() {}

    public static int solutionPartOne(List<String> args) {
        String timeStr = args.get(0);
        String distStr = args.get(1);

        Matcher mTime = numberPattern.matcher(timeStr);
        Matcher mDist = numberPattern.matcher(distStr);

        int sum = 1;
        while (mTime.find() && mDist.find()) {
            int time = Integer.valueOf(mTime.group());
            int record = Integer.valueOf(mDist.group());
            sum = sum * calculateWaysToWin(time, record);
        }

        return sum;
    }

    static int calculateWaysToWin(int time, int distRecord) {
        // x = how long you hold the button
        // y = time left in the race once you let go
        // x + y = total race time
        // x * y = distance traveled
        // solve for when x * y > distRecord, return the size of the range
        // x * (time - x) > distRecord
        // x * time - x^2 > distRecord
        int count = 0;
        //brute solution
        for (int i = 1; i < time; i++) {
            if (i * (time - i) > distRecord) count++;
        }

        return count;
    }

    public static int solutionPartTwo(List<String> args) {
        return 0;
    }

}
