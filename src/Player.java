
public class Player {
	String name;
	Garden garden=new Garden();
	//this entire class is mainly dedicated to access the garden class and to help in writing the LetsPlay class.
	
	public Player(String n1,int g1) {
		name = n1;
		garden.size=g1;
	}
	
	public String getName() {return name;}
	
	public int howManyFlowersPossible() {//tells us how many spots are available to plant flower.
		return garden.countPossibleFlowers();
	}
	
	public int howManyTreesPossible() {//tells us how many spots are available to plant tree.
		return garden.countPossibleTrees();
	}
	
	public String whatIsPlanted(int r3,int c3) {//tells us what's planted in a given spot.
		return garden.getInLocation(r3, c3);
	}
	
	public void plantFlowerInGarden(int r4,int c4) {//plants flower in a given spot
		garden.plantFlower(r4, c4);
	}
	
	public void plantTreeInGarden(int r5,int c5) {//plants tree in a given spot
		garden.plantTree(r5, c5);
	}
	
	public void eatHere(int r6,int c6) {//removes whatever is planted in a given spot.
		garden.removeFlower(r6, c6);
	}
	
	public boolean isGardenFull() {//tells us if garden is full.
		return garden.gardenFull();
	}
	
	public void showGarden() {//displays garden.
		String gardenToString = garden.toString();
		System.out.println(gardenToString);
	}
	

}
