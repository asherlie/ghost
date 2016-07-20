package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class WordSearch {
	
	String _current_word = "";
	String _victory_percent = "";

	public int setup(String input, int block) throws FileNotFoundException{
//	String[] dict = new String[1000];
	HashSet<String> dic = new HashSet<String>();
	Scanner file = new Scanner(new File("words.txt"));
	//TODO figure out how to use args to determine the filepath
//	Scanner file = new Scanner(new File(filename));

	while (file.hasNextLine()){
		dic.add(file.nextLine().toLowerCase());
	} 
	file.close();

	Object[] array = dic.toArray();
	
	//tryig to write out al the s words at the end
//	for(int i = 0; i < array.length; i++){
//		if(dic.contains(array[i])array[i])
//	}
	
//	System.out.println(array[0]);
//	System.out.println(array.length);

	int x = 0;
	int vic = 0;
	for(int i = 0; i < array.length; i++){
		if(array[i].toString().contains(input)){
			
			//the one line below increments a variable if the word could lead to a victory in a 2p game.
			if(array[i].toString().length()%2 != 0){
				vic++;
			}
			x++;
//			System.out.println("The word, '" + array[i] + "' contains the char sequence: '" + input);
			_current_word = (String)array[i];
		}
		
	}
	 _victory_percent = vic + "/" + x;
	return x;

	}
	
	public int setup(String input) throws FileNotFoundException{
//		String[] dict = new String[1000];
		
		HashSet<String> dic = new HashSet<String>();
		Scanner file = new Scanner(new File("words.txt"));

		while (file.hasNextLine()){
			dic.add(file.nextLine().toLowerCase());
		}
		file.close();
		Object[] array = dic.toArray();
		
//		System.out.println(array[0]);
//		System.out.println(array.length);

		int x = 0;
//		int temp_for_fraction = 0;
		for(int i = 0; i < array.length; i++){
			if(array[i].toString().contains(input)){
				x++;
//				temp_for_fraction = setup(input, 2);
				System.out.println("The word, '" + array[i] + "' contains the char sequence: '" + input + "'");
//+ x + "/" + temp_for_fraction);
				//the above was used for the fraction. it worked but was way too slow
				_current_word = (String)array[i];
			}
			
		}
		return x;

		}

	
	
}
