package bleachbear;

public class Background{
	private int x, y;
	
	Background(){
		x = 0;
		y = 0;
	}
	
	public void scroll(int dx, int dy){
		x += dx;
	}

	public int getX() {
		return x;
	}
	
	public int getY(){
		return y;
	}
}
