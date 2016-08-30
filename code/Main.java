package code;

import java.io.FileNotFoundException;
//import java.util.HashSet;
//import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		if(args.length != 0 && args[0].equals("TWO")){
			GhostMethods g = new GhostMethods();
			g.printArray(g.twoLetterCombos());
		}
		if(args.length != 0 && args[0].equals("STAT")){
			if(Integer.parseInt(args[1]) == 0){
				System.out.println("fuck off");
				throw new IndexOutOfBoundsException();
			}
			Stats st = new Stats();
			try {
				st.manyTimes(Integer.parseInt(args[1]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(args.length != 0 && args[0].equals("SELF")){
			
//			b.whereIs("mastixes", "stixes");
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
			if(Integer.parseInt(args[2]) > 2){
				System.out.println("As of now, BestMove only supports up to two players.");
				throw new IndexOutOfBoundsException();
			}
			
//			BestChoice bes = new BestChoice();
			BestMove bes = new BestMove();
			try {
				char[] answ = new char[2];
				answ = bes.best(args[1], Integer.parseInt(args[2]));
				if(answ[1] == 'u')System.out.println("there is no best move");
				System.out.println(answ);
//				System.out.println(bes.best(args[1], Integer.parseInt(args[2])));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		if(args.length == 0){
			System.out.println("Illegal arguments.");
			System.out.println("Usage: java -jar GHOST.jar [BEST, MAX, SELF, STAT, integer(0-2147483647)]");
//			GhostMethods gho = new GhostMethods();
//			gho.maxOptions();
		}
		
		if(args.length != 0 && args[0].charAt(0) <= 122 && args[0].charAt(0) >= 97){
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
		 if(!(args[0].charAt(0) <= 122 && args[0].charAt(0) >= 97) && args.length != 0 && !args[0].equals("BEST") && !args[0].equals("MAX") && !args[0].equals("SELF") && !args[0].equals("STAT") && !args[0].equals("TWO")){
//		if(!(args[0].charAt(0) <= 122) && !(args[0].charAt(0) >= 97) && !args[0].equals("BEST") && !args[0].equals("MAX")){
//			 System.out.println("trying");
			GhostMethods gho = new GhostMethods();
			//figure out how to turn off verbose mode even though the method now resides in GhostMethods
			try {
				gho.possibilities(Integer.parseInt(args[0]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
	}
	
}
