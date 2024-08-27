import java.awt.image.BufferedImage;

public class AI {
	
	private boolean move;
	private boolean attack;
	private int health;
	private boolean jump;
	private boolean block;
	private boolean punched;
	private int x,y;
	private int a,b;
	
	int borders=190;
	boolean retreatRight=false;
	boolean retreatLeft=false;
	
	public AI(int health, int x, int y, int a, int b, boolean attack) {
		this.health=health;
		this.move=move();
		this.attack=attack;
		
		this.x=x;
		this.y=y;
		
		this.b=b;
		this.a=a;		
	}
	
	public boolean move() {
		System.out.println(attack);
		return (!attack && !isInAttack(this.a));
	}
	
	public boolean isInAttack(int a) {
		return a<=x+200;
	}
	
	
	
}
