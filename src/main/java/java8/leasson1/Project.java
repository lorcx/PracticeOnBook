package java8.leasson1;

import lombok.Builder;
import lombok.Data;

/**
 * @Author lx
 * @Date 2018/2/19 17:05
 */
@Data
@Builder
public class Project {

    /**
     * 项目名称
     */
    private String  name;

    /**
     * 编程语言
     */
    private String  language;

    /**
     * star 数
     */
    private Integer stars;

    /**
     * 描述
     */
    private String  description;

    /**
     * 作者
     */
    private String  author;

}