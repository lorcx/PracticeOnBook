package java8_in_action.part11.impl3;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 实现基本的异步任务，并添加异常处理
 * @Author lx
 * @Date 2018/10/27 14:28
 */
public class CompleteableFutrueDemo {
    private static List<Shop> shops = Arrays.asList(new Shop("BestShop"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("DShop"),
            new Shop("BBig"),
            new Shop("CShop"),
            new Shop("FAll"),
            new Shop("FAll2"),
            new Shop("3453Shop"),
            new Shop("YTAll"),
            new Shop("F2All2"),
            new Shop("37p"),
            new Shop("HTY"),
            new Shop("YTUJ"));

    /**
     * 自定义线程池
     * 为每个商店分配一个线程
     * 最大不超过100
     */
    private static final Executor executor = Executors.newFixedThreadPool(
            Math.min(shops.size(), 100), runnable -> {
                Thread t = new Thread(runnable);
                t.setDaemon(true);
                return t;
            });

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        long start = System.nanoTime();

        //System.out.println(findPrices("My favorite product"));
        //System.out.println(findPricesParallel("My favorite product"));
        System.out.println(findPricesCompletableFuture("My favorite product"));

        // 执行更多的任务
        doSomething();

        // 任务执行完毕后
        long retrievalTime = ((System.nanoTime() - start)/ 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomething() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 采用顺序方式查询所有商店
     * @param product
     * @return
     */
    private static List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 采用并行流方式查询所有商店
     * @param product
     * @return
     */
    private static List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 采用CompletableFuture方式查询所有商店
     * @param product
     * @return
     */
    private static List<String> findPricesCompletableFuture(String product) {
        List<CompletableFuture<String>> futureList = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getShopName() + " price is " + shop.getPrice(product), executor))
                .collect(Collectors.toList());

        return futureList.stream()
                .map(CompletableFuture::join)// join相当于future的get 阻塞等待所有返回结果
                .collect(Collectors.toList());
    }

}
