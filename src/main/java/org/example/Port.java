package org.example;

import java.util.ArrayList;
import java.util.List;

public class Port {
    // Метод, преобразующий массив строк indexes в массив последовательностей чисел
    public static List<List<Integer>> convertIndexes(String[] indexes) {
        List<List<Integer>> result = new ArrayList<>();
        for (String index : indexes) {
            String[] ranges = index.split(",");
            List<Integer> sequence = new ArrayList<>();
            for (String range : ranges) {
                String[] nums = range.split("-");
                int start = Integer.parseInt(nums[0]);
                int end = nums.length > 1 ? Integer.parseInt(nums[1]) : start;
                for (int i = start; i <= end; i++) {
                    sequence.add(i);
                }
            }
            result.add(sequence);
        }
        return result;
    }

    // Метод, возвращающий всевозможные уникальные упорядоченные группы элементов
    public static List<List<Integer>> generateGroups(List<List<Integer>> sequences) {
        List<List<Integer>> result = new ArrayList<>();
        generateGroupsHelper(sequences, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generateGroupsHelper(List<List<Integer>> sequences, int index,
                                             List<Integer> current, List<List<Integer>> result) {
        if (index == sequences.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (Integer num : sequences.get(index)) {
            current.add(num);
            generateGroupsHelper(sequences, index + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        String[] indexes = {"1,3-5", "2", "3-4"};
        List<List<Integer>> sequences = convertIndexes(indexes);
        List<List<Integer>> groups = generateGroups(sequences);
        System.out.println(groups);
    }
}