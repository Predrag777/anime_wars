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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Crtaj extends JPanel implements KeyListener, ActionListener{

	Timer t=new Timer(100,this);
	Fighter f1,f2;
	
	int level=0, enemyAttackConstr;
	
	int speed=0;
	int enemySpeed=15;
	int enemyNumberAttack, enemyCounterAttacks=0;
	public Crtaj(Fighter f1, Fighter f2, int level) {
		t.start();
		setSize(1000,1000);
		setVisible(true);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.f1=f1;
		this.f2=f2;
		this.level=level;
		if(level==1) {
			enemySpeed=15;
			enemyAttackConstr=20;
			enemyNumberAttack=1;
		}else if(level==2) {
			enemySpeed=20;
			enemyAttackConstr=15;
			enemyNumberAttack=3;
		}else {
			enemySpeed=25;
			enemyAttackConstr=8;
			enemyNumberAttack=6;
		}
		//playSound("ZvucniEfekti/back_sound.wav", 100);
		System.out.println(level+"    "+enemySpeed+"    "+enemyAttackConstr);
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
	int enemyAttackedCount=0;
	
	
	int x=100,y=790,holdX=0,holdY=0, holdFlyX=0, holdFlyY=0;
	int a=700,b=790,holdA=0,holdB=0;
	int teleport_c=1;
	
	
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
	boolean enemyBlock=false;
	boolean selectNewAttack =true;
	boolean chase=false;
	
	String enemyBaseAttacks[] = {"Punch.png", "MidKick.png","HeightKick.png"};
	String enemyJumpAtacks[] = {"JumpAttack.png"};
	
	int specX=0,specY=700,holdSpec;
	
	
	boolean jump=false;
	boolean fly=false;
	int flyCounter=0;
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
	boolean double_back=false;
	boolean double_back_gate=false;
	int double_back_counter=0;
	int teleport_counter=0;
	float myKi=100.0f;
	
	
	boolean faza1=false;
	boolean faza2=false;
	
	
	
	boolean changeBase=true;
	
	boolean slide=false;
	
	int side=1;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage image;
		BufferedImage wave;
		
		if(myKi<100) {
			myKi+=1;
		}
		try {
			String ss=f1.getFile()+"/"+f1.getName().toLowerCase()+ulti_ss+"Base.png";
			String waff=f2.getFile()+"/"+f2.getName().toLowerCase()+"Base.png";

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
					if(Math.abs(x-a)<200) {
						enemyHelth-=5;
						enemyPunched=true;
						playSound("ZvucniEfekti/punch.wav",0);
						attackCount=30;
					}
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
					if(Math.abs(x-a)<=200) {
						enemyHelth-=5;
						enemyPunched=true;
						playSound("ZvucniEfekti/punch.wav",0);
						attackCount=30;
					}
				}else {
					midKick=false;
					attackCount=0;
					
				}
				attackCount++;
			}
			if(heightKick && !jump) {
				if(attackCount<5) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Kick1.png";
					System.out.println(ss);
				}else if(attackCount<20) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"HeightKick.png";
					if(Math.abs(x-a)<=200) {
						enemyHelth-=5;
						enemyPunched=true;
						playSound("ZvucniEfekti/punch2.wav",0);
						attackCount=30;
					}
				}else {
					heightKick=false;
					attackCount=0;
					
				}
				attackCount++;
			}
			if(specAttack) {
				
				if(count==30) {
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
					myHelth-=10;
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
					System.out.println(ss);
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
			}
			if(double_back_gate) {
				
				if(double_back_counter>3) {
					double_back_gate=false;
					double_back_counter=0;
				}
				double_back_counter++;
			}
			
			if(double_back) {
				playSound("ZvucniEfekti/teleport.wav",0);
				x-=150;
				double_back=false;
				double_back_gate=false;
				double_back_counter=0;
				myKi-=50;
				
				
			}
			
			if(myHelth<=0) {
				y=790;
				punched=false;
				enemyAttack=false;
				if(deadCount<5) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Dead1.png";
				}else if(deadCount<10) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Dead2.png";
				}else if(deadCount<15) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Dead.png";
				}else {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Dead.png";
					System.out.println(ss);
					playSound(waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Won.wav",0);
					
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
			e.printStackTrace();
		}
	
		BufferedImage fighter2;
		
		try {
			String ss=f1.getFile()+"/"+f1.getName().toLowerCase()+ulti_ss+"Base.png";
			String waff=f2.getFile()+"/"+f2.getName().toLowerCase()+"Base.png";
			
			if(!enemyChangeBase && enemyMove) {
				waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Base2.png";
			}
			
			if((enemyAttack || chase) && !enemyMove) {//enemyNumberAttack, enemyCounterAttacks=0;
				if(enemyCounterAttacks<enemyNumberAttack)
					chase=true;
				else {
					enemyAttack=false;
					chase=false;
				}
				if(selectNewAttack) {
					selectNewAttack=false;
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
				//System.out.println("------------------------------------------");
				if(enemyPunch) {
					if(enemyAttackCount<enemyAttackConstr/2) {//<5
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Punch2.png";
					}else if(enemyAttackCount<enemyAttackConstr) {//<10
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyBaseAttacks[0];
						if(x<a && a<x+200) {
							myHelth-=5;
							playSound("ZvucniEfekti/punch.wav",0);
							punched=true;
							enemyAttackCount=30;
						}
						
					}else {
						removeHealth=false;
						enemyAttackCount=0;
						enemyPunch=false;
						enemyAttack=false;
						enemyCounterAttacks++;
						
						selectNewAttack=true;
					}
					
					
				}
				if(enemyHeightKick) {
					if(enemyAttackCount<enemyAttackConstr/2) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Kick1.png";
					}else if(enemyAttackCount<enemyAttackConstr) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyBaseAttacks[2];
						//removeHealth=true;
						if(x<a && a<x+200) {
							myHelth-=8;
							punched=true;
							enemyAttackCount=30;
							playSound("ZvucniEfekti/punch2.wav",0);
						}
					}else {
						removeHealth=false;
						enemyAttackCount=0;
						enemyHeightKick=false;
						enemyAttack=false;
						enemyCounterAttacks++;
						
						
						selectNewAttack=true;
					}
				}
				if(enemyMidKick) {
					if(enemyAttackCount<enemyAttackConstr/2) {
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Kick1.png";
					}else if(enemyAttackCount<enemyAttackConstr) {
						//removeHealth=true;
						waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyBaseAttacks[1];
						if(x<a && a<x+200 && b>=y) {
							myHelth-=15;
							punched=true;
							enemyAttackCount=30;
							playSound("ZvucniEfekti/punch2.wav",0);
						}
					}else {
						enemyCounterAttacks++;
						removeHealth=false;
						enemyAttackCount=0;
						enemyMidKick=false;
						enemyAttack=false;
						
						
						selectNewAttack=true;
						
					}
				}
				
				enemyAttackCount++;
			}else
				enemyAttackCount=0;
			if(enemyJump) {
				
				if(enemyJumpAttack) {
					waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+enemyJumpAtacks[0];//////////////////////////CHANGE!!!
				}else
					waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Jump.png";
			}
			
			
			if(enemyReceivedSpecAttack) {
				enemyAttack=false;
				if (enemyAttackedCount>=30) {
					enemyReceivedSpecAttack = false;
					enemyAttackCount=0;
	            } else {
	                waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Burnt.png";
	            }
				enemyAttackedCount++;
				
			}
			
			
			
			if(enemyHelth<=0) {
				b=790;
				enemyAttack=false;
				if(deadCount<5) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Dead1.png";
				}else if(deadCount<10) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Dead2.png";
				}else if(deadCount<15) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Dead.png";
				}else {
					//playSound(ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Won.wav",0);
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Dead.png";
					t.stop();				deadCount=0;}
				
				deadCount++;
			}
			
			if(enemyEscape) {
				enemyAttack=false;
				if(enemyCount<3) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Escape1.png";
				}else if(enemyCount<6) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Escape2.png";
				}else if(enemyCount<9) {
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Escape3.png";
				}else if(enemyCount<13){
					waff = waff.substring(0, waff.indexOf('/') + 1) + f2.getName().toLowerCase() + "Escape4.png";
					enemyCount=-1;
					enemyEscape=false;
				}
				
				enemyCount++;
				
			}
			
			if(enemyPunched) {
				enemyAttack=false;
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
		    
		    g.setColor(Color.BLUE);
		    g.drawString(String.valueOf(myKi), 10, 150); 
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
		if(!jump && !fly) {
			holdX=15;
			holdY=0;
			move=true;
		}
		if(fly) {
			holdFlyX=15;
			holdFlyX=0;
		}
	}
	
	public void left() {
		if(!jump) {
			holdX=-15;
			holdY=0;
			move=false;
		}
		System.out.println(fly);
		if(fly) {
			System.out.println("SS");
			holdFlyX=-15;
			holdFlyX=0;
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
		String ss=f1.getFile()+"/"+f1.getName().toLowerCase()+ulti_ss+"Base.png";

		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_UP && !double_back) {
			System.out.println(flyCounter);
			flyCounter++;
			if(jump && flyCounter==2 && myKi>30) {
				fly=true;
				System.out.println("FLY");
			}
			
			
			if(y==790) {
				playSound("ZvucniEfekti/jump.wav", 0);
				jump();
			}
		}

		if(code==KeyEvent.VK_A && !punched && !double_back) {
			
			punch();
		}
		if(code==KeyEvent.VK_D && !punched && !double_back) {
			midKick();
		}
		if(code==KeyEvent.VK_W && !punched && !double_back) {
			heightKick();
		}
		if(code==KeyEvent.VK_Q && !double_back) {
			playSound(ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Ulty.wav",0);
			ulty=true;
		}
		if(code==KeyEvent.VK_F && !double_back) {
			block=true;
		}
		//if(y==790) {
			if(code==KeyEvent.VK_S && !double_back) {
				playSound(ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"SpecAttack.wav",0);
				specAttack();
				//startAttackTime=System.currentTimeMillis();
			}
			
			if(code==KeyEvent.VK_RIGHT && !double_back) {
				right();
				changeBase=changeBase ? false:true;
			}
			if(code==KeyEvent.VK_LEFT && !double_back) {//////////////////////////////////////////
				double_back_gate=true;
				teleport_c++;
				if(double_back_counter>1 && double_back_counter<3 && teleport_c%5==0 && myKi>50) {
					double_back=true;
				}
				changeBase=changeBase ? false:true;
				
				left();
			}
		
		
	}
	
	
	public static void playSound(String soundFile, int loopCount) {
	    try {
	        File soundPath = new File(soundFile);
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundPath);
	        Clip clip = AudioSystem.getClip();

	        clip.open(audioStream);
	        clip.loop(loopCount);  // Ponavljanje zvuka
	        clip.start();
	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	        e.printStackTrace();
	    }
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		if(fly) {
			holdX=0;holdY=0;
		}
		
		
		
		if((x+holdX<=50 && holdX<0) || (x+holdX>=900 && holdX>0)) {
			holdX=0;
		}
		
		
		//System.out.println(holdA);
		if((a+holdA<=50 && holdA<0) || (a+holdA>=900 && holdA>0)) {
			holdA=0;
		}
		System.out.println(holdFlyX);
		x+=holdX+holdFlyX;y-=holdY+holdFlyY;
		//a+=holdA;b+=holdB;
		
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
		if(reachTop && y<790 && move && !fly) {
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
			if(specX>=a-260 && b>700) {
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
		
		
		
		/////////////AI Fighter 2//////////////////////////////////////////////////////////////////////////////////////////////
		//System.out.println(enemyMove+"   "+enemyAttack+"    "+enemyAttackedCount);//Problem true true Fixiraj to!!!!!!!!!!!!!!!!!!

		if(enemyAttackCount>20)
			enemyAttackCount=0;
		
		
		if(x<a && (a>x+300))
			enemyMove=true;
		if(enemyMove && !enemyEscape) {
			enemyChangeBase=enemyChangeBase ? false:true;
			if(x<a && a>=x+borders) {
				holdA=-enemySpeed*side;////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}else if(x<a && a<=x+borders){
				retreatRight=true;
			}else if(x>a && a<=x-borders) {
				holdA=enemySpeed*side;
			}else if(x>a && a>=x-borders){
				retreatLeft=true;
			}
			
			if(enemyJump && x>=a-350 && y>=b-120) {
				enemyJumpAttack=true;
				if(x>=a-350 && !block) {
					//myHelth-=10;
					punched=true;
				}
			}
			
			//System.out.println(retreatRight+"   "+retreatLeft+"    X="+(x+borders)+" a="+a+"  ");
			if(a>=800)
				retreatRight=false;
			if(x<a && retreatRight && a<800) {
				holdA=enemySpeed*side;
			} 
			else if(x<a && a>800)
				retreatRight=false;
			else if(x>a && retreatLeft && a>100) {
				holdA=-enemySpeed*side;
			}
			else if(a<100)
				retreatLeft = false;
			enemyAttack=false;
			enemyAttackCount=0;
			
		}else {
			enemyChangeBase=enemyChangeBase ? false:true;
			if((a>=800 || a<x+250) && !enemyAttack)
				enemyMove=true;
			
		}
		
		
		if(enemyReceivedSpecAttack) {
			holdA=15;
			enemyAttack=false;
			if(b>785)
				holdB=-5;
			else
				holdB=5;
		}else
			holdB=0;
		
		
		
		//enemy Attack controls
		if((x<a && a<=x+200 && !enemyReceivedSpecAttack && !enemyPunched && !enemyEscape)) {
			//System.out.println(x+"  ATTACK  "+a+ "    "+enemyAttackCount);
			enemyAttack=true;
			enemyMove=false;
			holdA=0;
		}
		
		if(x<a && specX>=a-600 && specX<=a-100 && specX>0 && !enemyReceivedSpecAttack && !enemyPunched) {// || b<=790
			enemyJump=true;
			enemyAttack=false;
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
			enemyAttack=false;
			holdA=20*side;
			holdB=0;
			b=790;
			enemyMove=false;
			
		}
		
		if(!enemyJump && !enemyReceivedSpecAttack) {
			b=790;
		}

		
		
		
		
	}
	
}


public class Fight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Menu m=new Menu();
		Crtaj c=new Crtaj(m.getFighter1(), m.getFighter2(), m.getLevel());
		JFrame p=new JFrame();
		p.setSize(1000,1000);
		p.setVisible(true);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.add(c);
	}

}
