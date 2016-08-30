package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class WordSearch {
	
	String _current_word = "";
	String _victory_percent = "";
	String _victory_percent_for_player2 = "";
	
	//the vp variables are divided for comparisons
	double _vp = 0;
	double _vp_for_player2 = 0;
	
	//vic vars are numerator for division, the raw number of wins for either player
	double _vic = 0;
	double _vic_for_player2 = 0;
	double _total = 0;
	
	/**
	wordFileToArray uses a scanner to add all of the words from a wordlist with the name 'words.txt'
	to a hashset. I initially put all the words in a hashset so i don't need to specify the size to allocate
	upon creation. 
	
	I then create an array of type object and assign it the value of the hashset.ToArray()
	 

	 */
	public Object[] wordFileToArray() throws FileNotFoundException{
		HashSet<String> dic = new HashSet<String>();
		Scanner file = new Scanner(new File("words.txt"));
		
		//added the extra criteria to the while loop to get rid of
		//all words with less than for letters but doesnt work
		//for some reason
		
		while(file.hasNextLine()){
//	what a fucking stupid mistake below
//		while(file.hasNextLine() && file.nextLine().toString().length() >= 4){
			if(file.nextLine().toString().length() >= 4){
				dic.add(file.nextLine().toLowerCase().trim());
			}			
			
			//i think i should honestly keep all the words in the array
				//and just ignore some. there are pros and cons, speed etc
			//but it may be smarter to just adjust the isWord criteria as needed
			
			//from teh if statement below, i realized that qat is stored as a 4 letter word
			//i took a lazy approach and manually deleted it from word list
//			if(file.nextLine().toString().equals("qat"))System.out.println(file.next().toString().length());
		}
		file.close();
		//i need to typecast it because the toarray method on the hashset defaults to an array of type
		//object. 
		Object[] array = dic.toArray();
		
		
		//loop below removes white space, which skews winner result - took it out and added to above
//		for(int i = 0; i < array.length; i++){
//			array[i] = array[i].toString().trim();
//		}
		return array;
		
		
	}
	//i can implement somethign similar to a binary search for this
	//because a dictionary is an ordered list. check the value of the first 
//	char and go to middle if i need to find something
	
	/**
	  the isWord method checks if the input is a word based on the word list array provided in the parameter.
	
	 
	 **/
	
	public boolean isWord(String input, Object[] list){
//			throws FileNotFoundException{
//		int startPos;
////		char start = input.charAt(0);
//		if(input.charAt(0) > list[list.length/2].toString().toLowerCase().charAt(0)){
//			startPos = list.length/2;
//		}
//		if(input.char)
//		implementing a binary search type additino to speed it up. might make a real difference in runtime because it's in while loop
		
//		WordSearch ws = new WordSearch();
		for(int i = 0; i < list.length; i++){
			if(input.equals(list[i]) && input.length() >= 4)return true;
//			if(ws.setup(input, 2) == 0)return true;
		}
		return false;
	}
	
	
	/**
	 I honestly think i was drunk when i wrote this method, it makes absolutely no sense. 
	 **/
	public boolean isAchievableS(String input) throws FileNotFoundException{
		WordSearch ws = new WordSearch();

//		String newstr = "";
//		for(int i = 0; i < input.length(); i++){
//			newstr = newstr + input.charAt(i);
//			if(newstr.length() >= 4 && ws.isWord(newstr, ws.wordFileToArray()) && newstr.length() < input.length()){
//				return false;
//			}
//			
//		}
//		return true;
		
		String to_test_minus = "";
//		String to_test_plus = "";

//		int setup = ws.setup(input, 2);
		for(int i = 0; i < ws._current_word.length()-1; i++){
			to_test_minus = to_test_minus + ws._current_word.charAt(i);
		}
//		to_test_plus = ws._current_word + 's';
		if(ws.isWord(to_test_minus, ws.wordFileToArray())){
			//if to test minus is a word, two test plus is inachievable
			return false;
		}
		else{
			return true;
		}
		//migrated above to farther above
	}
	
	
	/**
	 this inappropriately named method is essentially just a loop that goes through all the words in the wordlist, checking if the
	 input parameter is contained in the words. it keeps track of the number of matches by incrementing a variable called 'x'
	 
	 if the word would lead to a victory for player one a variable called 'vic' is incremented. 
	 at the end of the method, vic is used to calculate winning percentages for both players, which help later on with the BestMove method

	THE 'block' PARAMETER IS NOT IMPORTANT! IT SIMPLY DESIGNATES THAT I WANT NO SYSOUTS. 
	this is achieved by overloading the method name 'setup'. the one with sysout has no parameter for block
		the convention for blocking sysouts is putting in the number 2. I decided this arbitrarily when i was intoxicated.
	 */
	public int setup(String input, int block) throws FileNotFoundException{

		
	WordSearch ws = new WordSearch();
	
	Object[] array = ws.wordFileToArray();
	double x = 0;
	double vic = 0;
	
	ArrayList<String> words = new ArrayList<String>(); 

	for(int i = 0; i < array.length; i++){
		if(array[i].toString().contains(input)){
			words.add((String)array[i]);
			//the one line below increments a variable if the word could lead to a victory in a 2p game.
			if(array[i].toString().length()%2 == 0){
				vic++;
				
			}
			//if it's even then player one wins ^
			x++;
//			System.out.println("The word, '" + array[i] + "' contains the char sequence: '" + input);
			_current_word = (String)array[i];
		}
		
	}
	/**
	 this terribly written block of code below basically deals with cases where there are two options, caused by one of them being plural
	 i.e, having an 's' at the end and being unreachable because of it. If there are two options and one is one letter longer than the other
	 make x = 1 and direct _current_word to the correct one 
	 */
	if(x != 0){
	if(x == 2){
		if(words.get(0).length() == words.get(1).length() + 1){
			//first option is longer than second by one
			_current_word = words.get(1);
			x = 1;
		}
		if(words.get(1).length() == words.get(0).length() + 1){
			//if second option is longer than first by one
			_current_word = words.get(0);
			x = 1;
		}
	}
	_vp = vic/x;
	_vp_for_player2 = (x-vic)/x;
	
	 _victory_percent = vic + "/" + x;
	 _victory_percent_for_player2 = (x - vic) + "/" + x;
	 _vic_for_player2 = x-vic;
	 _vic = vic;
	 _total = x;

	}
	if(x == 0){
		_vp = 0;
		_vp_for_player2 = 0;
		
		_victory_percent = 0 + "/" + 0;
		_victory_percent_for_player2 = 0 + "/" + 0;
		_vic_for_player2 = 0;
		_vic = 0;
		_total = 0;


	}
	
	
	return (int) x;

	}
	
	public int setup(String input) throws FileNotFoundException{
//		String[] dict = new String[1000];
		
//		HashSet<String> dic = new HashSet<String>();
//		Scanner file = new Scanner(new File("words.txt"));
//
//		while (file.hasNextLine()){
//			dic.add(file.nextLine().toLowerCase());
//		}
//		file.close();
//		Object[] array = dic.toArray();
		
//		System.out.println(array[0]);
//		System.out.println(array.length);
		WordSearch ws = new WordSearch();
		Object[] array = ws.wordFileToArray();
		int x = 0;
		ArrayList<String> words = new ArrayList<String>(); 
//		int temp_for_fraction = 0;
		for(int i = 0; i < array.length; i++){
			if(array[i].toString().contains(input)){
				words.add((String) array[i]);
				x++;
//				temp_for_fraction = setup(input, 2);
				System.out.println("The word, '" + array[i] + "' contains the char sequence: '" + input + "'");
//+ x + "/" + temp_for_fraction);
				//the above was used for the fraction. it worked but was way too slow
				_current_word = (String)array[i];
			}
			
		}
//		WordSearch ws = new WordSearch();
//		if(x == 2 && ws.isAchievableS(words.get(0)))
		///				ADD FUNCTIONALITY FOR WORDS LIKE LAXNESSES, WHERE THE PLURAL HAS TWO MORE LETTERS
					//	ALSO FIGURE OUT HOW TO ACCOUNT FOR IF THE WORD STARTS WITH AN S, THIS SHOULD BE EASY
					//	AND CAN PROBABLY BE ACHIEVED WITH A SIMPLE WELL PLACED IF STATEMENT
		if(x == 2){
			if(words.get(0).length() == words.get(1).length() + 1){
				//first option is longer than second by one
				_current_word = words.get(1);
				x = 1;
			}
			if(words.get(1).length() == words.get(0).length() + 1){
				//if second option is longer than first by one
				_current_word = words.get(0);
				x = 1;
			}
		}
		return x;
	}
		
}

	//make a separate class to set up the array of word lists.
	
	
//	//the method below is for when i want to use this on games that are not superghost.
//	public boolean canUseInReg(char char, String[] wordlist){
//		for(int i = 0; i < wordlist.length; i++){
//			if(wordlist[i].contains(char)){
//				for(int j = 0; j < wordlist[j].length(); j++){
////					if(wordlist[i].charAt(j) == charSeq.charAt(0) && wordlist[i])
//					if(wordlist[i].charAt(0) == char)
//				}
//			}
//		}
//		
//		return true;
//	}

	
	
//}
