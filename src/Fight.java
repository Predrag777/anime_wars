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
	
	int myHelth=100;
	int enemyHelth=100;
	int borders=190;
	
	
	int count=0;
	int enemyCount=0, enemyCount2=0;
	int deadCount=0;
	int blockCount=0;
	
	int enemyAttackCount=0;
	int attackCount=0;
	
	
	int x=100,y=790,holdX=0,holdY=0;
	int a=700,b=790,holdA=0,holdB=0;
	int enemySpeed=15;
	
	
	String ulti_ss="";
	
	int startAttackTime;
	
	
	
	
	boolean enemyMove=true;
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
	boolean enemyEscape=false;
	boolean enemyReachTop=false;
	boolean enemyJumpAttack=false;
	
	String enemyBaseAttacks[] = {"Punch.png", "MidKick.png","HeightKick.png"};
	String enemyJumpAtacks[] = {"JumpAttack.png"};
	
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
	boolean reachTop=false;
	boolean block=false;
	boolean removeHealth=false;
	
	boolean faza1=false;
	boolean faza2=false;
	
	
	boolean changeBase=true;
	
	boolean slide=false;
	
	int side=1;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage image;
		BufferedImage wave;
		
		try {
			String ss=f1.getFile()+"/"+f1.getName().toLowerCase()+ulti_ss+"Base.png";
			String s="";
			
			if(!changeBase) {
				ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Base2.png";
			}
			
			if(jump) {
				ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Jump.png";
			}
			if(attack && !jump) {
				if(attackCount<5) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Punch2.png";
				}else if(attackCount<10) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Punch.png";
				}else {
					attack=false;
					attackCount=0;
				}
				attackCount++;
			}
			if(midKick && !jump) {
				if(attackCount<5) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Kick1.png";
				}else if(attackCount<20) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"MidKick.png";
				}else {
					midKick=false;
					attackCount=0;
				}
				attackCount++;
			}
			if(heightKick && !jump) {
				if(attackCount<5) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Kick1.png";
				}else if(attackCount<20) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"HeightKick.png";
				}else {
					heightKick=false;
					attackCount=0;
				}
				attackCount++;
			}
			if(specAttack) {
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
					punched=false;
					count=0;
				}else {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Punched.png";
				}
				count++;
			}
			
			if(jump && attack) {
				ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"JumpAttack.png";
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
			
			if(block) {
				if(blockCount<20) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Block.png";
				}else {
					block=false;
					blockCount=0;
				}
				blockCount++;
				//System.out.println("SS");
			}
			
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
					t.stop();	
					deadCount=0;
				}
				
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
			
			if(!enemyChangeBase) {
				waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Base2.png";
			}
			if(!enemyAttack) {
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
			}
			
			if(enemyAttack) {
				if(enemyPunch) {
					if(enemyAttackCount<5) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Punch1.png";
					}else if(enemyAttackCount<10) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyBaseAttacks[0];
						removeHealth=true;
					}else {
						removeHealth=false;
						enemyAttackCount=0;
						enemyPunch=false;
						enemyAttack=false;
					}
					
					
				}
				if(enemyHeightKick) {
					if(enemyAttackCount<10) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Kick.png";
					}else if(enemyAttackCount<15) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyBaseAttacks[2];
						removeHealth=true;
					}else {
						removeHealth=false;
						enemyAttackCount=0;
						enemyHeightKick=false;
						enemyAttack=false;
					}
				}
				if(enemyMidKick) {
					if(enemyAttackCount<10) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Kick.png";
					}else if(enemyAttackCount<15) {
						removeHealth=true;
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyBaseAttacks[1];
					}else {
						removeHealth=false;
						enemyAttackCount=0;
						enemyMidKick=false;
						enemyAttack=false;
					}
				}
				
				enemyAttackCount++;
			}
			if(enemyJump) {
				
				if(enemyJumpAttack) {
					waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyJumpAtacks[0];//////////////////////////CHANGE!!!
				}else
					waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Jump.png";
			}
			
			
			if(enemyReceivedSpecAttack) {
				
				if (enemyAttackCount>=30) {
					enemyReceivedSpecAttack = false;
					enemyAttackCount=0;
	            } else {
	                waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Burnt.png";
	            }
				enemyAttackCount++;
				
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
					t.stop();				deadCount=0;}
				
				deadCount++;
			}
			
			if(enemyEscape) {
				if(enemyCount<10) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Escape1.png";
				}else if(enemyCount<20) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Escape2.png";
				}else if(enemyCount<30) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Escape3.png";
				}else if(enemyCount<35){
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Escape4.png";
					enemyCount=-1;
					enemyEscape=false;
				}
				
				enemyCount++;
				
			}
			
			if(enemyPunched) {
				if(enemyCount>10) {
					enemyPunched=false;
					enemyCount=0;
				}else {
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
		if(code==KeyEvent.VK_F) {
			block=true;
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
			if(code==KeyEvent.VK_E) {
				slide();
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		if((x+holdX<=50 && holdX<0) || (x+holdX>=900 && holdX>0)) {
			holdX=0;
		}
		
		

		/*if((a+holdA<=50 && holdA<0) || (a+holdA>=900 && holdA>0)) {
			holdA=0;
		}*/
		System.out.println(holdA);
		x+=holdX;y-=holdY;
		a+=holdA;b+=holdB;
		
		Timer time=new Timer(1000, this);
		if(a<x+100) {
			side=-1;
			
		}else
			side=1;
		
		if(y<=600)
			reachTop=true;
		//Skok
		if(jump && !reachTop && y>=600 && move) {
			holdX=10;
			holdY=25;
		}
		if(jump && !reachTop && y>=600 && !move) {
			holdX=-10;
			holdY=25;
		}
		if(reachTop && y<790 && move) {
			holdY=-25;
			holdX=10;
			if(y>=780) {
				y=790;
				jump=false;
				reachTop=false;
			}
		}
		if(reachTop && y<790 && !move) {
			holdY=-25;
			holdX=-10;
			if(y>780) {
				y=790;
				reachTop=false;
				jump=false;
			}
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
			if(specX>=a-260 && b>660) {
				enemyHelth-=40;
				enemyJumpAttack=enemyJump=false;
				
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
		
		
		/////////////AI Fighter 2//////////////////////////////////////////////////////////////////////////////////////////////
		//System.out.println(enemyMove+"   "+enemyAttack);//Problem true true Fixiraj to!!!!!!!!!!!!!!!!!!
		AI ai=new AI(enemyHelth, x,y,a,b,false,false,false);
		//movement
		int moveNum=ai.move();
		System.out.println(moveNum);
		if(moveNum==1) {
			holdA=-enemySpeed;
		}else if(moveNum==3) {
			holdA=enemySpeed;
		}
		/*if(enemyMove) {
			enemyChangeBase=enemyChangeBase ? false:true;
			if(x<a && a>=x+borders) {
				holdA=-enemySpeed;////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}else if(x<a && a<=x+borders){
				retreatRight=true;
			}else if(x>a && a<=x-borders) {
				holdA=enemySpeed;
			}else if(x>a && a>=x-borders){
				retreatLeft=true;
			}
			
			if(enemyJump && x>=a-350 && y>=b-120) {
				enemyJumpAttack=true;
				if(x>=a-350 && !block) {
					myHelth-=10;
					punched=true;
				}
			}
		
			if(x<a && retreatRight && a<800) {
				holdA=enemySpeed;
			}else if(x<a && a>800)
				retreatRight=false;
			if(x>a && retreatLeft && a>100) {
				holdA=-enemySpeed;
			}else if(a<100)
				retreatLeft = false;
		}else {
			enemyChangeBase=enemyChangeBase ? false:true;
			if((a>=800 || a<x+250) && !enemyAttack)
				enemyMove=true;
			
		}*/
		
		/*if(enemyReceivedSpecAttack) {
			holdA=15;
			if(b>785)
				holdB=-5;
			else
				holdB=5;
		}else
			holdB=0;
		
		if(x<a && a<=x+200 && !enemyPunched && !enemyPunched && !block && !enemyEscape) {
			enemyAttack=true;
			enemyMove=false;
			holdA=0;
			if(enemyCount2<=3 && removeHealth) {
				myHelth-=5;
				holdX=-10;
				enemyCount2++;
				punched=true;
			}
			
			if(enemyCount2>=3) {
				holdX=-80;
				enemyCount2=0;
			}
		}
		
		if(x<a && specX>=a-600 && specX<=a-100 && specX>0) {// || b<=790
			enemyJump=true;
			holdA=-20;
			if(b<600)
				enemyReachTop=true;
			if(!enemyReachTop) {
				holdB=-25;
			}else
				holdB=10;
			
			if(b<790 && enemyReachTop) {
				holdB=0;
				enemyJump=false;
				enemyReachTop=false;
			}
		}
		
		if(jump && x>=a-300) {
			enemyEscape=true;
			holdA=5;
			holdB=0;
			b=790;
			enemyMove=false;
			
		}
		
		if(!enemyJump && !enemyReceivedSpecAttack) {
			b=790;
		}
		
		
		/*
		if(b>=760)
			enemyJumpAttack=false;
		
		
		
		if(x<a && a<=x+240 && !enemyPunched) {
			enemyAttack=true;			
			enemyChangeBase=false;
		}
		
		if((attack || midKick || heightKick) && x>=a-350 && attackCount<2) {
			holdA+=50;
			enemyHelth-=5;
			enemyPunched=true;
		}
		
		if(enemyPunched) {
					
		}

		
		
		
		if(x<a && specX>=a-600 && specX<=a-100 && specX>0) {// || b<=790
			enemyJump=true;
			holdA=-20;
			if(b<600)
				enemyReachTop=true;
			if(!enemyReachTop) {
				holdB=-25;
			}else
				holdB=10;
			
			if(b<790 && enemyReachTop) {
				holdB=0;
				enemyJump=false;
				enemyReachTop=false;
			}
		}
		
		}
		if(enemyEscape) {
			holdA=5;
			holdB=0;
			b=790;
		}
		
		if(specAttack && (specX==0 || specX>a)) {
			enemyJump=false;
		}
		
		if(!enemyJump && b<790) {
			holdB=25;
		}
		if(b>790)
			b=790;
		enemyAttack=false;
		if(enemyAttack) {
			System.out.println("KURAC");
			holdA=0;
		}else {
			holdA=-enemySpeed;
		}
		
		if(enemyHelth<=0 || myHelth<=0) {
			enemyAttack=false;
			holdX=holdY=holdA=holdB=0;
		}*/
		
		
		
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
