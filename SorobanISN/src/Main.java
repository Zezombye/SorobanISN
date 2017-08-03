import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class Main {
	
	public static Soroban soroban;
	public static Window window;
	public static Menu menu;
	
	
	public static long chronoTime = 0;
	public static int nbCalculs = 0;
	public static Thread chronoThread;
	
	public static void main(String[] args) {
		
		
		
		window = new Window();
		//window.setContentPane(new CalculPanel());
		//window.setContentPane(new Tutoriel("tuto_add"));
		window.update();
	}
	
}




