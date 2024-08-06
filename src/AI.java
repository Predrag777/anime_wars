import java.awt.image.BufferedImage;

public class AI {
	
	private boolean move;
	private boolean attack;
	private boolean defend;
	private int health;
	private int x,y;
	private int a,b;
	
	int borders=190;
	boolean retreatRight=false;
	boolean retreatLeft=false;
	
	public AI(int health, int x, int y, int a, int b, boolean move, boolean attack, boolean defend) {
		this.health=health;
		this.move=move;
		this.attack=attack;
		this.defend=defend;
		this.x=x;
		this.y=y;
		
		this.b=b;
		this.a=a;
		
	}
	
	public int move() {// 0 stoj, 1 napred, 2 nazad, 3 nazad desno, 4 nazad levo
		if(!retreatRight && x<a && a>=x+borders) {
			retreatRight=false;
			return 1;
		}else if(x<a && a<=x+borders){
			retreatRight=true;;
			return 3;
		}else if(x>a && a<=x-borders) {
			return 2;
		}else if(x>a && a>=x-borders){
			return 4;
		}
		
		
		return 0;
	}
	
	
	
}
