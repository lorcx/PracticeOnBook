package think_in_java;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;


/**
 * 对映射文件上锁
 * @author dell
 *
 */
public class LockMappingFiles {
	static final int LENGTH = 0x8FFFFFF;//128mb
	static FileChannel fc;
	
	public static class LockAndModify extends Thread {
		private ByteBuffer buff;
	    private int start, end;
	    LockAndModify(ByteBuffer mbb, int start, int end) {
	      this.start = start;
	      this.end = end;
	      mbb.limit(end);
	      mbb.position(start);
	      buff = mbb.slice();
	      start();
	    }
	    public void run() {
	      try {
	        FileLock fl = fc.lock(start, end, false);
	        System.out.println("Locked: "+ start +" to "+ end);
	        while(buff.position() < buff.limit() - 1)
	          buff.put((byte)(buff.get() + 1));
	        fl.release();
	        System.out.println("Released: "+start+" to "+ end);
	      } catch(Exception e) {
	        throw new RuntimeException(e);
	      }
	    }
	} 
	
	public static void main(String[] args) {
		try {
			fc = new RandomAccessFile("E:\\2.txt", "rw").getChannel();
			MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
			for (int i = 0; i < LENGTH; i++) {
				buffer.put((byte)'x');
			}
			new LockAndModify(buffer,0,0 + LENGTH /3);
			new LockAndModify(buffer,LENGTH /2,LENGTH /2 + LENGTH /4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
