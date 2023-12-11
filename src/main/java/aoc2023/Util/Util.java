package aoc2023.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();

       // try (InputStream is = Main1.class.getClassLoader().getResourceAsStream(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
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

