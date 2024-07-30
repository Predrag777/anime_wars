import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Crtaj extends JPanel implements KeyListener, ActionListener{

	Timer t=new Timer(100,this);
	Fighter f1,f2;
	public Crtaj(Fighter f1, Fighter f2) {
		t.start();
		setSize(1000,1000);
		setVisible(true);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.f1=f1;
		this.f2=f2;
	}
	int count=0;
	int enemyCount=0;
	int deadCount=0;
	
	int enemyAttackCount=0;
	int attackCount=0;
	
	
	int x=100,y=790,holdX=0,holdY=0;
	int a=700,b=790,holdA=0,holdB=0;
	String ulti_ss="";
	
	int startAttackTime;
	
	
	int myHelth=100;
	int enemyHelth=100;
	
	boolean enemyMove=false;
	boolean enemyAttack=false;
	boolean enemyHeightKick=false;
	boolean enemyMidKick=false;
	boolean enemyPunch=false;
	boolean enemyJump=false;
	boolean retreatLeft=false;
	boolean retreatRight=false;
	boolean enemyChangeBase=false;
	boolean enemyDefeated=false;
	boolean enemyReceivedSpecAttack=false;
	boolean enemyPunched=false;
	
	String enemyBaseAttacks[] = {"Punch.png", "MidKick.png","HeightKick.png"};
	
	int specX=0,specY=700,holdSpec;
	
	
	boolean jump=false;
	boolean fly=false;
	boolean attack=false;
	boolean midKick=false;
	boolean heightKick=false;
	boolean specAttack=false;
	boolean move=false;//ide u desno false ide u levo
	boolean punched=false;
	boolean defeated=false;
	boolean ulty=false;
	
	boolean faza1=false;
	boolean faza2=false;
	
	
	boolean changeBase=true;
	
	boolean slide=false;
	
	int side=1;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage image;
		BufferedImage wave;
		//Timer timer=new Timer(1000,this);
		try {
			String ss=f1.getFile()+"/"+f1.getName().toLowerCase()+ulti_ss+"Base.png";
			String s="";
			
			if(!changeBase) {
				ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Base2.png";
			}
			
			if(jump) {
				ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Jump.png";
			}
			if(attack) {
				if(attackCount<5)
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Punch.png";
				else {
					attack=false;
					attackCount=0;
				}
				attackCount++;
			}
			if(midKick) {
				if(attackCount<8)
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"MidKick.png";
				else {
					midKick=false;
					attackCount=0;
				}
				attackCount++;
			}
			if(heightKick) {
				if(attackCount<15)
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"HeightKick.png";
				else {
					heightKick=false;
					attackCount=0;
				}
				attackCount++;
			}
			if(specAttack) {
				System.out.println(count);
				if(count==20) {
					faza2=true;
				}else {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"SpecAttack.png";
				}
				if(faza2) {
					count=0;
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"SpecAttack2.png";
				}
				count++;
			}
			if(slide) {
				ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Slide.png";
			}
			if(punched) {
				if(count>10) {
					System.out.println("Oporavio se");
					punched=false;
				}else {
					//System.out.println("Wermaht: "+count);
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Punched.png";
				}
				count++;
			}
			
			if(ulty) {
				if(count<30) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"NewForm.png";
					ulti_ss="Ulty";
					
				}else {
					ulty=false;
					count=0;
				}
				count++;
			}
			
			//System.out.println(ss);
			
			
			
			if(myHelth<=0) {
				y=790;
				punched=false;
				if(deadCount<5) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Dead1.png";
				}else if(deadCount<10) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Dead2.png";
				}else if(deadCount<15) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Dead.png";
					
				}else {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Dead.png";
					t.stop();				}
				
				deadCount++;
			}
			
			
			
			
			
						
			BufferedImage img=ImageIO.read(new File("background/background.jpeg"));
			Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    image=ImageIO.read(new File(ss));
		    g.drawImage(img,0,0,1000,1000,null);
			
			g.drawImage(image,x+100*side*-1,y,200*side,200,null);
			if(faza2) {
				wave=ImageIO.read(new File("folder1/wave.png"));
				g.drawImage(wave, specX+200, specY+100,100*side,100,null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		BufferedImage fighter2;
		
		try {
			String waff=f2.getFile()+"/"+f2.getName().toLowerCase()+"Base.png";
			
			//System.out.println(waff);
			if(!enemyChangeBase) {
				waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Base2.png";
			}
			int randomNum = (int)(Math.random() * 3);
			if(randomNum==0) {
				enemyPunch=true;
				enemyHeightKick=false;
				enemyMidKick=false;
			}else if(randomNum==1) {
				enemyPunch=false;
				enemyHeightKick=true;
				enemyMidKick=false;
			}else {
				enemyPunch=false;
				enemyHeightKick=false;
				enemyMidKick=true;
			}
			
			if(enemyAttack) {
				if(enemyPunch) {
					if(enemyCount<20) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyBaseAttacks[randomNum];
					}else {
						enemyCount=0;
						enemyPunch=false;
						enemyAttack=false;
					}
				}
				if(enemyHeightKick) {
					if(enemyCount<20) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyBaseAttacks[randomNum];
					}else {
						enemyCount=0;
						enemyHeightKick=false;
						enemyAttack=false;
					}
				}
				if(enemyMidKick) {
					if(enemyCount<20) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyBaseAttacks[randomNum];
					}else {
						enemyCount=0;
						enemyMidKick=false;
						enemyAttack=false;
					}
				}
				
				enemyCount++;
				System.out.println(enemyPunch+"    "+enemyMidKick+"    "+enemyHeightKick+"    "+enemyCount);
			}
			
			if(enemyReceivedSpecAttack) {
				
				if (count>=30) {
					enemyReceivedSpecAttack = false;
					System.out.println("Prekini");
	            } else {
	                waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Burnt.png";
	            }
				count++;
				
			}
			
			
			
			if(enemyHelth<=0) {
				b=790;
				if(deadCount<5) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Dead1.png";
				}else if(deadCount<10) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Dead2.png";
				}else if(deadCount<15) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Dead.png";
				}else {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Dead.png";
					t.stop();				}
				
				deadCount++;
			}
			
			if(enemyPunched) {
				if(enemyCount>10) {
					System.out.println("Enemy recovered");
					enemyPunched=false;
					enemyCount=0;
				}else {
					System.out.println("Enemy "+enemyCount);
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Punched.png";
				}
				enemyCount++;
			}
			
			
			fighter2=ImageIO.read(new File(waff));
			
			g.drawImage(fighter2,a+100*side,b,200*side*-1,200,null);//Okreni sliku
			
			g.setColor(Color.RED);
		    Font font = new Font("Arial", Font.BOLD, 30);
		    g.setFont(font);
		    g.drawString(String.valueOf(myHelth), 10, 60); 
		    g.drawString(String.valueOf(enemyHelth), getWidth() - 60, 60); 

		    

			//Mogao sam ovo lepse da iskucam
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void jump() {
		jump=true;
		if(!move) {
			holdX=-2;
		}else {
			holdX=2;
		}
		holdY=5;
	}
	
	public void fly() {
		fly=true;
		//y=400;
	}
	
	public void right() {
		if(!jump) {
			holdX=5;
			holdY=0;
			move=true;
		}
	}
	
	public void left() {
		if(!jump) {
			holdX=-5;
			holdY=0;
			move=false;
		}
	}
	
	public void punch() {
		attack=true;
	}
	public void heightKick() {
		x+=10;
		heightKick=true;
	}
	public void midKick() {
		midKick=true;
	}
	public void specAttack() {
		specAttack=true;
		holdSpec=30;
	}
	public void slide() {
		slide=true;
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(!jump) {
			holdX=0;holdY=0;
		}
		//attack=false;
		//midKick=false;
		//heightKick=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_UP && y==790) {
			jump();
		}
		if(code==KeyEvent.VK_SPACE) {//Popraviti
			fly();
		}
		if(code==KeyEvent.VK_A && !punched) {
			punch();
		}
		if(code==KeyEvent.VK_D && !punched) {
			midKick();
		}
		if(code==KeyEvent.VK_W && !punched) {
			heightKick();
		}
		if(code==KeyEvent.VK_Q) {
			ulty=true;
		}
		if(y==790) {
			if(code==KeyEvent.VK_S) {
				specAttack();
				//startAttackTime=System.currentTimeMillis();
			}
			
			if(code==KeyEvent.VK_RIGHT) {
				right();
				changeBase=changeBase ? false:true;
			}
			if(code==KeyEvent.VK_LEFT) {
				changeBase=changeBase ? false:true;
				left();
			}
			if(code==KeyEvent.VK_F) {
				slide();
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		x+=holdX;y-=holdY;
		a+=holdA;b+=holdB;
		
		Timer time=new Timer(1000, this);
		if(a<x+100) {
			side=-1;
			
		}else
			side=1;
		
		//Skok
		if(y<=600)
			jump=false;
		if(jump) {
			holdX=2;
			holdY=25;
		}
		if(!jump && move) {
			holdY=-25;
			holdX=2;
		}
		if(!jump && !move) {
			holdY=-25;
			holdX=-2;
		}
		
		//Slide
		if(slide) {
			x+=10*side;
			count++;
			if(count==15) {
				slide=false;
				count=0;
				time.stop();
			}
			time.start();
			holdX=0;holdY=0;
		}
		
		//Prizemljenje
		if(y>=790) {
			y=790;
			holdX=0;		
		}

		//Specijalni napad
		if(specAttack && faza2) {
			specX+=holdSpec*side;
			if(specX>=a-260 && specY>b) {
				enemyHelth-=40;
				enemyReceivedSpecAttack=true;
				specAttack=false;
				specX=0;
				faza2=false;
				
			}
			holdX=holdY=0;
		}
		if(specX>=1000) {
			holdX=holdY=holdSpec=0;
			specAttack=false;
			specX=0;
			faza2=false;
		}
		
		if((attack || midKick || heightKick) &&(x>=a-200)) {
			System.out.println("PUNCHED");
			enemyPunched=true;
		}
		
		
		
		/////////////AI Fighter 2
		
		
		
		
		if(x<a && a>=x+210) {
			holdA=-2;
			enemyChangeBase=enemyChangeBase ? false:true;
		}else if(x<a && a<=x+210){
			retreatRight=true;
		}else if(x>a && a<=x-210) {
			holdA=2;
			enemyChangeBase=enemyChangeBase ? false:true;
		}else if(x>a && a>=x-210){
			retreatLeft=true;
		}
		
		
		
		
		if(x<a && retreatRight && a<800) {
			holdA=5;
			enemyChangeBase=enemyChangeBase ? false:true;
		}else if(x<a && a>800)
			retreatRight=false;
		if(x>a && retreatLeft && a>100) {
			holdA=-5;
			enemyChangeBase=enemyChangeBase ? false:true;
		}else if(a<100)
			retreatLeft = false;
		
		enemyMove=true;
		
		if(x<a && a<=x+240 && !enemyPunched) {
			enemyAttack=true;			
			enemyChangeBase=false;
		}
		

		if(enemyReceivedSpecAttack) {
			holdA=1;
			if(b>785)
				holdB=-5;
			else
				holdB=5;
		}else
			holdB=0;
		
		if(((x<a && specX>0 && specX>=a-600  && specX<=a-500) || b<790)) {
			//System.out.println(x+" "+a+"  "+specX+"    "+b);
			//System.out.println(b);
			enemyJump=true;
			if(b>600)
				holdB=-25;
			else {
				enemyJump=false;
				holdB=25;
			}
			if(!enemyJump) {
				b=770;
				///System.out.println("Wermaht "+b);
			}
			
		}
		//System.out.println(x+"     "+a);
		if((attack || midKick || heightKick) && x>=a-350) {
			holdA+=50;
			enemyHelth-=5;
			enemyPunched=true;
		}
		
		if(enemyAttack && a<=x+230 && !enemyPunched) {
			
			holdX=-10;
			myHelth-=5;
			enemyCount++;
			punched=true;
			count=0;
			if(enemyCount>=3) {
				holdX=-80;
				enemyCount=0;
				enemyAttack=false;
			}
		}
		
		if(enemyHelth<=0 || myHelth<=0) {
			enemyAttack=false;
			holdX=holdY=holdA=holdB=0;
		}
		
		//System.out.println(x+"   "+a+"    "+specX+"     "+a);
		
		//System.out.println("You: {X="+x+"  Y="+y+"}  ?  Enemy: {A="+a+"  "+b+"} => Move="+enemyChangeBase);
		if(punched)
			System.out.println(punched);
		
	}
	
}


public class Fight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Menu m=new Menu();
		Crtaj c=new Crtaj(m.getFighter1(), m.getFighter2());
		JFrame p=new JFrame();
		p.setSize(1000,1000);
		p.setVisible(true);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.add(c);
	}

}
