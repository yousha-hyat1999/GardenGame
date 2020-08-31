
public class Garden {
	
	int size;
	
	String garden[][]=new String[size][size];
	
	//Constructors:
		
		public Garden() {
			
			initializeGarden(3);
			}

		public Garden(int newSize) {
		
			
			initializeGarden(newSize);
			}
		
	//the private initializeGarden() method:	
		
		 private String[][] initializeGarden(int dash) {
			 
			 garden=new String[dash][dash];
				for (int i=0;i<dash;i++) {
					for(int j=0;j<dash;j++)
						garden[i][j]="-";
					}
				
			return garden;
			 
		 }

	//the getInLocation() method:
		 
		 public String getInLocation(int r,int c) {
			 return garden[r][c];
				 }
		 
	//the plantFlower() method:
		 
		 public void plantFlower(int r,int c) {
			 garden[r][c]="f";
				
		 }
		 
	//the plantTree() method:
		 
		 public void plantTree(int r,int c) {
			 	garden[r][c]="t";
				garden[r+1][c]="t";
				garden[r][c+1]="t";
				garden[r+1][c+1]="t";
						 }
		 
	//the removeFlower() method:
		 
		 public void removeFlower(int r,int c){
			 garden[r][c]="-";
			 }
			 
	//the countPossibleTrees() method:
		 
		 public int countPossibleTrees(){
			 
			 int tree=0;
			 
			 
				for (int i=0;i<=size/2;i++) {
					for(int j=0;j<=size/2;j++) {
						
						if (garden[i][j]=="-") 
							if (garden[i+1][j]=="-")
								if (garden[i][j+1]=="-")
									if (garden[i+1][j+1]=="-")
										tree++;
						}
					}
				return tree;
			  }
		 
	//the countPossibleFlowers() method:
		 
		 public int countPossibleFlowers() {
			 
			 int fl=0;
		 
		
			for (int i=0;i<size;i++) {
				for(int j=0;j<size;j++) 
					if (garden[i][j]=="-")
						fl++;
					}
			return fl;
			}
		 
	//the gardenFull() method:
		 
		 public boolean gardenFull() {
			 
			 int isGardenFull=0;
			 
			 
				for (int i=0;i<size;i++) {
					for(int j=0;j<size;j++) 
						if (garden[i][j] == "-")
							isGardenFull++;
						}
				if (isGardenFull == 0)
					return true;
				else
					return false;
		
		 }
		 
	//the toString() method:
		 
		 public String toString() {
			String output=""; 
			
			
			
			 for (int i=0;i<size;i++) {
				 
				 output +="\n"+i+"|";
				 for (int j=0;j<size;j++) {
					  output +=" "+garden[i][j];}
				 
				 }
			 
			 return output;
			 
		 }
			
			
		
		

}
