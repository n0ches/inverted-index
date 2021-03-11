
public interface Entry<K,V> {
	
	K getKey();
	
	V getValue();
	
	Entry<K,V> getNext();
	
	int hashidx();
	
	String toString();
	
}
