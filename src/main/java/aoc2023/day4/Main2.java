package aoc2023.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main2 {


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

        List<Integer> cards = new ArrayList<>(Collections.nCopies(lines.size(), 1));

        int sum=0;
        int numberOfCard = 1;
        for (String line : lines) {
            String[] winningNumbers;
            String[] numbers;

            line = line.substring(line.indexOf(":") + 1).trim();
            winningNumbers = line.substring(0, line.indexOf("|")).trim().split(" {1,2}");
            numbers = line.substring(line.indexOf("|") + 1).trim().split(" {1,2}");

            int pointsForOneCard = 0;

            for (String s : numbers) {
                for (String win : winningNumbers) {

                    if (win.equals(s)) {
                        pointsForOneCard++;
                    }
                }
            }

            System.out.println("Card: " + (numberOfCard));
            System.out.println("Points: " + pointsForOneCard);

            for (int x = 0; x < cards.get(numberOfCard - 1); x++) {
                for (int i = 0; i < pointsForOneCard; i++) {
                    cards.set(numberOfCard + i, ((cards.get(numberOfCard + i) + 1)));
                }
            }
            numberOfCard++;
        }
        for(int i :cards) {
            sum+=i;

        }
        System.out.println("suma: " + sum);

    }

}
