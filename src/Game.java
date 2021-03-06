
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game implements Runnable {
	static Display display;
	public static int width;
	public static int height;
	static int fps;
	static int initfps;
	public static String title;
	static String highscore = "0";

	public static boolean running = false;
	private static Thread thread;

	private static BufferStrategy bs;
	static Graphics gs;
	static Graphics gf;
	public static Snake s;
	public static Food f;

	private File background;
	private static Clip clip;

	@SuppressWarnings("static-access")
	public Game(String title, int width, int height, int fps) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.fps = fps;
		this.initfps = fps;
		this.s = new Snake(6, 3, 1, 0);
		this.f = new Food();
		this.background = new File("src/sounds/background.wav");
	}

	private void init() {
		display = new Display(title, width, height, s);
		highscore = Game.GetHighScore();
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(background));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void tick() {
		s.update();
		if (s.eat()) {
			f.update();
		}
	}

	private static void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		gs = bs.getDrawGraphics();
		gs.clearRect(0, 0, width, height);
		gf = bs.getDrawGraphics();
		gf.clearRect(0, 0, width, height);
		// Draw Here!

		s.show(gs);
		f.show(gf);

		// End Drawing!!
		bs.show();
		gf.dispose();
		gs.dispose();
		Display.counter
				.setText("<html><pre>Score: " + (s.len - s.initlen) + "\t Highscore: " + highscore + "</pre></html>");

	}

	public void run() {
		init();

		while (running) {
			long startTime = System.nanoTime();

			tick();
			render();

			long URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			long waitTime = 1000 / fps - URDTimeMillis;

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
			}
		}

		stop();
	}

	public synchronized void start() {

		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public synchronized static void stop() {
		clip.stop();
		if (!running)
			return;

		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String GetHighScore() {
		FileReader readFile = null;
		BufferedReader reader = null;

		if (initfps == FrontScreen.fpseasy) {
			try {
				readFile = new FileReader("highscoreEasy.dat");
				reader = new BufferedReader(readFile);
				try {
					return reader.readLine();
				} catch (Exception e) {
					return "0";
				}
			} catch (FileNotFoundException e) {
				return "0";
			} finally {
				try {
					reader.close();
				} catch (Exception e) {
					return "0";
				}
			}
		} else if (initfps == FrontScreen.fpsmedium) {
			try {
				readFile = new FileReader("highscoreMedium.dat");
				reader = new BufferedReader(readFile);
				try {
					return reader.readLine();
				} catch (Exception e) {
					return "0";
				}
			} catch (FileNotFoundException e) {
				return "0";
			} finally {
				try {
					reader.close();
				} catch (Exception e) {
					return "0";
				}
			}
		} else if (initfps == FrontScreen.fpshard) {
			try {
				readFile = new FileReader("highscoreHard.dat");
				reader = new BufferedReader(readFile);
				try {
					return reader.readLine();
				} catch (Exception e) {
					return "0";
				}
			} catch (FileNotFoundException e) {
				return "0";
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (Exception e) {
					return "0";
				}
			}
		} else {
			try {
				readFile = new FileReader("highscoreArcade.dat");
				reader = new BufferedReader(readFile);
				try {
					return reader.readLine();
				} catch (Exception e) {
					return "0";
				}
			} catch (FileNotFoundException e) {
				return "0";
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (Exception e) {
					return "0";
				}
			}
		}

	}
}
