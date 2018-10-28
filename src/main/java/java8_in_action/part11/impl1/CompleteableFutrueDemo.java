package java8_in_action.part11.impl1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 实现基本的异步任务
 * @Author lx
 * @Date 2018/10/27 14:28
 */
public class CompleteableFutrueDemo {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();

        CompletableFuture<Double> future = shop.getPriceAsync("My favorite product");
        // 调用返回之后
        long invocationTime = ((System.nanoTime() - start)/ 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // 执行更多的任务
        doSomething();
        try {
            // 从future中读取价格
            Double price = future.get();
            System.out.printf("Price is %.2f\n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 任务执行完毕后
        long retrievalTime = ((System.nanoTime() - start)/ 1_000_000);
        System.out.printf("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomething() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
