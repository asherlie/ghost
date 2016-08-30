package code;
//this entire class is dedicated to keeping track of occurences of first letters and whether or not the second move is guaranteed
//it has one getter method and one setter method for the double[][] highest_vp
public class KeepingTrack {
	
	//all the highest_vp array does is check for repeats of the highest value. this is used in the bestMove class
	//the array that actually stores the saved second moves is called guarantees in this very class
	
	//i can make this a single array and it'll take up much less space. don't need original 26, 
	double[][] highest_vp = new double[26][2];
	//highestvp replaces it's counterpart in BesetMove. this will
	//be changed from the selfplay class so that values 
	//will be static.
	
	//guarantees is meant to replace the guarantees variable in SelfPlay
	//this class is obsolete because in the stats class i use one instance of selfPlay.
	//i can use a local _guarantees variable
	double[][] guarantees = new double[26][3];
	/**
	[0] = first letter of game
	[1] = Before or After
	[2] = D as in done?
			if sub two is D, it knows to use a previously stored guarantee. 
			once guarantees are stored, immediately mark sub two with a 'D'
	
	**/
	public void setHighestVP(double value, int outer, int inner){
		highest_vp[outer][inner] = value; 
		System.out.println("highest_vp sub " + outer + " sub " + inner + " has been set to " + value);
		
	}
	
	public double[][] getHighestVP(){
		return highest_vp;
	}
//							 best letter    bef/aft		done/not
	public void setGuarantees(char begin_letter, char value, char BoA, char DoN){
		guarantees[begin_letter-97][0] = value;
		guarantees[begin_letter-97][1] = BoA;
		guarantees[begin_letter-97][2] = DoN;
		System.out.println("setting guarantees to true for " + begin_letter);

	}
	
	public void addOccurence(char letter){
		highest_vp[letter-97][1] = highest_vp[letter-97][1] + 1;
		System.out.println("occurence incremented to " + highest_vp[letter-97][1]);
	}
	public double[][] getGuarantees(){
		return guarantees;
	}

}
