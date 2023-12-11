package aoc2023.day4;

import aoc2023.Util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task2 {


    public static void main(String[] args) {

        List<String> lines = Util.readFile("src/main/resources/inputDay4.txt");
        List<Integer> cards = new ArrayList<>(Collections.nCopies(lines.size(), 1));
        int numberOfCard = 1;
        for (String line : lines) {

            line = line.substring(line.indexOf(":") + 1).trim();
            Set<String> winningNumbers = new HashSet<>(
                    Arrays.asList(line.substring(0, line.indexOf("|")).trim().split(" {1,2}")));
            Set<String> numbers = new HashSet<>(
                    Arrays.asList(line.substring(line.indexOf("|") + 1).trim().split(" {1,2}")));
            numbers.retainAll(winningNumbers);
            int pointsForOneCard = numbers.size();

            for (int x = 0; x < cards.get(numberOfCard - 1); x++) {
                for (int i = 0; i < pointsForOneCard; i++) {
                    cards.set(numberOfCard + i, ((cards.get(numberOfCard + i) + 1)));
                }
            }
            numberOfCard++;
        }
        int sum = 0;
        for (int i : cards) {
            sum += i;
        }
        System.out.println(sum);
    }
}
