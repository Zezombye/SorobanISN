import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Window extends JFrame {
		
	public Window() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		this.setTitle("Projet Soroban");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 800);
		this.setMinimumSize(new Dimension(1100, 725));
		this.setLocationRelativeTo(null);
		this.setContentPane(new Menu());
		
		
		/*new Thread(new Runnable() {
			public void run() {
				while (true) {
					window.setSize(window.getWidth()-1, window.getHeight());
					window.setSize(window.getWidth()+1, window.getHeight());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();*/
	}
	/**
	 * Because window.repaint() doesn't work
	 */
	public void update() {
		this.paintComponents(this.getGraphics());
		//window.setSize(window.getWidth()-1, window.getHeight());
		//window.setSize(window.getWidth()+1, window.getHeight());
	}
}
