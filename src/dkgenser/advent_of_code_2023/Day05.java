package dkgenser.advent_of_code_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Day 05
 */
public final class Day05 {
    private static final String BREAK = "^[\\s\\n]*$";
    private static final Pattern seedPattern;
    private static final Pattern seedRangePattern;
    static {
        seedPattern = Pattern.compile("\\d+");
        seedRangePattern = Pattern.compile("(\\d+)\\s+(\\d+)");
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
        List<Long> seeds = getSeedsPartOne(args.get(0));
        List<GardenMap> maps = getMaps(args);

        long min = seeds.stream()
                .mapToLong(seed -> {
                    long current = seed;
                    for (GardenMap map: maps) {
                        current = map.get(current);
                    }
                    return current;
                })
                .min()
                .getAsLong();
        return (int) min;
    }

    private static List<Long> getSeedsPartOne(String seedString) {
        List<Long> seeds = new ArrayList<>();
        Matcher m = seedPattern.matcher(seedString);
        while (m.find()) {
            seeds.add(Long.valueOf(m.group()));
        }
        return seeds;
    }

    private static List<GardenMap> getMaps(List<String> args) {
        List<GardenMap> maps = new ArrayList<>();
        int startLine = 1;
        for (int i = 2; i <= args.size(); i++) {
            if (args.size() == i || Pattern.matches(BREAK, args.get(i))) {
                maps.add(new GardenMap(args.subList(startLine, i)));
                startLine = i;
            }
        }
        return maps;
    }

    class RangeTuple {
        long start;
        long range;
    }

    //TODO: this is unfinished. Initial thoughts towards using ranges instead
    private static List<RangeTuple> splitIfNeeded(RangeTuple input, long start, long range) {
        if (input.start >= start && (input.start + input.range) <= (start + range)) {
            return List.of(input);
        }
        return List.of(input);
    }
    public static int solutionPartTwo(List<String> args) {
        List<GardenMap> maps = getMaps(args);

        /**
         * TODO: This is so slow. It does work without overloading the heap space, but it would be so much more
         * efficient to keep the seed data as ranges whenever possible.
         * for each set of ranges, compare to the ranges in the map; split as needed then convert.
         */

        Matcher m = seedRangePattern.matcher(args.get(0));
        long min = Long.MAX_VALUE;
        while (m.find()) {
            long start = Long.valueOf(m.group(1));
            long range = Long.valueOf(m.group(2));
            long minForRange = LongStream.range(start, start + range)
                    .map(seed -> {
                        long current = seed;
                        for (GardenMap map: maps) {
                            current = map.get(current);
                        }
                        return current;
                    })
                    .min()
                    .getAsLong();
            min = Math.min(min, minForRange);
        }
        return (int) min;
    }

}
