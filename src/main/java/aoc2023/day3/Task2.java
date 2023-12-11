package aoc2023.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Task2 {

    private static class numberParameters {

        int startOfNumber = -1;
        int endOfNumber = -1;
        int length = 0;
        int starPosition = -1;

        @Override
        public String toString() {
            return "numberParameters{" +
                    "startOfNumber=" + startOfNumber +
                    ", endOfNumber=" + endOfNumber +
                    ", length=" + length +
                    '}';
        }
    }

    private static class starPosition {

        int starLine = -1;
        int starPosition = -1;

        @Override
        public String toString() {
            return "starPosition{" +
                    "starLine=" + starLine +
                    ", starPosition=" + starPosition +
                    '}';
        }

        public starPosition(int starLine, int starPosition) {
            this.starLine = starLine;
            this.starPosition = starPosition;
        }
    }

    private static class engineParts {

        String number = " ";
        starPosition StarPosition = new starPosition(-1, -1);
        int lineNumber = -1;

        @Override
        public String toString() {
            return "engineParts{" +
                    "number='" + number + '\'' +
                    ", StarPosition=" + StarPosition +
                    ", lineNumber=" + lineNumber +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(StarPosition);
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

    public static int checkLine(numberParameters parameters, int position, int howLong,
            String line) {
        for (int i = position; i < howLong; i++) {
            if (line.charAt(i) == '*') {
                return i;
            }
        }
        return -1;
    }

    public static starPosition getPositionOfStarNearNumber(numberParameters parameters,
            String currentLine,
            String followingLine, String previousLine, whichLine flag, int lineIndex) {
        int startPosition = -1;
        int howLong = -1;
        //check in current line
        if (parameters.startOfNumber == 0) {
            startPosition = 0;
            howLong = parameters.length + 1;
            if ((currentLine.charAt(parameters.endOfNumber + 1)) == '*') {
                return new starPosition(lineIndex, parameters.endOfNumber + 1);
            }
        } else if (parameters.endOfNumber == currentLine.length() - 1) {

            if ((currentLine.charAt(parameters.startOfNumber - 1)) == '*') {
                return new starPosition(lineIndex, parameters.startOfNumber - 1);
            }
            startPosition = parameters.startOfNumber - 1;
            howLong = parameters.length + parameters.startOfNumber;

        } else {
            startPosition = parameters.startOfNumber - 1;
            howLong = parameters.length + parameters.startOfNumber + 1;
            if ((currentLine.charAt(parameters.startOfNumber - 1)) == '*') {
                return new starPosition(lineIndex, parameters.startOfNumber - 1);
            } else if (currentLine.charAt(parameters.endOfNumber + 1) == '*') {
                return new starPosition(lineIndex, parameters.endOfNumber + 1);

            }

        }

        if (flag == whichLine.First) {
            if (checkLine(parameters, startPosition, howLong, followingLine) != -1) {
                return new starPosition(lineIndex + 1,
                        checkLine(parameters, startPosition, howLong, followingLine));

            }
        } else if (flag == whichLine.Last) {
            if (checkLine(parameters, startPosition, howLong, previousLine) != -1) {
                return new starPosition(lineIndex - 1,
                        checkLine(parameters, startPosition, howLong, previousLine));
            }
        } else if (flag == whichLine.Center) {
            if (checkLine(parameters, startPosition, howLong, previousLine) != -1) {
                return new starPosition(lineIndex - 1,
                        checkLine(parameters, startPosition, howLong, previousLine));
            } else if (checkLine(parameters, startPosition, howLong, followingLine) != -1) {
                return new starPosition(lineIndex + 1,
                        checkLine(parameters, startPosition, howLong, followingLine));
            }
        }
        return new starPosition(-1, -1);
    }

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        List<engineParts> partsOfEngine = new ArrayList<>();
        int sum = 0;
        whichLine flag;
        try (InputStream is = Task1.class.getClassLoader()
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
            List<Integer> starForLine = new ArrayList<>();
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
                    if (getPositionOfStarNearNumber(parameters, line, lines.get(numberOfLine + 1),
                            line, flag, numberOfLine).starLine != -1) {
                        engineParts part = new engineParts();
                        part.StarPosition = getPositionOfStarNearNumber(parameters, line,
                                lines.get(numberOfLine + 1), line, flag, numberOfLine);
                        part.number = numbersForLine.get(index);
                        part.lineNumber = numberOfLine;
                        partsOfEngine.add(part);
                    }
                    index++;
                }
            } else if (numberOfLine == lines.size() - 1) {
                index = 0;
                flag = whichLine.Last;
                for (numberParameters parameters : numberParameters) {
                    if (getPositionOfStarNearNumber(parameters, line, line,
                            lines.get(numberOfLine - 1), flag, numberOfLine).starLine != -1) {
                        engineParts part = new engineParts();
                        part.StarPosition = getPositionOfStarNearNumber(parameters, line, line,
                                lines.get(numberOfLine - 1), flag, numberOfLine);
                        part.number = numbersForLine.get(index);
                        part.lineNumber = numberOfLine;
                        partsOfEngine.add(part);
                    }
                    index++;
                }
            } else {
                index = 0;
                flag = whichLine.Center;
                for (numberParameters parameters : numberParameters) {
                    if (getPositionOfStarNearNumber(parameters, line, lines.get(numberOfLine + 1),
                            lines.get(numberOfLine - 1), flag, numberOfLine).starLine != -1) {
                        engineParts part = new engineParts();
                        part.StarPosition = getPositionOfStarNearNumber(parameters, line,
                                lines.get(numberOfLine + 1),
                                lines.get(numberOfLine - 1), flag, numberOfLine);
                        part.number = numbersForLine.get(index);
                        part.lineNumber = numberOfLine;
                        partsOfEngine.add(part);
                    }
                    index++;
                }
            }
            numberOfLine++;
        }

        int counter = 0;
        for (int i = 0; i < partsOfEngine.size(); i++) {
            for (int x = i + 1; x < partsOfEngine.size(); x++) {
                if (partsOfEngine.get(i).StarPosition.starLine == partsOfEngine.get(
                        x).StarPosition.starLine
                        && partsOfEngine.get(x).lineNumber - partsOfEngine.get(i).lineNumber <= 2
                        && partsOfEngine.get(i).StarPosition.starPosition == partsOfEngine.get(
                        x).StarPosition.starPosition){
                    sum += Integer.parseInt(partsOfEngine.get(i).number) * Integer.parseInt(
                            partsOfEngine.get(x).number);

                }

            }


        }
        System.out.println(sum);
    }
}
