package Thread;

import java.util.Arrays;
import java.util.List;

public class FindValues extends Thread {
    static List<Integer> integers = Arrays.asList(1, 3, 5, 4, 2, 81, 77, 33, 2, 4, 12, 33, 87, 22);
    static int start = 1;
    static int finish = 40;

    public static void main(String[] args) throws InterruptedException {
        Thread findMax = new Thread(() -> {
            int result = Integer.MIN_VALUE;

            for (Integer integer : integers) {
                if (result < integer) {
                    result = integer;
                }
            }
            System.out.println("Максимальное значение :" + result);
        });
        long startTime = System.currentTimeMillis();
        findMax.start();
        Thread.sleep(1000);
        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println(finishTime);

        Thread findInRange = new Thread(() -> {
            List<Integer> result = integers.stream()
                    .filter(count -> count >= start && count <= finish)
                    .toList();
            System.out.println("Значения в диапазоне от " + start + " до " + finish + " :" + result);
        });
        findInRange.start();
    }
}
