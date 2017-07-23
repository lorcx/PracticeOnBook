package think_in_java;

/**
 * Signal : 信号
 * Created by lx on 2015/12/19.
 */
public enum Signal {
    GREEN,YELLOW,RED
}

//红绿灯
class TraffictLight{
    Signal color = Signal.RED;

    public void change(){
        switch(color){
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    @Override
    public String toString() {
        return color.toString();
    }

    public static void main(String[] args) {
        TraffictLight t = new TraffictLight();
        for (int i = 0;i < 7 ;i++){
            System.out.println(t);
            t.change();
        }
    }
}