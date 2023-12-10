package aoc2023.day2;

import aoc2023.Util.Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task1 {

    static final int MAX_RED_CUBES = 12;
    static final int MAX_GREEN_CUBES = 13;
    static final int MAX_BLUE_CUBES = 14;

    static HashMap<String, Integer> numberOfColors(String turn) {
        String[] splitByColor = turn.split(",");
        HashMap<String, Integer> numberAndColor = new HashMap<>();
        for (String s : splitByColor) {
            String[] numAndCol = s.trim().split(" ");
            numberAndColor.put(numAndCol[1], Integer.parseInt(numAndCol[0]));
        }
        return numberAndColor;
    }

    public static void main(String[] args) {
        List<String> lines = Util.readFile("inputDay2.txt");

        int gameNumber = 1;
        int sum = 0;
        int powersum = 0;

        for (String line : lines) {
            Map<String, Integer> maxCubesForColor = new HashMap<>();
            maxCubesForColor.put("red", 0);
            maxCubesForColor.put("blue", 0);
            maxCubesForColor.put("green", 0);
            int colonIndex = line.indexOf(":");
            line = line.substring(colonIndex + 1).trim();
            String[] turns = line.split(";");
            for (String turn : turns) {
                Map<String, Integer> cubesForColor = numberOfColors(turn);
                maxCubesForColor.put("red", Integer.max(maxCubesForColor.get("red"),
                        cubesForColor.getOrDefault("red", 0)));
                maxCubesForColor.put("blue", Integer.max(maxCubesForColor.get("blue"),
                        cubesForColor.getOrDefault("blue", 0)));
                maxCubesForColor.put("green", Integer.max(maxCubesForColor.get("green"),
                        cubesForColor.getOrDefault("green", 0)));
            }
            if (MAX_RED_CUBES >= maxCubesForColor.get("red") &&
                    MAX_BLUE_CUBES >= maxCubesForColor.get("blue") &&
                    MAX_GREEN_CUBES >= maxCubesForColor.get("green")) {
                sum += gameNumber;
            }
            powersum += maxCubesForColor.get("red") * maxCubesForColor.get("blue")
                    * maxCubesForColor.get("green");
            gameNumber++;
        }
        System.out.println(sum);
        System.out.println(powersum);
    }
}
