package aoc2023.day5;

import aoc2023.Util.Util;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static class ranges {
        Long[] sourceRange = new Long[2];
        Long[] destinationRange = new Long[2];
    }
    public static boolean isInRange(Long number, Long lowerRange, Long upperRange) {
        return number >= lowerRange && number <= upperRange;
    }
    public static void main(String[] args) {
        List<String> lines = Util.readFile("src/main/resources/inputDay5.txt");
        List<Long> seeds = new ArrayList<>();

        String[] separatedSeeds = lines.get(0).substring(7).trim().split(" ");
        for (String x : separatedSeeds) {
            seeds.add(Long.parseLong(x));
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
            //for (Long seed : seeds) {
            for (int i = 0; i < seeds.size(); i++) {
                for (ranges range : rangesForOneMap) {
                    if (isInRange(seeds.get(i), range.sourceRange[0], range.sourceRange[1])) {
                        Long whatNumberInRange = seeds.get(i) - range.sourceRange[0];
                        seeds.set(i, range.destinationRange[0] + whatNumberInRange);
                        break;
                    }
                }
            }
            index += 2;
        }
        System.out.println(seeds.stream().min(Long::compare));
    }
}


