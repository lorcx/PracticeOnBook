package think_in_java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 泛型
 * @author lx
 * Contract:合约
 * transfer :转让
 *
 */
public class Fill {
	public static <T> void fill (Collection<T> collection,Class<? extends T> classToken,int size){
		for (int i = 0; i < size; i++) {
			try {
				collection.add(classToken.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
}

class Contract{
	private static long counter = 0;
	
	private final long  id = counter++;
	
	public String toString() {
		return getClass().getName()+":"+id;
	}
}


class TileTransfer extends Contract {}


class FillTest{
	public static void main(String[] args) {
		List<Contract> contracts = new ArrayList<Contract>();
		Fill.fill(contracts, Contract.class, 3);
		Fill.fill(contracts, TileTransfer.class, 3);
		
		for (Contract c : contracts) {
			System.out.println(c);
		}
	}
}











