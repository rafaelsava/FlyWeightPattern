package com.balitechy.spacewar.main;

import com.balitechy.spacewar.main.Bullets.BulletController;
import com.balitechy.spacewar.main.Bullets.GameBullet;
import com.balitechy.spacewar.main.Enemies.EnemyController;
import com.balitechy.spacewar.main.Enemies.GameEnemy;
import com.balitechy.spacewar.main.utils.BackgroundRenderer;
import com.balitechy.spacewar.main.utils.InputHandler;
import com.balitechy.spacewar.main.utils.SpritesImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Space War 2D";
	private static final int ENTER_KEY = KeyEvent.VK_ENTER;
	private boolean restartRequested = false;

	private boolean running = false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	private EnemyController enemies; // Añade esto

	private SpritesImageLoader sprites;
	
	//Game components
	private Player player;
	private BulletController bullets;
	private BackgroundRenderer backgRenderer;
	private Explosion explosion;
	private boolean gameOver = false;
	
	public void init(){
		requestFocus();
		
		
		sprites = new SpritesImageLoader("/sprites.png");
		try {			
			sprites.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Add keyboard listener
		addKeyListener(new InputHandler(this));
		
		// Initialize game components.
		
		
		// Set player position at the bottom center.
		player = new Player((WIDTH * SCALE - Player.WIDTH) / 2, HEIGHT * SCALE - 50 , this);
		bullets = new BulletController();
		backgRenderer=new BackgroundRenderer();
		enemies = new EnemyController(this); // Añade esto
		explosion = new Explosion(this);


	}

	public SpritesImageLoader getSprites(){
		return sprites;
	}
	
	public BulletController getBullets(){
		return bullets;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if(gameOver && key == ENTER_KEY) {
			restartRequested = true;
			return;
		}

		if(!gameOver) { // Solo procesar movimientos si el juego está activo
			switch(key){
				case KeyEvent.VK_RIGHT:
					player.setVelX(5);
					break;

				case KeyEvent.VK_LEFT:
					player.setVelX(-5);
					break;

				case KeyEvent.VK_UP:
					player.setVelY(-5);
					break;

				case KeyEvent.VK_DOWN:
					player.setVelY(5);
					break;

				case KeyEvent.VK_SPACE:
					player.shoot();
					break;
			}
		}
		

	}


	private void resetGame() {
		// Reiniciar componentes del juego
		player = new Player((WIDTH * SCALE - Player.WIDTH) / 2, HEIGHT * SCALE - 50, this);
		bullets = new BulletController();
		enemies = new EnemyController(this);
		explosion = new Explosion(this);
		gameOver = false;
		restartRequested = false;

		// Reiniciar controles
		player.setVelX(0);
		player.setVelY(0);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key){
			case KeyEvent.VK_RIGHT:
				player.setVelX(0);
			break;
			
			case KeyEvent.VK_LEFT:
				player.setVelX(0);
			break;
			
			case KeyEvent.VK_UP:
				player.setVelY(0);
			break;
			
			case KeyEvent.VK_DOWN:
				player.setVelY(0);
			break;
			
		}
	}
	
	private synchronized void start(){
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		if(!running) return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	/*
	 * Game thread runner. 
	 */
	@Override
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		final double numOfTicks = 60.0;
		double ns = 1000000000 / numOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + "ticks, fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	/*
	 * Run the ticks of all game components.
	 */
	public void tick() {
		if(gameOver && restartRequested) {
			resetGame();
			return;
		}
		if(!gameOver) {
			player.tick();
			bullets.tick();
			enemies.tick();
			checkCollisions();
			checkPlayerCollision();
		}
		explosion.tick();
	}

	/*
	 * Render overall game components.
	 */
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////
		
		try {
			backgRenderer.render(g, this);
			player.render(g);
			bullets.render(g);
			enemies.render(g);


		} catch (IOException e) {
			e.printStackTrace();
		}

		if(gameOver) {
			explosion.render(g);

			// Mensaje de Game Over
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 48));
			g.drawString("GAME OVER", getWidth()/2 - 120, getHeight()/2 - 30);

			// Instrucción de reinicio
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 24));
			g.drawString("Press ENTER to restart", getWidth()/2 - 100, getHeight()/2 + 30);
		}
		
		
		////////////////////////////////
		g.dispose();
		bs.show();
	}

	private void checkCollisions() {
		ArrayList<GameBullet> bulletsCopy = new ArrayList<>(bullets.getBulletsCopy());
		ArrayList<GameEnemy> enemiesCopy = new ArrayList<>(enemies.getEnemiesCopy());

		ArrayList<GameBullet> bulletsToRemove = new ArrayList<>();
		ArrayList<GameEnemy> enemiesToRemove = new ArrayList<>();

		for (GameBullet bullet : bulletsCopy) {
			Rectangle bulletBounds = bullet.getBounds();
			for (GameEnemy enemy : enemiesCopy) {
				if (bulletBounds.intersects(enemy.getBounds())) {
					bulletsToRemove.add(bullet);
					enemiesToRemove.add(enemy);
				}
			}
		}

		// Eliminar balas y enemigos colisionados
		bullets.removeBullets(bulletsToRemove);
		enemies.removeEnemies(enemiesToRemove); // <- Nuevo método en EnemyController
	}

	private void checkPlayerCollision() {
		Rectangle playerBounds = new Rectangle(
				(int) player.getX(),
				(int) player.getY(),
				Player.WIDTH,
				Player.HEIGHT
		);

		// Iterar sobre todos los enemigos (sin importar el tipo)
		for (GameEnemy enemy : enemies.getEnemiesCopy()) {
			if (playerBounds.intersects(enemy.getBounds())) {
				triggerGameOver();
				return;
			}
		}
	}

	private void triggerGameOver() {
		gameOver = true;
		explosion.activate(player.getX(), player.getY());
		player.setVelX(0);
		player.setVelY(0);
	}


	public static void main(String args[]){
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	public EnemyController getEnemies() {
		return enemies;
	}
	
}
