package think_in_java;
/**
 * associative:联想的、联合的
 * pairs:一双
 * 17章理解map
 * 自定义map
 * @author dell
 *
 */
public class AssociativeArray<k,v> {
	private Object[][] pairs;
	private int index;
	
	public AssociativeArray(int length){
		pairs = new Object[length][2];
	}
	
	public void put(k key,v value){
		if(index > pairs.length - 1)
			throw new ArrayIndexOutOfBoundsException();
		pairs[index++] = new Object[]{key,value};
	}
	
	@SuppressWarnings("unchecked")
	public v get (k key){
		for (int i = 0; i < index; i++) {
			if(pairs[i][0] == key) //key.equals(pairs[i][0])
				return (v)pairs[i][1];
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int j = 0; j < index; j++) {
			result.append(pairs[j][0].toString()+":");
			result.append(pairs[j][1].toString());
			if(j < index - 1){
				result.append("\n");
			}
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		AssociativeArray<String, String> aa = new AssociativeArray<String, String>(6);
		aa.put("1", "a");
		aa.put("2", "b");
		aa.put("3", "c");
		aa.put("4", "e");
		aa.put("f", "5");
		System.out.println(aa);
		System.out.println(aa.get("f"));
		
	}
}
