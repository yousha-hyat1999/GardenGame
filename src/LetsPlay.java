// ---------------------------------------------------------------
// Assignment 4 
// Written by: Yousha Hyatoolla; Student ID: 40099559
// For COMP 248	Section EE - Fall 2018
// ---------------------------------------------------------------
import java.util.Random;
import java.util.Scanner;
public class LetsPlay {
	public static void main(String[] args) {
//Displaying the welcoming message	
		System.out.println("-------****-------****-------****-------****-------****-------");
		System.out.println("\n         Welcome to Crazy Nancy's Garden Game!");
		System.out.println("\n-------****-------****-------****-------****-------****-------");
		
		System.out.println("\n\nTo win this game you need some luck with the dice and a bit of strategy.");
		System.out.println("Your garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A");
		System.out.println("tree takes 4 spots (2x2).");
		System.out.println("You roll the dice and based on the outcome you get to plant a pre-set number of trees and flowers");
		System.out.println("\nRolls and their outcome:\n----------------------");
		
		System.out.println("\n 3: plant a tree (2x2) and a flower (1x1)\n 6: plant 2 flowers (2 times 1x1)\n 12: plant 2 trees (2 times 2x2)");
		System.out.println(" 5 or 10: the rabbit will eat something that you have planted - might be a flower or part of a tree(1x1)");
		System.out.println("  Any other EVEN rolls: plant a tree (2x2)\n Any other ODD rolls: plant a flower (1x1)\n\nMinimum number of players: 2.");
		System.out.println("Minimum garden size: 3x3.\nYou can only plant in empty locations. To plant a tree you give the top left\ncoordinates of the 2x2 space");
		System.out.println("and I will check to make sure all 4 locations are free.\nIf however you can't plant a tree or a flower due to the fact that all spaces are being used, you will miss your turn.");
		System.out.println("Okay .. Let's start the game! May the best gardener win!!!");
//------------------------------------------------------------------End of welcoming message-------------------------------------------------------------------------------
		
		System.out.println("\nThe default  garden size is a 3x3 square. You can use this default board size or change the size");
		Scanner kb=new Scanner(System.in);
		System.out.print("\nEnter 0 to use the default garden size or -1 to enter your own size: "); 
		int choice = kb.nextInt();//user enters 0 for a default garden and -1 to choose his/her own dimensions.
		int sizeOfGarden;
		while (true) {
		
		if (choice == 0) {
			sizeOfGarden=3;
			break;
		}
		else if (choice == -1) {
			System.out.print("\nWhat size board would you like: ");
			sizeOfGarden=kb.nextInt();//choosing his/her own dimensions.
			break;
		}
		else {//making sure that 0 or -1 is entered.
			System.out.print("\nSorry but "+choice+" is not a legal choice. Enter your choice:");
			choice = kb.nextInt();	}
		}
		
		int nPlayers;//number of players.
		while (true) {
		System.out.print("\nHow many gardeners will there be (minimum 2 required to play, max allowed 10)? ");
		nPlayers=kb.nextInt();
		if (nPlayers<2 || nPlayers>10)
			System.out.print("\n** Sorry but "+nPlayers+" is not a legal number of players. **");//loop will continue until the number of players entered is between 2 and 10.
		else {
			System.out.println("Great, "+nPlayers+" players it will be!");
			break;//while loop will stop only if the number of players is between 2 and 10.
			}
			
		}
		System.out.println();
	//following code is for storing all players in an array with the players' names.	
		String[] playerNames=new String[nPlayers];
		for (int i=0;i<playerNames.length;i++) {
			System.out.print("--> Name of player "+(i+1)+" (no spaces allowed): ");
			playerNames[i]=kb.next();
			
			}
		
//------------Using the DICE class--------------------------
	//the following code makes sure that there will always be someone who goes first.	
		Dice plRoll=new Dice();
		int[] diceValue = new int[nPlayers];//storing sum of dice in an array.
		String winner=null;//i.e player who rolled the highest dice value
		int breaker=0;
		
		do {//loop will continue until there is a player who rolls the highest value.
			breaker = 0;
		for (int j=0;j<playerNames.length;j++) {
			int sumDice=plRoll.rollDice();
			System.out.print("\n"+playerNames[j]+" rolled a "+sumDice);//printing out the sums of the dice for each player.
			diceValue[j] = sumDice;
		}
		
		int largest = 0;
		for (int x=0;x<playerNames.length;x++) {
			if (diceValue[x]==largest) {
				System.out.println("\nWe will start over again as identical sums were rolled");
				breaker = 1;
				break;
			}
			else if (diceValue[x]>largest) {
				largest = diceValue[x];
				winner = playerNames[x];
			}
		}
		}while (breaker!=0);
		
		System.out.println("\n\n"+winner+" goes first.");//displaying the player who rolled the highest sum.
		System.out.println("\nTime to play!!!\n-----------------");
		
//-----------------------The game---------------------------------------------
		
		String[] newPls=new String[nPlayers];// new array to store all players starting from the one who rolled the highest number.
		newPls[0]=winner;
		for (int p=0,t=1;p<nPlayers;p++) {
			if (playerNames[p] != winner) {
				newPls[t]=playerNames[p];
				t++;}
		}
	
		String name1=null;
		Player[] gardeners=new Player[nPlayers];//array of type Player to help accessing the methods of the class Player
		
		for (int i=0;i<nPlayers;i++) {
			gardeners[i]=new Player(name1,sizeOfGarden);
		}
	
		int rounds=0; //to count how many rounds it took to get a winner.
		while (true) {//the main while-loop which will take care of everything.
			int breakCycle=0;//to help break the cycle when a garden in full.
			 for (int i=0;i<nPlayers;i++) {//to ensure that every player has their turn.
	
				 int diceVal1=plRoll.rollDice();//sum of the dice
					System.out.println("\n"+newPls[i]+" rolled "+diceVal1+". ("+plRoll.toString()+")");//printing the value of each die.
					 switch (diceVal1) {//switch statement depending on the sum of the dice.
//*************************************************CASE 3(if 3 is rolled)**********************************************************************					 
					 case 3:{
						 System.out.println("\nYou have to plant a tree (2x2) and a flower (1x1)");
						 System.out.print(" |");// this part is to make sure that the garden is well printed with all the rows and columns.
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nYou have "+ gardeners[i].howManyFlowersPossible()+" places to plant a flower and "+gardeners[i].howManyTreesPossible()+" places to plant a tree");
						 if (gardeners[i].howManyFlowersPossible()==0) {//if there is no room to plant a flower, this message will be displayed and the player will miss a turn.
							 System.out.println("\nSorry, no more space available. You can't plant a flower. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for flower: ");//entering the coordinates.
						 int coordR=kb.nextInt();
						 int coordC=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("f")){//if the coordinates entered are already taken up by a flower, this message will be displayed and the player will have a chance to enter other coordinates until appropriate coordinates are entered.
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();

							 coordR=Erow;
							 coordC=Ecol;
							 
							 }
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("t")){//if the coordinates entered already contain a tree, this message will be displayed and it will give the player another chance to enter other coordinates.
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							coordR=Erow;
							coordC=Ecol;}
						 if (coordR >= sizeOfGarden || coordC >= sizeOfGarden) {//if the coordinates entered are out of bounds of the garden, this message will be displayed and the player will be able to enter other coordinates.
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantFlowerInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantFlowerInGarden(coordR, coordC);
						 if (gardeners[i].howManyTreesPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a tree. :(");//only if there is no room to plant a tree and the player will also miss a turn.
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for tree: ");
						 int coordR1=kb.nextInt();
						 int coordC1=kb.nextInt();
						 //the same rulings mentioned above:
						 while (gardeners[i].whatIsPlanted(coordR1, coordC1).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR1=Erow;
							 coordC1=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR1, coordC1).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR1=Erow;
							 coordC1=Ecol;
							 }
						 if (coordR1>sizeOfGarden/2 || coordC1>sizeOfGarden/2) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantTreeInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantTreeInGarden(coordR1, coordC1);
						 break;
					 }
//********************************************************CASE 6(if 6 is rolled)***********************************************************************************	
					 //the following cases will also resemble case 3 which is why I decided not to comment on them unless they differ.
					 case 6:{
						 System.out.println("\n You have to plant 2 flowers (2 times 1x1)");
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nYou have "+ gardeners[i].howManyFlowersPossible()+" places to plant a flower");
						 if (gardeners[i].howManyFlowersPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a flower. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your first flower: ");
						 int coordR=kb.nextInt();
						 int coordC=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;
							 }
						 if (coordR >= sizeOfGarden || coordC >= sizeOfGarden) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantFlowerInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantFlowerInGarden(coordR, coordC);
						 if (gardeners[i].howManyFlowersPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a flower. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your second flower: ");
						 int coordR1=kb.nextInt();
						 int coordC1=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR1, coordC1).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR1=Erow;
							 coordC1=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR1, coordC1).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR1=Erow;
							 coordC1=Ecol;}
						 if (coordR1 >= sizeOfGarden || coordC1 >= sizeOfGarden) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantFlowerInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantFlowerInGarden(coordR1, coordC1);
						 break;
					 }
//***********************************************************CASE 12(if 12 is rolled)*******************************************************************************************
					 case 12:{
						 System.out.println("\n You have to plant 2 trees (2 times 2x2)");
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nYou have "+gardeners[i].howManyTreesPossible()+" places to plant a tree");
						 if (gardeners[i].howManyTreesPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a tree. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your first tree: ");
						 int coordR=kb.nextInt();
						 int coordC=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;}
						 if (coordR>sizeOfGarden/2 || coordC>sizeOfGarden/2) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantTreeInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantTreeInGarden(coordR, coordC);
						 if (gardeners[i].howManyTreesPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a tree. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your second tree: ");
						 int coordR1=kb.nextInt();
						 int coordC1=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR1, coordC1).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR1=Erow;
							 coordC1=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR1, coordC1).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR1=Erow;
							 coordC1=Ecol;}
						 if (coordR1>sizeOfGarden/2 || coordC1>sizeOfGarden/2) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantTreeInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantTreeInGarden(coordR1, coordC1);
						 break;
					 }
//******************************************************CASE 5(if 5 is rolled)**********************************************************************
					 case 5:{
						 Random eater=new Random();
						 int rabbit1=eater.nextInt(sizeOfGarden);//randomize coordinates which will be eaten by the rabbit.
						 int rabbit2=eater.nextInt(sizeOfGarden);
						 //displaying 2 times the garden:
						 System.out.print(" |");//before being eaten
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nThe rabbit ate whatever was at ("+rabbit1+", "+rabbit2+").");
						 gardeners[i].eatHere(rabbit1, rabbit2);
						 System.out.print(" |");//after being eaten
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 break;
					 }
//**************************************************CASE 10(if 10 is rolled)*****************************************************************************
					 //the same comments as case 5 are also applicable for case 10.
					 case 10:{
						 Random eater=new Random();
						 int rabbit1=eater.nextInt(sizeOfGarden);
						 int rabbit2=eater.nextInt(sizeOfGarden);
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nThe rabbit ate whatever was at ("+rabbit1+", "+rabbit2+").");
						 gardeners[i].eatHere(rabbit1, rabbit2);
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 break;
					 }
//***************************************************CASE 2(if 2 is rolled)************************************************************************************************
					 case 2:{
						 System.out.println("\nYou have to plant a tree (2x2)");
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nYou have "+gardeners[i].howManyTreesPossible()+" places to plant a tree");
						 if (gardeners[i].howManyTreesPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a tree. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your tree: ");
						 int coordR=kb.nextInt();
						 int coordC=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;}
						 if (coordR>sizeOfGarden/2 || coordC>sizeOfGarden/2) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantTreeInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantTreeInGarden(coordR, coordC);
						 break;
						  }
//***********************************************************CASE 4(if 4 is rolled)**********************************************************************
					 case 4:{
						 System.out.println("\nYou have to plant a tree (2x2)");
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nYou have "+gardeners[i].howManyTreesPossible()+" places to plant a tree");
						 if (gardeners[i].howManyTreesPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a tree. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your tree: ");
						 int coordR=kb.nextInt();
						 int coordC=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;}
						 if (coordR>sizeOfGarden/2 || coordC>sizeOfGarden/2) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantTreeInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantTreeInGarden(coordR, coordC);
						 break;
					 }
//***********************************************************CASE 8(if 8 is rolled)**********************************************************************
					 case 8:{
						 System.out.println("\nYou have to plant a tree (2x2)");
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nYou have "+gardeners[i].howManyTreesPossible()+" places to plant a tree");
						 if (gardeners[i].howManyTreesPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a tree. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your tree: ");
						 int coordR=kb.nextInt();
						 int coordC=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;}
						 if (coordR>sizeOfGarden/2 || coordC>sizeOfGarden/2) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantTreeInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantTreeInGarden(coordR, coordC);
						 break;
					 }
//***********************************************************CASE 7(if 7 is rolled)**********************************************************************
					 case 7:{
						 System.out.println("\nYou have to plant a flower (1x1)");
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nYou have "+ gardeners[i].howManyFlowersPossible()+" places to plant a flower");
						 if (gardeners[i].howManyFlowersPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a flower. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your flower: ");
						 int coordR=kb.nextInt();
						 int coordC=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;}
						 if (coordR >= sizeOfGarden || coordC >= sizeOfGarden) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantFlowerInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantFlowerInGarden(coordR, coordC);
						 break;
						 }
//***********************************************************CASE 9(if 9 is rolled)**********************************************************************
					 case 9:{
						 System.out.println("\nYou have to plant a flower (1x1)");
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nYou have "+ gardeners[i].howManyFlowersPossible()+" places to plant a flower");
						 if (gardeners[i].howManyFlowersPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a flower. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your flower: ");
						 int coordR=kb.nextInt();
						 int coordC=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;}
						 if (coordR >= sizeOfGarden || coordC >= sizeOfGarden) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantFlowerInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantFlowerInGarden(coordR, coordC);
						 break;
						 }
//***********************************************************CASE 11(if 11 is rolled)**********************************************************************
					 case 11:{
						 System.out.println("\nYou have to plant a flower (1x1)");
						 System.out.print(" |");
						 for (int j=0;j<sizeOfGarden;j++)
							 System.out.print(" "+j);
						 gardeners[i].showGarden();
						 System.out.println("\nYou have "+ gardeners[i].howManyFlowersPossible()+" places to plant a flower");
						 if (gardeners[i].howManyFlowersPossible()==0) {
							 System.out.println("\nSorry, no more space available. You can't plant a flower. :(");
							 break;
						 }
						 System.out.print("\nEnter coordinates as row column for your flower: ");
						 int coordR=kb.nextInt();
						 int coordC=kb.nextInt();
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("f")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a flower. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;
						 }
						 while (gardeners[i].whatIsPlanted(coordR, coordC).equals("t")){
							 System.out.println("Sorry, the coordinates you entered are taken up by a tree. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 coordR=Erow;
							 coordC=Ecol;}
						 if (coordR >= sizeOfGarden || coordC >= sizeOfGarden) {
							 System.out.println("Sorry, but the coordinates entered are not within your garden. Try again:");
							 int Erow=kb.nextInt();
							 int Ecol=kb.nextInt();
							 gardeners[i].plantFlowerInGarden(Erow, Ecol);
							 break;
						 }
						 gardeners[i].plantFlowerInGarden(coordR, coordC);//if a garden is full, break out of the for loop.
						 break;
						 }
					 
						 
						 }//closing brackets for switch statement.
				
					 if (gardeners[i].isGardenFull()) {
						 breakCycle++;
						 break;}//if a garden is full, break out of the for loop.
					 
				 
			 }//closing brackets for the for-loop.
			rounds++;
			if (breakCycle != 0)
				break;//break out of the main while loop.
			}//closing brackets for the main while-loop.
		
//--------------------------------------------FINAL RESULTS-------------------------------------------------------------------------
		//Displaying final results:
		System.out.println("\nFINAL RESULTS\n----------------------");
		System.out.println("Here are the gardens after "+rounds+" rounds.");

		String overallW=""; //for the winner of the game.
		
		for (int i=0;i<nPlayers;i++) {// for printing all the gardens.
		
			System.out.println("\n"+newPls[i]+"'s garden:");
			 System.out.print(" |");
			 for (int j=0;j<sizeOfGarden;j++)
				 System.out.print(" "+j);
			 gardeners[i].showGarden();
			 if (gardeners[i].isGardenFull())
				 overallW=newPls[i];
				 
				 
			
		}
		System.out.println("\nCongratulations to "+overallW+", you are the winner.\nHope you had fun!!!!");//congratulating the winner.
		
	
		
		kb.close();
	}
	
}
//---------------------------------------------------------------END OF THE PROGRAM----------------------------------------------------------------------------------------
//***********************************************************************************************************************************************************************\\