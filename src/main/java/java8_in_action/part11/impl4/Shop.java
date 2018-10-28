package java8_in_action.part11.impl4;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @Author lx
 * @Date 2018/10/27 14:29
 */
public class Shop {

    private String shopName;
    private Random random;

    public Shop(String shopName) {
        this.shopName = shopName;
        this.random = new Random(shopName.charAt(0) * shopName.charAt(1) * shopName.charAt(2));
    }

    /**
     * 获取价格和折扣
     * @param product
     * @return
     */
    public double getPrice2(String product) {
        double price = calculatePrice(product);
        return price;
        //Discount.Code code = Discount.Code.values()[Discount.Code.values().length - 1];
        //return String.format("%s:%.2f:%s", shopName, price, code);
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[Discount.Code.values().length - 1];
        return String.format("%s:%.2f:%s", shopName, price, code);
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
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public void delay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            new RuntimeException(e);
        }
    }

    public String getShopName() {
        return shopName;
    }
}
