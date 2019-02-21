import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display {

	public static JFrame frame;
	private Canvas canvas;
	static JLabel counter;

	private String title;
	public static int width, height;


	@SuppressWarnings("static-access")
	public Display(String title, int width, int height, Snake s) {
		this.title = title;
		this.width = width;
		this.height = height;


		createDisplay(s);
	}

	private void createDisplay(Snake s) {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(0, 0);
		frame.setVisible(true);
		frame.addKeyListener(s);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setBackground(new Color(169, 221, 169));
		
		counter = new JLabel();
		counter.setText("Score: " + (Game.s.len - 1));
		counter.setFont(new Font("Courier New", Font.ITALIC, 30));
	    counter.setHorizontalAlignment(JLabel.CENTER);
	    counter.setVerticalAlignment(JLabel.CENTER);
	    counter.setPreferredSize(new Dimension(100, 35));
	    counter.setBackground(new Color(130, 221, 130));
	    counter.setOpaque(true);
		
		frame.add(counter, BorderLayout.NORTH);
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();

	}

	public Canvas getCanvas() {
		return canvas;
	}
}
