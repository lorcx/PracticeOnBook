package think_in_java;

/**
 * Created by lx on 2016/2/13.
 */
@SuppressWarnings("unused")
public class SerialNumberGenerator {
    private static volatile int serialNumber;
    public static int nextSerialNumber(){
        return serialNumber++;
    }

}
