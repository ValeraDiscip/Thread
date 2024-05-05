package Thread;

public class Main {

    public static void main(String[] args) throws Exception {
        ValueSearcher listSearcher = new ValueSearcher(14);
        long start = System.nanoTime();
        System.out.println(listSearcher.findPrime(100, 10_000_000));
        long finish = System.nanoTime();
        System.out.println(finish - start);


//        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 45);
//        System.out.println("filled");
//        ListSearcher listSearcher = new ListSearcher(12);
//
//        long start = System.nanoTime();
//
//        System.out.println(listSearcher.findMaxValue(integers));
//        long finish = System.nanoTime();
//        System.out.println(finish - start);
//
//
//        start = System.nanoTime();
//        int maxValue = Integer.MIN_VALUE;
//        for (int i = 0; i < integers.size(); i++) {
//            int element = integers.get(i);
//            if (maxValue < element) {
//                maxValue = element;
//            }
//        }
//        finish = System.nanoTime();
//        System.out.println(finish - start);
    }
}
