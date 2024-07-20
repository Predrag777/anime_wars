import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;
	private JTextField txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private int count;
	private Fighter fighters[];
	private Fighter fighter1;
	private Fighter fighter2;
	
	private boolean log;
	public Menu() {
		Test t=new Test();
		t.test();
		this.count=0;
		this.fighters=t.getFighters();
		this.log=false;
		initialize();
	}

	public void setFighter1(Fighter fighter1) {
		this.fighter1 = fighter1;
	}

	public void setFighter2(Fighter fighter2) {
		this.fighter2 = fighter2;
	}

	public Fighter getFighter1() {
		return fighter1;
	}

	public Fighter getFighter2() {
		return fighter2;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 708, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel imgLbl = new JLabel("");
		imgLbl.setBounds(39, 22, 273, 275);
		frame.getContentPane().add(imgLbl);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(39, 322, 70, 15);
		frame.getContentPane().add(lblName);
		
		txt = new JTextField();
		txt.setBounds(98, 320, 134, 19);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		
		JLabel lblImg2 = new JLabel("");
		lblImg2.setBounds(324, 22, 273, 275);
		frame.getContentPane().add(lblImg2);
		
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(count>=fighters.length || fighters[count]==null)
					count=0;
				fighter1=fighters[count];
				System.out.println(count);
				txt.setText(fighters[count].getName());
				String url="images/";
				ImageIcon img=new ImageIcon(url+fighters[count].getImg());
				imgLbl.setIcon(img);
				count++;
			}
		});
		btnNext.setBounds(39, 403, 117, 25);
		frame.getContentPane().add(btnNext);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	setFighter1(fighters[count]);
				Random rand=new Random();
				int c=0;
				
				c=rand.nextInt(fighters.length);
				setFighter2(fighters[c]);
				String url="images/";
				ImageIcon img=new ImageIcon(url+fighters[c].getImg());
				lblImg2.setIcon(img);
				fighter2=fighters[2];
		//		System.out.println(fighter1.getName()+" VS "+fighter2.getName());
				File file=new File("Borbe.txt");
				try {
					PrintStream ps=new PrintStream(file);
					ps.println(fighter1.getName()+" VS "+fighter2.getName());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Crtaj a=new Crtaj(fighter1, fighter2);
				JFrame p=new JFrame();
				p.setSize(1000,1000);
				p.setVisible(true);
				p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        p.add(a);
			}
		});
		btnSubmit.setBounds(591, 403, 95, 25);
		frame.getContentPane().add(btnSubmit);
		
		
	}
}
