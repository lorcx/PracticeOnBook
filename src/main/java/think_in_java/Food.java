package think_in_java;

/**
 * enum接口
 * 可以进行分类
 * 这些枚举类也是food的子类
 * 内部类
 * Created by lx on 2015/12/20.
 */
@SuppressWarnings("all")
public interface Food {
    enum Appetizer implements Food{
        SALAD,SOUP,SPRING_ROLLS
    }

    enum MainCourse implements Food{
        LASAGNE,BURRITO,PAD_THAI,LENTILS,HUMMOUS,VINDALOO
    }

    enum Dessert implements  Food{
        TIRAMISU,GELATO,BLACK_FOREST_CAKE,FRUIT,CREME_CARAMEL
    }

    enum  Coffee implements Food{
        BLACK_COOFEE,DECAF_COFFEE,ESPRESSO,LATTE,CAPPUCCION,TEA,HERB_TEA
    }
}

class TypeOfFood{
    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        food = Food.Coffee.BLACK_COOFEE;
        System.out.println(food);

    }
}