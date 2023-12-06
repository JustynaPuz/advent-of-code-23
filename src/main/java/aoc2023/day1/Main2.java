package aoc2023.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2 {

    static final String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static List<Integer> getNumberFromLine(String line) {
        List<Integer> numbersInLine = new ArrayList<>();

        for (int i=0; i < line.length(); i++) {
            for(int numIndex=0; numIndex < numbers.length; numIndex++) {
                String num = numbers[numIndex];
                if (line.startsWith(num, i)) {
                    numbersInLine.add(numIndex + 1);
                } else if (Character.isDigit(line.charAt(i))) {
                    numbersInLine.add(Character.getNumericValue(line.charAt(i)));
                }
            }
        }
        return numbersInLine;
    }

    public static void main(String[] args) {
        String path = "inputDay1.txt";
        List<String> lines = new ArrayList<>();

        lines=Util.readFile(path);

        int sum = 0;

        for (String line : lines) {
            List<Integer> numbers;
            numbers = getNumberFromLine(line);
            sum += numbers.get(0) * 10 + numbers.get(numbers.size() - 1);
            System.out.println(sum);
        }
        System.out.println(sum);
    }

}


