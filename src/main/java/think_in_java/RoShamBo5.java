package think_in_java;

import java.util.EnumMap;

/**
 * 使用enumMap实现（真正的）多路分发
 *
 * 数据结构
 * map< xx1 , map< xx2 , yy > >
 *   key代表行 value的key代表列
 *
 *   比如：initRow(PAPER, Outcome.DRAW, Outcome.WIN, Outcome.LOSE); 用 a b c代替 其中的三个参数
 *        xx2    xx2    xx2
 *   --------------------------------
 *   xx1 | a      a      a               (结果是自己定义的)
 *   xx1 | b      b      b
 *   xx1 | c      c      c
 *   --------------------------------
 * Created by lx on 2015/12/22.
 */
@SuppressWarnings("all")
public enum  RoShamBo5 implements Competitor<RoShamBo5>{

    PAPER,ROCK,SISSORS;

    private static EnumMap<RoShamBo5,EnumMap<RoShamBo5,Outcome>> table = new EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>>(RoShamBo5.class);

    static {
        for (RoShamBo5 rb : RoShamBo5.values()){
            table.put(rb,new EnumMap<RoShamBo5, Outcome>(RoShamBo5.class));
        }
        initRow(PAPER, Outcome.DRAW, Outcome.WIN, Outcome.LOSE); //对比的结果 按照RoShamBo5定义的顺序 p r s vs p r s
        initRow(ROCK,Outcome.LOSE,Outcome.DRAW,Outcome.WIN);
        initRow(SISSORS,Outcome.WIN,Outcome.LOSE,Outcome.DRAW);
    }

    /**
     * 初始化行
     */
    private static void initRow(RoShamBo5 rb,Outcome op,Outcome or,Outcome os){
        EnumMap<RoShamBo5,Outcome> row = RoShamBo5.table.get(rb);//value : new EnumMap<RoShamBo5, Outcome>(RoShamBo5.class)
        row.put(rb.PAPER,op);
        row.put(rb.ROCK,or);
        row.put(rb.SISSORS,os);
    }

    @Override
    public Outcome compete(RoShamBo5 it) {
        return table.get(this).get(it);
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo5.class,20);
    }
}
