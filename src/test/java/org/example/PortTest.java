package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PortTest {
    @Test
    void testConvertIndexes() {
        String[] indexes = {"1,3-5", "2", "3-4"};
        List<List<Integer>> sequences = Port.convertIndexes(indexes);

        Assertions.assertEquals(3, sequences.size());
        Assertions.assertTrue(sequences.contains(List.of(1, 3, 4, 5)));
        Assertions.assertTrue(sequences.contains(List.of(2)));
        Assertions.assertTrue(sequences.contains(List.of(3, 4)));

        String[] indexes2 = {"10-12", "15,18-20", "25"};
        List<List<Integer>> sequences2 = Port.convertIndexes(indexes2);

        Assertions.assertEquals(3, sequences2.size());
        Assertions.assertTrue(sequences2.contains(List.of(10, 11, 12)));
        Assertions.assertTrue(sequences2.contains(List.of(15, 18, 19, 20)));
        Assertions.assertTrue(sequences2.contains(List.of(25)));

        String[] indexes3 = {"1", "2", "3", "4"};
        List<List<Integer>> sequences3 = Port.convertIndexes(indexes3);

        Assertions.assertEquals(4, sequences3.size());
        Assertions.assertTrue(sequences3.contains(List.of(1)));
        Assertions.assertTrue(sequences3.contains(List.of(2)));
        Assertions.assertTrue(sequences3.contains(List.of(3)));
        Assertions.assertTrue(sequences3.contains(List.of(4)));
    }

    @Test
    void testGenerateGroups() {
        List<List<Integer>> sequences = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6)
        );
        List<List<Integer>> groups = Port.generateGroups(sequences);

        Assertions.assertEquals(6, groups.size());
        Assertions.assertTrue(groups.contains(List.of(1, 4, 6)));
        Assertions.assertTrue(groups.contains(List.of(1, 5, 6)));
        Assertions.assertTrue(groups.contains(List.of(2, 4, 6)));
        Assertions.assertTrue(groups.contains(List.of(2, 5, 6)));
        Assertions.assertTrue(groups.contains(List.of(3, 4, 6)));
        Assertions.assertTrue(groups.contains(List.of(3, 5, 6)));

        List<List<Integer>> sequences2 = List.of(
                List.of(10, 11, 12),
                List.of(15, 18, 19, 20),
                List.of(25)
        );
        List<List<Integer>> groups2 = Port.generateGroups(sequences2);

        Assertions.assertEquals(12, groups2.size());
        Assertions.assertTrue(groups2.contains(List.of(10, 15, 25)));
        Assertions.assertTrue(groups2.contains(List.of(10, 18, 25)));
        Assertions.assertTrue(groups2.contains(List.of(10, 19, 25)));
        Assertions.assertTrue(groups2.contains(List.of(10, 20, 25)));

        List<List<Integer>> sequences3 = List.of(
                List.of(1),
                List.of(2),
                List.of(3),
                List.of(4)
        );
        List<List<Integer>> groups3 = Port.generateGroups(sequences3);

        Assertions.assertEquals(1, groups3.size());
        Assertions.assertTrue(groups3.contains(List.of(1, 2, 3, 4)));
    }
}