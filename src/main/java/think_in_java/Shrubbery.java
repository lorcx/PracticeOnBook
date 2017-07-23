package think_in_java;

/**
 * 19章枚举
 * Shrubbery：灌木
 * 枚举不用 ;
 * Created by lx on 2015/12/19.
 */
@SuppressWarnings("all")
public enum Shrubbery {
    //地面   表面龟裂    悬挂
    GROUND,CRAWLING,HANGING
}

class EnumClass{
    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()){
//            System.out.println(s.ordinal()); //ordinal顺序  返回枚举常量的序数
//            System.out.println(s.compareTo(Shrubbery.CRAWLING) + " ");//比较
//            System.out.println(s.equals(Shrubbery.CRAWLING) + " ");
//            System.out.println((s == Shrubbery.CRAWLING) + " ");
//            System.out.println(s.getDeclaringClass());//返回与此枚举常量的枚举类型相对应的 Class 对象
//            System.out.println(s.name());//返回此枚举常量的名称

        }

        for (String s1 : "GROUND CRAWLING HANGING".split(" ")){
            System.out.println(Enum.valueOf(Shrubbery.class, s1));//返回带指定名称的指定枚举类型的枚举常量。
        }
    }
}