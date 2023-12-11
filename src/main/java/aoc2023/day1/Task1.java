package aoc2023.day1;

import aoc2023.Util.Util;
import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        String path = "src/main/resources/inputDay1.txt";
        List<String> lines = Util.readFile(path);

        int sum = 0;

        for (String line : lines) {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    numbers.add(Character.getNumericValue(line.charAt(i)));
                }
            }
            sum += numbers.get(0) * 10 + numbers.get(numbers.size() - 1);
        }
        System.out.println(sum);

    }
}
