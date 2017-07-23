package think_in_java;

import java.util.Random;

/**
 * enum不能继承只能用impl
 * Created by lx on 2015/12/20.
 */
public enum CartoonCharapter implements Generator<CartoonCharapter> {
    BOB,SLAPPY,SPANKY,PUNCHY,SILLY;

    private Random rn = new Random(47);

    @Override
    public CartoonCharapter next() {
        return values()[rn.nextInt(values().length)];// values是编译器 加入到这个类的 static{}中的
    }

}


class EnumImpletion{
    public static <T> void printNext(Generator<T> rg){
        System.out.print(rg.next() + " ");
    }

    public static void main(String[] args) {
        CartoonCharapter cc = CartoonCharapter.BOB;
        for (int i = 0;i < 10;i++){
            printNext(cc);
        }

    }
}
