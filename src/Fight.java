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
import java.util.Random;

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
	
	int speed=0;double pravacX=1, pravacY=1, dirX=1, dirY=1;
	
	int alchemyAttackX=0;
	
	int permutCircleCounter=0;
	
	
	double target=0.0, targetMe=0.0;
	int enemySpeed=15;
	int enemySpecX=-10;int enemySpecY=0;
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
	
	
	int count=0, groundedCount=0;
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
	boolean grounded=false;
	boolean enemyFly=false,  enemyBarage=false;int enemyBarageCounter=0;
	int enemyKi=100;
	
	String enemyBaseAttacks[] = {"Punch.png", "MidKick.png","HeightKick.png"};
	String enemyJumpAtacks[] = {"JumpAttack.png"};
	
	int specX=0,specY=0,holdSpec, holdSpecY;
	
	
	boolean jump=false;
	boolean fly=false;
	int flyCounter=0;
	int fallDownCounter=0;
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
	float myKi=1000000.0f;
		
		
	boolean faza1=false;
	boolean faza2=false;
		
		
		
	boolean changeBase=true;
		
	boolean slide=false;
		
	int side=1;
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage image;
		BufferedImage wave;
		BufferedImage enemyWave;
		if(myKi<100 && !fly && !specAttack) {
			myKi+=1;
		}
		
		if(enemyKi<100 && !enemyFly && !enemyBarage) {
			enemyKi+=1;
		}
		
		try {
			String ss=f1.getFile()+"/"+f1.getName().toLowerCase()+ulti_ss+"Base.png";
			String waff=f2.getFile()+"/"+f2.getName().toLowerCase()+"Base.png";
			
			String s="";
			
			if(!changeBase) {
				ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Base2.png";
			}
			
			if(jump || fly) {
				ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"Jump.png";
			}
			
			if(fly)
				myKi-=1;
			
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
					specX=x;specY=y;
					target=izracunajRastojanje(specX, specY, a, b);
					if(fly) {
						pravacX = (a - specX) / target;
			            pravacY = (b - specY) / target;
					}
					faza2=true;
				}else {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+ulti_ss+"SpecAttack.png";
				}
				if(faza2) {
					count=0;
					if(!f1.getWho().equalsIgnoreCase("Saiyan"))
						alchemyAttackX=a;
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
				
				if(f1.getWho().equalsIgnoreCase("Saiyan")) {
					if(count<30) {
						ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"NewForm.png";
						System.out.println(ss);
						ulti_ss="Ulty";
						
					}else {
						ulty=false;
						count=0;
					}
				}else {
					if(count<10) {
						ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"NewForm1.png";
						System.out.println(ss);
						ulti_ss="Ulty";
						
					}else if(count<20){
						ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"NewForm2.png";
					}else {
						ulty=false;
						count=0;
					}
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
			
			if(grounded) {
				if(groundedCount<1)
					playSound("ZvucniEfekti/landing.wav",0);
				else if(groundedCount<10) {
					ss=ss.substring(0,ss.indexOf('/')+1)+f1.getName().toLowerCase()+"Grounded.png";
				}else
					grounded=false;
				
				groundedCount++;
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
				
				wave=ImageIO.read(new File(f1.getFile()+"/"+f1.getName().toLowerCase()+"Power.png"));	
				if(!f1.getWho().equalsIgnoreCase("Saiyan")) {
					System.out.println(alchemyAttackX);
					if(permutCircleCounter<15)
						g.drawImage(wave,alchemyAttackX-200, 900,   400,100,null);////Projektil
					else {
						faza2=false;
						permutCircleCounter=0;
					}
					permutCircleCounter++;
				}else
					g.drawImage(wave,specX+200, specY,   100*side,100,null);////Projektil
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
							if(block)
								myHelth-=3;
							else
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
							if(block)
								myHelth-=4;
							else
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
							if(block)
								myHelth-=10;
							else
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
			
			if(enemyFly) {
				enemyKi-=1;
				waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"Jump.png";
			}
			
			if(enemyBarage) {
				enemyBarageCounter++;
				if(enemyBarageCounter<5) {
					targetMe = izracunajRastojanje(specX, specY, a, b);
					dirX = (x - enemySpecX) / targetMe;
		            dirY = (y - enemySpecY) / targetMe;
					waff=waff.substring(0,waff.indexOf('/')+1)+f2.getName().toLowerCase()+"BarageFire.png";
				}else {

					enemyKi-=10;
					playSound("ZvucniEfekti/barageFire.wav",0);
					enemyBarage=false;
					enemyBarageCounter=0;
				}
				
			}
			
			if(enemySpecX>0) {
				enemyWave=ImageIO.read(new File("folder1/wave.png"));
				g.drawImage(enemyWave,enemySpecX-100, enemySpecY, 100*side*(-1),100,null);////Projektil
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
		    g.drawString(String.valueOf(enemyHelth), getWidth() - 90, 60);
		    
		    g.setColor(Color.BLUE);
		    g.drawString(String.valueOf(myKi), 10, 150); 
		    
		    g.setColor(Color.BLUE);
		    g.drawString(String.valueOf(enemyKi), getWidth() - 90, 150); 
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
			holdX=15;
			holdY=0;
			move=true;
		}
		
	}
	
	public void left() {
		if(!jump) {
			holdX=-15;
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
		myKi-=50;
		specAttack=true;
		holdSpec=30;
		holdSpecY=0;
		if(fly) 
			holdSpecY=100;
	}
	public void slide() {
		slide=true;
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(!jump || fly) {
			holdX=0;holdY=0;
			fallDownCounter=0;
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
			//System.out.println(flyCounter);
			flyCounter++;
			if(jump && flyCounter==2 && myKi>30) {
				fly=true;
				playSound("ZvucniEfekti/fly.wav",0);
				jump=false;
				flyCounter=0;
				holdX=0;holdY=0;
			}
			
			
			if(fly)
				holdY=10;
			
			if(y==790) {
				playSound("ZvucniEfekti/jump.wav", 0);
				jump();
			}
		}
		
		if(code==KeyEvent.VK_DOWN && !punched && !jump && fly) {
			if(fly) {
				fallDownCounter++;
				if(fallDownCounter%5==0 && myKi>25) {
					playSound("ZvucniEfekti/teleport.wav",0);
					myKi-=20;
					y=790;
					fallDownCounter=0;
				}
			}
			holdY=-10;
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
	public void actionPerformed(ActionEvent event) {
	    repaint();
	    handleBoundaryCollision();
	    handleFlyCondition();
	    handleMovement();
	    handleJump();
	    handleSlide();
	    handleGrounding();
	    handleSpecialAttack();
	    handleEnemyAI();
	}

	private void handleBoundaryCollision() {
	    if ((x + holdX <= 50 && holdX < 0) || (x + holdX >= 900 && holdX > 0)) {
	        holdX = 0;
	    }
	    if ((a + holdA <= 50 && holdA < 0) || (a + holdA >= 900 && holdA > 0)) {
	        holdA = 0;
	    }
	}

	private void handleFlyCondition() {
	    if (fly && myKi < 20) {
	        fly = false;
	    }
	}

	private void handleMovement() {
	    x += holdX + holdFlyX;
	    y -= holdY + holdFlyY;
	    /*b += holdB;
	    a += holdA;*/
	    if (a < x + 100) {
	        side = -1;
	    } else {
	        side = 1;
	    }

	    if (y <= 600) {
	        reachTop = true;
	    }
	}

	private void handleJump() {
	    if (jump && !reachTop && y >= 600 && move && !fly) {
	        holdX = 10;
	        holdY = 25;
	    } else if (jump && !reachTop && y >= 600 && !move && !fly) {
	        holdX = -10;
	        holdY = 25;
	    } else if (reachTop && y < 790 && move && !fly) {
	        holdY = -25;
	        holdX = 10;
	        if (y >= 780) {
	            y = 790;
	            jump = false;
	            reachTop = false;
	        }
	    } else if (reachTop && !move && !fly) {
	        holdY = -25;
	        holdX = -10;
	        if (y > 780) {
	            flyCounter = 0;
	            y = 790;
	            reachTop = false;
	            jump = false;
	        }
	    }
	}

	private void handleSlide() {
	    if (slide) {
	        x += 10 * side;
	        count++;
	        if (count == 15) {
	            slide = false;
	            count = 0;
	        }
	        holdX = 0;
	        holdY = 0;
	    }
	}

	private void handleGrounding() {
	    if (y >= 790) {
	        y = 790;
	        holdX = 0;
	    }
	    if (y >= 730 && fly) {
	        holdY = -10;
	        if (y >= 770) {
	            flyCounter = 0;
	            fly = false;
	            y = 770;
	        }
	        grounded = true;
	    }
	}

	private void handleSpecialAttack() {
	    if (specAttack && faza2) {
	        specX += holdSpec * side;
	        specY += holdSpecY * pravacY;
	        if (specX >= a - 260 && b > 700 && specY >= 750) {
	            enemyHelth -= 40;
	            enemyJumpAttack = enemyJump = false;
	            enemyReceivedSpecAttack = true;
	            specAttack = false;
	            specX = 0;
	            faza2 = false;
	        }
	        holdX = holdY = 0;
	    }
	    if (specX >= 1000) {
	        holdX = holdY = holdSpec = 0;
	        specAttack = false;
	        specX = 0;
	        faza2 = false;
	    }
	}

	private void handleEnemyAI() {
	    if (enemyAttackCount > 20) enemyAttackCount = 0;

	    if (x < a && (a > x + 300)) enemyMove = true;
	    
	    /*if (enemyMove && !enemyEscape) {
	        enemyChangeBase = !enemyChangeBase;
	        handleEnemyMovement();
	        handleEnemyJump();
	        handleEnemyRetreat();
	    } else {
	        enemyChangeBase = !enemyChangeBase;
	        if ((a >= 800 || a < x + 250) && !enemyAttack) enemyMove = true;
	    }*/
	    
	    int move=MonteKarlo(15, x, a, enemySpeed, 5, 5, myHelth, enemyHelth, 40, 100, 20, enemyKi);
	    //System.out.println(move+"    "+a+"    "+x);
	    if(move==1) {
	    	a-=holdA;
	    	enemyMove=true;
	    }else if(move==2) {
	    	a+=holdA;
	    	enemyMove=true;
	    	enemyJump=enemyPunch=false;
	    }else if(move==3) {
	        enemyJump = true;
	        enemyAttack = false;
	        holdA = -20;
	        if (b < 600) {
	            enemyReachTop = true;
	        }
	        holdB = enemyReachTop ? 10 : -25;
	        if (b < 790 && enemyReachTop) {
	            holdB = 0;
	            enemyJump = false;
	            enemyReachTop = false;
	        }
	        enemyMove=enemyPunch=false;
	    }else if(move==4) {
	    	enemyAttack=true;
	    	enemyMove=enemyJump=enemyPunch=false;
	    }else {
	    	enemyAttack=true;
	    	enemyMove=enemyJump=enemyPunch=false;
	    	System.out.println("SSSSSS");
	    	
	    }
	    handleEnemyFly();
	    enemySpecialAttacked();

	    if (!enemyJump && !enemyReceivedSpecAttack && !enemyFly) {
	        b = 790;
	    }
	}
	
	
	private void handleEnemyMovement() {
	    if (x < a && a >= x + borders) {
	        holdA = -enemySpeed * side;
	    } else if (x < a && a <= x + borders) {
	        retreatRight = true;
	    } else if (x > a && a <= x - borders) {
	        holdA = enemySpeed * side;
	    } else if (x > a && a >= x - borders) {
	        retreatLeft = true;
	    }
	}

	private void handleEnemyJump() {
	    if (enemyJump && x >= a - 350 && y >= b - 120 && !enemyFly) {
	        enemyJumpAttack = true;
	        if (x >= a - 350 && !block) {
	            punched = true;
	        }
	    }
	}

	private void handleEnemyRetreat() {
	    if (a >= 800) retreatRight = false;
	    if (x < a && retreatRight && a < 800) {
	        holdA = enemySpeed * side;
	    } else if (x < a && a > 800) {
	        retreatRight = false;
	    } else if (x > a && retreatLeft && a > 100) {
	        holdA = -enemySpeed * side;
	    } else if (a < 100) {
	        retreatLeft = false;
	    }
	    enemyAttack = false;
	    enemyAttackCount = 0;
	}

	private void handleEnemyFly() {
	    if (fly && !enemyBarage && enemyKi > 50) {
	        enemyFly = true;
	        if (b > y - 50 && b < y + 100 && enemySpecX <= 0) {
	            enemySpecX = a;
	            enemySpecY = b;
	            enemyBarage = true;
	        }
	    }

	    if (enemyBarage || enemySpecX > 0) {
	        enemySpecX -= 50 * side;
	    }

	    if (!fly && b > 770) {
	        enemyFly = false;
	        b = 790;
	    }

	    if (enemyReceivedSpecAttack) {
	        holdA = 15;
	        enemyAttack = false;
	        holdB = (b > 785) ? -5 : 5;
	    }

	    if (enemyFly) {
	        if (b > y + 30) {
	            holdB = -15;
	        } else if (b < y - 30) {
	            holdB = 15;
	        }
	    }
	}

	private void enemySpecialAttacked() {
	    if (enemyReceivedSpecAttack) {
	        holdA = 15;
	        enemyAttack = false;
	        if (b > 785) {
	            holdB = -5;
	        } else {
	            holdB = 5;
	        }
	    }

	    if (x < a && a <= x + 200  && !enemyReceivedSpecAttack && !enemyPunched && !enemyEscape) {
	        enemyAttack = true;
	        enemyMove = false;
	        holdA = 0;
	    }

	    if (x < a && specX >= a - 600 && specX <= a - 100 && specX > 0 && !enemyReceivedSpecAttack && !enemyPunched) {
	        enemyJump = true;
	        enemyAttack = false;
	        holdA = -20;
	        if (b < 600) {
	            enemyReachTop = true;
	        }
	        holdB = enemyReachTop ? 10 : -25;
	        if (b < 790 && enemyReachTop) {
	            holdB = 0;
	            enemyJump = false;
	            enemyReachTop = false;
	        }
	    }

	    if (jump && x >= a - 300) {
	        enemyEscape = true;
	        enemyAttack = false;
	        holdA = 20 * side;
	        holdB = 0;
	        b = 790;
	        enemyMove = false;
	    }

	    if (!enemyJump && !enemyReceivedSpecAttack && !enemyFly) {
	        b = 790;
	    }

	    if (enemyKi < 50 && b == 790) {
	        enemyFly = false;
	    }

	    if (enemyKi < 20 && enemyFly && !enemyJump) {
	        holdB = 50;
	        if (b > 790) {
	            b = 790;
	            enemyFly = false;
	        }
	    }
	}

	public static double izracunajRastojanje(int x1, int y1, int x2, int y2) {
	    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
    public int MonteKarlo(int stepSize, int x, int a, int effectiveDistance, int basicAttack,
            int timeForBasicAttack, int myHelth, int enemyHealth, int specAttack, int specAttackSpeed,
            int timeForSpecAttack, int ki) 
{
			Random rand = new Random();
			int numSimulations = 10; // Broj simulacija
			double bestScore = Double.NEGATIVE_INFINITY;
			int bestMove = -1;
			
			// Imamo 5 poteza: 1 = MoveLeft, 2 = MoveRight, 3 = Jump, 4 = BasicAttack, 5 = SpecAttack
			for (int move = 1; move <= 5; move++) {
			double totalScore = 0;
			
			// Simuliramo svaki potez više puta
			for (int i = 0; i < numSimulations; i++) {
			  totalScore += simulateMove(move, stepSize, x, a, effectiveDistance, basicAttack, 
			          timeForBasicAttack, myHelth, enemyHealth, specAttack, specAttackSpeed, timeForSpecAttack, ki);
			}
			
			if(totalScore>bestScore) {
				bestScore=totalScore;
				bestMove=move;
			}
			/*// Prosečan rezultat za trenutni potez
			double averageScore = totalScore / numSimulations;
			
			// Ako je ovaj potez bolji, postaje najbolji
			if (averageScore > bestScore) {
			  bestScore = averageScore;
			  bestMove = move;
			}*/
			}
			
			return bestMove; // Vraća broj koji predstavlja najbolji potez
}

// Simulacija jednog poteza
public double simulateMove(int move, int stepSize, int x, int a, int effectiveDistance, int basicAttack,
                 int timeForBasicAttack, int myHelth, int enemyHealth, int specAttack, int specAttackSpeed,
                 int timeForSpecAttack, int ki) 
{
			Random rand = new Random();
			double score = 0;
			int distance = Math.abs(a - x);  // Udaljenost između igrača i protivnika
			
			// Nasumično simuliramo akcije i njihove ishode
			switch (move) {
				case 1:
				  x-=stepSize;
				  if((a-x)>200) {
				  	score=rand.nextInt(40);
				  }
				  break;
				case 2:
					x-=stepSize;
					if((a-x)>200) {
						score=rand.nextInt(40);
					}
					break;
				case 3:
					x-=stepSize;
					if((a-x)>400) {
						score=rand.nextInt(50);
					}
					break;
				case 4:
					if(a-stepSize<effectiveDistance) {
						score=rand.nextInt(80);
					}else
						score=rand.nextInt(20);
				case 5:
					if(x-a>500 && ki>50) {
						score=rand.nextInt(50);
					}
			  
			}
			
			// Prilagođavanje rezultata na osnovu zdravlja
			score += (myHelth - enemyHealth) * 0.1; // Veći skor ako igrač ima više zdravlja
			
			return score;
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
