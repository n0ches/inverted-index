import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
	Management test;
	Scanner menu;
	
	/* user interface constructor */
	public UserInterface() throws IOException {
		test = new Management();
		menu=new Scanner(System.in);
	}
	
	/* function for displaying main menu */
	public void menuDisplay() {
		System.out.println();
		System.out.println("MENU");
		System.out.println("1. Read txt files");
		System.out.println("2. Print hash table");
		System.out.println("3. Add word");
		System.out.println("4. Search 1000 words");
		System.out.println("5. Search any word");
		System.out.println("6. Remove any word");
		System.out.println("7. Exit");
		
	}
	
	/* function for displaying read submenu */
	public void menuRead() {
		System.out.println();
		System.out.println("1. Read all txt files");
		System.out.println("2. Read business folder");
		System.out.println("3. Read entertainment folder");
		System.out.println("4. Read politics folder");
		System.out.println("5. Read sport folder");
		System.out.println("6. Read tech folder");
		System.out.println("7. Back");
	}
	
	/* menu function */
	public void menu() throws IOException {
		int str;
		//menuDisplay();
		while(true) {
			str=0;
			menuDisplay();
			str = menu.nextInt();
			if(str==1) {
				test=new Management();
				menuRead();
				str=menu.nextInt();
				if(str==1) {
					test.putAllFiles();
				}
				else if(str==2) {
					test.business();
				}
				else if(str==3) {
					test.entertainment();
				}
				else if(str==4) {
					test.politics();
				}
				else if(str==5) {
					test.sport();
				}
				else if(str==6) {
					test.tech();
				}
				else if(str==7) {
					menu();
				}
			}
			else if(str==2) {
				test.print();
			}
			else if(str==3) {
				System.out.println("Enter a word: ");
			    String word = menu.next();
				test.addWord(word);
			}
			else if(str==4) {
				test.search1000Words();
			}
			else if(str==5) {
				System.out.println("Enter a word: ");
				String word = menu.next();
				System.out.println(test.tbl.bucketGetPrint(word));
			}
			else if(str==6) {
				System.out.println("Enter a word: ");
				String word = menu.next();
				test.tbl.bucketRemove(word);
			}
			else if(str==7) {
				System.exit(1);
			}
		}
	}
	
}
