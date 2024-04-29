package Thread;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int result;
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 100, 6, 99, 7, 444, 88, 62, 5, 45, 3, 33, 11, 76, 9);
        FindMaxValue findMaxValue = new FindMaxValue(integers);
        FindMaxValue2 findMaxValue2 = new FindMaxValue2(integers);

        long startTime = System.nanoTime();
        findMaxValue.start();
        findMaxValue2.start();
        long finishTime = System.nanoTime() - startTime;
        System.out.println(finishTime);
        Thread.sleep(1);
        result = Math.max(findMaxValue.getResult(), findMaxValue2.getResult());
        System.out.println(result);
    }
}
