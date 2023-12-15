package dkgenser.advent_of_code_2023;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day 6
 */
public final class Day06 {

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

    static int calculateWaysToWin(int time, long distRecord) {
        // Since time can be stored as an int, we know the number of possible solutions can also be an int
        // (since the answer will always be <= time)
        // However, the math that results in the distance travelled can overflow an int.

        // x = how long you hold the button
        // y = time left in the race once you let go
        // x + y = total race time (-> y = time - x)
        // x * y = distance traveled
        // solve for when x * y > distRecord, return the size of the range
        // x * (time - x) > distRecord
        int marker = 0;
        // cut off the first ineligible numbers
        int slice = (int) (distRecord / time) - 1;
        //find the first possibility, only need to go halfway since multiplication is commutative
        for (int i = slice; i <= (time / 2); i++) {
            if (Math.multiplyExact((long) i,(time - i)) > distRecord) {
                marker = i;
                break;
            }
        }
        return (int) (time - 2 * marker + 1);
    }

    // Part two was just an update to the above, no new distinct code

}
