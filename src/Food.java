import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class Food {
	private static int scl = Snake.scl;
	public static int x = ThreadLocalRandom.current().nextInt(2, (Game.width / scl));
	public static int y = ThreadLocalRandom.current().nextInt(2, (Game.height / scl));
	private BufferedImage apple;
	
	public Food() {
		try {
			apple = ImageIO.read(getClass().getResourceAsStream("/graphics/apple.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	@SuppressWarnings("static-access")
	public void update() {
		this.x = ThreadLocalRandom.current().nextInt(1, (Game.width / this.scl));
		this.y = ThreadLocalRandom.current().nextInt(1, (Game.height / this.scl));
	}
	@SuppressWarnings("static-access")
	public void show(Graphics g) {
		g.drawImage(apple, (this.x * Game.s.scl),	(this.y * Game.s.scl), 25, 25, null);

	}

}
