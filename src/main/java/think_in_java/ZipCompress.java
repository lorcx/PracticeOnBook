package think_in_java;

import java.io.*;
import java.util.zip.*;

/**
 * zip文件读取和写入
 * checksum用来计算和校验
 * 读取几kb文件就会内存溢出
 * Created by lx on 2015/12/13.
 */
public class ZipCompress {
    public static void main(String[] args) {
        args = new String[]{"F:\\1.doc","F:\\2.doc"};
        String zipPath = "F:\\test.zip";
        write(args,zipPath);
//        read(zipPath);

    }

    /**
     * 压缩文件
     */
    public static void write(String[] args, String zipPath){
        try {
            FileOutputStream fos = new FileOutputStream(zipPath);
            CheckedOutputStream cos = new CheckedOutputStream(fos,new Adler32());
            ZipOutputStream zos = new ZipOutputStream(cos);
            BufferedOutputStream bos = new BufferedOutputStream(zos);
            String comment = "";//CodingUtil.toUTF8("这是注释");
            System.out.println(comment);
            zos.setComment(comment);
            for(String arg : args){
                System.out.println("write file:" + arg);
                BufferedReader bis = new BufferedReader(new FileReader(arg));
                zos.putNextEntry(new ZipEntry(arg));
                int c ;
                while ((c = bis.read()) != 0 ){
                    bos.write(c);
                }
                bis.close();
                bos.flush();
            }
            bos.close();
            System.out.println("checksum:" + cos.getChecksum().getValue());//文件校验
            System.out.println("wirte file finished..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 解压文件
     * @param zipPath
     */
    public static void read(String zipPath){
        System.out.println("read file..");
        try {
            FileInputStream fis = new FileInputStream(zipPath);
            CheckedInputStream cis = new CheckedInputStream(fis,new Adler32());
            ZipInputStream zis = new ZipInputStream(cis);
            BufferedInputStream bis = new BufferedInputStream(zis);
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null){
                System.out.println("reading file.." + ze);
                int x;
                while ((x = bis.read()) != 0 ){
                    System.out.write(x);
                }
            }
            bis.close();
            zis.close();
            cis.close();
            fis.close();

//            ZipFile zf = new ZipFile(zipPath);
//            Enumeration ea = zf.entries();
//            while (ea.hasMoreElements()){
//                ZipEntry ze2 = (ZipEntry) ea.nextElement();
//                System.out.println("File : " + ze2);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
