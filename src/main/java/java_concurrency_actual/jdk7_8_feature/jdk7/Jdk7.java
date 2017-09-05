package java_concurrency_actual.jdk7_8_feature.jdk7;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */
public class Jdk7 {

    void example() {
        /**
         * 通过字符串构造
         */
        Path p = Paths.get("F:\\apache-maven-3.3.9\\README.txt");
        File f = p.toFile();

        URI u = URI.create("file:///jdk7_8_feature/t.txt");
        Path p2 = Paths.get(u);

        /**
         * 通过FileSystem构造
         */
        Path filePath = FileSystems.getDefault().getPath("/jdk7_8_feature", "t.txt");

        /**
         * Path URI File之间构造
         */
        File f2 = new File("/jdk7_8_feature/t.txt");
        Path p1 = f2.toPath();
        f2.toURI();
        p1.toFile();

    }

    /**
     * 快速文件操作：读文件
     */
    static void exampleReadFile() {
        try {
            byte[] data = Files.readAllBytes(Paths.get("F:\\apache-maven-3.3.9\\README.txt"));
            String content = new String(data, StandardCharsets.UTF_8);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 快速文件操作：按行读文件
     */
    static void exampleReadLineFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("F:\\apache-maven-3.3.9\\README.txt"));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * jdk7 try-with-resources
     */
    static void tryWithResources() throws IOException {
        try (InputStream ins = new FileInputStream("F:\\apache-maven-3.3.9\\README.txt")) {
            char charStr = (char) ins.read();
            System.out.println(charStr);
        }
    }

    public static void main(String[] args) {
//        exampleReadFile();

//        try {
//            tryWithResources();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        exampleReadLineFile();
    }

}
