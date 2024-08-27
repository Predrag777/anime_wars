import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

public class Menu {

    private JFrame frame;
    private JLabel txt;

    private int count;
    private Fighter[] fighters;
    private Fighter fighter1;
    private Fighter fighter2;

    private boolean log;

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

    public Menu() {
        Test t = new Test();
        t.test();
        this.count = 0;
        this.fighters = t.getFighters();
        this.log = false;
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

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 708, 473);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Kreiranje panela za pozadinu
        BackgroundPanel backgroundPanel = new BackgroundPanel("background/stage1.jpg"); // Proverite da li je putanja ispravna
        backgroundPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.getContentPane().add(backgroundPanel);
        backgroundPanel.setLayout(null);

        JLabel imgLbl = new JLabel("");
        imgLbl.setBounds(39, 22, 273, 275);
        backgroundPanel.add(imgLbl);

        JLabel lblName = new JLabel("Name: ");
        lblName.setBounds(39, 322, 70, 15);
        lblName.setForeground(Color.WHITE);
        backgroundPanel.add(lblName);

        txt = new JLabel();
        txt.setBounds(98, 320, 134, 19);
        txt.setForeground(Color.WHITE);
        backgroundPanel.add(txt);
        

        JLabel lblImg2 = new JLabel("");
        lblImg2.setBounds(324, 22, 273, 275);
        backgroundPanel.add(lblImg2);

        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (count >= fighters.length || fighters[count] == null)
                    count = 0;
                fighter1 = fighters[count];
                txt.setText(fighters[count].getName());
                String url = "images/";
                ImageIcon img = new ImageIcon(url + fighters[count].getImg());
                imgLbl.setIcon(img);
                count++;
            }
        });
        btnNext.setBounds(39, 403, 117, 25);
        backgroundPanel.add(btnNext);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Random rand = new Random();
                int c = 0;

                c = rand.nextInt(fighters.length);
                setFighter2(fighters[c]);
                String url = "images/";
                ImageIcon img = new ImageIcon(url + fighters[c].getImg());
                lblImg2.setIcon(img);
                fighter2 = fighters[2];
                File file = new File("Borbe.txt");
                try {
                    PrintStream ps = new PrintStream(file);
                    ps.println(fighter1.getName() + " VS " + fighter2.getName());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Crtaj a = new Crtaj(fighter1, fighter2);
                JFrame p = new JFrame();
                p.setSize(1000, 1000);
                p.setVisible(true);
                p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                p.add(a);
            }
        });
        btnSubmit.setBounds(591, 403, 95, 25);
        backgroundPanel.add(btnSubmit);
    }
    
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
                if (backgroundImage == null) {
                    System.err.println("Slika nije pronađena: " + imagePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
