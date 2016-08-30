package code;

import java.io.FileNotFoundException;

public class Stats {
//	double[][] highestVP = new double[26][1];
	int[] occurences = new int[26];
	
	public boolean canGuarantee(char c){
		if(occurences[c-97] <= 1){
			return false;
		}
		return false;
	}
	public void manyTimes(int times) throws FileNotFoundException{
		
//		KeepingTrack kt = new KeepingTrack();
		
		String[] final_words = new String[times];
		int p1 = 0;
		int gameNo = 1;
		int increment = 0;
		double running_length = 0;
		
		long runtime = 0;
		
		int longest = 0;
		String longestWord = "";
		String[] result;
		SelfPlay se = new SelfPlay();
		for(int i = 0; i < times; i ++){
			System.out.println("simulating game " + (gameNo++) + "/" + times);

			result = se.play();
			if(result[1].length() > longest){
				longest = result[1].length();
				longestWord = result[1];
			}
			runtime = runtime + Long.parseLong(result[2]);
			final_words[increment++] = result[1];
			running_length = running_length + result[1].length();
			if(result[0].equals("1")){
				//above will work if i make return type array of a bunch of values
				//for self play
				p1++;
			}
			System.out.println("player one has won " + p1 + " times so far.");
			System.out.println("the average length of a winning word so far is " + running_length/(i+1));
			System.out.println("average runtime of a game so far is " + (runtime/1000000000)/(i+1) + " seconds");
			System.out.println("remainder of the games will take " + ((runtime/(i+1))*(times-gameNo))/1000000000 + " seconds based on current average");
//			System.out.println("current games per minute is: " + ((runtime/(i+1))*60)/1000000000);
			//try multiplying avg runtime by 60billion
			//avg times the amount left
		}
		System.out.println("player one won " + p1 + " times");
		System.out.println("all of the winning words are below.");
		for(int i = 0; i < final_words.length; i++){
			System.out.println(final_words[i]);
		}
		System.out.println("the average length of a winning word was " + running_length/times);
		System.out.println("the biggest length of a winning word was " + longest + " in the word " + longestWord);
		System.out.println("average runtime for a game was " + (runtime/times)/1000000000 + " seconds");

		System.out.println("average runtime for a game was " + runtime/times + " NANOseconds");
		
//		System.out.println("guarantees were " );
//		for(int i = 0; i < se._guarantees.length; i++){
//			System.out.println((char)(i+97) + " will always be " + se._guarantees[i][1] + se._guarantees[i][0]);
//		}
	}

}
