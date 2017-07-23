package think_in_java;

import java.util.*;

/**
 * 优先级队列
 * @author dell
 *
 */
public class PriorityQueueDemo {
	public static void main(String[] args) {
		PriorityQueue pq = new PriorityQueue();
		for (int i = 0; i < 10; i++) {
			pq.offer(new Random().nextInt(i+10));
		}
		 PrintQueue(pq);
		
		PriorityQueue pq1 = new PriorityQueue<E>(20, Collections.reverseOrder());
		pq1.addAll(pq);
		pq1.addAll(Arrays.asList(12, 3, 4, 5, 6, 63, 213, 123, 213, 123));
		
	 	PrintQueue(pq1);
	 	
	 	Set<Integer> set = new HashSet<Integer>();
	 	set.addAll(Arrays.asList(12, 3, 4, 5, 6, 63, 213, 123, 213, 123));
	 	PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(set);
	 	
	 	PrintQueue(pq2);
	 	
	}
	
	/**
	 * 输出队列
	 */
	public static void PrintQueue(Queue q){
		while(q.peek() != null){
			System.out.print(q.poll()+",");
		}
		System.out.println();
	}
}
