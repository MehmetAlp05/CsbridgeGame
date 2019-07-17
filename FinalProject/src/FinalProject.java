import acm.graphics.*;
import acm.program.*;
import acm.util.MediaTools;
import acm.util.RandomGenerator;

import acm.graphics.*;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.io.*;
import sun.audio.*;




public class FinalProject extends GraphicsProgram {
	public int enemyHealth=100;
	public int idlex;
	public int idley;
	public int idleleftx;
	public int enemywalkx;
	public int enemywalky;
	public int x;
	public int y;
	public GImage enemyattackl;
	public GImage death;
	public GImage enemyattackr;
	public GImage idle;
	public GImage running;
	public GImage background;
	public GImage attack;
	public GImage idleleft;
	public GImage runleft;
	public GImage attackleft;
	public GImage jumpleft;
	public GImage jumpright;
	public GImage enemywalkr;
	public GImage enemywalkl;
	public GImage Greytable;
	public GImage Heart1_1;
	public GImage Heart1_2;
	public GImage Heart1_3;
	public GImage Heart2;
	public GImage Heart3;
	public GImage EnemyDeath;
	public GImage youLose;
	public boolean right=true;
	public boolean jump=true;
	public boolean defense=false;
	public int adventurerHealth=6;
	public AudioStream audios =null;


	public void run() {
		addMouseListeners();
		addKeyListeners();
		setSize(1440,480 );
		if(adventurerHealth>0&&enemyHealth!=0) {
			
			music("music.wav");	
			
		}
		
		

		map1();
		enemy1();
		if(enemyHealth<=0) {
			AudioPlayer.player.stop(audios);
			
			music("dejavu.wav");
			GImage dejavu=new GImage("giphy.gif");
			add(dejavu,(getWidth()-dejavu.getWidth())/2,(getHeight()-dejavu.getHeight())/2);
		}
		//song();
		
	}
	public void keyTyped() {

	}
	public void keyPressed(KeyEvent e) {
		int code =e.getKeyCode();
		if(code==KeyEvent.VK_ESCAPE) {
			exit();
		}



		if(adventurerHealth>0) {
			if(code == KeyEvent.VK_DOWN){

				defense=true;

			} else if(code == KeyEvent.VK_LEFT){
				int x =(int) idle.getX();int y=(int) idle.getY();
				remove(idle);
				remove(attack);
				remove(idleleft);
				remove(running);
				remove(attackleft);
				remove(jumpright);
				add(runleft,x,y);
				idle.move(-6,0);
				right=false;
			} else if(code == KeyEvent.VK_RIGHT){
				int x =(int) idle.getX();int y=(int) idle.getY();
				remove(idle);
				remove(attack);
				remove(idleleft);
				remove(jumpright);
				remove(runleft);
				remove(attackleft);
				add(running,x,y);
				idle.move(6,0);
				right=true;
			}else if(code==KeyEvent.VK_Z&&code!=KeyEvent.VK_DOWN) {
				int x =(int) idle.getX();int y=(int) idle.getY();
				remove(idle);
				remove(running);
				remove(idleleft);
				remove(jumpright);
				remove(runleft);

				if(right) {
					add(attack,x,y);
					remove(attackleft);
					GObject hittedObject1= getElementAt(idle.getX()+60,getHeight()-5);

					if(hittedObject1==enemywalkl) {
						//enemywalkl.move(1, 0);

						enemyHealth--;
						println(enemyHealth);
					}

				}
				else if(!right) {
					add(attackleft,x,y);
					remove(attack);
					GObject hittedObject2= getElementAt(idle.getX()-20,getHeight()-5);
					if(hittedObject2==enemywalkr||hittedObject2==enemyattackr||hittedObject2==enemywalkl||hittedObject2==enemyattackl) {
						//enemywalkr.move(-1, 0);
						enemyHealth--;
						println(enemyHealth);
					}
				}
			}


		}
	}
	public void keyReleased(KeyEvent e) {
		int code =e.getKeyCode();
		if(adventurerHealth>0) {
			if(code == KeyEvent.VK_RIGHT){
				idle();
			}
			else if(code == KeyEvent.VK_Z){
				int x =(int) idle.getX();int y=(int) idle.getY();
				remove(running);
				remove(idleleft);
				remove(attack);
				remove(runleft);
				remove(attackleft);
				remove(jumpright);
				if(right) {
					add(idle,x,y);
				}
				else if(!right) {
					add(idleleft,x,y);
				}
			}
			else if(code == KeyEvent.VK_LEFT){
				int x =(int) idle.getX();int y=(int) idle.getY();
				remove(running);
				remove(attack);
				remove(idle);
				remove(runleft);
				remove(attackleft);
				remove(jumpright);
				add(idleleft,x,y);
				right=false;
			}
			if(code==KeyEvent.VK_UP) {
				idle();
				jump=true;
			}
			if (code==KeyEvent.VK_DOWN) {
				defense=false;
			}
		}
	}
	public void map1() {
		youLose =new GImage("youlose.png");
		youLose.setSize(1440,480);
		background =new GImage("forest background.png");
		background.setSize(1440,480);
		add(background);
		running =new GImage("running.gif");
		running.setSize(60,60);
		death =new GImage("death.gif");
		death.setSize(60,60);
		attack =new GImage("attack.gif");
		attack.setSize(60,60);
		idle=new GImage("idle.gif");
		idle.setSize(60,60);
		idleleft=new GImage("miridle.gif");
		idleleft.setSize(60,60);
		runleft=new GImage("runleft.gif");
		runleft.setSize(60,60);
		attackleft=new GImage("attackleft.gif");
		attackleft.setSize(60,60);
		jumpleft=new GImage("jumpleft.gif");
		jumpleft.setSize(60,60);
		jumpright=new GImage("jumpright.gif");
		jumpright.setSize(60,60);
		Greytable=new GImage("grey_button03.png");
		Heart1_1=new GImage("0.png");
		Heart1_2=new GImage("0.png");
		Heart1_3=new GImage("0.png");
		Heart2=new GImage("1.png");
		Heart3=new GImage("2.png");
		add(Greytable,10,10);
		add(Heart1_1,25,15);
		add(Heart1_2,85,15);
		add(Heart1_3,145,15);
		add(idle,75,getHeight()-idle.getHeight());
	}
	public void idle() {
		int x =(int) idle.getX();int y=(int) idle.getY();
		remove(running);
		remove(attack);
		remove(jumpright);
		remove(idleleft);
		remove(runleft);
		remove(attackleft);
		remove(jumpright);
		add(idle,x,y);
		right=true;
	}
	public void enemy1(){
		enemywalkr=new GImage("enemy1walkright.gif");
		enemywalkr.setSize(150,150);
		enemywalkl=new GImage("enemy1walkleft.gif");
		enemywalkl.setSize(150,150);
		EnemyDeath=new GImage("enemydeath.gif");
		EnemyDeath.setSize(150, 150);
		enemyattackl=new GImage("enemyattackleft.gif");
		enemyattackl.setSize(150, 150);
		enemyattackr=new GImage("enemyattackright.gif");
		enemyattackr.setSize(150, 150);


		add(enemywalkl,getWidth()-150,getHeight()-150);

		while(enemyHealth>0) {
			//take_coordinate();
			int idlex=(int) idle.getX();int idley=(int) idle.getY();
			int idleleftx=(int) idleleft.getX();int idlelefty=(int) idleleft.getY();
			int enemywalkx=(int) enemywalkl.getX();int enemywalky=(int) enemywalkl.getY();
			int x =(int) enemywalkl.getX();int y=(int) enemywalkl.getY();
			//GObject hittedObject1= getElementAt(enemywalkl.getX()-20,getHeight()-5);
			if(idlex-x<0) {
				remove(enemywalkr);
				remove(enemyattackl);
				remove(enemyattackr);
				add(enemywalkl,x,y);
				if(enemywalkx-idlex>-50&&enemywalkx-idlex<40) {
					remove(enemywalkl);
					add(enemyattackl,x,y);
					if(enemywalkx-idlex>-50&&enemywalkx-idlex<40&&!defense&&adventurerHealth>0) {
						adventurerHealth--;
						pause(1000);
						println(adventurerHealth);

						//healthbar();

						switch (adventurerHealth){
						case 5:
							Heart1_1.setImage("0.png");
							Heart1_2.setImage("0.png");
							Heart1_3.setImage("1.png");
							break;
						case 4:
							Heart1_1.setImage("0.png");
							Heart1_2.setImage("0.png");
							Heart1_3.setImage("2.png");
							break;
						case 3:
							Heart1_1.setImage("0.png");
							Heart1_2.setImage("1.png");
							Heart1_3.setImage("2.png");
							break;
						case 2:
							Heart1_1.setImage("0.png");
							Heart1_2.setImage("2.png");
							Heart1_3.setImage("2.png");
							break;
						case 1:
							Heart1_1.setImage("1.png");
							Heart1_2.setImage("2.png");
							Heart1_3.setImage("2.png");
							break;
						case 0:

							Heart1_1.setImage("2.png");
							Heart1_2.setImage("2.png");
							Heart1_3.setImage("2.png");
							remove(running);
							remove(attack);
							remove(idle);
							remove(idleleft);
							remove(runleft);
							remove(attackleft);
							add(death,idlex,idley);
							pause(3000);
							add(youLose);
							remove(enemyattackl);


							break;
						}
						if(adventurerHealth==0) {
							break;
						}


					}




				}
				else {
					remove(enemyattackl);
					add(enemywalkl,x,y);
					enemywalkl.move(-1, 0);
					pause(50);


				}
				if(enemyHealth<=0) {
					remove(enemywalkr);
					remove(enemywalkl);
					remove(enemyattackl);
					remove(enemyattackr);

					add(EnemyDeath,enemywalkx,enemywalky);
				}
			}
			if(idlex-x>0) {

				remove(enemywalkl);
				remove(enemyattackl);
				add(enemywalkr,x,y);
				if(idlex-enemywalkx>-50&&idlex-enemywalkx<40) {
					remove(enemywalkr);
					add(enemyattackr,x,y);
					if(idlex-enemywalkx>-150&&idlex-enemywalkx<140&&!defense&&adventurerHealth>0) {
						adventurerHealth--;
						pause(1000);
						println(adventurerHealth);

						//healthbar();

						switch (adventurerHealth){
						case 5:
							Heart1_1.setImage("0.png");
							Heart1_2.setImage("0.png");
							Heart1_3.setImage("1.png");
							break;
						case 4:
							Heart1_1.setImage("0.png");
							Heart1_2.setImage("0.png");
							Heart1_3.setImage("2.png");
							break;
						case 3:
							Heart1_1.setImage("0.png");
							Heart1_2.setImage("1.png");
							Heart1_3.setImage("2.png");
							break;
						case 2:
							Heart1_1.setImage("0.png");
							Heart1_2.setImage("2.png");
							Heart1_3.setImage("2.png");
							break;
						case 1:
							Heart1_1.setImage("1.png");
							Heart1_2.setImage("2.png");
							Heart1_3.setImage("2.png");
							break;
						case 0:

							Heart1_1.setImage("2.png");
							Heart1_2.setImage("2.png");
							Heart1_3.setImage("2.png");
							remove(running);
							remove(attack);
							remove(idle);
							remove(idleleft);
							remove(runleft);
							remove(attackleft);
							add(death,idlex,idley);
							pause(3000);
							add(youLose);
							remove(enemyattackr);


							break;
						}
						if(adventurerHealth==0) {
							break;
						}


					}


				}


				else if(enemywalkx-idlex>-50&&enemywalkx-idlex<40) {

				}
				else {
					remove(enemyattackr);
					add(enemywalkr,x,y);
					enemywalkl.move(1, 0);
					pause(50);


				}
				if(enemyHealth<=0) {
					remove(enemywalkr);
					remove(enemywalkl);
					remove(enemyattackl);
					remove(enemyattackr);

					add(EnemyDeath,enemywalkx,enemywalky);
				}



			}

			if(enemyHealth<=0) {
				break;
			}



		}








	}
	public void healthbar() {

		switch (adventurerHealth){
		case 5:
			Heart1_1.setImage("0.png");
			Heart1_2.setImage("0.png");
			Heart1_3.setImage("1.png");
			break;
		case 4:
			Heart1_1.setImage("0.png");
			Heart1_2.setImage("0.png");
			Heart1_3.setImage("2.png");
			break;
		case 3:
			Heart1_1.setImage("0.png");
			Heart1_2.setImage("1.png");
			Heart1_3.setImage("2.png");
			break;
		case 2:
			Heart1_1.setImage("0.png");
			Heart1_2.setImage("2.png");
			Heart1_3.setImage("2.png");
			break;
		case 1:
			Heart1_1.setImage("1.png");
			Heart1_2.setImage("2.png");
			Heart1_3.setImage("2.png");
			break;
		case 0:

			Heart1_1.setImage("2.png");
			Heart1_2.setImage("2.png");
			Heart1_3.setImage("2.png");
			remove(running);
			remove(attack);
			remove(idle);
			remove(idleleft);
			remove(runleft);
			remove(attackleft);

			add(death,idlex,idley);
			pause(3000);
			add(youLose);
			remove(enemyattackl);
			break;
		}



	}


	public void music(String Filepath){
		InputStream music;
		try {
			music=new FileInputStream(new File(Filepath));
			audios =new AudioStream(music);
			AudioPlayer.player.start(audios);
		}
		catch(Exception e){

		}
	}
	public void song() {
		
			
			if(adventurerHealth>0&&enemyHealth!=0) {
				
				music("music.wav");	
				
			}
			AudioPlayer.player.stop(audios);
			if(enemyHealth<=0) {
				
				music("dejavu.wav");
			}
		
	}
}
