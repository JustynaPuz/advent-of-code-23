package aoc2023.day4;

import aoc2023.Util.Util;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task1 {

    public static void main(String[] args) {

        List<String> lines = Util.readFile("src/main/resources/inputDay4.txt");

        int sum = 0;
        for (String line : lines) {
            line = line.substring(line.indexOf(":") + 1).trim();
            Set<String> winningNumbers = new HashSet<>(
                    Arrays.asList(line.substring(0, line.indexOf("|")).trim().split(" {1,2}")));
            Set<String> numbers = new HashSet<>(
                    Arrays.asList(line.substring(line.indexOf("|") + 1).trim().split(" {1,2}")));
            numbers.retainAll(winningNumbers);

            int pointsForOneCard = numbers.size();
            if (pointsForOneCard > 1) {
                pointsForOneCard = (int) Math.pow(2, pointsForOneCard - 1);
            }
            sum += pointsForOneCard;
        }
        System.out.println(sum);
    }
}
