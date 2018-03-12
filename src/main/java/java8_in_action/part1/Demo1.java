package java8_in_action.part1;

import java.io.File;

/**
 * @Author: lx
 * @Date: Created in 2018/3/12 0012
 */
public class Demo1 {
    public static void main(String[] args) {
        // 找出目录中所有隐藏文件
        File[] hiddenFiles = new File("c:").listFiles(File::isHidden);

        if (hiddenFiles != null) {
            for (File hiddenFile : hiddenFiles) {
                System.out.println(hiddenFile);
            }
        }


    }
}
