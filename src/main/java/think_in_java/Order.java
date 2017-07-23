package think_in_java;

/**
 * 饭店仿真
 * 21.8.2
 * Created by lx on 2016/4/3.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 订单
 */
public class Order {
    private static int counter = 0;
    private final int id = counter++;
    private final Customer1 customer;
    private final WaitPerson waitPerson;
    private final Food food;

    public Order(Customer1 customer, WaitPerson waitPerson, Food food) {
        this.customer = customer;
        this.waitPerson = waitPerson;
        this.food = food;
    }

    public Food item(){
        return food;
    }

    public WaitPerson getWaitPerson() {
        return waitPerson;
    }

    public Customer1 getCustomer() {
        return customer;
    }

    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", waitPerson=" + waitPerson +
                ", food=" + food +
                '}';
    }
}

/**
 * 盘子
 */
class Plate{
    private final Order order;
    private final Food food;

    public Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "order=" + order +
                ", food=" + food +
                '}';
    }
}

class Customer1 implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final WaitPerson waitPerson;
    private SynchronousQueue<Plate> palceSetting = new SynchronousQueue<Plate>();

    public Customer1(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }

    /**
     * 支付deliver
     */
    public void deliver(Plate p) throws InterruptedException {
        palceSetting.put(p);
    }

    @Override
    public void run() {
        //course 一道菜  过程
        for(Course course : Course.values()){
            Food food = course.randomSelect();
            try {
                waitPerson.placeOrder(this,food);
                System.out.println(this + " eating " + palceSetting.take());
            } catch (InterruptedException e) {
                System.out.println(this + "wating for " + course + " interrupted");
                break;
            }
        }
        System.out.println(this + "finished meal, leaving");
    }

    @Override
    public String toString() {
        return "Customer1{" +
                "id=" + id +
                '}';
    }
}

class WaitPerson implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant1 restaurant1;
    BlockingQueue<Plate> filledOrders = new LinkedBlockingDeque<Plate>();

    public WaitPerson(Restaurant1 restaurant) {
        this.restaurant1 = restaurant;
    }

    public void placeOrder(Customer1 cust,Food food){
        try {
            restaurant1.orders.put(new Order(cust,this,food));
        } catch (InterruptedException e){
            System.out.println(this + " placeOrder interrupted");
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                Plate plate = filledOrders.take();
                System.out.println(this + " received " + plate + " delivering to " + plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(this + " off duty");
    }

    @Override
    public String toString() {
        return "WaitPerson{" +
                "id=" + id +
                '}';
    }
}

class Chef1 implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant1 restaurant1;
    private Random rand = new Random(47);

    public Chef1(Restaurant1 restaurant1) {
        this.restaurant1 = restaurant1;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                Order order = restaurant1.orders.take();
                Food requestedItem = order.item();
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                Plate plate = new Plate(order,requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (Exception e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    @Override
    public String toString() {
        return "chef " + id;
    }
}

class Restaurant1 implements Runnable {
    private List<WaitPerson> waitPersons = new ArrayList<WaitPerson>();
    private List<Chef1> chefs= new ArrayList<Chef1>();
    private ExecutorService exec;
    private Random rand = new Random(47);
    BlockingQueue<Order> orders = new LinkedBlockingDeque<Order>();

    public Restaurant1(ExecutorService e, int nWaitPerson,int nchefs){
        exec = e;
        for(int i = 0;i < nWaitPerson;i++){
            WaitPerson waitPerson = new WaitPerson(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }
        for(int i = 0;i < nchefs;i++){
            Chef1 chef1 = new Chef1(this);
            chefs.add(chef1);
            exec.execute(chef1);
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                 WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
                 Customer1 c = new Customer1(wp);
                exec.execute(c);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (Exception e) {
            System.out.println("Restaurant1  interrupted");
        }
        System.out.println("Restaurant1 closing");
    }
}

class RestaurantWithQueues{
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Restaurant1 restaurant1 = new Restaurant1(exec,5,2);
        exec.execute(restaurant1);
        if(args.length > 0){
            TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        }else{
            System.out.println("Press 'ENTER' to quiet");
            System.in.read();
        }
        exec.shutdownNow();
    }
}