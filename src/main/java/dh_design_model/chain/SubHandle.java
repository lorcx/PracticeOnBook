package dh_design_model.chain;

/**
 * 具体的处理者
 * Created by lx on 2016/6/19.
 */
public class SubHandle extends Handle{
    @Override
    public void handleReuest(int request) {
        if(request > 0 && request < 10){
            System.out.println(this.getClass().getSimpleName() + "处理请求");
        }else if(null != successor){
            //让继承者去处理请求
            successor.handleReuest(request);
        }
    }
}
