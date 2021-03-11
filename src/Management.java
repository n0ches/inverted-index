import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Management {
	static String pathTxt;	//to show txt names
	static String filePath;
	static String file;
	public DoubleHashTable<String,String> tbl; //hash table
	public Timer time;
	public ArrayList<String> stopwords;   // to keep stopwords.
	
	public Management() throws IOException {
		tbl = new DoubleHashTable<String, String>(0.50f,"SSF");
		time=new Timer();
		stopwords = new ArrayList<String>();
		stopWords();
	}
	
	/* Function to read stop words */
	public void stopWords() throws IOException {
		String DELIMITERS = "[-+=" + " " + "\r\n " + "1234567890" + "Ã¢â‚¬â„¢'\"" + "(){}<>\\[\\]" + ":" + "," + "Ã¢â‚¬â€™Ã¢â‚¬â€œÃ¢â‚¬â€�Ã¢â‚¬â€¢" + "Ã¢â‚¬Â¦" + "!" + "." + "Ã‚Â«Ã‚Â»" + "-Ã¢â‚¬ï¿½" + "?" + "Ã¢â‚¬ËœÃ¢â‚¬â„¢Ã¢â‚¬Å“Ã¢â‚¬ï¿½" + ";" + "/" + "Ã¢ï¿½â€�" +"Ã¢ï¿½Â " + "Ã‚Â·" + "&" +"@" + 
	        	"*" + "\\" + "Ã¢â‚¬Â¢" + "^" + "Ã‚Â¤Ã‚Â¢$Ã¢â€šÂ¬Ã‚Â£Ã‚Â¥Ã¢â€šÂ©Ã¢â€šÂª" + "Ã¢â‚¬Â Ã¢â‚¬Â¡" + "Ã‚Â°" + "Ã‚Â¡" + "Ã‚Â¿" + "Ã‚Â¬" + "#" + "Ã¢â€�â€“" + "%Ã¢â‚¬Â°Ã¢â‚¬Â±" + "Ã‚Â¶" + "Ã¢â‚¬Â²" + "Ã‚Â§" + "~" + "Ã‚Â¨" + "_" + "|Ã‚Â¦" + "Ã¢ï¿½â€š" + "Ã¢Ëœï¿½" + "Ã¢Ë†Â´" + "Ã¢â‚¬Â½" + "Ã¢â‚¬Â»" + "]";
		Scanner s = new Scanner(new File("C:\\Users\\Rıdvan\\Desktop\\stop_words_en.txt"));
		while(s.hasNext()) {
			// I splitted stop word before put it into array list according to delimiters. Because we split the words before put it into hashtable, that's why some non expectated situations occur
			//Example we took the "they've" words from any txt file. And we splitted it before put it into hashtable. new words are "they" and "ve". "they" is the stopword but "ve" is not stop word.
			//That's why "ve" will be put into hashtable. But normaly, "they've" is stopword, neither "they" nor "ve" should not put into table. That's why ı splitted the stopwords before 
			//put it into array list 
			String[] splitted=s.next().split(DELIMITERS); 
			for (int i = 0; i < splitted.length; i++) {
				stopwords.add(splitted[i]);
			}
			
		}
	}
	
	/* Function to read, split words from txt file and put it into hash table. */
	public void stringProcess(String filePath) throws IOException {
		String DELIMITERS = "[-+=" + " " + "\r\n " + "1234567890" + "Ã¢â‚¬â„¢'\"" + "(){}<>\\[\\]" + ":" + "," + "Ã¢â‚¬â€™Ã¢â‚¬â€œÃ¢â‚¬â€�Ã¢â‚¬â€¢" + "Ã¢â‚¬Â¦" + "!" + "." + "Ã‚Â«Ã‚Â»" + "-Ã¢â‚¬ï¿½" + "?" + "Ã¢â‚¬ËœÃ¢â‚¬â„¢Ã¢â‚¬Å“Ã¢â‚¬ï¿½" + ";" + "/" + "Ã¢ï¿½â€�" +"Ã¢ï¿½Â " + "Ã‚Â·" + "&" +"@" + 
	        	"*" + "\\" + "Ã¢â‚¬Â¢" + "^" + "Ã‚Â¤Ã‚Â¢$Ã¢â€šÂ¬Ã‚Â£Ã‚Â¥Ã¢â€šÂ©Ã¢â€šÂª" + "Ã¢â‚¬Â Ã¢â‚¬Â¡" + "Ã‚Â°" + "Ã‚Â¡" + "Ã‚Â¿" + "Ã‚Â¬" + "#" + "Ã¢â€�â€“" + "%Ã¢â‚¬Â°Ã¢â‚¬Â±" + "Ã‚Â¶" + "Ã¢â‚¬Â²" + "Ã‚Â§" + "~" + "Ã‚Â¨" + "_" + "|Ã‚Â¦" + "Ã¢ï¿½â€š" + "Ã¢Ëœï¿½" + "Ã¢Ë†Â´" + "Ã¢â‚¬Â½" + "Ã¢â‚¬Â»" + "]";
	    Scanner s = new Scanner(new File(filePath));
	    while (s.hasNext()){
	    	//I used replace() function because toLowerCase() function convert "I" letter to "ı".
	    	String[] splitted = s.next().toLowerCase().replace("ı", "i").split(DELIMITERS); 
		    for (int i = 0; i < splitted.length; i++) {
		    	if(!stopwords.contains(splitted[i]) && !splitted[i].equals("") && !splitted[i].equals(null)) {//is this word that will put into hash table, the stopword?
		    		tbl.put(splitted[i], splitted[i]);
		    	}
			}
	    }
	    s.close();
	}
	
	/* Function to read words from business folder and put them into hash table */
	public void business() throws IOException {
		filePath= "C:\\Users\\Rıdvan\\Desktop\\bbc\\business\\"; //please change the filepath according to bbc\\business directory
		for (int i = 1; i < 511; i++) {
			if(i<10) {
				pathTxt= filePath.substring(28)+"00" + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
			else if(i>=10 && i<=99) {
				pathTxt= filePath.substring(28)+"0" + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
			else if(i>99) {
				pathTxt=filePath.substring(28) + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
		}
		System.out.println(tbl.size + " words are added successfully from business folder");
		System.out.println("Collision count: " + tbl.getCollisionCount());
	}
	
	/* Function to read words from entertainment folder and put them into hash table */
	public void entertainment() throws IOException {
		int temp = tbl.size;
		filePath= "C:\\Users\\Rıdvan\\Desktop\\bbc\\entertainment\\"; //please change the filepath according to bbc\entertainment directory
		for (int i = 1; i < 387; i++) {
			if(i<10) {
				pathTxt= filePath.substring(28)+"00" + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
			else if(i>=10 && i<=99) {
				pathTxt= filePath.substring(28)+"0" + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
			else if(i>99) {
				pathTxt=filePath.substring(28) + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
		}
		System.out.println(tbl.size - temp + " words are added successfully from entertainment folder");
		System.out.println("Collision count: " + tbl.getCollisionCount());
	}
	
	/* Function to read words from politics folder and put them into hash table */
	public void politics() throws IOException {
		int temp = tbl.size;
		filePath= "C:\\Users\\Rıdvan\\Desktop\\bbc\\politics\\"; //please change the filepath according to bbc\politics directory
		for (int i = 1; i < 418; i++) {
			if(i<10) {
				pathTxt= filePath.substring(28)+"00" + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
			else if(i>=10 && i<=99) {
				pathTxt= filePath.substring(28)+"0" + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
			else if(i>99) {
				pathTxt=filePath.substring(28) + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
		}
		System.out.println(tbl.size - temp + " words are added successfully from politics folder");
		System.out.println("Collision count: " + tbl.getCollisionCount());
	}
	
	/* Function to read words from sport folder and put them into hash table */
	public void sport() throws IOException {
		int temp = tbl.size;
		filePath= "C:\\Users\\Rıdvan\\Desktop\\bbc\\sport\\"; //please change the filepath according to bbc\sport directory
		for (int i = 1; i < 512; i++) {
			if(i<10) {
				pathTxt= filePath.substring(28)+"00" + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
			else if(i>=10 && i<=99) {
				pathTxt= filePath.substring(28)+"0" + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
			else if(i>99) {
				pathTxt=filePath.substring(28) + String.valueOf(i) +".txt";
				String file = filePath.substring(0, 28)+pathTxt;
				stringProcess(file);
			}
		}
		System.out.println(tbl.size - temp + " words are added successfully from sport folder");
		System.out.println("Collision count: " + tbl.getCollisionCount());
	}
	
	/* Function to read words from tech folder and put them into hash table */
	public void tech() throws IOException {
		int temp = tbl.size;
		filePath= "C:\\Users\\Rıdvan\\Desktop\\bbc\\tech\\"; //please change the filepath according to bbc\tech directory
		String pathTxt1= filePath.substring(28);
		String file;
		for (int i = 1; i < 402; i++) {
			if(i<10) {
				pathTxt =pathTxt1+ "00" + String.valueOf(i) +".txt";
				stringProcess(filePath+"00" + String.valueOf(i) +".txt");
			}
			else if(i>=10 && i<=99) {
				pathTxt =pathTxt1+ "0" + String.valueOf(i) +".txt";
				stringProcess(filePath+"0" + String.valueOf(i) +".txt");
			}
			else if(i>99) {
				pathTxt =pathTxt1 + String.valueOf(i) +".txt";
				stringProcess(filePath+String.valueOf(i) +".txt");
			}
		}
		System.out.println(tbl.size - temp + " words are added successfully from tech folder");
		System.out.println("Collision count: " + tbl.getCollisionCount());
	}
	
	/* Function to read all words from folder and put them into hash table */
	public void putAllFiles() throws IOException {
		Timer.start();
		business();
		entertainment();
		politics();
		sport();
		tech();
		Timer.stop();
		System.out.println(tbl.size + " words are added successfully from all files");
		System.out.println("Collision Count: " + tbl.getCollisionCount());
		System.out.println("Indexing time is: " + Timer.getElapsedSeconds() + " seconds");
	}
	
	/* function to take a word from user and put it into hash table */
	public void addWord(String word) throws IOException {
		if(tbl.bucketGet(word)) {
			System.out.println(word + " has already added!");
		}
		else {
			tbl.put(word,word);
			System.out.println(word + " are added successfully!");
		}
	}
	
	/* function to search the words from 1000.txt file and find minimum, maximum, average search time */
	public void search1000Words() throws IOException {
		Scanner s = new Scanner(new File("C:\\Users\\Rıdvan\\Desktop\\1000.txt"));
		/* variables for finding search time */
		double minSearch = 99999;
		double maxSearch=-1;
		double averTime=0;
		int searchSize=0;
		String minWord = null;		
		String maxWord=null;
		/* variables for finding search time */
		
		 while (s.hasNext()){
			 double searchTime=0.0;
			 String word = s.next().toLowerCase().replace("ı","i");
			 Timer.start();
			 if(tbl.bucketGet(word)) {
				 Timer.stop();
				 searchTime = Timer.getElapsedTime();
				 System.out.println(tbl.bucketGetPrint(word));
				 System.out.println();
				 System.out.println(word +" is found in " + searchTime + " nanoseconds");
			 }
			 else {
				 Timer.stop();
				 searchTime = Timer.getElapsedTime();
				 System.out.println(tbl.bucketGetPrint(word));
				 System.out.println();
				 System.out.println(word +" is not found in " + searchTime + " nanoseconds");
			 }
			 System.out.println();
		     averTime+=searchTime;
			 System.out.println();
			 if(searchTime<= minSearch) {
					 minSearch = searchTime;
					 minWord = word;
				 }
			 if(searchTime>= maxSearch) {
					 maxSearch = searchTime;
					 maxWord = word;
				 }
			 searchSize++;
			 }
		 minSearch = minSearch / 1000.0;
		 maxSearch = maxSearch / 1000.0;
		 averTime= (averTime/(double)searchSize) /1000.0;
		 System.out.println();
		 System.out.println(minWord+" has minimum search time: " + minSearch + " microseconds");
		 System.out.println(maxWord+" has maximum search time: " + maxSearch + " microseconds");
		 System.out.println("Average search time is: " + averTime+ " microseconds");
		 }
	
	/* Function to print hashtable */
	public void print() throws IOException {
		System.out.println("****   HashTable  ***");
		System.out.println(tbl.toString());
		System.out.println();
	}
}
