import java.io.IOException;

public class DoubleHashTable<K,V> extends AbstractHashMap<K,V> {
	private MapEntry<K,V>[] table;
	protected int primeN; //prime table size for double hashing.
	private int primeQ; //prime number q for double hashing.
	private String hashType;
	
	/* constructor default capacity and loadFactor */
	public DoubleHashTable(String hash) {super(); primeN=capacity; primeQ=7;hashType=hash;}
	public DoubleHashTable(float load, String hash) {super(load);primeN=capacity; primeQ=7;hashType=hash;}//default capacity
	public DoubleHashTable(int cap,float load, String hash) {super(cap,load); primeN=capacity; primeQ=7;hashType=hash;}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	protected void createTable() {
		table =(MapEntry<K,V>[]) new MapEntry[capacity];
	}
	
	/* function to get word without output */
	@Override
	protected boolean bucketGet(K key) throws IOException { 
		int hash = hashFunction(key); //finding hash
        int doubleHash = hashFunctionDouble(key);//finding hash for double hashing
        
        /* collision handling with double hashing*/
        while (table[hash] != null && !table[hash].getKey().equals(key)) {
              hash += doubleHash;
              hash%=primeN;
        }
        /* collision handling with double hashing*/
        
        /* return statements*/
        if(table[hash] ==null)
        	return false;
        else
        	return true;
	}
	
	/* function to get word with output  */
	@Override
	protected String bucketGetPrint(K key) throws IOException { 
		StringBuilder hashTableStr = new StringBuilder();
        int hash = hashFunction(key);  //finding hash
        int doubleHash = hashFunctionDouble(key); //finding hash for double hashing
        
        /* collision handling with double hashing*/
        while (table[hash] != null && !table[hash].getKey().equals(key)) {
              hash += doubleHash;
              hash%=primeN;
        }
        /* collision handling with double hashing*/
        
        /* not found statement */
        if (table[hash] == null) {
        	hashTableStr.append("\n>Search: ").append(key).append("\n "+"Not found");
        	return hashTableStr.toString();
        }
        else {
        int count=0;
      	  MapEntry<K,V> temp = table[hash];
      	  /* Finding the documents from which the word was taken */
      	  while(temp.getNext()!=null) {
      		  count++;
      		  temp=temp.getNext();
      	  }
      	  /* Finding the documents from which the word was taken */
      	  
      	  /* printing statements */
      	  if(count>1) {
      		  hashTableStr.append("\n>Search: ").append(table[hash].getValue()).append("\n "+count+" documents found.");
      	  }
      	  else {
      		  hashTableStr.append("\n>Search: ").append(table[hash].getValue()).append("\n "+count+" document found.");
      	  }
      	  temp = table[hash];
      	  /* adding the word and it's documents into string*/
      	  while(temp.getNext()!=null) {
      		  hashTableStr.append("\n " + temp.getNext().getValue()+"-"+temp.getNext().getKey());
      		  temp=temp.getNext();
      	  }
      	  /* adding the word and it's documents into string*/
      	  return hashTableStr.toString();
        }
        
	}

	/* function to put the value  */
	@Override
	protected void bucketPut(K key, V value) throws IOException {
		int hash=hashFunction(key); //finding hash
		int doubleHash = hashFunctionDouble(key); //finding hash for double hashing
		/* if index of hash is null, put the value into table*/
		if(table[hash]==null) {
			table[hash] = new MapEntry<K, V>(key, value); //put value
			table[hash].setNext(new MapEntry<K, V>(Management.pathTxt, "1")); //add the txt name and value count
			size++; //increasing size
			table[hash].setHashidx(hash);//setting hash value for printing hash table
		}
		/* if index of hash is null, put the value into table*/
		
		/* if value of index of hash and the value that will put into table are the same, program icrease the value count  */
		else if(table[hash]!=null && table[hash].getValue().equals(value)) {
			int count=0;
			MapEntry<K, V> temp = table[hash];
            while(temp.getNext() != null) {
            	if(temp.getNext().getKey().equals(Management.pathTxt)) {
            		count=Integer.parseInt((String) temp.getNext().getValue()) +1;//increasing value count
            		break;
            	}
            	else {
            		temp=temp.getNext();
            		count=1;
            	}
            }
          	temp.setNext(new MapEntry<K, V>(Management.pathTxt, String.valueOf(count)));
		}
		
		/* if value of index of hash and the value that will put into table are not the same (COLLISION STATEMENT)  */
		else if(table[hash]!=null && !table[hash].getValue().equals(value)){
			/* collision handling by using double hashing*/
			while(table[hash]!=null && !table[hash].getValue().equals(value)) {
				collisionCount++;
				hash += doubleHash;
	            hash%=primeN;
			}
			
			/* if index of hash is null, put the value into table*/
			if(table[hash]==null) {
				table[hash] = new MapEntry<K, V>(key, value);
	  			table[hash].setNext(new MapEntry<K, V>(Management.pathTxt, "1"));
	  			size++;
	  			table[hash].setHashidx(hash);
			}
			/* if value of index of hash and the value that will put into table are the same, program icrease the value count  */
			else if(table[hash].getValue().equals(value)){
				int count=0;
	  			MapEntry<K, V> temp = table[hash];
	              while(temp.getNext() != null) {
	              	if(temp.getNext().getKey().equals(Management.pathTxt)) {
	              		count=Integer.parseInt((String) temp.getNext().getValue()) +1;
	              		break;
	              	}
	              	else {
	              		temp=temp.getNext();
	              		count=1;
	              	}
	              }
	              temp.setNext(new MapEntry<K, V>(Management.pathTxt, String.valueOf(count)));
			}
		}
		
	}

	/* function to remove the value  */
	@Override
	protected void bucketRemove(K key) throws IOException {
		int hash = hashFunction(key); //finding hash
		int doubleHash = hashFunctionDouble(key);//finding  hash for double hashing
		
		/* if there has been a collision, loop will find the value*/
        while (table[hash] != null && !table[hash].getKey().equals(key)) {
        	hash += doubleHash;
            hash%=primeN;
        }
        
        /* removing value*/
        if (table[hash] != null) {
              table[hash] = null;
              size--;
              System.out.println(key + " is removed successfully from hash table!");
        }
        else {
        	System.out.println(key + " is not found!");
        }
		
	}

	/* function to resize hash table  */
	@Override
	protected void resize() throws IOException {
		int tableSize = 2 * table.length; //increasing table length
        maxSize = (int) (tableSize * loadFactor); //setting maximum number of elements the table can take
        MapEntry<K,V>[] oldTable = table; //temp table
        table = (MapEntry<K,V>[])new MapEntry[tableSize]; //re-assigning the table with new size
        primeN=getPrime(table.length); //find the prime table size for double hashing
        primeQ=getPrime(primeN); //finding the prime q for double hashing
        for (int i = 0; i < oldTable.length; i++) {
        	if (oldTable[i] != null ) {
            	  int hash=hashFunction(oldTable[i].getKey());
            	  if(table[hash]!=null) {
            		  int doubleHash = hashFunctionDouble(oldTable[i].getKey());
            		  // if a collision occurs
            		  while(table[hash]!=null) {
            			  collisionCount++;
            			  hash += doubleHash;
          	              hash%=primeN;
                	  }
            	  }
            	  
            	  // I didn't use put function because this way is faster than using put function.
            	  table[hash] = oldTable[i];	
            	  table[hash].setHashidx(hash);
              }
        }
	}
	
	
	/* Function which gives a hash value for a given string */
    private int hashFunction(K key) throws IOException
    {
        int  hashVal = hashcode((String)key , hashType) % primeN;
        if (hashVal < 0)
            hashVal += primeN;
        return hashVal;
    }
    
    /* Function which gives a hash value for double hashing */
    private int hashFunctionDouble(K key ) throws IOException
    {
        int hashVal = hashcode((String)key , hashType) % primeN;
        if (hashVal < 0)
            hashVal += primeN;
        return primeQ - hashVal % primeQ;
    }
    
    /* function to print the hash table */
    public String toString() {
        StringBuilder hashTableStr = new StringBuilder();
        for (MapEntry<K,V> entry : table) {
            if(entry == null) {
                continue;
            }
            hashTableStr.append("\n bucket[")
                    .append(entry.hashidx())
                    .append("] = ")
                    .append(entry.toString());
            
            MapEntry<K,V> temp = entry.getNext();
            while(temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.getNext();
            }
        }
        return hashTableStr.toString();
    }
	
}
