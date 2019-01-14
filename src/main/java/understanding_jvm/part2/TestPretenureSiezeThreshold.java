package understanding_jvm.part2;

/**
 * 大对象直接分配在老年代
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
 * @Author: lx
 * @Date: Created in 2019/1/14 0014
 */
public class TestPretenureSiezeThreshold {
    private static final int _1mb = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1;
        allocation1 = new byte[4 * _1mb];
    }

}
