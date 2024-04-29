package Thread;

import java.util.List;

public class FindMaxValue2 extends Thread {
    private int result = Integer.MIN_VALUE;

    private List<Integer> integers;

    public FindMaxValue2(List<Integer> integers) {
        this.integers = integers;
    }

    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        List<Integer> reversedList = integers.reversed();

        for (Integer integer : reversedList.subList(0, reversedList.size() / 2 + 1)) {

            if (result < integer) {
                result = integer;
            }
        }
    }
}
