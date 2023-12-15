package aoc2023.day5;

import aoc2023.Util.Util;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static boolean isInRange(Long number, Long lowerRange, Long upperRange) {
        return number >= lowerRange && number <= upperRange;
    }

    public static startAndEndOfRange commonPartOfRanges(startAndEndOfRange seeds,
            startAndEndOfRange rangesFromMap) {
        long startMax = Math.max(seeds.start, rangesFromMap.start);
        long endMin = Math.min(seeds.end, rangesFromMap.end);
        if (startMax <= endMin) {
            return new startAndEndOfRange(startMax, endMin);
        }
        return null;
    }

    public static List<startAndEndOfRange> notCommonPartsOfRanges(startAndEndOfRange seeds,
            startAndEndOfRange largerSeeds) {
        List<startAndEndOfRange> ranges = new ArrayList<>();
        if (seeds.start > largerSeeds.start) {
            ranges.add(new startAndEndOfRange(largerSeeds.start, seeds.start - 1));
        }
        if (seeds.end < largerSeeds.end) {
            ranges.add(new startAndEndOfRange(seeds.end + 1, largerSeeds.end));
        }
        return ranges;
    }

    public static class ranges {

        Long[] sourceRange = new Long[2];
        Long[] destinationRange = new Long[2];
    }

    public record startAndEndOfRange(long start, long end) {

    }

    public static void main(String[] args) {
        List<String> lines = Util.readFile("src/main/resources/inputDay5.txt");
        List<startAndEndOfRange> seedsRanges = new ArrayList<>();
        String[] seedsStartAndRange = lines.get(0).substring(7).trim().split(" ");

        for (int i = 0; i < seedsStartAndRange.length; i++) {
            startAndEndOfRange range = new startAndEndOfRange(Long.parseLong(seedsStartAndRange[i]),
                    Long.parseLong(seedsStartAndRange[i + 1]) + Long.parseLong(
                            seedsStartAndRange[i]) - 1);
            seedsRanges.add(range);
            i++;
        }
        int index = 3;
        while (index <= lines.size()) {
            List<ranges> rangesForOneMap = new ArrayList<>();
            while (!lines.get(index).isBlank() && index != lines.size() - 1) {
                String[] numbers = lines.get(index).split(" ");
                ranges rangeForSeed = new ranges();
                rangeForSeed.sourceRange[0] = Long.parseLong(numbers[1]);
                rangeForSeed.sourceRange[1] =
                        Long.parseLong(numbers[1]) + Long.parseLong(numbers[2]) - 1;
                rangeForSeed.destinationRange[0] = Long.parseLong(numbers[0]);
                rangeForSeed.destinationRange[1] =
                        Long.parseLong(numbers[0]) + Long.parseLong(numbers[2]) - 1;
                rangesForOneMap.add(rangeForSeed);
                index++;
            }
            List<startAndEndOfRange> seedsInRange = new ArrayList<>();
            int numberOfSeeds = -1;
            for (ranges range : rangesForOneMap) {
                int i = 0;
                while (i != seedsRanges.size()) {
                    List<startAndEndOfRange> seedsNotInRange = new ArrayList<>();
                    startAndEndOfRange comRange = commonPartOfRanges(seedsRanges.get(i),
                            new startAndEndOfRange(range.sourceRange[0],
                                    range.sourceRange[1]));
                    if (comRange != null) {
                        seedsInRange.add(comRange);
                    }
                    if (comRange != null) {
                        seedsNotInRange.addAll(
                                notCommonPartsOfRanges(comRange, seedsRanges.get(i)));
                    }
                    if (comRange != null) {
                        numberOfSeeds++;
                        Long whatNumberInRangeStart =
                                comRange.start - range.sourceRange[0];

                        seedsInRange.set(numberOfSeeds, new startAndEndOfRange(
                                range.destinationRange[0] + whatNumberInRangeStart,
                                range.destinationRange[0] + whatNumberInRangeStart +
                                        (comRange.end) - comRange.start));
                        seedsRanges.remove(i);
                        seedsRanges.addAll(seedsNotInRange);
                    } else {
                        i++;
                    }
                }
            }
            seedsRanges.addAll(seedsInRange);
            index += 2;
        }
        long min = seedsRanges.get(0).start;
        for (int i = 1; i < seedsRanges.size(); i++) {
            if (seedsRanges.get(i).start < min) {
                min = seedsRanges.get(i).start;
            }
        }
        System.out.println(min);

    }
}
