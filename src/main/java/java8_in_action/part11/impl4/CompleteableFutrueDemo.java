package java8_in_action.part11.impl4;

import java8_in_action.part11.impl4.ExchangeService.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 实现基本的异步任务，并添加异常处理
 *
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
        //System.out.println(findPricesInUSD("My favorite product"));
        //System.out.println(findPriceInUSDJava7("My favorite product"));
        //System.out.println(findPricesCompletableFuture("My favorite product"));

        CompletableFuture[] futures = findPricesStream("My favorite product")
                .map(f -> f.thenAccept(
                        futget -> System.out.println(futget + "(done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();

        // 执行更多的任务
        doSomething();

        // 任务执行完毕后
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
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
     *
     * @param product
     * @return
     */
    private static List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 采用并行流方式查询所有商店
     *
     * @param product
     * @return
     */
    private static List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 采用CompletableFuture方式查询所有商店
     *
     * @param product
     * @return
     */
    private static List<String> findPricesCompletableFuture(String product) {
        List<CompletableFuture<String>> futureList = shops.stream()
                // 以异步的方式获取每个商店的价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                // quote对象存在时对其进行转换
                .map(future -> future.thenApply(Quote::parse))
                // 使用另一个异步任务构造future申请折扣
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        return futureList.stream()
                // 等待流中的所有future执行完毕，并获取各自的返回值
                .map(CompletableFuture::join)// join相当于future的get 阻塞等待所有返回结果
                .collect(Collectors.toList());
    }

    /**
     * 将两个异步任务合并
     * @param product
     * @return
     */
    private static List<String> findPricesInUSD(String product) {
        List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            CompletableFuture<Double> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> shop.getPrice2(product))
                    .thenCombine(CompletableFuture.supplyAsync(() ->  ExchangeService.getRate(Money.EUR, Money.USD)),
                        (price, rate) -> price * rate  // price 为第一个异步任务返回的结果 rate为第二个异步任务返回的结果
                    );
            priceFutures.add(futurePriceInUSD);
        }

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .map(price -> "price is " + price)
                .collect(Collectors.toList());
    }

    /**
     * java7编写两个异步任务
     * @param product
     * @return
     */
    public static List<String> findPriceInUSDJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return ExchangeService.getRate(Money.EUR, Money.USD);
                }
            });

            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    double priceInEUR = shop.getPrice2(product);
                    return priceInEUR * futureRate.get();
                }
            });
            priceFutures.add(futurePriceInUSD);
        }

        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(" price is " + priceFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }


    private static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                // 以异步的方式获取每个商店的价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                // quote对象存在时对其进行转换
                .map(future -> future.thenApply(Quote::parse))
                // 使用另一个异步任务构造future申请折扣
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));

    }

}
