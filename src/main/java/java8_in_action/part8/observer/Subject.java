package java8_in_action.part8.observer;

/**
 * @Author lx
 * @Date 2018/4/7 14:56
 */
public interface Subject {
    void registerObserver(Observer o);
    void notifyObserver(String tweet);
}
