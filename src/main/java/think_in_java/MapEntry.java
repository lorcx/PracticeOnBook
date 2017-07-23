package think_in_java;

import java.util.Map.Entry;

public class MapEntry<k,v> implements Entry<k, v> {
	
	private k key;
	private v value;
	
	public MapEntry(k key, v value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public k getKey() {
		return key;
	}

	@Override
	public v getValue() {
		return value;
	}

	@Override
	public v setValue(v v) {
		v result = value;
		value = v;
		return result;
	}

	@Override
	public int hashCode() {
		return (key == null ? 0 : key.hashCode()) ^
				(value == null ? 0 : value.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MapEntry))
			return false;
		MapEntry me = (MapEntry) obj;
		return (key == null ?
					me.getKey() == null : key.equals(me.getKey())) 
					&&
				(value == null ?
					me.getValue() == null :value.equals(me.getValue()));
	}
	
	@Override
	public String toString() {
		return key + ":" + value;
	}
}
