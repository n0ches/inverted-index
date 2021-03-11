import java.io.IOException;
import java.util.Random;

public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {
	protected int size; //variable to keep count of elements in hashtable.
	protected long collisionCount; //variable to keep collision count.
	protected int capacity; //length of table
	protected float loadFactor;//load factor
	protected int maxSize; //maximum number of elements the table can hold
	Converting convertOperations; //converting strings to the integer 
	
	/* constructor */
	public AbstractHashMap(int cap,float load) {
		convertOperations = new Converting();
		capacity=cap;
		loadFactor=load;
		collisionCount=0;
		size=0;
		maxSize=(int) (capacity*loadFactor);
		createTable();
		
	}
	public AbstractHashMap(float load) {//default capacity
		this(128,load);
	}
	
	public AbstractHashMap() {this(128, 0.5f);}//initial capacity and loadFactor
	
	//public methods
	 public boolean get(K key) throws IOException { return bucketGet(key); }
	 public String getPrint(K key) throws IOException { return bucketGetPrint(key); } 
	 public void remove(K key) throws IOException { bucketRemove(key); } 
	 public void put(K key, V value) throws IOException {
		 bucketPut(key,value);
		 if(size >= maxSize) {
			 resize();}
	 }
	//public methods
	 
	/* method that find smaller prime number than table size */
	public int getPrime(int tableSize)
    {
        for (int i = tableSize - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        /* Return a prime number */
        return 3;
    }
	/* method that find smaller prime number than table size */
	
	public long getCollisionCount() {
		return collisionCount;
	}
	
	/* hashcode function that change according to hashType(SSF or PAF) */
	public int hashcode(String key , String hashType) {
		int hash = -1;
		if(hashType.equals("SSF"))
			hash = convertOperations.hashcodeSSF(key);
		else if(hashType.equals("PAF"))
			hash = (int) convertOperations.hashcodePAF(key);
		return hash;
	}
	
	/*  protected abstract methods to be implemented by subclasses */
	protected abstract void createTable();
	protected abstract String bucketGetPrint(K key) throws IOException;
	protected abstract boolean bucketGet(K key) throws IOException;
	protected abstract void bucketPut(K key, V value) throws IOException;
	protected abstract void bucketRemove(K key) throws IOException;
	protected abstract void resize() throws IOException;
}
