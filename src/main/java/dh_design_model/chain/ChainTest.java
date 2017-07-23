package dh_design_model.chain;

/**
 * Created by lx on 2016/6/19.
 */
public class ChainTest {
    public static void main(String[] args) {
        Handle h = new SubHandle();
        Handle h2 = new Sub2Handle();
        Handle h3 = new Sub3Handle();
        h.setSuccessor(h2);
        h2.setSuccessor(h3);
        int[] resuests = {2,3,13,7,28,45,12,21};
        for(int request : resuests){
            h.handleReuest(request);
        }
    }
}
