package dkgenser.advent_of_code_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GardenMap {
    private static final Pattern inputFormat;
    static {
        // (1) destination range start, (2) source range start, (3) range length
        inputFormat = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(\\d+)");
    }

    private class MapNode implements Comparable<MapNode>{
        private long min; //start of source range
        private long max; //end of source range
        private long change; //math needed to get to the destination
        MapNode(long min, long max, long change){
            this.min = min;
            this.max = max;
            this.change = change;
        }

        @Override
        /**
         * Note: this class has a natural ordering that is inconsistent with equals.
         */
        public int compareTo(MapNode o) {
            return Long.compare(getKey(), o.getKey());
        }

        private long getKey() {
            return (min + max) / 2;
        }

        public boolean appliesTo(long o) {
            return min <= o && o <= max;
        }

        public long apply(long o) {
            if (!appliesTo(o)) {
                throw new IndexOutOfBoundsException();
            }
            return o + change;
        }
    }
    List<MapNode> nodes;

    GardenMap(List<String> mapInput) {
        nodes = new ArrayList<>(); //todo: add comparator, or change to queue

        for(String line : mapInput) {
            Matcher m = inputFormat.matcher(line);
            if (m.find()) {
                nodes.add(buildNode(m));
            }
        }
    }

    GardenMap(String mapInput) {
        nodes = new ArrayList<>(); //todo: add comparator, or change to queue

        Matcher m = inputFormat.matcher(mapInput);
        while(m.find()) {
            nodes.add(buildNode(m));
        }
    }

    private MapNode buildNode(Matcher m) {
        long start = Long.valueOf(m.group(2));
        long range = Long.valueOf(m.group(3));
        long end = start + range - 1;
        long change = Long.valueOf(m.group(1)) - start;
        return new MapNode(start, end, change);
    }

    private Optional<MapNode> getMapNode(long i) {
        //Could instead leverage the sort to stop searching once we've passed the max
        return nodes.stream().filter(n -> n.appliesTo(i)).findFirst();
    }

    long get(long key) {
        Optional<MapNode> node = getMapNode(key);
        if (node.isPresent()) {
            return node.get().apply(key);
        } else {
            return key;
        }
    }
}
