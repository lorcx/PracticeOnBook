package dh_design_model.prototype;

/**
 * 简历类
 * Created by dell on 2016/6/15.
 */
public class Resume implements Cloneable{
    private String name;//求职者姓名
    private int phone;//电话
    private String post;//职位

//    public Resume(String name, int phone, String post) {
//        this.name = name;
//        this.phone = phone;
//        this.post = post;
//    }

    @Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", post='" + post + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
