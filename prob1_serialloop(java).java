import java.time.Duration;
import java.time.Instant;

public class SerialLoop {
    public static void main(String[] args) {
        int physicalCores = Runtime.getRuntime().availableProcessors() / 2;
        int logicalCores = Runtime.getRuntime().availableProcessors();

        System.out.println("Java detected: " + physicalCores + " physical cores.");
        System.out.println("Java detected: " + logicalCores + " logical cores.");

        Instant start = Instant.now();
        long result = serialTask(100_000_000);
        Instant end = Instant.now();

        System.out.println("Serial result: " + result);
        System.out.println("Time taken in serial loop: " + Duration.between(start, end).toMillis() + " milliseconds");
    }

    public static long serialTask(int n) {
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += i * i;
        }
        return result;
    }
}

