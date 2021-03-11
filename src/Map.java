import java.io.IOException;

public interface Map<K,V> {
	
	 int size();
	 boolean isEmpty();
	 boolean get(K key) throws IOException;
	 String getPrint(K key) throws IOException;
	 void put(K key, V value) throws IOException;
	 void remove(K key) throws IOException;
	
}
