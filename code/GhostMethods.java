package code;

import java.io.FileNotFoundException;

public class GhostMethods {
	
	public void printArray(String[] array){
		for(int i = 0; i < array.length; i++){
			System.out.println(array[i]);
		}
	}
	
	public String[] twoLetterCombos(){
		//this is the method that generates all possible combinations of two letters 
		//there must be a more efficent way. as of now, i have a separate set of nested loops just to 
		// find out how many possibilities there are. 
		String[] array = new String[1352];
		//1352 for all possible, (26*26)*2

//		int x = 0;
//		for(char i = 97; i <= 122; i++){
//			for(char j = 97; j <= 122; j++){
//				x++;
//			}
//		}
		int pos_from_mid_dec = 26*26;
		int pos_from_mid_inc = pos_from_mid_dec;
		
		//x is just a var to increment 
		for(char i = 97; i <= 122; i++){
			for(char j = 97; j <= 122; j++){
				array[pos_from_mid_dec-- -1] = Character.toString((char)i) + Character.toString((char)j);
				array[pos_from_mid_inc++] = Character.toString((char)j) + Character.toString((char)i);
			}
		}
		return array;
	}

	
	//this max options(the one with no parameter) finds the two letter combo with max options.
	//the other uses input and finds the letter to put down wiht most outcomes
	public int maxOptions(){
		GhostMethods g = new GhostMethods();
		String[] array = g.twoLetterCombos();
		
		WordSearch ws = new WordSearch();
		int running = 0;
		int temp_saved = 0;
		for(int i = 0; i < array.length; i++){
			try {
				if(ws.setup(array[i], 2) > running)
					try {
						temp_saved = ws.setup(array[i], 2);
						System.out.println("running highest has been replaced by " + array[i] + " with " + temp_saved + " possibilities");
						running = temp_saved;
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return running;
	}
	
	public int possibilities(int number) throws FileNotFoundException{
		GhostMethods g = new GhostMethods();
		WordSearch ws = new WordSearch();
		
		int temp = 0;
		int n = 0;
		
		String[] array = g.twoLetterCombos();
		for(int i = 0; i < array.length; i++){
			temp = ws.setup(array[i], 1);
			if(temp <= number && temp != 0){
				System.out.println("the letters " + array[i] + " have " + temp + " possibilities, all of which are listed below");

				System.out.println(array[i] + " is contained in " + ws._current_word);
				n++;
			}
		}
		System.out.println("there are " + n + " pairs of two letters that have " + number + " or less possibilities.");
		return n;
	}
	
//	public void possibilities(int number){
//		GhostMethods g = new GhostMethods();
//		String[] array = g.twoLetterCombos();
//		WordSearch ws = new WordSearch();
//		int temp_saved = 0;
//		for(int i = 0; i < g.twoLetterCombos().length; i++){
//			try {
//				temp_saved = ws.setup(array[i], 2);
////				if(ws.setup(array[i], 2) != 0 && ws.setup(array[i], 2) <= Integer.parseInt(args[0])){
//				if(temp_saved != 0 && temp_saved <= number){
//					if(ws._current_word.charAt(ws._current_word.length()-1) != 's' && temp_saved == 2 && ws._current_word.length()%2 != 0){System.out.println("INSTANT WIN MOVE!!!! " + array[i] + " will always lead to " + ws._current_word);}
//					
//					System.out.println("the letters: " + array[i] + " have " + temp_saved +  " possible outcome(s).");
////					if(args.length > 1 && args[1].equals("v")){
//					System.out.println("all possible outcomes listed below:");
//					ws.setup(array[i]);
////					}
//				
//					
//					
//					
//							//+ " One of which is: " + ws._current_word );
////					System.out.println("these are: " + ws.setup(array[i]));
//				}
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public char maxOptions(String so_far) throws FileNotFoundException{
		WordSearch ws = new WordSearch();
		int temp;
		int temp2;
		int running_max2 = 0;
		int running_max = 0;
		char current = 0;
		char current2 = 0;
		for(char i = 97; i <= 122; i++){
			temp = ws.setup(so_far + (char)i, 2);
			temp2 = ws.setup((char)i + so_far, 2);
			if(temp > running_max){
				running_max = temp;
				current = (char)i;
				System.out.println("max options for adding letter to end is now " + (char)i + ", with " + running_max + " options");

			}
			if(temp2 > running_max2){
				running_max2 = temp2;
				current2 = (char)i;
				System.out.println("max options for adding letter to beginning is now " + (char)i + ", with " + running_max2 + " options");

			}
		}	
		
		if(running_max < running_max2)current = current2;
		return current;
				
	}
}
	
	

