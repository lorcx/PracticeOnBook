package java8_in_action.part8;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Author lx
 * @Date 2018/4/7 13:40
 */
public interface BufferedReaderProcessor {
    String process(BufferedReader d)throws IOException;
}
