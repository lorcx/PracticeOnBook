package dh_design_model.prototype;

/**
 * Created by dell on 2016/6/15.
 */
public class ResumeTest {
    public static void main(String[] args) {
        Resume r = new Resume();
        r.setName("张三");
        r.setPhone(123456789);
        r.setPost("高级项目经理");
        Resume r1 = null;
        Resume r2 = null;
        try {
          r1 = (Resume) r.clone();
          r1.setName("李四");
          r1.setPhone(9876543);
          r1.setPost("ceo");
          r2 = (Resume) r.clone();
          r2.setName("王五");
          r2.setPhone(876543234);
          r2.setPost("cto");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(r);
        System.out.println(r1);
        System.out.println(r2);
    }
}
