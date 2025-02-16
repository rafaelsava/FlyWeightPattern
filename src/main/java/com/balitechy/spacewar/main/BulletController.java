package com.balitechy.spacewar.main;

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

	public synchronized List<GameBullet> getBulletsCopy() {
		return new ArrayList<>(bullets);
	}

	public void render(Graphics g) {
		for (GameBullet bullet : bullets) {
			bullet.render(g);
		}
	}

	public void addBullet(GameBullet bullet) {
		bullets.add(bullet);
	}

	public void removeBullet(GameBullet bullet) {
		bullets.remove(bullet);
	}

	public LinkedList<GameBullet> getBullets() {
		return bullets;
	}
}
