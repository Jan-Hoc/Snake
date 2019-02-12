import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Snake implements KeyListener {
	private int x, y;
	int xspeed;
	int yspeed;
	public static int scl = 25;
	public int initlen = 3;
	public int len = initlen;
	private int[][] positions = new int[initlen][2];
	private int[][] oldpositions = new int[initlen][2];
	private BufferedImage headup;
	private BufferedImage headdown;
	private BufferedImage headleft;
	private BufferedImage headright;
	private BufferedImage bodyup;
	private BufferedImage bodydown;
	private BufferedImage bodyleft;
	private BufferedImage bodyright;
	private BufferedImage tailup;
	private BufferedImage taildown;
	private BufferedImage tailleft;
	private BufferedImage tailright;

	public Snake(int x, int y, int xspeed, int yspeed) {
		this.x = x;
		this.y = y;
		this.xspeed = xspeed;
		this.yspeed = yspeed;

		for (int i = 0; i < positions.length; i++) {
			positions[i][0] = this.x - i;
			positions[i][1] = this.y;
		}
		try {
			headup = ImageIO.read(getClass().getResourceAsStream("/graphics/headup.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bodyup = ImageIO.read(getClass().getResourceAsStream("/graphics/bodyup.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			tailup = ImageIO.read(getClass().getResourceAsStream("/graphics/tailup.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			headdown = ImageIO.read(getClass().getResourceAsStream("/graphics/headdown.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bodydown = ImageIO.read(getClass().getResourceAsStream("/graphics/bodydown.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			taildown = ImageIO.read(getClass().getResourceAsStream("/graphics/taildown.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			headleft = ImageIO.read(getClass().getResourceAsStream("/graphics/headleft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bodyleft = ImageIO.read(getClass().getResourceAsStream("/graphics/bodyleft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			tailleft = ImageIO.read(getClass().getResourceAsStream("/graphics/tailleft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			headright = ImageIO.read(getClass().getResourceAsStream("/graphics/headright.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bodyright = ImageIO.read(getClass().getResourceAsStream("/graphics/bodyright.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			tailright = ImageIO.read(getClass().getResourceAsStream("/graphics/tailright.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		int i = 1;
		while (i <= len) {
			if (i == len) {
				positions[0][0] = this.x + xspeed;
				positions[0][1] = this.y + yspeed;
				this.x = this.x + this.xspeed;
				this.y = this.y + this.yspeed;
			} else {
				positions[(len - i)][0] = oldpositions[((len - i) - 1)][0];
				positions[(len - i)][1] = oldpositions[((len - i) - 1)][1];
			}
			oldpositions = positions;
			i += 1;
		}
		if (constrain(positions[0][0] * scl, 0, Game.width - scl)) {
			Game.running = false;
			dead();
		}
		if (constrain(positions[0][1] * scl, 0, Game.height - scl)) {
			Game.running = false;
			dead();
		}
		if (eatself()) {
			Game.running = false;
			dead();
		}

	}

	public void show(Graphics g) {
		int i = 0;
		while (i < len) {
			if (i == 0) {
				if (this.xspeed == 1) {
					g.drawImage(headright, positions[i][0] * scl, positions[i][1] * scl, null);
				} else if (this.xspeed == -1) {
					g.drawImage(headleft, positions[i][0] * scl, positions[i][1] * scl, null);
				} else if (this.yspeed == 1) {
					g.drawImage(headdown, positions[i][0] * scl, positions[i][1] * scl, null);
				} else {
					g.drawImage(headup, positions[i][0] * scl, positions[i][1] * scl, null);
				}

			} else if (i == len - 1) {
				if ((this.positions[i][0] - this.positions[i - 1][0]) == -1) {
					g.drawImage(tailright, positions[i][0] * scl, positions[i][1] * scl, null);
				} else if ((this.positions[i][0] - this.positions[i - 1][0]) == 1) {
					g.drawImage(tailleft, positions[i][0] * scl, positions[i][1] * scl, null);
				} else if ((this.positions[i][1] - this.positions[i - 1][1]) == -1) {
					g.drawImage(taildown, positions[i][0] * scl, positions[i][1] * scl, null);
				} else {
					g.drawImage(tailup, positions[i][0] * scl, positions[i][1] * scl, null);
				}
			} else {
				if ((this.positions[i][0] - this.positions[i - 1][0]) == -1) {
					g.drawImage(bodyright, positions[i][0] * scl, positions[i][1] * scl, null);
				} else if ((this.positions[i][0] - this.positions[i - 1][0]) == 1) {
					g.drawImage(bodyleft, positions[i][0] * scl, positions[i][1] * scl, null);
				} else if ((this.positions[i][1] - this.positions[i - 1][1]) == -1) {
					g.drawImage(bodydown, positions[i][0] * scl, positions[i][1] * scl, null);
				} else {
					g.drawImage(bodyup, positions[i][0] * scl, positions[i][1] * scl, null);
				}
			}

			i += 1;
		}
	}

	public void dir(int x, int y) {
		this.xspeed = x;
		this.yspeed = y;
	}

	public boolean constrain(int initial, int min, int max) {
		if (initial < min) {
			this.xspeed = 0;
			this.yspeed = 0;

			return true;
		} else if (initial > max) {
			this.xspeed = 0;
			this.yspeed = 0;
			return true;
		} else {
			return false;
		}
	}

	public boolean eat() {
		if (positions[0][0] == Food.x && positions[0][1] == Food.y) {
			this.len += 1;
			this.positions = new int[len][2];
			for (int i = 0; i < oldpositions.length; i++) {
				this.positions[i][0] = this.oldpositions[i][0];
				this.positions[i][1] = this.oldpositions[i][1];
			}

			if (len > 3) {
				this.positions[len - 1][0] = this.positions[len - 2][0]
						+ (this.positions[len - 2][0] - this.positions[len - 3][0]);
				this.positions[len - 1][1] = this.positions[len - 2][1]
						+ (this.positions[len - 2][1] - this.positions[len - 3][1]);
			} else {
				this.positions[len - 1][0] = this.positions[len - 2][0] - this.xspeed;
				this.positions[len - 1][1] = this.positions[len - 2][1] - this.yspeed;
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean eatself() {
		for (int i = 1; i < positions.length; i++) {
			if (positions[0][0] == positions[i][0] && positions[0][1] == positions[i][1]) {
				return true;
			}
		}
		return false;
	}

	public void dead() {
		FileWriter writefile = null;
		BufferedWriter writer = null;
		if (len - initlen > Integer.parseInt(Game.highscore)) {
			Game.highscore = Integer.toString(len - initlen);
			if (Game.fps == 12) {
				File scorefile = new File("highscoreEasy.dat");
				if (!scorefile.exists()) {
					try {
						scorefile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					writefile = new FileWriter(scorefile);
					writer = new BufferedWriter(writefile);
					writer.write(Game.highscore);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}

			else if (Game.fps == 20) {
				File scorefile = new File("highscoreMedium.dat");
				if (!scorefile.exists()) {
					try {
						scorefile.createNewFile();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					writefile = new FileWriter(scorefile);
					writer = new BufferedWriter(writefile);
					writer.write(Game.highscore);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}

			else if (Game.fps == 30) {
				File scorefile = new File("highscoreHard.dat");
				if (!scorefile.exists()) {
					try {
						scorefile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					writefile = new FileWriter(scorefile);
					writer = new BufferedWriter(writefile);
					writer.write(Game.highscore);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}
		new DisplayDead("Deathscreen", 400, 300);
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 38 || keyCode == 87) {
			if (yspeed == 1) {
				;
			} else {
				dir(0, -1);
			}
		} else if (keyCode == 40 || keyCode == 83) {
			if (yspeed == -1) {
				;
			} else {
				dir(0, 1);
			}

		} else if (keyCode == 37 || keyCode == 65) {
			if (xspeed == 1) {
				;
			} else {
				dir(-1, 0);
			}

		} else if (keyCode == 39 || keyCode == 68) {
			if (xspeed == -1) {
				;
			} else {
				dir(1, 0);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
