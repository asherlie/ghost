package code;

//import java.io.FileNotFoundException;

public class BestChoice {
	/**
	public char[] best(String so_far, int player) throws FileNotFoundException{
		int temp;
		int temp2;
		WordSearch ws = new WordSearch();
		//the reason it doesnt work after a while is below. once so_far is longer, and ws.setup with a appended after is run, the running min is set 0
		//possibly try not initializing the variable. if this doesn't give me an error, 
		//if that doesn't compile, just initialize it with the maximum value of an 32 bit int. either option would be faster than what is currently
		//utilized, and even better, would actually work.
//		int running_min = ws.setup(so_far + 'a', 2);
//		int running_min2 = ws.setup('a' + so_far, 2);
		int running_min = 2147483647;
		int running_min2 = 2147483647;
		char running_best_char = 0;
		char running_best_char2 = 0;
		double running_highest_vp = 0;
		double running_highest_vp2 = 0;
		char running_highest_vp_char = 0;
		char running_highest_vp2_char = 0;

		//commented out the block of code below to opt for the max int possible
		//the outer if statement might be redundant
//		int num = 97;
//		if(running_min == 0){
//			while(running_min == 0 && num <= 122){
//				running_min = ws.setup(so_far + (char)num++, 2);
//				if(running_min != 0)break;
//
//				System.out.println("post is zero");
//			}
//		}
//		if(running_min == 0)System.out.println("there are no options for post additions");
//		
//		int num2 = 97;
//		if(running_min2 == 0){
//			while(running_min2 == 0 && num2 <= 122){
//				running_min2 = ws.setup((char)num2++ + so_far, 2);
//				if(running_min2 != 0)break;
//				System.out.println("pre is zero");
//			}
//		}
//		if(running_min2 == 0)System.out.println("there are no options for pre additions");




		String position;
		
		String goal = "";
		for(char i = 97; i <= 122; i++){
			temp = ws.setup(so_far + (char)i, 2);
			if(temp < running_min && temp != 0){
				//i can take out if temp != 2 from first if below
				if(temp != 2 || ws._vp > 0){
					if(temp == 2 && ws.isAchievableS(ws._current_word)){
						running_min = 1;
						running_best_char = (char)i;
					}
					running_min = temp;
					running_best_char = (char)i;
				}
//				if(running_min == 2 || running_min == 1){
				
					if(player == 1){
						//first part of if is redundant below
						if(running_min == 2 && ws.isAchievableS(ws._current_word) && ws._current_word.length()%2 == 0){
							running_min = 1;
							running_best_char = (char)i;
							goal = ws._current_word;

						//if there are two options and the winnign one is reachable
						// i could rewrite this with my isWord method
						}
						else{
							running_highest_vp = ws._vp;
							//default to vp
						}
					}
					if(player == 2){
						if(running_min == 2 && ws.isAchievableS(ws._current_word) && ws._current_word.length()%2 != 0){
							running_min = 1;
							running_best_char = (char)i;
							goal = ws._current_word;

						}
						else{
							running_highest_vp = ws._vp_for_player2;
						}
					}
//					goal = ws._current_word;
				
				
				//moved the block below that generates running highest vps and such
				if(player == 1){
					if(ws._vp > running_highest_vp){
						running_highest_vp = ws._vp;
						running_highest_vp_char = (char)i;
						//make sure to account for s's
				}
					if(ws._vp == running_highest_vp){
						//write somethign here to save it if it's
						//the same vp. this would help me implement the
						//randomness idea
					}
				}
				if(player == 2){
//					if((1 - ws._vp) > running_highest_vp){
					if(ws._vp_for_player2 > running_highest_vp){
						running_highest_vp = ws._vp_for_player2;
						running_highest_vp_char = (char)i;
					}
				}
				if(player == 1){
//					System.out.println("player one's turn");
					System.out.println("running min for post for p1 is " + running_min + " achieved by placing the letter '" + (char)i + "'. " + ws._victory_percent + " would lead to a victory.");
				}
				if(player == 2){
//					System.out.println("player two's turn");
					System.out.println("running min for post for p2 is " + running_min + " achieved by placing the letter '" + (char)i + "'. "  + ws._victory_percent_for_player2 + " would lead to a victory.");

				}

				
			}
			
		}
		String goal2 = "";
		for(char i = 97; i <= 122; i++){
			temp2 = ws.setup((char)i + so_far, 2);

			if(temp2 < running_min2 && temp2 != 0){
				if(temp2 != 2 || ws._vp > 0){
					if(temp2 == 2 && ws.isAchievableS(ws._current_word)){
						running_min2 = 1;
						running_best_char2 = (char)i;
					}
					//i'm going line by line copying my corrections
					//from the first for post to pre. i stopped here for sleep
					running_min2 = temp2;
					running_best_char2 = (char)i;
				}
//				if(running_min2 == 2 || running_min == 1){
					if(player == 1){
						if(running_min2 == 2 && ws.isAchievableS(ws._current_word) && ws._current_word.length()%2 == 0){
							running_min2 = 1;
							running_best_char2 = (char)i;
							goal2 = ws._current_word;

						}
						else{
							running_highest_vp2 = ws._vp;
						}
					}
					if(player == 2){
						if(running_min2 ==2 && ws.isAchievableS(ws._current_word) && ws._current_word.length()%2 != 0){
							running_min2 = 1;
							running_best_char2 = (char)i;
							goal2 = ws._current_word;

						}
						else{
							running_highest_vp2 = ws._vp_for_player2;
						}
					}
//					goal2 = ws._current_word;
				
				
				if(player == 1){
					if(ws._vp > running_highest_vp2){
						running_highest_vp2 = ws._vp;
						running_highest_vp2_char = (char)i;
						//make sure to account for s's
					}
					System.out.println("running min for pre for p1 is " + running_min2 + " achieved by placing the letter '" + (char)i + "'. " + ws._victory_percent + " would lead to a victory.");

				}
				if(player == 2){
//					if((1 - ws._vp) > running_highest_vp2){
//if(ws._vp > running_highest_vp && (ws._current_word.charAt(ws._current_word.length()-1) != 's' && temp != 2)){

					if(ws._vp_for_player2 > running_highest_vp2){
						running_highest_vp2 = ws._vp_for_player2;
						running_highest_vp2_char = (char)i;
//						System.out.println("player two's turn. vp subtracted from one");
//						running_highest_vp2 = (1 - ws._vp);
//						running_highest_vp2_char = (char)i;

					}
					System.out.println("running min for pre for p2 is " + running_min2 + " achieved by placing the letter '" + (char)i + "'. "  + ws._victory_percent_for_player2 + " would lead to a victory.");
				}

				
			}
		}
		position = "undetermined";
		if(running_min2 <= running_min && running_min2 != 0){
			goal = goal2;
			running_min = running_min2;
			running_best_char = running_best_char2;
			position = "before";
			ws.setup(running_best_char + so_far, 2);
			//the line above is only so that I can compare victory percentages in the current instance to the highest

			}
//		if(running_min == running_min2)
		if(running_min2 > running_min && running_min != 0){
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
		
//		if(running_min == 2 && goal.length()%2 != 0){
//			System.out.println("The letter: '" + running_best_char + "' will guarantee a win. It can only lead to '" + goal + "'");
//		}

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
//		if(running_best_char <= 2){
		
		if(running_min <= 2){
		System.out.println("best char should be placed " + position + " to ensure " + goal);
			if(goal.length()%2 == 0)System.out.println("but it will not guarantee a win in a two person game");

		}
		else{
			System.out.println("because there are no moves that would narrow options down to one, the recommended move is"
					+ " the one with the highest percentage chance of victory");
		}
		
		if(running_min == 0){
			System.out.println("the code has severely fucked up");
		}
		
		char[] running_best_char_and_position = new char[2];
		running_best_char_and_position[0] = running_best_char;
		
		if(position.equals("before"))running_best_char_and_position[1] = 'B';
		if(position.equals("after"))running_best_char_and_position[1] = 'A';
		
		return running_best_char_and_position;
	}
*/

}
