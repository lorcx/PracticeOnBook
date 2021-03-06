package java_concurrency_in_pratice.part10;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 在相互协作对象之间的锁顺序死锁
 *
 * setLocation、getImage都会获得2个锁
 * Cooperating合作
 * @Author lx
 * @Date 2018/2/11 14:57
 */
public class CooperatingDeadLock {
    class Taxi {
        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public synchronized void setLocation() {
            this.location = location;
            if (location.equals(destination)) {
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

        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi t : taxis) {
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
