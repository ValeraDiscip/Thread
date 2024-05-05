package Thread;

import java.util.List;
import java.util.concurrent.Callable;

public class MaxValueSearcher implements Callable<Integer> {
    private final List<Integer> integers;
    private final int start;
    private final int finish;

    public MaxValueSearcher(List<Integer> integers, int start, int finish) {
        this.integers = integers;
        this.start = start;
        this.finish = finish;
    }

    @Override
    public Integer call() throws Exception {
        int maxValue = Integer.MIN_VALUE;
        for (int i = start; i <= finish; i++) {
            int element = integers.get(i);
            if (maxValue < element) {
                maxValue = element;
            }
        }
        return maxValue;
    }

    @Override
    public String toString() {
        return "MaxValueSearcher{" +
                "start=" + start +
                ", finish=" + finish +
                '}';
    }
}
