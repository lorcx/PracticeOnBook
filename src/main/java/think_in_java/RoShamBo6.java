package think_in_java;

/**
 * RoShamBo6
 * 二维数组实现方式
 * Created by lx on 2015/12/22.
 */
@SuppressWarnings("all")
public enum RoShamBo6 implements Competitor<RoShamBo6>{

    PAPER,ROCK,SISSORS;

    private static Outcome table[][] = {
            {Outcome.DRAW,Outcome.WIN,Outcome.LOSE},
            {Outcome.LOSE,Outcome.DRAW,Outcome.WIN},
            {Outcome.WIN,Outcome.LOSE,Outcome.DRAW}
    };

    @Override
    public Outcome compete(RoShamBo6 competitor) {
        return table[this.ordinal()][competitor.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class,20);
    }
}
