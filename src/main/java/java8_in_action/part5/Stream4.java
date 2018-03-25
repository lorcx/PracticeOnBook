package java8_in_action.part5;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author lx
 * @Date 2018/3/25 11:50
 */
public class Stream4 {
    public static void main(String[] args) {
        // 由值创建流
        Stream<String> stream = Stream.of("java8", "Lambda", "in", "action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // 创建空的流
        Stream stream1 = Stream.empty();

        // 由数组创建流
        int[] arr = {1, 2, 3};
        int sum = Arrays.stream(arr).sum();
        System.out.println(sum);

        // 文件流
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("d:\\vsftpd.conf"), Charset.defaultCharset())) {
            // 内部迭代所以不用+=
            // map是个每个单词生成一个流，count求出的是行数
            // flatmap是把多个流合并成一个流
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(uniqueWords);

        // 生成无限流
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::println);

        // 生成斐波那契数列
        System.out.println("生成斐波那契数列");
        Stream.iterate(new int[]{0, 1},
                arr1 -> new int[]{arr1[1], arr1[0] + arr1[1]}
        ).limit(20)
        .map(arr1 -> arr1[0])
        .forEach(System.out::print);
    }
}
