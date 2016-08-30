package code;
//make this recursive at some point
import java.io.FileNotFoundException;
import java.util.Random;

public class SelfPlay {
	char[][] _guarantees = new char[26][3];
//	public int play() throws FileNotFoundException{
	public String[] play() throws FileNotFoundException{

		
		KeepingTrack kt = new KeepingTrack();
		
		long startTime = System.nanoTime();
		
		String so_far = "";
		int whosTurn = 1;
		Random rand = new Random();
		int value = rand.nextInt(26);
		value = value + 97;
		whosTurn++;
		//we increment whosTurn because choosing a random first letter was p1's first turn.
		
		so_far = (char)value + "";
//		so_far = "f";
		kt.addOccurence(so_far.charAt(0));
		System.out.println("game has been started by player one with the letter " + (char)value);
//		BestChoice be = new BestChoice();
		BestMove be = new BestMove();
//		be.best(so_far);
		
		char[] temp = new char[3];
		temp[1] = 'A';
		//just as a reminder, temp[1] now represents before or after
		//'a' is arbitray. i just needed to populate the array so the loop below would work
		//because of the updated contents of the while loop, 'A' is no longer arbitrary.
		
//		temp = be.best(so_far);
		
		String player = "";
		int plyr = 1;
//		while(be.best(so_far) != null){
//		while(temp.length > 0){
		WordSearch check_if_word = new WordSearch();
		//add anotehr criteria for the while loop. break if so_far == a word on list
		//what i'm doing now will make the list generation a standalone method
//		while(temp[1] == 'A' || temp[1] == 'B' && !(check_if_word.isWord(so_far, check_if_word.wordFileToArray()))){
		
//		while(!check_if_word.isWord(so_far, check_if_word.wordFileToArray()) && check_if_word.setup(so_far, 2) != 0){	
//		while(!check_if_word.isWord(so_far, check_if_word.wordFileToArray())){	
//		int charAt = 0;
		int x = 1;
		// just making sure (x != 0)
		
//		String eOd = "";
		
		while(!check_if_word.isWord(so_far, check_if_word.wordFileToArray()) && x != 0){	
			x = check_if_word.setup(so_far, 2);
			
//			migrated the if below and took away the else statement for seemingly no reason. idk why the fuck i did thsi
//			if(so_far.length() == 1 && this._guarantees[(so_far.charAt(0)-97)][2] == 'A'){
			//d as in done D
			//trying it without this.guarantees, just guarantees
			//originally all of the _guarantees had this. before them
			//migrated from below to more responsible separate class for storage
//			if(so_far.length() == 1 && this._guarantees[(so_far.charAt(0)-97)][2] == 'D'){
//			if(so_far.length() == 1)kt.setGuarantees(so_far.charAt(0), value, BoA, DoN);
			if(so_far.length() == 1 && kt.getGuarantees()[so_far.charAt(0)-97][2] == 'D'){

				System.out.println("using guarantees from earlier");
//				if(this._guarantees[(so_far.charAt(0)-97)][1] == 'A')so_far = so_far + this._guarantees[(so_far.charAt(0)-97)][0];
//				if(this._guarantees[(so_far.charAt(0)-97)][1] == 'B')so_far = this._guarantees[(so_far.charAt(0)-97)][0] + so_far;
				if(kt.getGuarantees()[(so_far.charAt(0)-97)][1] == 'A')so_far = so_far + kt.getGuarantees()[(so_far.charAt(0)-97)][0];
				if(kt.getGuarantees()[(so_far.charAt(0)-97)][1] == 'B')so_far = kt.getGuarantees()[(so_far.charAt(0)-97)][0] + so_far;

				
				if(whosTurn%2 == 0)player = "two";
				if(whosTurn%2 != 0)player = "one";
				System.out.println("player "+ player + " has put down '" + kt.getGuarantees()[(so_far.charAt(0)-97)][0] + "' the word in progress is now " + so_far);


				whosTurn++;
			}
	
			else{
			
			if(x > 1){
				if(whosTurn % 2 == 0){
					plyr = 2;
				}
				if(whosTurn % 2 != 0){
					plyr = 1;
				}
			
				temp = be.best(so_far, plyr);
				//i should try to move the block below to above the if(x > 1){}. might not be reaching this for some reason
//				if(so_far.length() == 1 && temp[2] == 'N' && _guarantees[(so_far.charAt(0)-97)][0] != temp[0]){
//				if(so_far.length() == 1 && temp[2] == 'A' && _guarantees[(so_far.charAt(0)-97)][0] != temp[0]){
				
//				if(so_far.length() == 1 && this._guarantees[(so_far.charAt(0)-97)][2] == 'A'){
				//d as in done D
				//trying it without this.guarantees, just guarantees
				//originally all of the _guarantees had this. before them
//				if(so_far.length() == 1 && this._guarantees[(so_far.charAt(0)-97)][2] == 'D'){
//					//if the lenght is one and the saved value has already been added to the array in the corresponding
//					//loation, shown by a 'D' in _guarantees[i][2]
//					System.out.println("using guarantees from earlier");
//					if(this._guarantees[(so_far.charAt(0)-97)][1] == 'A')so_far = so_far + this._guarantees[(so_far.charAt(0)-97)][0];
//					if(this._guarantees[(so_far.charAt(0)-97)][1] == 'B')so_far = this._guarantees[(so_far.charAt(0)-97)][0] + so_far;
//
//					if(whosTurn%2 == 0)player = "two";
//					if(whosTurn%2 != 0)player = "one";
//					System.out.println("player "+ player + " has put down '" + this._guarantees[(so_far.charAt(0)-97)][0] + "' the word in progress is now " + so_far);
//
//
//					whosTurn++;
//				}

				
				
//				if(so_far.length() == 1 && temp[2] == 'A' && _guarantees[(so_far.charAt(0)-97)][0] != temp[0]){
				if(so_far.length() == 1 && temp[2] == 'A' && kt.getGuarantees()[so_far.charAt(0)-97][0] != temp[0]){
				
					// the reason i'm doing this is that 'A' indicates that it is the only option
					//maybe take out the last criteria. i dont see why it's necessary 
					//nvm it is, otherwise it would add a guarantee everytime it came across an immutable best. now it
					//makes sure it ahsnt been added yet
			//maybe add if(temp[2] != 'A' && temp[2] != 'N') because if it's n that means it will never be a guarantee
					System.out.println("adding a guarantee");
					//the reason i subtract 96 
					
//					_guarantees[(so_far.charAt(0)-97)] = temp;
//					_guarantees[(so_far.charAt(0)-97)][2] = 'D';
					//subtraction is done in the setGuarantees method, and what a sexy method it is
					kt.setGuarantees(so_far.charAt(0), temp[0], temp[1], 'D');

					
//					_guarantees[(temp[0]-97)][0] = temp[0];
//					_guarantees[(temp[0]-97)][1] = temp[1];
//					_guarantees[(temp[0]-97)][2] = temp[2];
				}
				//before or after
				if(temp[1] == 'A')so_far = so_far + temp[0];
				if(temp[1] == 'B')so_far = temp[0] + so_far;
//				temp = be.best(so_far);
				if(whosTurn%2 == 0)player = "two";
				if(whosTurn%2 != 0)player = "one";
				System.out.println("player "+ player + " has put down '" + temp[0] + "' the word in progress is now " + so_far);
				whosTurn++;
//				charAt++;
//				if(temp[1] == '0')break;
			}
			//trying to write something below that says if there are two options of the same length, auto write them until 
			//the last char. for example, equimolal and equimolar
//			if(x == 2 && check_if_word.)
			
			if(x == 1){
				int[] whereAmI = be.whereIs(check_if_word._current_word, so_far);
				int firstPos = whereAmI[0];
				int secondPos = whereAmI[1];

				for(int i = 0; i < check_if_word._current_word.length(); i++){
					if(whosTurn%2 == 0)player = "two";
					if(whosTurn%2 != 0)player = "one";
//					if(whereAmI[0] == 0){
//						//if it starts at the beginning of the word to be
//						
//					}
					if(i < whereAmI[0]){
//						System.out.println("player "+ player + " has put down '" + check_if_word._current_word.charAt(firstPos-1) + "' the word in progress is now " + so_far);

						so_far = check_if_word._current_word.charAt(firstPos-- -1) + so_far;
						whosTurn++;
						System.out.println("player " + player + " has put down '" + check_if_word._current_word.charAt(firstPos) + "' the word in progress is now " + so_far);
					}
					
					if(i > whereAmI[1]){
						so_far = so_far + check_if_word._current_word.charAt(secondPos++ + 1);
						whosTurn++;
						System.out.println("player "+ player + " has put down '" + check_if_word._current_word.charAt(secondPos) + "' the word in progress is now " + so_far);

					}
//					whosTurn++;


				}
//				if(whosTurn % 2 == 0){
//					plyr = 2;
//				}
//				if(whosTurn % 2 != 0){
//					plyr = 1;
//				}
//					WHAT I WAS TRYING TO DO BELOW, WITH INCREMENTING A CHARAT VARIABLE TO GIVE THE ILLUSION
					//OF THE TWO PLAYERS PLAYING THE GAME OUT WOULDNT WORK BECUASE IDK THE POSITION
					//OF THE WIP
//				System.out.println("because there is only one possibility, '" + check_if_word._current_word + "', ");
//				while(so_far.length() != check_if_word._current_word.length()){
//					so_far = so_far + check_if_word._current_word.charAt(charAt);
//					System.out.println("player "+ player + " has put down '" + check_if_word._current_word.charAt(charAt) + "' the word in progress is now " + so_far);
//					whosTurn++;
//					charAt++;
//
//				}
				
			//DECIDE IF I SHOULD LEAVE THE BELOW CHUNK COMMENTED
				
				
				
				//so_far = check_if_word._current_word;
//
//				if(so_far.length() % 2 == 0)eOd = "even";
//				if(so_far.length() % 2 != 0)eOd = "odd";
//				System.out.println("Because the only possible outcome is: '" + check_if_word._current_word + "', which has an " + eOd + " number of characters, the game is over.");
//
//				//if whosTurn is even, winner is player 2 according to code later.
//				if(so_far.length() % 2 == 0){
//					//below is simply setting whosTurn to an odd number, so player one will win
//					whosTurn = 1;
//				}
//				if(so_far.length() % 2 != 0)whosTurn = 2;
				
				
				
			}
			
			//below ends else
			}
		
		}
		int loser;
		int winner;
		if(whosTurn % 2 == 0){
			loser = 1;
			winner = 2;
		}
		else{
			loser = 2;
			winner = 1;
		}
		System.out.println("player " + loser + " has finished a word. Player " + winner + " has won.");
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		String[] returned = new String[3];
		
		System.out.println("game took " + elapsedTime/1000000000 + " seconds");
		System.out.println("game took " + elapsedTime + " NANOSECONDS");

		returned[0] = winner + "";
		returned[1] = so_far;
		returned[2] = elapsedTime + "";
//		returned[2] = elapsedTime/1000000000 + "";
		
//		for(int i = 0; i < kt.getGuarantees().length; i++){
//			System.out.println(kt.getGuarantees()[i][1]);
//		}
		
		return returned;
		
	}
	
}
