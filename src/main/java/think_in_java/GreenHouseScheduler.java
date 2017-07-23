package think_in_java;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 21.7.5 scheduledExecutor的使用
 * 温度湿度控制器
 * Created by lx on 2016/4/2.
 */
public class GreenHouseScheduler {
    ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(10);
    private Random rand = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());
    private volatile boolean water = false;
    private volatile boolean light = false;
    private String thermostat = "Day";
    private Calendar lastTime = Calendar.getInstance();
    {
        lastTime.set(Calendar.MINUTE,30);
        lastTime.set(Calendar.SECOND,00);
    }

    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;


    public synchronized void setThermostat(String th){
        this.thermostat = th;
    }

    public void schedule(Runnable event,long delay){
        schedule.schedule(event,delay, TimeUnit.MICROSECONDS);
    }

    public void repeat(Runnable event,long initialDelay,long period){//period周期
        schedule.scheduleAtFixedRate(event,initialDelay,period, TimeUnit.MILLISECONDS);
    }

    class Terminate implements Runnable {

        @Override
        public void run() {
            System.out.println("Terminating");
            schedule.shutdownNow();
            new Thread(){
                @Override
                public void run() {
                    for(DataPoint d :data){
                        System.out.println(d);
                    }
                }
            };
        }
    }
    
    
    static class DataPoint{
        final Calendar calendar;
        final float temperature;
        final float humidity;

        public DataPoint(Calendar calendar, float temperature, float humidity) {
            this.calendar = calendar;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return "DataPoint{" +
                    "calendar=" + calendar +
                    ", temperature=" + temperature +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    class Bell implements Runnable {

        @Override
        public void run() {
            System.out.println("Bing!");
        }
    }

    class LightOn implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }

    class waterOff implements Runnable {

        @Override
        public void run() {
            System.out.println("turning greenhouse water off");
            water = false;
        }
    }

    class waterOn implements Runnable {

        @Override
        public void run() {
            System.out.println("turning greenhouse water off");
            water = true;
        }
    }

    class ThermostatDay implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning to Day setting");
            setThermostat("Day");
        }
    }

    class ThermostatNight implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning to night setting");
            setThermostat("Night");
        }
    }

    class CollectData implements Runnable {

        @Override
        public void run() {
            System.out.println("CollectData data");
            synchronized (GreenHouseScheduler.this){
                lastTime.set(Calendar.MINUTE,lastTime.get(Calendar.MINUTE ) + 30);
                if(rand.nextInt(5) == 4){
                    tempDirection = -tempDirection;
                }
                lastTemp = lastTemp + tempDirection * (1.0f + rand.nextFloat());
                if(rand.nextInt(5) == 4){
                    humidityDirection = -humidityDirection;
                }
                lastHumidity = lastHumidity + humidityDirection * rand.nextFloat();
                data.add(new DataPoint((Calendar)lastTime.clone(),lastTemp,lastHumidity));
            }
        }
    }

    public static void main(String[] args) {
        GreenHouseScheduler gh = new GreenHouseScheduler();
        gh.schedule(gh.new Terminate(),5000);
        gh.repeat(gh.new Bell(), 0, 1000);
        gh.repeat(gh.new ThermostatNight(),0,2000);
        gh.repeat(gh.new LightOn(),0,200);
        gh.repeat(gh.new LightOff(),0,400);
        gh.repeat(gh.new waterOff(),0,400);
        gh.repeat(gh.new waterOn(),0,600);
        gh.repeat(gh.new ThermostatDay(),0,600);
        gh.repeat(gh.new CollectData(),0,600);
    }
}
