package think_in_java;

import java.util.EnumSet;

/**
 * 洗车 enum
 * Created by lx on 2015/12/20.
 */
@SuppressWarnings("all")
public class CarWash {
    public enum Cycle {
        UNDERBODY {
            void action() {
                System.out.println("Spraying the underbody");
            }
        },
        WHEELWASH {
            void action() {
                System.out.println("Washing the wheels");
            }
        },
        PREWASH {
            void action() {
                System.out.println("Loosening the dirt");
            }
        },
        BASIC {
            void action() {
                System.out.println("The basic wash");
            }
        },
        HOTWAX {
            void action() {
                System.out.println("Applying hot wax");
            }
        },
        RINSE {
            void action() {
                System.out.println("Rinsing");
            }
        },
        BLOWDRY {
            void action() {
                System.out.println("Blowing dry");
            }
        };

        abstract void action();
    }

    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE); //初始化一个enumset

    @Override
    public String toString() {
        return cycles.toString();
    }

    //添加
    public void add(Cycle cc){
        cycles.add(cc);
    }

    //洗车
    public void watch(){
        for (Cycle c : cycles){
            c.action();
        }
    }

    public static void main(String[] args) {
        CarWash cw = new CarWash();
        System.out.println(cw);
        cw.watch();

        System.out.println("------------------------------------");
        cw.add(Cycle.BLOWDRY);
        cw.add(Cycle.HOTWAX);
        cw.add(Cycle.UNDERBODY);
        cw.add(Cycle.RINSE);

        System.out.println(cw);
        cw.watch();
    }

}