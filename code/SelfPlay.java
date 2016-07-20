package code;
//make this recursive at some point
import java.io.FileNotFoundException;
import java.util.Random;

public class SelfPlay {
	
	public void play() throws FileNotFoundException{
		String so_far = "";
		int whosTurn = 0;
		Random rand = new Random();
		int value = rand.nextInt(26);
		value = value + 97;
		whosTurn++;
		//we increment whosTurn because choosing a random first letter was p1's first turn.
		
		so_far = (char)value + "";
		System.out.println("game has been started by player 1 with the letter " + (char)value);
		BestChoice be = new BestChoice();
		be.best(so_far);
		char[] temp;
		
		while(be.best(so_far) != null){
			temp = be.best(so_far);
			if(temp[1] == 'A')so_far = so_far + temp[0];
			if(temp[1] == 'B')so_far = temp[0] + so_far;
			
			System.out.println("player has put down " + temp[0] + " the word in progress is now " + so_far);
			whosTurn++;
		}
		int loser;
		if(whosTurn % 2 == 0){
			loser = 1;
		}
		else{
			loser = 2;
		}
		System.out.println("game has ended on player " + loser);
		
	}
	
}
