package think_in_java;

/**
 *  枚举的枚举
 *  生成菜单
 * Created by lx on 2015/12/20.
 */
public enum  Course {
    APPRETIZER(Food.Appetizer.class),//开胃菜
    MAINCOURSE(Food.MainCourse.class),//主菜
    DESSERT(Food.Dessert.class),//甜点
    COFFEE(Food.Coffee.class);//咖啡


    private Food[] values;
    private Course(Class<? extends Food> kind) {
        this.values = kind.getEnumConstants();
    }

    //随机选择菜单
    public  Food randomSelect(){
        return Enums.random(values);
    }
}

//一顿饭
class Meal{
    public static void main(String[] args) {
        for(int i = 0;i < 5;i++){
            for(Course f : Course.values()){
                Food f1 = f.randomSelect();
                System.out.println(f1);
            }
            System.out.println("-----------");
        }
    }
}