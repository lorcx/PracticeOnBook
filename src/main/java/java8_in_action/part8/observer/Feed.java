package java8_in_action.part8.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lx
 * @Date 2018/4/7 14:57
 */
public class Feed implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver(String tweet) {
        observers.forEach(o -> o.inform(tweet));
    }
}
