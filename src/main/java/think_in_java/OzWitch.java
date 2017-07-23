package think_in_java;

/**
 * OzWitch : oz盎司
 *           witch女巫
 * Created by lx on 2015/12/19.
 */
public enum OzWitch {
    //如果只定义属性，没有值 就不用加 ; 有值必须加
    WEST("west description...."),
    NORTH("north desciption ..."),
    EAST("east descripion ..."),
    SOURTH("sourth descript...");

    private String description;//描述

    //构造方法必须是私有的
    private OzWitch(String desciption) {
        this.description = desciption;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        for (OzWitch s : OzWitch.values()){
            System.out.println(s.getDescription());
        }
    }
}
