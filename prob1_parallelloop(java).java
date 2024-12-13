import java.util.concurrent.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ParallelLoop {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int physicalCores = Runtime.getRuntime().availableProcessors() / 2;
        int logicalCores = Runtime.getRuntime().availableProcessors();

        System.out.println("Java detected: " + physicalCores + " physical cores.");
        System.out.println("Java detected: " + logicalCores + " logical cores.");

        Instant start = Instant.now();
        long parallelResult = parallelTask(100_000_000, logicalCores);
        Instant end = Instant.now();

        System.out.println("Parallel result: " + parallelResult);
        System.out.println("Time taken in parallel loop: " + Duration.between(start, end).toMillis() + " milliseconds");
    }

    public static long parallelTask(int n, int numberOfTasks) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);
        List<Future<Long>> futures = new ArrayList<>();
        int chunkSize = n / numberOfTasks;

        for (int i = 0; i < numberOfTasks; i++) {
            int start = i * chunkSize;
            int end = (i == numberOfTasks - 1) ? n : (i + 1) * chunkSize;
            futures.add(executor.submit(() -> parallelSubTask(start, end)));
        }

        long result = 0;
        for (Future<Long> future : futures) {
            result += future.get();
        }

        executor.shutdown();
        return result;
    }

    public static long parallelSubTask(int start, int end) {
        long result = 0;
        for (int i = start; i < end; i++) {
            result += i * i;
        }
        return result;
    }
}
