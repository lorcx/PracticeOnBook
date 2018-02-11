package java_concurrency_in_pratice.part10;

/**
 * 加锁顺序不一致导致死锁
 * @Author lx
 * @Date 2018/2/11 10:34
 */
public class LeftRightDeadLock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomethingElse();
            }
        }
    }

    private void doSomethingElse() {

    }

    private void doSomething() {

    }

}
