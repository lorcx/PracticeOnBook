package java8.option;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lx
 * @Date 2018/2/21 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    /**
     * 街道
     */
    private String street;

    /**
     * 门牌
     */
    private String door;

}