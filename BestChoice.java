package code;

import java.io.FileNotFoundException;

public class BestChoice {
	
	public char best(String so_far) throws FileNotFoundException{
		int temp;
		int temp2;
		WordSearch ws = new WordSearch();
		int running_min = ws.setup(so_far + 'a', 2);
		int running_min2 = ws.setup('a' + so_far, 2);
		char running_best_char = 0;
		char running_best_char2 = 0;
		String position;
		
		String goal = "";
		for(char i = 97; i <= 122; i++){
			temp = ws.setup(so_far + (char)i, 2);
			if(temp < running_min && temp != 0){
				running_min = temp;
				running_best_char = (char)i;
				if(running_min == 2 || running_min == 1)goal = ws._current_word;
				System.out.println("running min for post is " + running_min + " achieved by placing the letter '" + (char)i + "'. " + ws._victory_percent + " would lead to a victory.");

				
				
			}
			
		}
		String goal2 = "";
		for(char i = 97; i <= 122; i++){
			temp2 = ws.setup((char)i + so_far, 2);
			if(temp2 < running_min2 && temp2 != 0){
				running_min2 = temp2;
				running_best_char2 = (char)i;
				if(running_min2 == 2 || running_min == 1)goal2 = ws._current_word;
				System.out.println("running min for pre is " + running_min2 + " achieved by placing the letter '" + (char)i + "'. " + ws._victory_percent + " would lead to a victory.");
				
			}
		}
		position = "undetermined";
		if(running_min2 <= running_min){
			goal = goal2;
			running_min = running_min2;
			running_best_char = running_best_char2;
			position = "before";
			}
//		if(running_min == running_min2)
		if(running_min2 > running_min){
			position = "after";
		}
//		if(position == "before"){
//			ws.setup((running_best_char + so_far));
//		}
//		else{
//			ws.setup((so_far + running_best_char)); 
//			
//		}
		
		if(running_min == 2 && goal.length()%2 != 0){
			System.out.println("The letter: '" + running_best_char + "' will guarantee a win. It can only lead to '" + goal + "'");
		}
//		if(temp2 < temp)return temp2;
		
		System.out.println("best char should be placed " + position + " to ensure " + goal);
		return running_best_char;
	}

}
