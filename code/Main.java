package code;

import java.io.FileNotFoundException;
//import java.util.HashSet;
//import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		
		if(args.length != 0 && args[0].equals("SELF")){
			SelfPlay sel = new SelfPlay();
			try {
				sel.play();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		
		if(args.length != 0 && args[0].equals("MAX")){
			GhostMethods gho = new GhostMethods();
			try {
				gho.maxOptions(args[1]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		if(args.length != 0 && args[0].equals("BEST")){
			BestChoice bes = new BestChoice();
			try {
				System.out.println(bes.best(args[1]));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		if(args.length == 0){
			GhostMethods gho = new GhostMethods();
			gho.maxOptions();
		}
		
		if(args[0].charAt(0) <= 122 && args[0].charAt(0) >= 97){
			WordSearch ws = new WordSearch();
			try {
				ws.setup(args[0]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//find out how to make it only work for numbers so
		//i dont need to jump through these ridiculous hoops
//		 if(!args[0].equals("BEST") && !args[0].equals("MAX")){
		if(!(args[0].charAt(0) <= 122) && !(args[0].charAt(0) >= 97) && !args[0].equals("BEST") && !args[0].equals("MAX")){

			GhostMethods gho = new GhostMethods();
			//figure out how to turn off verbose mode even though the method now resides in GhostMethods
			gho.possibilities(Integer.parseInt(args[0]));
		}		
		
	}
	
}
