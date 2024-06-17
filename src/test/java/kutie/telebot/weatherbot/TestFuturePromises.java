package kutie.telebot.weatherbot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;

public class TestFuturePromises {

    /*
    1. Порахуй, скільки разів викликається getFibonacci(n)
    2. Напиши алгоритм, який буде рахувати це швидше (підказка: цикл)
     */

    /*
    n 0  1  2  3  4  5  6   7
    v 1, 1, 2, 3, 5, 8, 13, 21, ...
     */
    static long getFibonacci(int n) {
        if (n == 1 || n == 0){
            return 1;
        }
        return getFibonacci(n-1) + getFibonacci(n-2);
    }

    public static void main(String[] args) throws Exception {
        promise();
    }

    static void future() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Long> futureResult = executorService.submit(() -> getFibonacci(45));

        while (!futureResult.isDone()) {
            System.out.println("Future task is still in progress...");
            Thread.sleep(500);
        }

        Long resultFromFuture = futureResult.get();
        System.out.println("Future Result: " + resultFromFuture);

        executorService.shutdown();
        executorService.close();
    }

    static void promise() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<Long> completableFutureResult = CompletableFuture
                .supplyAsync(
                        () -> getFibonacci(45),
                        executorService
                );

        completableFutureResult.thenAccept(
                result -> System.out.println("45-th fibonacci number is " + result))
                .exceptionally(throwable -> {
                    System.err.println("Error occurred: " + throwable.getMessage());
                    return null;
                });

        for (int i = 0; i < 9; i++) {
            Thread.sleep(500);
            System.out.println("Doing other tasks...");
        }

        executorService.shutdown();
        executorService.close();
    }
}
