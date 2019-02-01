package understanding_jvm.part2;

/**
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:MaxTenuringThreshold=1 -XX:+UseSerialGC
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:MaxTenuringThreshold=15 -XX:+UseSerialGC
 * 默认SurvivorRatio=8
 * eden : survivor = 8 : 2 = (8m) : (1m : 1m)
 * 测试晋升老年代阈值
 * 如果想同年龄的对象占用总和超过了servivor的一半，年龄大于等于该年龄的对象直接进入老年代
 * @Author: lx
 * @Date: Created in 2019/1/14 0014
 */
public class TestTenuringThreshold2 {
    private static final int _1mb = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1mb / 4];
        allocation2 = new byte[_1mb / 4]; //超过了survivo的一半
        allocation3 = new byte[4 * _1mb];
        allocation4 = new byte[4 * _1mb];
        allocation4 = null;
        allocation4 = new byte[4 * _1mb];


    }
}
