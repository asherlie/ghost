package code;

import java.io.FileNotFoundException;

public class GhostMethods {
	
	public String[] twoLetterCombos(){
		int x = 0;
		for(char i = 97; i <= 122; i++){
			for(char j = 97; j <= 122; j++){
				x++;
			}
		}
		String[] array = new String[x*2];
		int pos_from_middle_dec = x-1;
		int pos_from_middle_inc = x;
		for(char i = 97; i <= 122; i++){
			for(char j = 97; j <= 122; j++){
				array[pos_from_middle_dec--] = Character.toString((char)i) + Character.toString((char)j);
				array[pos_from_middle_inc++] = Character.toString((char)j) + Character.toString((char)i);
				
			}
		}
		return array;
	}

//		int x = 0;
//		for(int i = 97; i <= 122; i++){
//			for(int j = 97; j <= 122; j++){
//				x++;
//			}
//		}
//		
//		
//		String[] array = new String[x * 2];
//		int pos = 0;
//		for(int i = 97; i <= 122; i++){
//			for(int j = 97; j <= 122; j++){
//				array[pos++] = Character.toString((char)i) + Character.toString((char)j);
//			}
//		}
//		
//		int numba = x;
////		String tempstr = "";
//		for(int i = 0; i < x; i++){
////			tempstr = Character.toString(array[numba].charAt(1)) + Character.toString(array[numba--].charAt(0));
//			array[pos++] = Character.toString(array[numba-1].charAt(1)) + Character.toString(array[numba-1].charAt(0));
//			numba--;		
//			
//	}
//		return array;
//}
	
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
	
	public void possibilities(int number){
		GhostMethods g = new GhostMethods();
		String[] array = g.twoLetterCombos();
		WordSearch ws = new WordSearch();
		int temp_saved = 0;
		for(int i = 0; i < g.twoLetterCombos().length; i++){
			try {
				temp_saved = ws.setup(array[i], 2);
//				if(ws.setup(array[i], 2) != 0 && ws.setup(array[i], 2) <= Integer.parseInt(args[0])){
				if(temp_saved != 0 && temp_saved <= number){
					if(ws._current_word.charAt(ws._current_word.length()-1) != 's' && temp_saved == 2 && ws._current_word.length()%2 != 0){System.out.println("INSTANT WIN MOVE!!!! " + array[i] + " will always lead to " + ws._current_word);}
					
					System.out.println("the letters: " + array[i] + " have " + temp_saved +  " possible outcome(s).");
//					if(args.length > 1 && args[1].equals("v")){
					System.out.println("all possible outcomes listed below:");
					ws.setup(array[i]);
//					}
				
					
					
					
							//+ " One of which is: " + ws._current_word );
//					System.out.println("these are: " + ws.setup(array[i]));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
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
	
	

