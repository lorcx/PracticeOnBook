package think_in_java;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;


/**
 * 测试 旧io和新io之间的效率
 * 模板模式
 * @author lx
 * duration持续
 *
 */
@SuppressWarnings("all")
public class MappedIo {

	private static int numOfInts = 4000000;
	private static int numOfUbuffInts = 200000;
	
	public abstract static class Tests{
		public String name;
		public Tests(String msg) {
			this.name = msg;
		}
		/**
		 * 运行测试
		 */
		public void runTest(){
			System.out.print(name + ":");
			try {
				long startTime = System.nanoTime();
				test();
				double duration = System.nanoTime() - startTime;
				System.out.format("%.2f\n", duration/1.0e9);//1.0e9 1.0 * 10的9次方
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * 测试方法
		 * @throws Exception
		 */
		public abstract void test()throws Exception;
	}
	
	public static Tests[] tests = {
		new Tests("stream write") {
			@Override
			public void test() throws Exception {
				 DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("2.tmp"))));
				 for (int i = 0; i < numOfInts; i++) 
					out.writeInt(i);    //writeInt
				out.close();
			}
		},
		new Tests("mapped write") {
			@Override
			public void test() throws Exception {
				 FileChannel fc = new RandomAccessFile(new File("2.tmp"), "rw").getChannel();
		         IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
		         for(int i = 0; i < numOfInts; i++)
			          ib.put(i);
		         fc.close();	 
			}
		},
		new Tests("stream read") {
			@Override
			public void test() throws Exception {
				 DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("2.tmp"))));
				 for (int i = 0; i < numOfInts; i++) 
					 in.readInt();
				 in.close();		 
			}
		},	
		new Tests("mapped read") {
			@Override
			public void test() throws Exception {
				 FileChannel fc = new FileInputStream(new File("2.tmp")).getChannel();
				 IntBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size()).asIntBuffer();
				 while(buffer.hasRemaining())
					 buffer.get();
				 fc.close();		 
			}
		},	
		new Tests("Stream Read/Write") {
		      public void test() throws IOException {
		        RandomAccessFile raf = new RandomAccessFile(
		          new File("temp.tmp"), "rw");
		        raf.writeInt(1);
		        for(int i = 0; i < numOfUbuffInts; i++) {
		          raf.seek(raf.length() - 4);
		          raf.writeInt(raf.readInt());
		        }
		        raf.close();
		      }
		    },
		new Tests("mapped read/write") {
			@Override
			public void test() throws Exception {
				 FileChannel fc = new RandomAccessFile(new File("2.tmp"),"rw").getChannel();
				 IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
				 ib.put(0);
				 for (int i = 1; i < numOfUbuffInts; i++) /// i = 1
					 ib.put(ib.get(i - 1));
				 fc.close();
			}
		}
	};
	
	public static void main(String[] args) {
		for (Tests test : tests) {
			test.runTest();
		}
	}
}
