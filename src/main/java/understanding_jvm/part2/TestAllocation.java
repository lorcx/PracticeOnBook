package understanding_jvm.part2;

/**
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails
 * @Author: lx
 * @Date: Created in 2019/1/14 0014
 */
public class TestAllocation {
    private static final int _1mb = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1mb];
        allocation2 = new byte[2 * _1mb];
        allocation3 = new byte[2 * _1mb];
        allocation4 = new byte[4 * _1mb];// 触发minor gc
        /**
         * 年轻带和老年代个分配10m内存
         * eden和survivor比例是8：1   eden 8m  from 1m 1m
         * allocation1 2 3 总共6m内存存入eden区，当分配allocation4时发现内存不足虚拟机执行minor gc
         * 虚拟机发现2mb对象无法放到survivor区，通过虚拟机的担保机制提前将allocation4放到老年代
         */

    }
}
