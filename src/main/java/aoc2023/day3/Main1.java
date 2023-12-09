package aoc2023.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main1 {

    private static class numberParameters {
        int startOfNumber = -1;
        int endOfNumber = -1;
        int length = 0;

        @Override
        public String toString() {
            return "numberParameters{" +
                    "startOfNumber=" + startOfNumber +
                    ", endOfNumber=" + endOfNumber +
                    ", length=" + length +
                    '}';
        }
    }
    public enum whichLine {First, Last, Center}
    public static int checkLengthOfNumber(String line) {
        int length = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                length++;
            }
            if (!Character.isDigit(line.charAt(i))) {
                return length;
            }
        }
        return length;
    }
    public static boolean checkLine(numberParameters parameters, int position, int howLong, String line) {
        for (int i = position; i < howLong; i++) {
            if (!Character.isDigit(line.charAt(i))
                    && line.charAt(i) != '.') {
                return true;
            }
        }
        return false;
    }
    public static boolean checkNumber(numberParameters parameters, String currentLine,
            String followingLine, String previousLine, whichLine flag) {
        int startPosition = -1;
        int howLong = -1;
        //check in current line
        if (parameters.startOfNumber == 0) {
            startPosition = 0;
            howLong = parameters.length + 1;
            if ((currentLine.charAt(parameters.endOfNumber + 1)) != '.') {
                return true;
            }
        } else if (parameters.endOfNumber == currentLine.length() - 1) {

            startPosition = parameters.startOfNumber - 1;
            howLong = parameters.length + parameters.startOfNumber;
            if ((currentLine.charAt(parameters.startOfNumber - 1)) != '.') {
                return true;
            }

        } else {
            startPosition = parameters.startOfNumber - 1;
            howLong = parameters.length + parameters.startOfNumber + 1;
            if ((currentLine.charAt(parameters.startOfNumber - 1)) != '.'
                    || currentLine.charAt(parameters.endOfNumber + 1) != '.') {
                return true;
            }
        }

        if (flag == whichLine.First) {
            if (checkLine(parameters, startPosition, howLong, followingLine)) {
                return true;
            }
        } else if (flag == whichLine.Last) {
            if (checkLine(parameters, startPosition, howLong, previousLine)) {
                return true;
            }
        } else if (flag == whichLine.Center) {
            if (checkLine(parameters, startPosition, howLong, previousLine) || checkLine(parameters,
                    startPosition, howLong, followingLine)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        List<String> partsOfEngine = new ArrayList<>();
        int sum = 0;
        whichLine flag;
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
        int numberOfLine = 0;

        for (String line : lines) {
            List<String> numbersForLine = new ArrayList<>();
            List<numberParameters> numberParameters = new ArrayList<>();

            for (int i = 0; i < line.length(); i++) {

                if (Character.isDigit(line.charAt(i))) {

                    numberParameters positions = new numberParameters();
                    int length = 0;
                    if (positions.startOfNumber == -1) {
                        positions.startOfNumber = i;

                    }
                    if (i < line.length() - 3) {
                        length = checkLengthOfNumber(line.substring(i, i + 3));
                    } else {
                        length = checkLengthOfNumber(line.substring(i, i + (line.length() - i)));
                    }
                    String number = line.substring(i, i + length);
                    positions.endOfNumber = length + i - 1;
                    positions.length = length;
                    i += length - 1;
                    numbersForLine.add(number);
                    numberParameters.add(positions);
                }
            }
            int index = 0;
            if (numberOfLine == 0) {
                index = 0;
                flag = whichLine.First;
                for (numberParameters parameters : numberParameters) {
                    if (checkNumber(parameters, line, lines.get(numberOfLine + 1), line, flag)) {
                        partsOfEngine.add(numbersForLine.get(index));
                    }
                    index++;
                }
            } else if (numberOfLine == lines.size() - 1) {
                index = 0;
                flag = whichLine.Last;
                for (numberParameters parameters : numberParameters) {
                    if (checkNumber(parameters, line, line, lines.get(numberOfLine - 1), flag)) {
                        partsOfEngine.add(numbersForLine.get(index));
                    }
                    index++;
                }
            } else {
                index = 0;
                flag = whichLine.Center;
                for (numberParameters parameters : numberParameters) {
                    if (checkNumber(parameters, line, lines.get(numberOfLine + 1),
                            lines.get(numberOfLine - 1), flag)) {
                        partsOfEngine.add(numbersForLine.get(index));
                    }
                    index++;
                }
            }
            numberOfLine++;
        }
        for (String s : partsOfEngine) {
            sum += Integer.parseInt(s);
        }
        System.out.println(sum);
    }
}
