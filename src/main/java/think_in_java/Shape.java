package think_in_java;

import java.util.Arrays;
import java.util.List;

/**
 * 多态的使用
 * shape：形状
 * Circle:圆
 * square:平方型
 * Triangle:三角形
 * @author dell
 *
 */
public abstract class Shape {
	public String draw() {
		return this + ".draw()";
	}
	
	public abstract String toString();
}

class Circle extends Shape{
	public String toString() {
		return "circle";
	}
}

class Square extends Shape {
	public String toString() {
		return "Square";
	}
}

class Triangle extends Shape {
	public String toString() {
		return "Triangle";
	}
}

class Shapes{
	public static void main(String[] args) {
		List<Shape> list = Arrays.asList(new Circle(), new Triangle(), new Square());
		for (Shape sh : list) {
			System.out.println(sh.draw());
		}
	}
}
