package web_inside_story;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by lx on 2016/4/24.
 */
public class CharToByte {
    public static void main(String[] args) {
        Charset charset = Charset.forName("UTF-8");//设置编解码字符集
        ByteBuffer byteBuffer = charset.encode("这是字符串");
        CharBuffer charBuffer = charset.decode(byteBuffer);
        System.out.println(charBuffer);
    }
}
