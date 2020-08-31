import java.util.Random;

public class Dice {
	
	private int die1;
	private int die2;

//Constructors:	
	
	public Dice() {
		die1=0;
		die2=0;
	}
	
//Accessors:
	
	public int getDie1() {return die1;}
	public int getDie2() {return die2;}
	
//the rollDice() method:
	
	public int rollDice() {
		Random ranDie= new Random();//to randomize every roll.
		
		die1=ranDie.nextInt(6)+1;// +1 so that the value starts at 1 instead of 0.
		die2=ranDie.nextInt(6)+1;
		
		int sumOfDice=die1+die2;//sum of the 2 dice.
		return sumOfDice;
	}

//the toString() method:	
	
	public String toString() {
		String output="Die 1: "+die1+"\t"+"Die 2: "+die2;
		return output;
	}

}
