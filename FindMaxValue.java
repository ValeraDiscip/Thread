package Thread;

import java.util.List;

public class FindMaxValue extends Thread {
    private int result = Integer.MIN_VALUE;

    private List<Integer> integers;

    public FindMaxValue(List<Integer> integers) {
        this.integers = integers;
    }

    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    public List<Integer> getIntegers() {
        return this.integers;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        for (Integer integer : integers.subList(0, integers.size() / 2)) {

            if (result < integer) {
                result = integer;
            }
        }
    }
}
