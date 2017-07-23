package think_in_java;

import java.nio.ByteBuffer;

/**
 * nio bytebuff获取基本数据类型
 * @author lx
 *
 */
public class GetData {
	public static final int size = 1024;
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(size);
		int i = 0;
		while(i++ < buffer.limit()){//返回此缓冲区的限制
			if(buffer.get() != 0)
				System.out.println("nonzero");
		}
		System.out.println("i=" + i);
		buffer.rewind();//返回数据的开始部分
		
		char c ;
		buffer.asCharBuffer().put("hellow !");
		while((c = buffer.getChar()) != 0){
			System.out.print(c + " ");
		}
		System.out.println();
		buffer.rewind();
		
		buffer.asShortBuffer().put((short) 471142);
		System.out.println(buffer.getShort());
		buffer.rewind();
		
		buffer.asIntBuffer().put(99471142);
		System.out.println(buffer.getInt());
		buffer.rewind();
		
		buffer.asLongBuffer().put(99471142);
		System.out.println(buffer.getLong());
		buffer.rewind();
		
		buffer.asFloatBuffer().put(99471142);
		System.out.println(buffer.getFloat());
		buffer.rewind();
		
		buffer.asDoubleBuffer().put(99471142);
		System.out.println(buffer.getDouble());
		buffer.rewind();
	}
}
