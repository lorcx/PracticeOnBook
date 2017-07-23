package think_in_java;

/**
 * Created by lx on 2015/12/22.
 */
@SuppressWarnings("all")
public enum RoShamBo2 implements Competitor<RoShamBo2>{
    PAPER(Outcome.DRAW,Outcome.LOSE,Outcome.WIN),
    ROCK(Outcome.LOSE,Outcome.WIN,Outcome.DRAW),
    SISSORS(Outcome.WIN,Outcome.DRAW,Outcome.LOSE);

    private Outcome p,r,s;

    RoShamBo2(Outcome p ,Outcome s ,Outcome r) {
        this.p = p;
        this.r = r;
        this.s = s;
    }

    @Override
    public Outcome compete(RoShamBo2 competitor) {
        switch (competitor){
            default:
            case PAPER:
                return p;
            case ROCK:
                return r;
            case SISSORS:
                return s;
        }
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo2.class,20);
    }

}
