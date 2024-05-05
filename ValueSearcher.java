package Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ValueSearcher {
    private final ExecutorService executorService;
    private final int threadsCount;

    public ValueSearcher(int threadsCount) {
        executorService = Executors.newFixedThreadPool(threadsCount);
        this.threadsCount = threadsCount;
    }

    public List<Integer> findPrime(int rangeFrom, int rangeTo) {
        List<Future<List<Integer>>> tasks = createThreadFindPrime(rangeFrom, rangeTo);
        return tasks.stream()
                .flatMap(listFuture -> {
                    try {
                        return listFuture.get().stream();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    private List<Future<List<Integer>>> createThreadFindPrime(int rangeFrom, int rangeTo) {
        List<Future<List<Integer>>> futures = new ArrayList<>();
        //по моей логике ищем с (включительно) до (не включительно).
        int totalElements = rangeTo - rangeFrom;
        int elementsForThread = (int) Math.ceil((float) totalElements / threadsCount);
        int start = rangeFrom;
        int finish = start + elementsForThread;
        for (int i = 0; i < threadsCount; i++) {
            if (i != 0) {
                start += elementsForThread;
                finish += elementsForThread;
            }
            if (finish > rangeTo) {
                finish = rangeTo;
            }
            PrimeSearcher primeSearcher = new PrimeSearcher(start, finish);
            futures.add(executorService.submit(primeSearcher));
        }
        executorService.shutdown();
        return futures;
    }

    public int findMaxValue(List<Integer> list) throws Exception {
        List<Future<Integer>> tasks = createThreadFindMaxValue(list);
        return tasks.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .max(Integer::compareTo)
                .get();
    }

    private List<Future<Integer>> createThreadFindMaxValue(List<Integer> list) {
        List<Future<Integer>> futures = new ArrayList<>();
        int elementsForThread = Math.round((float) list.size() / threadsCount);
        for (int i = 0; i < threadsCount; i++) {
            int finish = i * elementsForThread - 1 + elementsForThread;
            if (finish > list.size() - 1) {
                finish = list.size() - 1;
            }
            MaxValueSearcher maxValueSearcher = new MaxValueSearcher(list, i * elementsForThread, finish);
            futures.add(executorService.submit(maxValueSearcher));
        }
        return futures;
    }
}

