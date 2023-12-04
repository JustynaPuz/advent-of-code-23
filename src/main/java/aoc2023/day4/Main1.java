package aoc2023.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main1 {

    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();

        try (InputStream is = aoc2023.day4.Main1.class.getClassLoader()
                .getResourceAsStream("inputDay4.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        int sum=0;
        for (String line : lines) {
            String[] winningNumbers;
            String[] numbers;

            line = line.substring(line.indexOf(":") + 1).trim();
            winningNumbers = line.substring(0, line.indexOf("|")).trim().split(" {1,2}");
            numbers = line.substring(line.indexOf("|") + 1).trim().split(" {1,2}");

            int pointsForOneCard=0;
            for(String s : numbers) {
                for (String win : winningNumbers) {

                    if(win.equals(s)){
                        pointsForOneCard++;
                    }

                }

            }
            System.out.println("Point: " +pointsForOneCard);
          if(pointsForOneCard > 1 ) {
              pointsForOneCard = (int)Math.pow(2,pointsForOneCard-1);
          }
            System.out.println("Point: " +pointsForOneCard);
           sum+=pointsForOneCard;
            System.out.println("SUM: " + sum);
        }
        System.out.println(sum);
    }
}
