package com.balitechy.spacewar.main.Bullets;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BulletController {
	private LinkedList<GameBullet> bullets = new LinkedList<>();

	public void tick() {
		Iterator<GameBullet> it = bullets.iterator();
		while (it.hasNext()) {
			GameBullet bullet = it.next();
			if (bullet.getY() < 0) {
				it.remove();
			} else {
				bullet.tick();
			}
		}
	}

	public void render(Graphics g) {
		for (GameBullet bullet : bullets) {
			bullet.render(g);
		}
	}

	public void removeBullets(ArrayList<GameBullet> bulletsToRemove) {
		bullets.removeAll(bulletsToRemove);
	}
	// Devuelve una copia inmodificable
	public List<GameBullet> getBulletsCopy() {
		return new ArrayList<>(bullets);
	}

	public void addBullet(GameBullet bullet) {
		bullets.add(bullet);
	}

}
