package aoc2023.day8;

import aoc2023.Util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main1 {

    public static void main(String[] args) {
        List<String> lines = Util.readFile("src/main/resources/inputDay8.txt");
        List<Character> instructions = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (char c : lines.get(i).toCharArray()) {
                instructions.add(c);
            }
        }
        Map<String, String> RightMoves = new HashMap<>();
        Map<String, String> LeftMoves = new HashMap<>();
        for (int i = 3; i < lines.size(); i++) {
            RightMoves.put(lines.get(i).substring(0, 3).trim(),
                    lines.get(i).substring(12, 15).trim());
            LeftMoves.put(lines.get(i).substring(0, 3).trim(),
                    lines.get(i).substring(7, 10).trim());
        }
        int counter = 0;
        String current = "AAA";
        while (!Objects.equals(current, "ZZZ")) {
            for (Character c : instructions) {
                if (c == 'R') {
                    current = RightMoves.get(current);
                    counter++;
                } else {
                    current = LeftMoves.get(current);
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }


}
