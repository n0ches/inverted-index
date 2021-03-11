

public abstract class AbstractMap<K,V> implements Map<K,V> {
	public boolean isEmpty() {return size()==0;}
	protected static class MapEntry<K,V> implements Entry<K,V>{
		private K key;
		private V value;
		private MapEntry<K,V> next;
		private int hashidx;
		
		public MapEntry(K k, V v) {
			key=k;
			value=v;
		}
		public MapEntry(String k, String v) {
			key=(K) k;
			value=(V) v;
		}

		/* get methods */
		
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public MapEntry<K, V> getNext() {
			// TODO Auto-generated method stub
			return next;
		}
		
		@Override
		public int hashidx() {
			// TODO Auto-generated method stub
			return hashidx;
		}
		
		/* get methods */
		
		/* set methods */
		protected void setKey(K k) { key=k;}
		protected void setValue(V v) {value=v;}
		protected void setNext(MapEntry<K,V> next) {this.next=next;}
		protected void setHashidx(int hash) {hashidx=hash;}
		/* set methods */
		
		public String toString() { return   "[" + key + ", " + value + "]";}
		
		
		

	}

}
