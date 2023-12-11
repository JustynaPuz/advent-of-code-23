package aoc2023.day9;

import aoc2023.Util.Util;
import java.util.ArrayList;
import java.util.List;

public class Task1 {

    private static boolean containsListWithOnlyZeros(List<List<Integer>> listOfLists) {
        for (List<Integer> innerList : listOfLists) {
            if (innerList.stream().allMatch(num -> num == 0)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> lines = Util.readFile("src/main/resources/inputDay9.txt");

        int sum = 0;
        for (String line : lines) {
            ArrayList<List<Integer>> difference = new ArrayList<>();

            String[] split = line.split(" ");
            List<Integer> numbers = new ArrayList<>();

            for (String s : split) {
                numbers.add(Integer.parseInt(s));
            }
            difference.add(numbers);

            int index = 0;
            while (!containsListWithOnlyZeros(difference)) {

                List<Integer> differenceForLine = new ArrayList<>();
                for (int i = 0; i < difference.get(index).size() - 1; i++) {
                    differenceForLine.add(
                            difference.get(index).get(i + 1) - difference.get(index).get(i));
                }
                difference.add(differenceForLine);
                index++;
            }
            difference.get(index).add(0);
            for (int i = index - 1; i >= 0; i--) {
                difference.get(i).add(difference.get(i).get(difference.get(i).size() - 1)
                        + difference.get(i + 1).get(difference.get(i + 1).size() - 1));
            }
            sum += difference.get(0).get(difference.get(0).size() - 1);

        }
        System.out.println(sum);

    }
}
