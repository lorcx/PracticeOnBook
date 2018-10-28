package java8_in_action.part11.impl1;

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
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
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
