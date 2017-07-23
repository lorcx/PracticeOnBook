package think_in_java;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 压缩和解压缩
 */
public class GZIPcompress {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("失败");
            System.exit(1);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            BufferedOutputStream bos = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("E:\\test.gz")));
            System.out.println("压缩文件");
            int c;
            while ((c = br.read()) != -1){
                bos.write(c);
            }
            bos.close();
            br.close();

            System.out.println("解压文件");
            BufferedReader br1 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("E:\\test.gz"))));
            String s;
            while ((s = br1.readLine()) != null){
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
