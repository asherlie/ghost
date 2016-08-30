package code;

import java.io.FileNotFoundException;
//import java.util.ArrayList;
import java.util.Random;



public class BestMove {
//	BestMove s = new BestMove();
	boolean immutableBest = true;
//	double[][] highest_vp = new double[26][2];
	//immutableBest should be true if there is only one option after the first move
	
//	public int sameUntil(String[] possibilities){
//		//goal of this is to check if all of the strings in possibilities are the same
//			//until a certain point. this would make it very easy. i could use the same exact
//		//whereIs method, with the current word as part and the last place that all options are exactly the same
//		//as the full word. 
//		int until = 0;
//		for(int i = 0; i <  possibilities.length-1; i++){
//			for(int j = 0; j < possibilities[i].length(); j++){
//				if(possibilities[i].charAt(0) == possibilities[i+1].charAt(0) && possibilities[i].charAt(j) == possibilities[i+1].charAt(j)){
//					until = j;
//					System.out.println(j);
//				}
//				if(possibilities[i].charAt(j) != possibilities[i+1].charAt(j)){
//				System.out.println("fak");
//				}
//			}
//		}
////		for(int i = 0; i < possibilities.length; i++){
////			if(possibilities[i].charAt(0) == possibilities)
////		}
//		for(int i = 0; i < possibilities.length; i++){
//			System.out.println(possibilities[i]);
//		}
//		System.out.println("are all the same until index " + until);
//		return until;
//	}
	
	//whereIs mehtod is meant to create the illusion of a game in selfplay
	//once the winner has been prematurely found
	/**
	 whereIs takes full word and partial word as parameter. This method is used in the auto complete function of the SelfPlay class.
	 I can use the _current_word because this is only used if x == 1
	 
	 Basically just a for loop that iterates through the length of the full word. if full.charAt(i) is equal to the first char of
	 the part word and if full.charAt(i+1) == part.charAt(second), then i've determined the starting pos, which is i.
	 
	 To determine end pos i use a loop that goes until length-1 of the full word, if charAt(i) == part.length-2, minus two so i have room 
	 to check the next digit, and if charat i+1 == part.length-1, i've determinedd it ends at index i+1 of the full word.
	 
	 whereIs returns, as an array of type int, the start index of the part word as compared to the full word in index 0
	 and the end index of the part word as compared to the full word in index 1
	 */
	
	public int[] whereIs(String full, String part){
		int x = 0;
		int y = 0;
//		the loop below should possibly go to full.length()-1
		//i changed it for good because of the word hexylresorcinol starting at 'r'
		//and similar IOOB exceptions
//		for(int i = 0; i < full.length(); i++){

		for(int i = 0; i < full.length()-1; i++){
			if(full.charAt(i) == part.charAt(0) && full.charAt(i+1) == part.charAt(1)){
				System.out.println(part + " starts at " + full + " sub " + i);
				x = i;
			}
			if(full.charAt(i) == part.charAt(part.length()-2) && full.charAt(i+1) == part.charAt(part.length()-1)){
				System.out.println("and ends at " + (i+1));
				y = i+1;
			}
		}
		int[] answ = {x, y};
		return answ;
	}
	
	/**
	 * 
	 * @param so_far
	 * @param player
	 * @return char[]
	 * @throws FileNotFoundException
	 * 
	 best(so_far, player#) keeps track of a running highest vp for pre and post. If player == 1, _vp is used for running_highest_vp
	 and if player == 2, _vp_for_player2 is used.
	 */
	public char[] best(String so_far, int player) throws FileNotFoundException{
		KeepingTrack kt = new KeepingTrack();
		//the reason kt doesn't work is that i create a new intance of best every time i calculate a best char. i can migrate it to the selfplay class
		//if so_far.length == 1 kt.highestvp.add(x++)
//		kt.setHighestVP(20, 4, 0);

		
		//below populates highest vp with the value 0
//		if(so_far.length() == 1){
//			for(int i = 0; i < highest_vp.length; i++){
//				highest_vp[i][0] = 0;
//			}
//		}

//		BestMove b = new BestMove();
		//^^commented this out when i moved the storage of game history in keepingtrack class
		
		//max vars for worst case scenario
		int max_options = 0;
		int max_options_post = 0;
		int max_options_pre = 0;
		char max_options_char = 0;
		char max_options_post_char = 0;
		char max_options_pre_char = 0;
		String max_options_pos = "undetermined";
//		String max_options_pre_pos = "undetermined";
		
		//running vars for post
		int running_min_post = 2147483647;
		char running_min_post_char = 0;
		int temp_post = 0;
		double running_highest_vp_post = 0;
		char running_highest_vp_post_char = 0;

		//running vars for pre
		int running_min_pre	= 2147483647;
		char running_min_pre_char = 0;
		int temp_pre = 0;
		double running_highest_vp_pre = 0;
		char running_highest_vp_pre_char = 0;

		//final vars below
		
//		@SuppressWarnings("unused")
		int min_final = 2147483647;
		
		char min_char_final = 0;
		double highest_vp_final = 0;
		char highest_vp_char = 0;
		char final_char = 0;
		
		//position vars
		String position = "undetermined";
		String position_vp = "undetermined";
		
		//random for boolean values
		Random randBoo = new Random();
		boolean booVal;
		
		//return value array - [0]=rec letter [1]=position(B/A)
		char[] answer = new char[3];

		WordSearch ws = new WordSearch();
		for(char i = 97; i <= 122; i++){
			temp_post = ws.setup(so_far + (char)i, 2);
//			ws.setup(so_far + (char)i, 2);
			
			//MAKES NO FUCKING SENSE. WHY WOULD I USE MAX OPTIONS. AS OF NOW, IT DEFAULTS TO MAX OPTIONS 
			
			if(temp_post > max_options_post){
				max_options_post = temp_post;
				max_options_post_char = (char)i;
				max_options_pos = "AFTER";
			}
			booVal = randBoo.nextBoolean();
//			if(temp_post != 0 && temp_post == max_options_post){
				
			if(temp_post != 0 && temp_post == max_options_post && booVal){
//				if(booVal){
				max_options_post = temp_post;
				max_options_post_char = (char)i;
				max_options_pos = "AFTER";
				
					
//				}

			}
			
			//5 if statements below handle post
			if(temp_post <  running_min_post && temp_post != 0){
//				System.out.println("running min post is now " + temp_post + " with " + (char)i);
				running_min_post = temp_post;
				running_min_post_char = (char)i;
			}
			if(player == 1 && ws._vp > running_highest_vp_post){
				running_highest_vp_post = ws._vp;
				running_highest_vp_post_char = (char)i;
//				System.out.println("highest vp incremented for p1 to " + running_highest_vp_post);

//				System.out.println("highest vp incremented for p1 to " + running_highest_vp_post + "with" + running_highest_vp_post_char);

			}
			//below is adding a feature to make the running max random between options of equal vp

//			if(player == 1 && ws._vp == running_highest_vp_post && running_highest_vp_post != 0 && randBoo.nextBoolean()){
			if(player == 1 && ws._vp == running_highest_vp_post && running_highest_vp_post != 0){
				int count1 = 0;
				//the outer sub was originally i-97. should not be so i dont think
				if(ws._vp == kt.getHighestVP()[so_far.charAt(0)-97][0] && so_far.length() == 1){
//					System.out.println("count is " + count);
					count1++;
				}
				if(count1 > 1){
					immutableBest = false;
					System.out.println("there is more than one option with the same highest vp in post and pre comparison");

				}
				//moved booval out of outer if so i can change immutableBest to false since there are more than one 
				//of the same option
//				if(so_far.length() == 1){
//				immutableBest = false;
//				System.out.println("there is more than one option with the same vp");
//				}

				//the fact that it's up to a random decision means that it's not immutable
				if(randBoo.nextBoolean()){
//					System.out.println("vp post highest == current word vp and its gonna be used");
					running_highest_vp_post = ws._vp;
					running_highest_vp_post_char = (char)i;
				}
				
			}
			if(player == 2 && ws._vp_for_player2 > running_highest_vp_post){
				running_highest_vp_post = ws._vp_for_player2;
				running_highest_vp_post_char = (char)i;
//				System.out.println("highest vp incremented for p2 to " + running_highest_vp_post + "with" + running_highest_vp_post_char);


			}
			//below is adding a feature to make the running max random between options of equal vp
//			if(player == 2 && ws._vp_for_player2 == running_highest_vp_post && running_highest_vp_post != 0 && randBoo.nextBoolean()){
			if(player == 2 && ws._vp_for_player2 == running_highest_vp_post && running_highest_vp_post != 0){
				int count2 = 0;
				//was originally i -97
				if(ws._vp_for_player2 == kt.getHighestVP()[so_far.charAt(0)-97][0] && so_far.length() == 1){
					count2++;
				}
				if(count2 > 1){
					immutableBest = false;
					System.out.println("there is more than one option with the same highest vp in running_highest_post");

				}
			//				System.out.println("vp post highest == current word vp and its gonna be used");
				
				//moved booval out of outer if so i can change immutableBest to false since there are more than one 
				//of the same option
//				if(so_far.length() == 1){
//				immutableBest = false;
//				System.out.println("there is more than one option with the same vp");
//				}
					
				if(randBoo.nextBoolean()){
					running_highest_vp_post = ws._vp_for_player2;
					running_highest_vp_post_char = (char)i;
				}
			}
			
			
			temp_pre = ws.setup((char)i + so_far, 2);
			
			if(temp_pre > max_options_pre){
				max_options_pre = temp_pre;
				max_options_pre_char = (char)i;
//				max_options_pre_pos = "BEFORE";
			}
			if(temp_pre != 0 && temp_pre == max_options_pre && randBoo.nextBoolean()){				
			max_options_pre = temp_pre;
			max_options_pre_char = (char)i;
//			max_options_pre_pos = "BEFORE";

			}

			
			//5 if statements below handle pre
			if(temp_pre < running_min_pre && temp_pre != 0){
				running_min_pre = temp_pre;
				running_min_pre_char = (char)i;
			}
			if(player == 1 && ws._vp > running_highest_vp_pre){
				running_highest_vp_pre = ws._vp;
				running_highest_vp_pre_char = (char)i;
			}
			//if below handles equality
//			if(player == 1 && ws._vp == running_highest_vp_pre && running_highest_vp_pre != 0 && randBoo.nextBoolean()){
			if(player == 1 && ws._vp == running_highest_vp_pre && running_highest_vp_pre != 0){
				int count3 = 0;
				//was originlaly i-97. should not be so.
				if(ws._vp == kt.getHighestVP()[so_far.charAt(0)-97][0] && so_far.length() == 1){
					count3++;
				}
				if(count3 > 1){
					immutableBest = false;
					System.out.println("there is more than one option with the same highest vp in running_highest_pre");
				}
//				if(so_far.length() == 1){
//					immutableBest = false;
//					System.out.println("there is more than one option with the same vp");
//				}
				
//				System.out.println("vp pre highest == current word vp and its gonna be used");
				if(randBoo.nextBoolean()){
					running_highest_vp_pre = ws._vp;
					running_highest_vp_pre_char = (char)i;
				}
				
			}

			if(player == 2 && ws._vp_for_player2 > running_highest_vp_pre){
				running_highest_vp_pre = ws._vp_for_player2;
				running_highest_vp_pre_char = (char)i;
//				System.out.println("running highest vp pre incremented to " + ws._vp_for_player2);
			}
			//if below handles equality
//			if(player == 2 && ws._vp_for_player2 == running_highest_vp_pre && running_highest_vp_pre != 0 && randBoo.nextBoolean()){
			if(player == 2 && ws._vp_for_player2 == running_highest_vp_pre && running_highest_vp_pre != 0){
		//				System.out.println("vp pre highest == current word vp and its gonna be used");
				int count4 = 0;
				//was originally i -97. should not be so
				if(ws._vp_for_player2 == kt.getHighestVP()[so_far.charAt(0)-97][0] && so_far.length() == 1){
					count4++;
				}
				if(count4 > 1){
					immutableBest = false;
					System.out.println("there is more than one option with the same highest vp in running_highest_pre");
				}
//				if(so_far.length() == 1){
//					immutableBest = false;
//					System.out.println("there is more than one option with the same vp");
//				}
				
					
					
				if(randBoo.nextBoolean()){
					running_highest_vp_pre = ws._vp_for_player2;
					running_highest_vp_pre_char = (char)i;
				}
				
			}
		}
		
		//uncomment the below if i want the highest vp array to print out 
//		if(so_far.length() == 1){
//		for(int i =0; i < kt.getHighestVP().length; i++){
//			for(int j = 0; j < kt.getHighestVP()[i].length; j++){
////				System.out.println(b.getHighestVP());
//				System.out.println("highest_vp[" + i + "][" + j + "] is " + kt.getHighestVP()[i][j]);
//			}
//		}
//		}
		

		
		
		
		
		if(running_highest_vp_post > running_highest_vp_pre){
			highest_vp_final = running_highest_vp_post;
			highest_vp_char = running_highest_vp_post_char;
			position_vp = "AFTER";
		}
		if(running_highest_vp_pre > running_highest_vp_post){
			highest_vp_final = running_highest_vp_pre;
			highest_vp_char = running_highest_vp_pre_char;
			position_vp = "BEFORE";
		}
		

		//randomly choose if they're equal
		if(running_highest_vp_pre == running_highest_vp_post){
			highest_vp_final = running_highest_vp_pre;
			//doesnt matter which i put above
			if(so_far.length() == 1){
				immutableBest = false;
				System.out.println("there is more than one option with the same vp in highest_vp_pre == highest_vp_post");
			}

			
			Random rand = new Random();
			int ra = rand.nextInt(2);
			if(ra == 0){
				highest_vp_final = running_highest_vp_post;
				highest_vp_char = running_highest_vp_post_char;
				position_vp = "AFTER";
			}
			if(ra == 1){
				highest_vp_final = running_highest_vp_pre;
				highest_vp_char = running_highest_vp_pre_char;
				position_vp = "BEFORE";
			}
		}
		
		//this stores the value of the highest vp in the index of the first letter of word so far if its only on first letr
		if(so_far.length() == 1){
//			System.out.println("starts at " + highest_vp[(so_far.charAt(0)-97)][0]);
			//found that it starts at 0
//			highest_vp[(so_far.charAt(0)-97)][0] = highest_vp_final; migrated to getters and setters
			kt.setHighestVP(highest_vp_final, so_far.charAt(0)-97, 0);
//			System.out.println("sub 0 should be " + highest_vp_final);
//			highest_vp[(so_far.charAt(0)-97)][1]++;
			//incrementing using getters/setters instead to see if it sticks
			kt.setHighestVP(kt.getHighestVP()[so_far.charAt(0)-97][1]+1, (so_far.charAt(0)-97), 1);
			System.out.println(so_far.charAt(0) + " has occured as a first char " + kt.getHighestVP()[(so_far.charAt(0)-97)][1] + " times");
//			highest_vp[(so_far.charAt(0)-97)][1] = highest_vp[(so_far.charAt(0)-97)][1] + 1;
;

		}
//		System.out.println(highest_vp[(so_far.charAt(0)-97)][1]);
//		if(highest_vp[so_far.charAt(0)-97][1] <= 1){
		// == 1 or == 0; idk how 1 works, zero was the oritinal and worked semi ok
		
		//i think i found the root of my issue. shouldnt be i-97 anywhere
		if(kt.getHighestVP()[so_far.charAt(0)-97][1] == 1 && so_far.length() == 1){
			//if it's equal to its default value. i can now take out all of the shit relating to count incremnted variable
			//if this is the first occurence of the letter, dont use the best
			System.out.println("first occurence");
			//it's getting caught here every tiem
			immutableBest = false;
		}

		
		if(running_min_post < running_min_pre){
			position = "AFTER";
			min_final = running_min_post;
			min_char_final = running_min_post_char;
			
			
		}
		if(running_min_pre < running_min_post){
			position = "BEFORE";
			min_final = running_min_pre;
			min_char_final = running_min_pre_char;
		}
		
		//randomly choose if they're equal
		if(running_min_pre == running_min_post){
			Random rand = new Random();
			int ran = rand.nextInt(2);
			if(ran == 0){
				position = "BEFORE";
				min_final = running_min_pre;
				min_char_final = running_min_pre_char;
			}
			if(ran == 1){
				position = "AFTER";
				min_final = running_min_post;
				min_char_final = running_min_post_char;
			}

		}
		
		if(position == "AFTER"){
			//i set this up so i can compare vp's
			ws.setup(so_far + min_char_final, 2);
		}
		
		if(position == "BEFORE"){
			//i set this up so i can compare vp's
			ws.setup(min_char_final + so_far, 2);
		}
		double vp_for_comp = 0;
		if(player == 1)vp_for_comp = ws._vp;
		if(player == 2)vp_for_comp = ws._vp_for_player2;

		if(highest_vp_final >= vp_for_comp){
			System.out.println("ignoring min");
			final_char = highest_vp_char;
			position = position_vp;
		}
		else{
			final_char = min_char_final;
		}
		
		//if all else fails make the answer the max options. i mean if theres is no max vp if its zero
//
		answer[0] = final_char;
		//Uppercase a or b to indicate position
		answer[1] = position.charAt(0);
		if(so_far.length() == 1 && immutableBest){
			System.out.println("immutable best is true for" + so_far);
			answer[2] = 'A';
			}
		if(so_far.length() == 1 && !immutableBest){
			System.out.println("immutable best is false for " + so_far);

			answer[2] = 'N';
			}
		//my issue is that it sets answer[2] to 'N' in all other cases. if so_far is more than one char long, 
//		else{answer[2] = 'N';}
		//for a 'z' after the word in progress, which is the second move and is the only move possible it would be
				//z A A
		if(highest_vp_final == 0 && vp_for_comp == 0){
//				System.out.println("Option with highest percentage of victory for player " + player + " 0%. Recommending choice with most possibilities to postpone the inevitable.");
				if(max_options_pre_char > max_options_post_char){
					max_options_char = max_options_pre_char;
					max_options_pos = "BEFORE";
				}
				if(max_options_post_char > max_options_pre_char){
					max_options_char = max_options_post_char;
					max_options_pos = "AFTER";

				}
				if(max_options_post_char == max_options_pre_char && randBoo.nextBoolean() && max_options_post != 0){
					max_options_char = max_options_post_char;
					max_options_pos = "AFTER";
				
				}
				if(max_options == min_final){
					System.out.println("Player " + player + " will not win.");
				}
			
				answer[0] = max_options_char;
				answer[1] = max_options_pos.charAt(0);
		}	

//		if(immutableBest){
//			
//		}
//		answer[2] = 'A';
		return answer;

	}
	
//	public double[][] getHighestVP(){
//		return highest_vp;
//	}
//	public void setHighestVP(double value, int outer, int inner){
//		highest_vp[outer][inner] = value; 
//		System.out.println("highest_vp sub " + outer + " sub " + inner + " has been set to " + value);
//	}
	
	public boolean alwaysSame(){
		return immutableBest;
	}
//	public char[] alwaysOrNot(){
//		char[] answer = new char[3];
//		answer[0] = 'N';
//		if(immutableBest){
//			answer[0] = 'A';
//			
//		}
//		
//		return answer;
//	}
}
