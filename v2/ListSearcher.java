package Thread.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ListSearcher {
    private final ExecutorService executorService;
    private final int threadsCount;

    public ListSearcher(int threadsCount) {
        executorService = Executors.newFixedThreadPool(threadsCount);
        this.threadsCount = threadsCount;
    }


    public List<Integer> findValueBetweenRange(List<Integer> list, int rangeFrom, int rangeTo) {
        List<Future<List<Integer>>> tasks = createAndRunTasks2(list,rangeFrom,rangeTo);
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


    private List<Future<List<Integer>>> createAndRunTasks2(List<Integer> list, int rangeFrom, int rangeTo) {
        List<Future<List<Integer>>> futures = new ArrayList<>();
        int elementsForThread = (int) Math.ceil((double) list.size() / threadsCount);
        for (int i = 0; i < threadsCount; i++) {
            int finish = i * elementsForThread - 1 + elementsForThread;
            if (finish > list.size() - 1) {
                finish = list.size() - 1;
            }
            ValueBetweenRangeSearcher valueBetweenRangeSearcher = new ValueBetweenRangeSearcher(list, i * elementsForThread, finish, rangeFrom, rangeTo);
            futures.add(executorService.submit(valueBetweenRangeSearcher));
        }
        executorService.shutdown();
        return futures;
    }


    public int findMaxValue(List<Integer> list) throws Exception {
        List<Future<Integer>> tasks = createAndRunTasks(list);
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

    private List<Future<Integer>> createAndRunTasks(List<Integer> list) {
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

