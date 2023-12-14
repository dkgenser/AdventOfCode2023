package dkgenser.advent_of_code_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day 05
 */
public final class Day05 {
    private static final String BREAK = "^[\\s\\n]*$";
    private static final Pattern seedPattern;
    static {
        seedPattern = Pattern.compile("\\d+");
    }

    private Day05() {}


    public static int solutionPartOne(List<String> args) {
        /*
         * I could build all the maps, then convert each seed one by one and capture all the calculations along the way.
         * Or, convert each seed with each map and just look at the final result because it appears that the maps come
         * in in the order that needs to be followed.
         *
         * The seed numbers and ranges are quite large in the puzzle input. I'm inclined not to build any arrays or
         * hashmaps for the conversion itself, probably go with a search tree of sorts.
         */
        List<Long> seeds = getSeeds(args.get(0));
        List<GardenMap> maps = new ArrayList<>();
        int start = 1;
        for (int i = 2; i <= args.size(); i++) {
            if (args.size() == i || Pattern.matches(BREAK, args.get(i))) {
                maps.add(new GardenMap(args.subList(start, i)));
                start = i;
            }
        }

        long min = Long.MAX_VALUE;
        for (Long seed : seeds) {
            long current = seed;
            for (GardenMap map: maps) {
                current = map.get(current);
            }
            min = Math.min(min, current);
        }
        return (int) min;
    }

    private static List<Long> getSeeds(String seedString) {
        List<Long> seeds = new ArrayList<>();
        Matcher m = seedPattern.matcher(seedString);
        while (m.find()) {
            seeds.add(Long.valueOf(m.group()));
        }
        return seeds;
    }

    public static int solutionPartTwo(List<String> args) {
        return 0;
    }

}
