package bleachbear;

public class Background{
	private int x, y, dx, dy;
	
	Background(){
		x = 0;
	}
	
	public void scroll(int dx){
		x += dx;
	}

	public int getX() {
		return x;
	}
}
