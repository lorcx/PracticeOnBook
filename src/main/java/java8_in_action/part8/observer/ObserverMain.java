package java8_in_action.part8.observer;

/**
 * @Author lx
 * @Date 2018/4/7 14:58
 */
public class ObserverMain {
    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });

        f.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London... " + tweet);
            }
        });

        f.notifyObserver("Money money money,give me meoney!");
    }
}
