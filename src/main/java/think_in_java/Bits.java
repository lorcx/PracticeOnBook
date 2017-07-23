package think_in_java;

import java.util.BitSet;
import java.util.Random;

/**
 * bitset 高性能存储，只比数组慢一点
 * @author lx
 *
 */
public class Bits {
	
	public static void printBitSet(BitSet bs){
		System.out.println("bs:" + bs);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bs.size(); i++) {
			sb.append(bs.get(i) ? 1 : 0);
			System.out.println("sb:" + sb);
		}
	}

	public static void main(String[] args) {
		Random rn = new Random(47);
		byte bt = (byte) rn.nextInt();
		BitSet bs = new BitSet();
		for (int i = 7; i >= 0; i--) {
			if(((1 << i) & bt) != 0)
				bs.set(i);
			else
				bs.clear(i);
		}
		System.out.println(bt);
		printBitSet(bs);
		
		short st = (short) rn.nextInt();
		for (int i = 15; i >= 0; i--) {
			if(((1 << i) & st) != 0)
				bs.set(i);
			else
				bs.clear(i);
		}
		System.out.println(st);
		printBitSet(bs);
		
		int it = rn.nextInt();
		for (int i = 31; i >= 0; i--) {
			if(((1 << i) & it) != 0)
				bs.set(i);
			else
				bs.clear(i);
		}
		System.out.println(it);
		printBitSet(bs);
		
		BitSet b127 = new BitSet();
		b127.set(127);
		System.out.println(b127);
		BitSet b255 = new BitSet(65);
		b255.set(255);
		BitSet b1023 = new BitSet(512);
		b1023.set(1023);
		b1023.set(1024);
		System.out.println(b1023);
		
		
		
	}
}
