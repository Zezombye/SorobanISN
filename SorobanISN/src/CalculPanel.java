import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CalculPanel extends JPanel {
	
	Image background;
	public ImageIcon retourImg = new ImageIcon(this.getClass().getResource("/return.png"));
	public ImageButton retour = new ImageButton(retourImg, null);
	public JLabel chrono = new JLabel();
	public NumberLabel consigne = new NumberLabel("");
	public Calcul calcul;
	boolean aPerdu = false;
	CalculFactory.TypeCalcul type;
	int lvl;
	
	public CalculPanel(CalculFactory.TypeCalcul type, int lvl) {
		
		background =  new ImageIcon(this.getClass().getResource("/wallpapers/bg_lvl.jpg")).getImage();
		
		this.setLayout(null);
		this.type = type;
		this.lvl = lvl;
		retour.setFocusable(false);
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!Main.chronoThread.isInterrupted()) {
					Main.chronoThread.interrupt();
				}
				Main.window.setContentPane(new CalculMenu());
				Main.window.update();
			}
		});
		
		chrono.setFont(new Font("Serif", Font.BOLD, 48));
		chrono.setForeground(Color.GREEN);
		//chrono.setText("00:59");            
		chrono.setPreferredSize(new Dimension(50, 50));
		chrono.setHorizontalAlignment(SwingConstants.CENTER);
		initChrono();
		calcul = new CalculFactory().getCalcul(type, lvl);
		consigne.setText(calcul.str);
		//consigne.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Main.soroban = new Soroban(9, false);

		this.add(retour);
		this.add(consigne);
		this.add(chrono);
		this.add(Main.soroban);
		Dimension size = Main.soroban.getPreferredSize();
		Main.soroban.setBounds(this.getWidth()/2-size.width/2, this.getHeight()/2-size.height/2, size.width, size.height);
		Main.soroban.afficherSoroban();
	}
	
	@Override protected void paintComponent(Graphics g) {
		try {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		this.setPreferredSize(this.getParent().getSize());
		if (Main.soroban.getValeur() == calcul.result) {
			System.out.println("Calcul correct");
			Main.soroban.init();
			calcul = new CalculFactory().getCalcul(type, lvl);
			consigne.setText(calcul.str);
			Main.chronoTime += 20*lvl;
			Main.nbCalculs++;
			Main.window.update();
		}
		//System.out.println(calcul);

		
		chrono.setText((Main.chronoTime/60 < 10 ? "0" : "") + Main.chronoTime/60 + ":" + (Main.chronoTime%60 < 10 ? "0" : "") +Main.chronoTime%60);
		if (Main.chronoTime < 30) {
			chrono.setForeground(Color.ORANGE);
		} else {
			chrono.setForeground(Color.GREEN);
		}
		if (Main.chronoTime < 10) {
			chrono.setForeground(Color.RED);
		}
		Dimension size = consigne.getPreferredSize();
		//System.out.println(size);
		consigne.setBounds(this.getWidth()/2-1000, this.getHeight()/4-Main.soroban.getHeight()/4-size.height/2, 2000, size.height);
		size = chrono.getPreferredSize();
		chrono.setBounds(this.getWidth()/2-500, this.getHeight()-(this.getHeight()/4-Main.soroban.getHeight()/4)-size.height/2, 1000, size.height);
		size = Main.soroban.getPreferredSize();
		Main.soroban.setBounds(this.getWidth()/2-size.width/2, this.getHeight()/2-size.height/2, size.width, size.height);

		size = retour.getPreferredSize();
		retour.setBounds(20, 20, size.width, size.height);
		if (Main.chronoTime <= 0 && !aPerdu) {
			perdu();
			return;
		}
		} catch (Exception e) {}
		
	}

	public void perdu() {
		aPerdu = true;
		new Thread(new Runnable() {
	        @Override
	        public void run() {
	            int option = JOptionPane.showConfirmDialog(Main.window, "Vous avez perdu après "+Main.nbCalculs+" calculs ! Recommencer ?", "Dommage...", JOptionPane.YES_NO_OPTION);
    			try {
					Main.chronoThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			Main.soroban.init();
	    		if (option == JOptionPane.NO_OPTION) {
	    			Main.window.setContentPane(new CalculMenu());
	    			Main.window.update();
	    		} else {
	    			calcul = new CalculFactory().getCalcul(type, lvl);
	    			consigne.setText(calcul.str);
	    			initChrono();
	    			Main.window.update();
	    		}
	        }
	    }).start();
		
	}
	
	public void initChrono() {
		Main.chronoTime = 600;
		Main.nbCalculs = 0;
		try {
			Main.chronoThread = new ChronoThread(this);
			Main.chronoThread.start();
		} catch (IllegalThreadStateException e) {}
	}
}

class ChronoThread extends Thread {
	
	CalculPanel cp;
	
	public ChronoThread(CalculPanel cp) {
		this.cp = cp;
	}
	public void run() {
		while (Main.chronoTime > 0) {
			Main.chronoTime--;
			cp.chrono.repaint();
			try {
				Thread.sleep(1000/(Main.nbCalculs+1));
			} catch (InterruptedException e) {
				try {
					join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
