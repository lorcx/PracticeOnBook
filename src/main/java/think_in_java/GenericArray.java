package think_in_java;

public class GenericArray<T> {
	T[] array;
	
	@SuppressWarnings("unchecked")
	public GenericArray (int sz){
		array = (T[]) new Object[sz];
	}
	
	public void put (int index,T item){
		array[index] = item;
	}
	
	public T[] get(int index){
		return (T[]) array[index];
	}
	
	public T[] rep(){
		return  array;
	}
	
	public String[] aa (){
		return new String[1];
	}
	
	public static void main(String[] args) {
		GenericArray<Integer> ga = new GenericArray<Integer>(10);
		Object ob = ga.rep();
		System.out.println(ob);
		System.out.println(ga.get(2));
		Object oa = ga.aa();//数组本身也是个object
	}
}





