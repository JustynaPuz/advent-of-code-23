package aoc2023.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main1 {

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();

        try (InputStream is = Main1.class.getClassLoader().getResourceAsStream("inputDay1.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

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
