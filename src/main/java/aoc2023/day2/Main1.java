package aoc2023.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main1 {

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
        List<String> lines = new ArrayList<>();

        try (InputStream is = aoc2023.day2.Main1.class.getClassLoader()
                .getResourceAsStream("inputDay2.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        int maxRedCubes = 12;
        int maxGreenCubes = 13;
        int maxBlueCubes = 14;
        int gameNumber = 1;

        int sum = 0;
        int powersum=0;

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
                maxCubesForColor.put("red", Integer.max(maxCubesForColor.get("red"), cubesForColor.getOrDefault("red", 0)));
                maxCubesForColor.put("blue", Integer.max(maxCubesForColor.get("blue"), cubesForColor.getOrDefault("blue", 0)));
                maxCubesForColor.put("green", Integer.max(maxCubesForColor.get("green"), cubesForColor.getOrDefault("green", 0)));

            }
            if (maxRedCubes >= maxCubesForColor.get("red") &&
                maxBlueCubes >= maxCubesForColor.get("blue") &&
                maxGreenCubes >= maxCubesForColor.get("green")) {
                sum += gameNumber;
            }
            System.out.println("turn" + maxCubesForColor.get("red") + maxCubesForColor.get("green") + maxCubesForColor.get("blue"));
            powersum += maxCubesForColor.get("red") * maxCubesForColor.get("blue") * maxCubesForColor.get("green");

            gameNumber++;
        }
        System.out.println(sum);

        System.out.println(powersum);


    }
}
