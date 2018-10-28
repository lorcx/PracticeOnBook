package java8.option;


/**
 * @Author lx
 * @Date 2018/2/21 15:59
 */
public class BeforeJava8 {
    /**
     * Java 8 之前
     *
     * @param user
     */
    public void saveUser(User user) {
        if (null != user) {
//            if (null != user.getAddress()) {
//                 保存 user
//            }
        }
    }

    /**
     * 过多的退出语句
     *
     * @param user
     */
    public void saveUser2(User user) {
        if (null == user) {
            return;
        }
//        if (null == user.getAddress()) {
//            return;
//        }
        // s保存 user
    }
}
