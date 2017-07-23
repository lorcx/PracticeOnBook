package think_in_java;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Endians字节顺序优先级
 * @author lx
 *
 */
public class Endians {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[12]);
		buffer.asCharBuffer().put("abcdf");
		System.out.println(Arrays.toString(buffer.array()));
		buffer.rewind();
		buffer.order(ByteOrder.BIG_ENDIAN);//高优先级
		buffer.asCharBuffer().put("abcdf");
		System.out.println(Arrays.toString(buffer.array()));
		buffer.rewind();
		buffer.order(ByteOrder.LITTLE_ENDIAN);//低优先级
		buffer.asCharBuffer().put("abcdf");
		System.out.println(Arrays.toString(buffer.array()));
	}
}
