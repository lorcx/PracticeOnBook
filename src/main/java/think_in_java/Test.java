package think_in_java;

import java.util.List;

/**
 * 性能测试框架
 * loop圈
 * loops循环
 * container 容器
 */
public abstract class Test<c> {
	String name;
	public Test (String name){
		this.name = name;
	}
	abstract int test(c container,TestParam p);
}

class TestParam {
	public final int size;
	public final int loop;
	
	public TestParam(int size,int loop) {
		this.size = size;
		this.loop = loop;
	}
	
	public static TestParam[] array (int ... values){
		int size = values.length / 2;
		TestParam[] tp = new TestParam[size];
		int n = 0;
		for (int i = 0; i < tp.length; i++) {
			tp[i] = new TestParam(values[n++], values[n++]);
		}
		return tp;
	}
	
	public static TestParam[] array (String... values){
		int[] vals = new int[values.length];
		for (int i = 0; i < vals.length; i++) {
			vals[i] = Integer.decode(values[i]);
		}
		return array(vals);
	}
}

class Tester<c>{
	public static int fieldWidth = 8;
	public static TestParam[] defaultParam = TestParam.array("10","5000","100","5000","1000","5000","10000","500");
	protected c container;
	
	protected c initialize(int size){
		return container;
	}
	
	private String headLine = "";
	public List<Test<c>> tests;
	
	private static String stringFiled(){
		return "%" + fieldWidth + "s";
	}
	
	private static String numberField(){
		return "%" + fieldWidth + "d";
	}
	
	private static int sizeWidth = 5;
	private static String sizeField = "%" + sizeWidth + "s";
	private TestParam[] paramList = defaultParam;
	
	public Tester (c container,List<Test<c>> tests) {
		this.container = container;
		this.tests = tests;
		if(container != null)
			headLine = container.getClass().getSimpleName();
	}
	
	public Tester (c container,List<Test<c>> tests, TestParam[] paramList) {
		 this(container,tests);
		 this.paramList = paramList;
	}
	
	public void setHeadLine(String newHeadLine){
		headLine = newHeadLine;
	}
	//run
	public static <c> void run(c cntr,List<Test<c>> tests){
		new Tester<c>(cntr, tests).timedTest();;
	}
	
	public static <c> void run(c cntr,List<Test<c>> tests,TestParam[] paramList){
		new Tester<c>(cntr, tests,paramList).timedTest();
	}
	
	private void displayHeader(){
		int width = fieldWidth * tests.size() + sizeWidth;
		int dashLength = width - headLine.length() - 1;
		StringBuilder head = new StringBuilder(width);
		for (int i = 0; i < dashLength / 2; i++) {
			head.append('-');
		}
		head.append(' ');
		head.append(headLine);
		head.append(' ');
		for (int i = 0; i < dashLength / 2; i++) {
			head.append('-');
		}
		System.out.println(head);
		System.out.format(sizeField, "size");
		for (Test test : tests) {
			System.out.format(stringFiled(), test.name);
		}
		System.out.println();
	}
	
	public void timedTest() {
		displayHeader();
		for (TestParam param : paramList) {
			System.out.format(sizeField, param.size);
			for (Test<c> test : tests) {
				c kontainer = initialize(param.size);
				long start = System.nanoTime();
				int reps = test.test(kontainer, param);//执行测试的方法
				long duration = System.nanoTime() - start;
				long timePerRep = duration / reps; //reps是执行的次数， duration所用时间  计算出每个的时间
				System.out.format(numberField(), timePerRep);
			}
			System.out.println();
		}
	}
}
