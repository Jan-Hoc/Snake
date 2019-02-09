import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FrontScreen {
	private JFrame frame;

	private String title = "Choose Difficulty!";
	private int width = 900;
	private int height = 310;
        private int widthscreen;
        private int heightscreen;
        
	public FrontScreen(){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            widthscreen = (int) (screenSize.getWidth() / Snake.scl);
            heightscreen = (int) (screenSize.getHeight() / Snake.scl) - 5;
		createFrontScreen();
	}
	private void createFrontScreen(){
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(((widthscreen * Snake.scl - width) / 2 ), ((heightscreen * Snake.scl - height) / 2));
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		frame.getContentPane().setBackground(new Color(169, 221, 169));
		
		JButton d1 = new JButton("<html>Click here to start!<br/>LEVEL: EASY!</html>");
		d1.setFont(new Font("Courier New", Font.ITALIC, 20));
		d1.setBackground(new Color(80, 180, 80));
		d1.setPreferredSize(new Dimension(280, 260));

		d1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Game game = new Game("Snake", widthscreen * Snake.scl, heightscreen *Snake.scl, 12);
				game.start();

			}
		});
		
		JButton d2 = new JButton("<html>Click here to start!<br/>LEVEL: MEDIUM!</html>");
		d2.setFont(new Font("Courier New", Font.ITALIC, 20));
		d2.setBackground(new Color(80, 180, 80));
		d2.setPreferredSize(new Dimension(280, 260));

		d2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Game game = new Game("Snake", widthscreen * Snake.scl, heightscreen * Snake.scl, 20);
				game.start();

			}
		});
		
		JButton d3 = new JButton("<html>Click here to start!<br/>LEVEL: HARD!</html>");
		d3.setFont(new Font("Courier New", Font.ITALIC, 20));
		d3.setBackground(new Color(80, 180, 80));
		d3.setPreferredSize(new Dimension(280, 260));

		d3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Game game = new Game("Snake", widthscreen * Snake.scl, heightscreen * Snake.scl, 30);
				game.start();

			}
		});
	frame.add(d1);
	frame.add(d2);
	frame.add(d3);
	
	frame.pack();
	}
}
