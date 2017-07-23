package think_in_java;

import java.util.EnumSet;

/**
 * Created by lx on 2015/12/20.
 */
public enum AlarmPoints {
    STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3,
    OFFICE4, BATHROOM, UTILITY, KITCHEN
}

//EnumSets 可以增删改查
class EnumSets{
    public static void main(String[] args) {
        EnumSet<AlarmPoints> es = EnumSet.noneOf(AlarmPoints.class);//empty空的
//        System.out.println(es);

        es.add(AlarmPoints.BATHROOM);
//        System.out.println(es);

        es.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));//of 创建一个包含枚举的set  addAll添加一个enum的set
//        System.out.println(es);

        es = es.allOf(AlarmPoints.class);//添加所有
//        System.out.println(es);

        es.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
//        System.out.println(es);

        es = es.complementOf(es);//创建一个其元素类型与指定枚举 set 相同的枚举 set
        System.out.println(es);
    }
}