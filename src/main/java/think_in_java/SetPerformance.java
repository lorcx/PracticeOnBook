package think_in_java;

import java.util.*;

/**
 * 性能测试框架 set的选择
 * @author dell
 *
 */
public class SetPerformance {
	static List<Test<Set<Integer>>> tests1 = new ArrayList<Test<Set<Integer>>>();
	
	static {
		tests1.add(new Test<Set<Integer>>("add"){
			@Override
			int test(Set<Integer> container, TestParam p) {
				int loops = p.loop;
				int size = p.size;
				for (int i = 0; i < loops; i++) {
					container.clear();
					for (int j = 0; j < size; j++) {
						container.add(j);
					}
				}
				return loops * size;
			}
		});
		
		tests1.add(new Test<Set<Integer>>("contains"){
			@Override
			int test(Set<Integer> container, TestParam p) {
				int loops = p.loop;
				int size = p.size * 2;
				for (int i = 0; i < loops; i++) {
					for (int j = 0; j < size; j++) {
						container.contains(j);
					}
				}
				return loops * size;
			}
		});
		
		tests1.add(new Test<Set<Integer>>("iterate"){
			@Override
			int test(Set<Integer> container, TestParam p) {
				int loops = p.loop * 10;
				for (int i = 0; i < loops; i++) {
					Iterator<Integer> it = container.iterator();
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
		Tester.fieldWidth = 10;
		Tester.run(new TreeSet<Integer>(), tests1);
		Tester.run(new HashSet<Integer>(), tests1);
		Tester.run(new LinkedHashSet<Integer>(), tests1);
	}
}
