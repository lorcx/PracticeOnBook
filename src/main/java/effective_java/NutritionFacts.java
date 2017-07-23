package effective_java;

/**
 * NutritionFacts：营养标示
 * 使用builder模式代替构造器和javaBeans
 * 构造器不能对某个可选属性设置 并且随着参数增多变得不容易读
 * javaBean可能导致创建状态不一样的对象
 * Created by dell on 2016/4/4.
 */
public class NutritionFacts {
    private final int servingSize;//默认
    private final int servings;//默认
    private final int calories;//可选
    private final int fat;//可选
    private final int sodium;//可选
    private final int carbohydrate;//可选

    static class Builder{
        private final int servingSize;//默认
        private final int servings;//默认

        private int calories = 0;//可选
        private int fat = 0;//可选
        private int sodium = 0;//可选
        private int carbohydrate = 0;//可选

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories){
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat){
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium){
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate){
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts builder(){
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder){
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.carbohydrate = builder.carbohydrate;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
    }

    @Override
    public String toString() {
        return "NutritionFacts{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }
}

class NutritionFactsTest{
    public static void main(String[] args) {
        NutritionFacts nf = new NutritionFacts.Builder(10,20)
//                                .calories(30)
//                                .carbohydrate(40)
//                                .sodium(50)
                                .fat(60)
                                .builder();
        System.out.println(nf);
    }
}