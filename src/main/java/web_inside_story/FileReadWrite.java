package web_inside_story;

import java.io.*;

/**
 * 简单的文件读写
 * Created by lx on 2016/4/24.
 */
public class FileReadWrite {
    public static void main(String[] args) throws Exception{
        String filePath = "F:/1.txt";
        String charSet = "UTF-8";
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos,charSet);
        try {
            osw.write("这是一段文本");
        } finally {
            osw.close();
            fos.close();
        }


        FileInputStream inputStream = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(inputStream,charSet);
        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[64];
        int count = 0;
        try {
            while((count = reader.read(buf)) != -1){
                buffer.append(buf,0,count);
            }
        } finally {
            reader.close();
            inputStream.close();
        }
        System.out.println(buffer);
    }
}
