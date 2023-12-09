package aoc2023;

import aoc2023.day8.Main1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();

        try (InputStream is = Main1.class.getClassLoader().getResourceAsStream(path);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return lines;

    }
}
