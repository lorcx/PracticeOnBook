package think_in_java;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * nio交换相邻字符
 * 
 * @author lx
 *
 */
public class UsingBuffers {

	private static void smyyetricScramble(CharBuffer charBuffer) {
		while(charBuffer.hasRemaining()){//告知在当前位置和限制之间是否有元素
			charBuffer.mark();// 设置标记   他的修改只在position -->mark之间  ，所以只修改了前2个字符
			char c1 = charBuffer.get();
			char c2 = charBuffer.get();
			charBuffer.reset();//重置为以前标记的位置
			charBuffer.put(c2).put(c1);
		}
	}	
	
	public static void main(String[] args) {
		char[] data = "UsingBuffers".toCharArray();
		ByteBuffer bb = ByteBuffer.allocate(data.length * 2);//缓冲区大小
		CharBuffer charBuffer = bb.asCharBuffer();
		charBuffer.put(data);
		System.out.println(charBuffer.rewind());
		smyyetricScramble(charBuffer);
		System.out.println(charBuffer.rewind());
		smyyetricScramble(charBuffer);
		System.out.println(charBuffer.rewind());//rewind相当于将指针设置到开始的位置
		smyyetricScramble(charBuffer);
	}

	
}
