package think_in_java;

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 21.8仿真
 * 银行 接纳员 和 顾客
 * 两个主要线程
 *   顾客生成器和银行经理（工作队列和休假队列）
 *   共享的资源顾客队列
 *
 *   他们都是循环 在没有被中断的情况下
 * Created by lx on 2016/4/3.
 */

/**
 * 顾客
 */
public class Customer {
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public String toString() {
        return "[" + serviceTime + "]";
    }
}

/**
 * 顾客队列 共享资源
 */
class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int maxLineSize) {
        super(maxLineSize);
    }

    @Override
    public String toString() {
        if(this.size() == 0){
            return "[空]";
        }
        StringBuilder result = new StringBuilder();
        for(Customer Customer : this){
            result.append(Customer);
        }
        return result.toString();
    }
}

/**
 * 顾客生成器
 */
class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random rand = new Random(47);

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    /**
     * 仿真生成顾客
     */
    public void run() {
        try {
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                customers.put(new Customer(rand.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 接纳员
 */
class Teller implements Runnable,Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++;
    private int customersServed = 0;//服务人数
    private CustomerLine customers;//客户队列
    private boolean servingCustomerLine = true;//服务状态 正在服务或没在

    public Teller(CustomerLine customers) {
        this.customers = customers;
    }

    /**
     * 用来确定优先级队列头部输出 工作量最小的行员
     * @param o
     * @return
     */
    public synchronized int compareTo(Teller o) {
        return customersServed < o.customersServed ? -1 :
                (customersServed == o.customersServed ? 0 :1);
    }

    /**
     * 对每一个队列中的顾客服务
     * 并计算服务的个数
     * 如果没有顾客则等待
     */
    public void run() {
        try {
            while(!Thread.interrupted()){
               Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this){
                    customersServed++;
                    while(!servingCustomerLine){//没在工作
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + "terminating");
        }
    }

    /**
     * 做别的事情 例如请假了
     */
    public synchronized void doSomethingElse(){
       customersServed = 0;
        servingCustomerLine = false;
    }

    /**
     * 回来工作
     */
    public synchronized void serveCustomerLine(){
        assert !servingCustomerLine:"already serving: " + this;
        servingCustomerLine = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller" + id + " ";
    }

    public String shortString(){
        return "T" + id;
    }

}

/**
 * 经理
 */
class TellerManager implements Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    /**
     * PriorityQueue
     * 一个基于优先级堆的无界优先级队列。优先级队列的元素按照其自然顺序进行排序，
     * 或者根据构造队列时提供的 Comparator 进行排序，具体取决于所使用的构造方法。
     * 优先级队列不允许使用 null 元素。
     * 依靠自然顺序的优先级队列还不允许插入不可比较的对象（这样做可能导致 ClassCastException）。
     */
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<Teller>();
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();
    private int adjustmentPeriod;//调整优先级
    private static Random rand = new Random(47);

    public TellerManager(ExecutorService e,CustomerLine customers,int adjustmentPeriod){
         this.customers = customers;
         exec = e;
         this.adjustmentPeriod = adjustmentPeriod;
         Teller teller = new Teller(customers);
         exec.execute(teller);
         workingTellers.add(teller);
    }

    /**
     * 调整接纳员个数
     */
    public void adjustTellerNumber(){
        if(customers.size() / workingTellers.size() > 2){//顾客比接纳员多
            if(tellersDoingOtherThings.size() > 0){//有正在干别的事情的接纳员
                Teller teller = tellersDoingOtherThings.remove();//让它回来干活
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            //人手不够了在招一个
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }

        if(workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2){
            if(customers.size() == 0){//没顾客了
                while(workingTellers.size() > 1){//至少有一个接纳员在工作
                    ressignOneTeller();//重新分配 让‘最早干过的接纳员放假
                }
            }
        }

    }

    /**
     * 重新分配一个接纳员
     */
    private void ressignOneTeller(){
        Teller teller = workingTellers.poll();//优先级队列能自动将工作量最小的推向前台
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }

    /**
     * 经理：
     *  调整优先级，调整接纳员人数，并打印目前存在的接纳员
     */
    public void run() {
        try {
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.println(customers + " { ");
                for(Teller t : workingTellers){
                    System.out.println(t.shortString() + " ");
                }
                System.out.println(" } ");
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    @Override
    public String toString() {
        return "TellerManager";
    }
}

/**
 * 测试
 * 银行行员仿真
 * Simulation（仿真）
 */
class BankTellerSimulation{
    static final int MAX_LINE_SIZE = 50;//顾客最大队列数
    static final int ADJUSTMENT_PERIOD = 1000;//调整的优先级

    public static void main(String[] args) throws InterruptedException, IOException {
        ExecutorService exec = Executors.newCachedThreadPool();
        //创建顾客队列
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        //模拟生成顾客
        exec.execute(new CustomerGenerator(customers));
        //经理生成行员
        exec.execute(new TellerManager(exec,customers,ADJUSTMENT_PERIOD));
        if(args.length > 0){
            TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        }else{
            System.out.println("Press 'ENTER' to quiet");
            System.in.read();
        }
        exec.shutdownNow();
    }
}