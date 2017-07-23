package think_in_java;


/**
 * 继承内部类
 * @author dell
 *
 */
public class ExtendInner {
	class Inner{
		
	}
}

class TestExtendInner extends ExtendInner.Inner{
	public TestExtendInner(ExtendInner ei){
		ei.super();
	}
	public static void main(String[] args) {
		ExtendInner edi = new ExtendInner();
		TestExtendInner tei = new TestExtendInner(edi);
		
	}
}