package effective_java;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2016/4/27.
 */
@SuppressWarnings("all")
public class PhoneNumber implements Comparable<PhoneNumber>{
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode,99,"区域码");
        rangeCheck(prefix,999,"前缀");
        rangeCheck(lineNumber,9999,"行号");
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }

    /**
     * 范围检查
     */
    public void rangeCheck(int arg,int max,String name){
        if(arg < 0 || arg > max){
            throw new IllegalArgumentException(name);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof PhoneNumber)){
            return false;
        }
        PhoneNumber pn = (PhoneNumber) obj;
        return pn.areaCode == this.areaCode &&
                pn.prefix == this.prefix &&
                pn.lineNumber  == this.lineNumber;
    }

//    @Override
//    public int hashCode() {
//        int result = 17;//非零初始化防止影响散列t
//        result = 31 * result + areaCode;
//        result = 31 * result + prefix;
//        result = 31 * result + lineNumber;
//        System.out.println(result);
//        return result;
//    }

    private volatile int hashCode;//如果这个类是不可变的，每次计算hahsCode会开销比较大 ，那么就将他缓存到类得内部

    @Override
    public int hashCode() {
        int result = hashCode;
        if(hashCode == 0){
            result = 31 * result + areaCode;
            result = 31 * result + prefix;
            result = 31 * result + lineNumber;
            System.out.println(result);
        }
        return result;
    }

    @Override
    protected PhoneNumber clone() throws CloneNotSupportedException {//默认是返回object类型，1.5以后加入了协变类型 所以这么写没有问题
        return (PhoneNumber) super.clone();
    }

    @Override
    public int compareTo(PhoneNumber o) {
        //此方法只要相减不溢出 就能正常工作
        int areaCodeDiff = areaCode - o.areaCode;
        //areaCodeDiff不等
        if(areaCodeDiff != 0){
            return areaCodeDiff;
        }
        int prefixDiff = prefix - o.prefix;
        //prefixDiff不等
        if(prefixDiff != 0){
            return prefixDiff;
        }
        return lineNumber - o.lineNumber;
    }
}
class PhoneNumberTest{
    public static void main(String[] args) {
        PhoneNumber pn = new PhoneNumber(10,20,30);
        PhoneNumber pn1 = new PhoneNumber(10,20,30);
        Map<PhoneNumber,String> map = new HashMap<PhoneNumber,String>();
        //没有重写hashCode
        map.put(new PhoneNumber(10,20,30),"lxxxx"); //放在一个散列桶中
        System.out.println(map.get(new PhoneNumber(10,20,30)));//返回null 取在另一个散列桶中
    }
}
