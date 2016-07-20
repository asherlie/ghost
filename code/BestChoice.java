package code;

import java.io.FileNotFoundException;

public class BestChoice {
	
	public char[] best(String so_far) throws FileNotFoundException{
		int temp;
		int temp2;
		WordSearch ws = new WordSearch();
		int running_min = ws.setup(so_far + 'a', 2);
		int running_min2 = ws.setup('a' + so_far, 2);
		char running_best_char = 0;
		char running_best_char2 = 0;
		double running_highest_vp = 0;
		double running_highest_vp2 = 0;
		char running_highest_vp_char = 0;
		char running_highest_vp2_char = 0;



		String position;
		
		String goal = "";
		for(char i = 97; i <= 122; i++){
			temp = ws.setup(so_far + (char)i, 2);
			if(temp < running_min && temp != 0){
				running_min = temp;
				running_best_char = (char)i;
				if(running_min == 2 || running_min == 1)goal = ws._current_word;
				if(ws._vp > running_highest_vp){
					running_highest_vp = ws._vp;
					running_highest_vp_char = (char)i;
					//make sure to account for s's
				}
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
				
				if(ws._vp > running_highest_vp2){
					running_highest_vp2 = ws._vp;
					running_highest_vp2_char = (char)i;
					//make sure to account for s's
				}

				System.out.println("running min for pre is " + running_min2 + " achieved by placing the letter '" + (char)i + "'. " + ws._victory_percent + " would lead to a victory.");
				
			}
		}
		position = "undetermined";
		if(running_min2 <= running_min){
			goal = goal2;
			running_min = running_min2;
			running_best_char = running_best_char2;
			position = "before";
			ws.setup(running_best_char + so_far, 2);
			//the line above is only so that I can compare victory percentages in the current instance to the highest

			}
//		if(running_min == running_min2)
		if(running_min2 > running_min){
			position = "after";
			ws.setup(so_far + running_best_char, 2);
			//the line above is only so that I can compare victory percentages in the current instance to the highest


		}
//		if(position == "before"){
//			ws.setup((running_best_char + so_far));
//		}
//		else{
//			ws.setup((so_far + running_best_char)); 
//			
//		}
		//2 before 
		//1 after
		String position_vp = "undetermined";
		if(running_highest_vp2 > running_highest_vp){
			running_highest_vp = running_highest_vp2;
			position_vp = "before";
			running_highest_vp_char = running_highest_vp2_char;
		}
		else{
			position_vp = "after";
		}
		
		if(running_min == 2 && goal.length()%2 != 0){
			System.out.println("The letter: '" + running_best_char + "' will guarantee a win. It can only lead to '" + goal + "'");
		}

		//if the lowst amount of options is not equal to two, which would mean a word and that word with an s, which is really one if you
		//start int he middle of the word because you wouldn't get to the 's' || or if the lenght of the goal word wouldnt guarantee a win
		//and if highest victory percentage that we've found is higher than that of the running min of options
		//add to end of if statement maybe. it might be redundant || goal.length()%2 == 0)
		//and maybe add if guaranteed win is false, a boolean value i can make
		if(running_min != 2 && running_highest_vp > ws._vp){
			running_best_char = running_highest_vp_char;
			position = position_vp;
			System.out.println("the character that would lead to the minimum number of possibilities would not lead to victory. We must recommend the character with the highest percent chance of leading to a win");
		}
		
//		if(temp2 < temp)return temp2;
		
		System.out.println("best char should be placed " + position + " to ensure " + goal);
		if(goal.length()%2 == 0)System.out.println("but it will not guarantee a win in a two person game");
		
		char[] running_best_char_and_position = new char[2];
		running_best_char_and_position[0] = running_best_char;
		if(position.equals("before"))running_best_char_and_position[1] = 'B';
		if(position.equals("after"))running_best_char_and_position[1] = 'A';

		return running_best_char_and_position;
	}

}
