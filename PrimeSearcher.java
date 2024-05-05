package Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PrimeSearcher implements Callable<List<Integer>> {
    private final int rangeFrom;
    private final int rangeTo;

    public PrimeSearcher(int rangeFrom, int rangeTo) {
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }
    @Override
    public List<Integer> call() throws Exception {
        List<Integer> primes = new ArrayList<>();
        for (int i = rangeFrom; i < rangeTo ; i++) {
            if(isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        if(number % 2 == 0) {
            return number == 2;
        }
        if(number % 3 == 0) {
            return number == 3;
        }
        for (int i = 5; i * i < number; i++) {
            if(number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}

