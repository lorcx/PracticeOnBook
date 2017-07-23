package think_in_java;

import java.nio.*;

/**
 * 不同的视图缓冲器
 * @author lx
 *
 */
public class ViewBuffer {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
		buffer.rewind();//回到数据的开始
		
		System.out.println("byte buffer");
		while(buffer.hasRemaining()){
			System.out.print(buffer.position() + "-->" + buffer.get() + ",");
		}
		System.out.println();
		
		System.out.println("char buffer");
		CharBuffer charBuffer = ((ByteBuffer)buffer.rewind()).asCharBuffer();
		while(charBuffer.hasRemaining()){
			System.out.print(charBuffer.position() + "-->" + charBuffer.get() + ",");
		}
		System.out.println();
		
		
		System.out.println("float buffer");
		FloatBuffer floatBuffer = ((ByteBuffer)buffer.rewind()).asFloatBuffer();
		while(floatBuffer.hasRemaining()){
			System.out.print(floatBuffer.position() + "-->" + floatBuffer.get() + ",");
		}
		System.out.println();
		
		System.out.println("int buffer");
		IntBuffer intBuffer = ((ByteBuffer)buffer.rewind()).asIntBuffer();
		while(intBuffer.hasRemaining()){
			System.out.print(intBuffer.position() + "-->" + intBuffer.get() + ",");
		}
		System.out.println();
		
		System.out.println("long buffer");
		LongBuffer longBuffer = ((ByteBuffer)buffer.rewind()).asLongBuffer();
		while(longBuffer.hasRemaining()){
			System.out.print(longBuffer.position() + "-->" + longBuffer.get() + ",");
		}
		System.out.println();
		
		System.out.println("short buffer");
		ShortBuffer shortBuffer = ((ByteBuffer)buffer.rewind()).asShortBuffer();
		while(shortBuffer.hasRemaining()){
			System.out.print(shortBuffer.position() + "-->" + shortBuffer.get() + ",");
		}
		System.out.println();
		
		System.out.println("double buffer");
		DoubleBuffer doubleBuffer = ((ByteBuffer)buffer.rewind()).asDoubleBuffer();
		while(doubleBuffer.hasRemaining()){
			System.out.print(doubleBuffer.position() + "-->" + doubleBuffer.get() + ",");
		}
		System.out.println();
	}
}
