import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tutoriel extends JPanel {
	
	public String[] tutoTypes = {"tuto_add", "tuto_sub", "tuto_mult", "tuto_div"};
	
	Image background = new ImageIcon(this.getClass().getResource("/wallpapers/bg_tuto.png")).getImage();
	public ImageIcon nextEnabled = new ImageIcon(this.getClass().getResource("/next.png"));
	public ImageIcon nextDisabled = new ImageIcon(this.getClass().getResource("/next_disabled.png"));
	public ImageIcon prevEnabled = new ImageIcon(this.getClass().getResource("/prev.png"));
	public ImageIcon prevDisabled = new ImageIcon(this.getClass().getResource("/prev_disabled.png"));
	public ImageIcon retourImg = new ImageIcon(Main.class.getResource("/return.png"));
	public ImageButton retour = new ImageButton(retourImg, null);
	//public JLabel texte = new JLabel();
	
	ImageButton prev = new ImageButton(prevEnabled, prevDisabled);
	ImageButton next = new ImageButton(nextEnabled, nextDisabled);
	JLabel img = new JLabel();
	ArrayList<ImageIcon> etapes = new ArrayList<ImageIcon>();
	JPanel center = new JPanel();
	int etapenb = 0;
	
	public Tutoriel(String type) {
		
		retour.setFocusable(false);
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.window.setContentPane(new CalculMenu());
				Main.window.update();
			}
		});
		
		getEtapes(type);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		retourImg = new ImageIcon(this.getClass().getResource("/return.png"));
		/*
		texte.setOpaque(false);
		texte.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
		texte.setForeground(Color.GREEN);
		texte.setPreferredSize(new Dimension(90, 110));
		texte.setHorizontalAlignment(SwingConstants.CENTER);
		texte.setText("Tutoriel pt 1");
		texte.setAlignmentX(Component.CENTER_ALIGNMENT);*/
		

		img.setIcon(etapes.get(etapenb));
		//img.setPreferredSize(new Dimension(etapes.get(0).getIconWidth(), etapes.get(0).getIconHeight()));
		
		center.setLayout(null);
		center.setOpaque(false);
		
		center.add(prev);
		center.add(img);
		center.add(next);
		center.add(retour);
		//center.setPreferredSize(new Dimension(this.getParent().getSize()));
		
		//center.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		//prev.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		//prev.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		//img.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		//img.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		//next.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		//next.setAlignmentY(JComponent.CENTER_ALIGNMENT);

		/*this.add(Box.createHorizontalGlue());
		this.add(prev);
		this.add(Box.createHorizontalGlue());
		this.add(img);
		this.add(Box.createHorizontalGlue());
		this.add(next);
		this.add(Box.createHorizontalGlue());*/
		/*center.add(prev);
		center.add(Box.createHorizontalGlue());
		center.add(img);
		center.add(Box.createHorizontalGlue());
		center.add(next);
		this.add(center);*/
		
		this.add(center);
		
		prev.setEnabled(false);
		prev.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				next.setEnabled(true);
				if (etapenb > 0) {
					etapenb--;
					img.setIcon(etapes.get(etapenb));
					//img.setPreferredSize(new Dimension(etapes.get(etapenb).getIconWidth(), etapes.get(etapenb).getIconHeight()));
				}
				if (etapenb == 0) {
					prev.setEnabled(false);
				}
			}
			
		});
		
		next.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				prev.setEnabled(true);
				if (etapenb+1 < etapes.size()) {
					etapenb++;
					img.setIcon(etapes.get(etapenb));
					//img.setPreferredSize(new Dimension(etapes.get(etapenb).getIconWidth(), etapes.get(etapenb).getIconHeight()));
				}
				if (etapenb+1 == etapes.size()) {
					((ImageButton)e.getSource()).setEnabled(false);
				}
			}
			
		});
				
	}
	
	public void getEtapes(String type) {
		try {
			for (int i = 1;; i++) {
				etapes.add(new ImageIcon(this.getClass().getResource("/"+type+"/"+i+".png")));
				//etapes.add(new ImageIcon(this.getClass().getResource("/tuto_add/1.png")));
			}
		} catch (NullPointerException e) {
			//e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		center.setPreferredSize(new Dimension(this.getParent().getSize()));
		Dimension size = prev.getPreferredSize();
		prev.setBounds(center.getWidth()/4-img.getWidth()/4-size.width/2, center.getHeight()/2-size.height/2, size.width, size.height);
		size = img.getPreferredSize();
		img.setBounds(center.getWidth()/2-size.width/2, center.getHeight()/2-size.height/2, size.width, size.height);
		size = next.getPreferredSize();
		next.setBounds(center.getWidth()-(center.getWidth()/4-img.getWidth()/4)-size.width/2, center.getHeight()/2-size.height/2, size.width, size.height);
		size = retour.getPreferredSize();
		retour.setBounds(20, 20, size.width, size.height);
	}
}
