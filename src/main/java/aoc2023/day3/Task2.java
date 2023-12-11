package aoc2023.day3;

import aoc2023.Util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static aoc2023.day3.Task1.findAllNumbersInLine;


public class Task2 {

    private record StarPosition(int y, int x) {

    }

    private static List<StarPosition> findAllStarsInInput(List<String> lines) {
        List<StarPosition> stars = new ArrayList<>();
        int y = 0;
        for (String line : lines) {
            for(int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '*') {
                    stars.add(new StarPosition(y, x));
                }
            }
            y++;
        }
        return stars;
    }

    private static boolean areAdjacent(StarPosition starPosition, Task1.NumberInfo numberInfo) {
        return Math.abs(starPosition.y - numberInfo.lineIndex()) <= 1 &&
                starPosition.x >= numberInfo.startIndex() - 1 &&
                starPosition.x <= numberInfo.endIndex();
    }

    public static void main(String[] args) {
        List<String> lines = Util.readFile("/Users/abaranski/justyna/advent-of-code-23/src/main/resources/inputDay3.txt");

        List<StarPosition> stars = findAllStarsInInput(lines);
        Map<StarPosition, List<Long>> starsNeighbours = new HashMap<>();
        for (StarPosition star : stars) {
            starsNeighbours.put(star, new ArrayList<>());
        }
        int lineIndex = 0;
        for (String line : lines) {
            List<Task1.NumberInfo> numbersFromRow = findAllNumbersInLine(line, lineIndex);
            for(Task1.NumberInfo number : numbersFromRow) {
                for (StarPosition starPosition : stars) {
                    if (areAdjacent(starPosition, number)) {
                        List<Long> starNeighbours = starsNeighbours.get(starPosition);
                        starNeighbours.add(number.value());
                    }
                }
            }
            lineIndex++;
        }

        long totalValue = 0;
        for (List<Long> numbersFromStar : starsNeighbours.values()) {
            if (numbersFromStar.size() == 2 ) {
                totalValue += numbersFromStar.get(0) * numbersFromStar.get(1);
            }
        }
        System.out.println(totalValue);
    }
}
