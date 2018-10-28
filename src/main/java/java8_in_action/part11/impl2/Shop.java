package java8_in_action.part11.impl2;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @Author lx
 * @Date 2018/10/27 14:29
 */
public class Shop {

    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取价格
     * @param product
     * @return
     */
    public double getPrice(String product) {
        return 0d;
    }

    /**
     * 异步获取价格
     * @param product
     * @return
     */
    public CompletableFuture<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
                //int i = 1/ 0;
                //System.out.println(i);
            } catch (Exception e) {
                //e.printStackTrace();
                // 报错会把异常抛出
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    /**
     * 使用CompletableFuture工厂方法调用异步任务
     * @param product
     * @return
     */
    public CompletableFuture<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        // 随机一个价格
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            new RuntimeException(e);
        }
    }

}
