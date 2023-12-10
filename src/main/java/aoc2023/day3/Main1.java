package aoc2023.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main1 {

    private record NumberInfo(long value, int startIndex, int endIndex, int lineIndex) {

    }

    public static int findEndOfNumber(String line, int numberStart) {
        int index = numberStart;
        while (index < line.length() && Character.isDigit(line.charAt(index))) {
            index++;
        }
        return index;
    }

    public static List<NumberInfo> findAllNumbersInLine(String line, int lineIndex) {
        ArrayList<NumberInfo> numbers = new ArrayList<>();
        int x = 0;
        while (x < line.length()) {
            if (Character.isDigit(line.charAt(x))) {
                int numberEnd = findEndOfNumber(line, x);
                int numberValue = Integer.parseInt(line.substring(x, numberEnd));
                numbers.add(new NumberInfo(numberValue, x, numberEnd, lineIndex));
                x = numberEnd + 1;
            } else {
                x++;
            }
        }
        return numbers;
    }

    public static boolean isAdjacentToAnySign(NumberInfo numberInfo, List<String> lines) {
        String line = lines.get(numberInfo.lineIndex);
        boolean signFromLeft = numberInfo.startIndex > 0
                && !Character.isDigit(line.charAt(numberInfo.startIndex - 1))
                && line.charAt(numberInfo.startIndex - 1) != '.';
        boolean signFromRight = numberInfo.endIndex < line.length()
                && !Character.isDigit(line.charAt(numberInfo.endIndex))
                && line.charAt(numberInfo.endIndex) != '.';

        int leftRange = Integer.max(0, numberInfo.startIndex - 1);
        int rightRange = Integer.min(line.length()-1, numberInfo.endIndex);
        boolean signAbove = false;
        if (numberInfo.lineIndex != 0) {
            for (char field : lines.get(numberInfo.lineIndex - 1).substring(leftRange, rightRange + 1).toCharArray()) {
                if (!Character.isDigit(field) && field != '.') {
                    signAbove = true;
                    break;
                }
            }
        }
        boolean signBelow = false;
        if (numberInfo.lineIndex != lines.size() - 1) {

            for (char field : lines.get(numberInfo.lineIndex + 1).substring(leftRange, rightRange + 1).toCharArray()) {
                if (!Character.isDigit(field) && field != '.') {
                    signBelow = true;
                    break;
                }
            }
        }

        return signBelow || signAbove || signFromLeft || signFromRight;
    }

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try (InputStream is = aoc2023.day3.Main1.class.getClassLoader()
                .getResourceAsStream("inputDay3.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        List<NumberInfo> allNumbers = new ArrayList<>();
        int x = 0;
        for (String line : lines) {
            allNumbers.addAll(findAllNumbersInLine(line, x));
            x++;
        }
        long sum = 0;

        for(NumberInfo info : allNumbers) {
            if (isAdjacentToAnySign(info, lines)){
                sum += info.value;
            }
        }
        System.out.println(sum);
    }
}
