import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayDead implements KeyListener {
	static JFrame frame;

	private String title;
	private int width, height;
	private int screenheight;
	private int screenwidth;

	public DisplayDead(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenwidth = (int) (screenSize.getWidth() / Snake.scl);
		screenheight = (int) (screenSize.getHeight() / Snake.scl) - 4;

		createDisplayDead();
	}

	private void createDisplayDead() {

		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(((screenwidth * Snake.scl - width) / 2), ((screenheight * Snake.scl - height) / 2));
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		frame.getContentPane().setBackground(new Color(169, 221, 169));
		frame.addKeyListener(this);

		JLabel Label = new JLabel();
		Label.setText("<html>You died!<br/>Your Score was: " + (Game.s.len - Game.s.initlen) + "<br/>Your Highscore is: "
				+ Game.highscore + "</html>");
		Label.setFont(new Font("Courier New", Font.ITALIC, 20));
		Label.setBackground(new Color(169, 221, 169));
		Label.setHorizontalAlignment(JLabel.CENTER);
		Label.setVerticalAlignment(JLabel.CENTER);
		Label.setPreferredSize(new Dimension(width - 20, (height - 50) / 2));
		Label.setOpaque(true);

		JButton RestartButton = new JButton("<html>Click Here to restart!<br/>Or press any button!</html>");
		RestartButton.setFont(new Font("Courier New", Font.ITALIC, 20));
		RestartButton.setBackground(new Color(80, 180, 80));
		RestartButton.setPreferredSize(new Dimension(width - 20, (height - 50) / 2));

		RestartButton.addActionListener(new ActionListener() {


			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				Display.frame.setVisible(false);
				FrontScreen fs = new FrontScreen();

			}

		});

		frame.add(Label);
		frame.add(RestartButton);
		frame.getRootPane().setDefaultButton(RestartButton);
		frame.pack();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unused")
	public void keyPressed(KeyEvent e) {
		frame.setVisible(false);
		Display.frame.setVisible(false);
		FrontScreen fs = new FrontScreen();
	}

}
