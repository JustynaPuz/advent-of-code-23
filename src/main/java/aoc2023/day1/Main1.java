package aoc2023.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main1 {

    public static void main(String[] args) {
        String path = "inputDay1.txt";
        List<String> lines = new ArrayList<>();

        lines=Util.readFile(path);

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
