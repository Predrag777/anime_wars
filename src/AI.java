import java.awt.image.BufferedImage;

public class AI {
	
	private boolean init;
	private boolean move;
	private boolean attack;
	private boolean deffend;
	
	public AI(boolean init, boolean move, boolean attack, boolean deffend) {
		this.init=init;
		this.move=move;
		this.attack=attack;
		this.deffend=deffend;
	}
	
	public void StateFiniteMachine(int posX, int posY, int enemyPosX, int enemyPosY) {
		if(this.init) {
			System.out.println("Ovo je prva pozicija init");
			while(posX<300) {
				posX++;
			}
			this.init=false;
		}else if(this.move) {
			System.out.println("Pomjeraj");
			while(posX<=enemyPosX-20) {
				posX+=1;
			}
			this.move=false;
			this.attack=true;
		}else if(this.attack) {
			System.out.println("Attack");
			//attack()
		}
		
	}
}
