package Thread.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ValueBetweenRangeSearcher implements Callable<List<Integer>> {
    private final List<Integer> integers;
    private final int start;
    private final int finish;
    private final int rangeFrom;
    private final int rangeTo;

    public ValueBetweenRangeSearcher(List<Integer> integers, int start, int finish, int rangeFrom, int rangeTo) {
        this.integers = integers;
        this.start = start;
        this.finish = finish;
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }


    @Override
    public List<Integer> call() throws Exception {
        List<Integer> integersBetweenRange = new ArrayList<>();
        for (int i = start; i <= finish; i++) {
            int element = integers.get(i);
            if (element > rangeFrom && element < rangeTo) {
                integersBetweenRange.add(element);
            }
        }
        return integersBetweenRange;
    }
}

