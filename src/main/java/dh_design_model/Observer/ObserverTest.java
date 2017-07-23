package dh_design_model.Observer;

/**
 * Created by lx on 2016/6/18.
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject boss = new Boss();
        Observer so = new StockOberver("张山",boss);
        Observer no = new NbaOberver("历史",boss);
        boss.attach(so);
        boss.attach(no);
//        boss.remove(no);
        boss.setAction("老子回来啦");
        boss.notifyObserver();

        Subject mm = new Mm();
        Observer so1 = new StockOberver("张山",mm);
        mm.attach(so1);
        mm.setAction("老板回来了快关上!!!!!");
        mm.notifyObserver();
    }
}
