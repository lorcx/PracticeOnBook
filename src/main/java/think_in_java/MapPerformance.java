package think_in_java;

import java.util.*;

/**
 * 性能测试框架 map
 * @author dell
 *
 */
public class MapPerformance {
	
	static List<Test<Map<Integer,Integer>>> tests1 = new ArrayList<Test<Map<Integer,Integer>>>();
	static{
		tests1.add(new Test<Map<Integer,Integer>>("put") {
			@Override
			int test(Map<Integer, Integer> container, TestParam p) {
				int loops = p.loop;
				int size = p.size * 2;
				for (int i = 0; i < loops; i++) {
					for (int j = 0; j < size; j++) {
						container.get(j);
					}
				}
				return loops * size;
			}
		});
		
		tests1.add(new Test<Map<Integer,Integer>>("get") {
			@Override
			int test(Map<Integer, Integer> container, TestParam p) {
				int loops = p.loop;
				int size = p.size;
				for (int i = 0; i < loops; i++) {
					container.clear();
					for (int j = 0; j < size; j++) {
						container.put(j, j);
					}
				}
				return loops * size;
			}
		});
		
		tests1.add(new Test<Map<Integer,Integer>>("iterate") {
			@Override
			int test(Map<Integer, Integer> container, TestParam p) {
				int loops = p.loop;
				int size = p.size;
				for (int i = 0; i < loops; i++) {
					Iterator it = container.entrySet().iterator();
					while(it.hasNext()){
						it.next();
					}
				}
				return loops * container.size();
			}
		});
	}
	
	public static void main(String[] args) {
		if(args.length > 0)
			Tester.defaultParam = TestParam.array(args);//自定义测试数据大小
		Tester.run(new TreeMap<Integer,Integer>(), tests1);
		Tester.run(new HashMap<Integer,Integer>(), tests1);
		Tester.run(new LinkedHashMap<Integer,Integer>(), tests1);
		
	}
	
}
