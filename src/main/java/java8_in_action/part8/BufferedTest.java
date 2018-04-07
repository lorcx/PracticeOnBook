package java8_in_action.part8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author lx
 * @Date 2018/4/7 13:41
 */
public class BufferedTest {
    public static void main(String[] args) {
        String line = processFile(br -> br.readLine());
        String twoLine = processFile(br -> br.readLine() + br.readLine());
    }

    public static String processFile(BufferedReaderProcessor p) {
        try (BufferedReader br = new BufferedReader(new FileReader("xxx"))) {
            return p.process(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }
}
