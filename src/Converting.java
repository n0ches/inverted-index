import java.io.IOException;

public class Converting {
	 char[] alphabet;
	 
	 public Converting() {
		 alphabet = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	 }
	
	//stringvalues : alphabetical order of letters in a word
 
		// x : prime number, for instance 37
	public long Horner(long[] stringValues, double x) {
			int n=stringValues.length-1;
			long result=stringValues[n];
			
			for (int i = n-1; i >= 0; i--) {
				result=(long)((result*x) + stringValues[i]);
			}
			return result;
		}
	
	/* Polynomial Accumulation Function (PAF) */
	public long hashcodePAF(String str){
		long value=0;
		int idx=1; // keep the alphabet value of the letters of the word. Because the last element in the calculation array is the first element in the computation array.
		long[] num = new long[str.length()]; // We have created an computation array in size of word length
		char[] harf = str.toCharArray(); // we divided the word into letters
		for (int j = 0; j < harf.length; j++) { // harf alfabedeki kaçýncý ahrf onu bylduk
			for (int k = 0; k < alphabet.length; k++) {
				if(harf[j] == alphabet[k]) {
					num[num.length-idx]=k+1; // put the value of the letter in the alphabet into computation array
					idx++;
					break;
				}
			}
		}
		value=Horner(num, 33); //computation part
		return value;
	}
	
	/* Simple Summation Function (SSF) */
	public int hashcodeSSF(String str) {
		int value = 0;
		char[]harf=str.toCharArray();
		for (int i = 0; i < harf.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if(harf[i]==alphabet[j])
					value+=j+1;
			}
		}
		return value;
	}
	
}
