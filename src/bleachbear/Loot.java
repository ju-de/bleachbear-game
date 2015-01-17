package bleachbear;

class Loot {
	private int x, y, item;
	
	Loot(int spawnX, int spawnY){
		x=spawnX;
		y=spawnY;
		
		item=(int)(Math.random()*4);	//generate random loot item
		
		//bob
	}
	
	public int getItem(){
		return item;
	}
	
	public String getPath(){
		switch(item){
		case 0:
			return "bee";
		case 1:
			return "bleach";
		case 2:
			return "bomb";
		case 3:
			return "boots";
		}
		return "";
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
