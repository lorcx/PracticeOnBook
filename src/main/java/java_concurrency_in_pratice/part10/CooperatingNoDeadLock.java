package java_concurrency_in_pratice.part10;

import java_concurrency_in_pratice.anno.ThreadSafe;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 通过开放调用（不加锁）来避免在相互协作的对象之间产生死锁
 *
 * 只对共享资源加锁，缩小锁的范围。
 * <p>
 * setLocation、getImage都会获得2个锁
 * Cooperating合作
 * reached 到达
 *
 * @Author lx
 * @Date 2018/2/11 14:57
 */
public class CooperatingNoDeadLock {
    @ThreadSafe
    class Taxi {
        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public void setLocation() {
            // 修改后不会获得taxi锁又要获得dispatcher锁
            boolean reachedDestination;
            synchronized (this) {
                this.location = location;
                reachedDestination = location.equals(destination);
            }

            if (reachedDestination) {
                dispatcher.notifyAvailable(this);
            }
        }

        public synchronized Point getDestination() {
            return destination;
        }

        public synchronized void setDestination(Point destination) {
            this.destination = destination;
        }
    }

    @ThreadSafe
    class Dispatcher {
        private final Set<Taxi> taxis;
        private final Set<Taxi> availableTaxi;

        public Dispatcher() {
            taxis = new HashSet<>();
            availableTaxi = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxi.add(taxi);
        }

        public Image getImage() {
            Set<Taxi> copy;
            synchronized (this) {
                copy = new HashSet<>(taxis);
            }

            Image image = new Image();
            for (Taxi t : copy) {
                image.drawMarker(t.getLocation());
            }
            return image;
        }
    }

    class Image {
        public void drawMarker(Point p) {

        }
    }
}
