package think_in_java;

import java.util.*;

/**
 * 性能测试框架 测试类 list的选择
 * @author lx
 *
 */
public class ListPerformance {
	
	static Random random = new Random();
	static int reps = 1000;
	static List<Test<List<Integer>>> tests1 = new ArrayList<Test<List<Integer>>>();
	static List<Test<LinkedList<Integer>>> qTests = new ArrayList<Test<LinkedList<Integer>>>();
	//要执行的测试方法
	static{
		tests1.add(new Test<List<Integer>>("add") {
			@Override
			int test(List<Integer> container, TestParam p) {
				int loops = p.loop;
				int listSize = p.size;
				for (int i = 0; i < loops; i++) {
					container.clear();
					for (int j = 0; j < listSize; j++) {
						container.add(j);
					}
				}
				return loops * listSize;
			}
		});
		
		tests1.add(new Test<List<Integer>>("get") {
			@Override
			int test(List<Integer> container, TestParam p) {
				int loops = p.loop * reps;
				int listSize = container.size();
				for (int i = 0; i < loops; i++) {
					container.get(random.nextInt(listSize));
				}
				return loops;
			}
		});
		
		tests1.add(new Test<List<Integer>>("set") {
			@Override
			int test(List<Integer> container, TestParam p) {
				int loops = p.loop * reps;
				int listSize = container.size();
				for (int i = 0; i < loops; i++) {
					container.set(random.nextInt(listSize),47);
				}
				return loops;
			}
		});
		
		tests1.add(new Test<List<Integer>>("iteradd") {
			@Override
			int test(List<Integer> container, TestParam p) {
				final int LOOPS = 1000000;
				int half = container.size() / 2;
				ListIterator<Integer> it = container.listIterator(half);
				for (int i = 0; i < LOOPS; i++) {
					container.add(47);
				}
				return LOOPS;
			}
		});
		
		tests1.add(new Test<List<Integer>>("insert") {
			@Override
			int test(List<Integer> container, TestParam p) {
				int loops = p.loop;
				for (int i = 0; i < loops; i++) {
					container.add(5,47);
				}
				return loops;
			}
		});
		
		tests1.add(new Test<List<Integer>>("remove") {
			@Override
			int test(List<Integer> container, TestParam p) {
				int loops = p.loop;
				int size = p.size;
				for (int i = 0; i < loops; i++) {
					container.clear();
					container.addAll(new CountingIntegerList(size));
					while(container.size() > 5){
						container.remove(5);
					}
				}
				return loops * size;
			}
		});
		
		qTests.add(new Test<LinkedList<Integer>>("addFirst") {
			@Override
			int test(LinkedList<Integer> container, TestParam p) {
				int loops = p.loop;
				int size = p.size;
				for (int i = 0; i < loops; i++) {
					container.clear();
					for (int j = 0; j < size; j++) {
						container.addFirst(47);	
					}
				}
				return loops * size;
			}
		});
		
		qTests.add(new Test<LinkedList<Integer>>("addLast") {
			@Override
			int test(LinkedList<Integer> container, TestParam p) {
				int loops = p.loop;
				int size = p.size;
				for (int i = 0; i < loops; i++) {
					container.clear();
					for (int j = 0; j < size; j++) {
						container.addLast(47);	
					}
				}
				return loops * size;
			}
		});
		
		qTests.add(new Test<LinkedList<Integer>>("rmFirst") {
			@Override
			int test(LinkedList<Integer> container, TestParam p) {
				int loops = p.loop;
				int size = p.size;
				for (int i = 0; i < loops; i++) {
					container.clear();
					container.addAll(new CountingIntegerList(size));
					while(container.size() > 5){
						container.removeFirst();
					}
				}
				return loops * size;
			}
		});
		
		qTests.add(new Test<LinkedList<Integer>>("rmLast") {
		@Override
		int test(LinkedList<Integer> container, TestParam p) {
			int loops = p.loop;
			int size = p.size;
			for (int i = 0; i < loops; i++) {
				container.clear();
				container.addAll(new CountingIntegerList(size));
				while(container.size() > 5){
					container.removeLast();
				}
			}
			return loops * size;
		}
	});
}
	
	static class ListTester extends Tester<List<Integer>>{

		public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
			super(container, tests);
		}

		@Override
		protected List<Integer> initialize(int size) {
			container.clear();
			container.addAll(new CountingIntegerList(size));
			return container;
		}
		
		public static void run(List<Integer> list, List<Test<List<Integer>>> tests){
			new ListTester(list, tests).timedTest();
		}
		
		public static void main(String[] args) {
//			args = new String[]{"78","456","68","234","32","12","34","76","32","11","44"};
			if(args.length > 0)
				Tester.defaultParam = TestParam.array(args);//自定义测试数据大小
			
			Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null, tests1.subList(1,3)){
				@Override
				protected List<Integer> initialize(int size) {
					Integer[] ia = Generated.array(Integer.class,new CountingGenerator.Integer(),size);
					return Arrays.asList(ia);
				}
			};
			arrayTest.setHeadLine("Array as List");
			arrayTest.timedTest();
			Tester.defaultParam = TestParam.array(10,5000,100,5000,1000,10000,200);
			if(args.length > 0 )
				Tester.defaultParam = TestParam.array(args);
			ListTester.run(new ArrayList<Integer>(), tests1);
			ListTester.run(new LinkedList<Integer>(), tests1);
			ListTester.run(new Vector<Integer>(), tests1);
			Tester.fieldWidth = 12;
			Tester<LinkedList<Integer>> qTest = new Tester<LinkedList<Integer>>(new LinkedList<Integer>(), qTests);
			qTest.setHeadLine("queue tests");
			qTest.timedTest();
		}
	}
}




