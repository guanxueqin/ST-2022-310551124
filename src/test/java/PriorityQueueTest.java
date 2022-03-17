import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    @ParameterizedTest(name="#{index} - Test with Argument={0}, {1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new java.util.PriorityQueue<Integer>();
        int[] result = new int[random_array.length];
        int index = 0;
        for (index = 0;index < random_array.length;index++) {
            test.add(random_array[index]);
        }
        for (index = 0;index < random_array.length;index++) {
            result[index] = test.poll();
        }
        assertArrayEquals(correct_array, result);
    }

    public static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[] {2, 4, 3}, new int[] {2, 3, 4}),
                Arguments.of(new int[] {8, 7, 8, 7, 8}, new int[] {7, 7, 8, 8, 8}),
                Arguments.of(new int[] {-5, -4, -3, -2, -1}, new int[]{-5, -4, -3, -2, -1}),
                Arguments.of(new int[] {7, 9, 5, 8, -1}, new int[] {-1, 5, 7, 8, 9}),
                Arguments.of(new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE}, new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE})
        );

        /* Failure version to test CI */
        // return Stream.of(
        //         Arguments.of(new int[] {2, 4, 3}, new int[] {3, 3, 4}),
        //         Arguments.of(new int[] {4, 6, 2, 0}, new int[] {1, 2, 4, 6}),
        //         Arguments.of(new int[] {-8, 6, 0, -4}, new int[] {-7, -4, 0, 6}),
        //         Arguments.of(new int[] {7, 9, 5, 8, -1}, new int[] {0, 5, 7, 8, 9}),
        //         Arguments.of(new int[] {3, 6, 9, -2}, new int[] {-1, 3, 6, 9})
        // );
    }

    @Test
    public void PriorityQueue_InitialCapacity_IllegalArgumentExceptionTest() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            java.util.PriorityQueue<Integer> test_PQ = new java.util.PriorityQueue<>(Integer.MIN_VALUE);
        });
    }

    @Test
    public void addNullTest_NullPointerException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new PriorityQueue().add(null);
        });
    }

    @Test
    public void forEachTest_NullPointerException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new PriorityQueue().forEach(null);
        });
    }
}