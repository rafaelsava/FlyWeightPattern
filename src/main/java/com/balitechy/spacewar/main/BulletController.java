package com.balitechy.spacewar.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BulletController {
	private LinkedList<Bullet> bl = new LinkedList<>();



	public void tick() {
		Iterator<Bullet> it = bl.iterator();
		while (it.hasNext()) {
			Bullet bullet = it.next();
			if (bullet.getY() < 0) {
				it.remove(); // Elimina seguro usando el iterador
			} else {
				bullet.tick();
			}
		}
	}
	public synchronized List<Bullet> getBulletsCopy() {
		return new ArrayList<>(bl);
	}


	public void render(Graphics g){
		for(int i=0; i < bl.size(); i++){
			bl.get(i).render(g);
		}
	}
	
	public void addBullet(Bullet bullet){
		bl.add(bullet);
	}
	
	public void removeBullet(Bullet bullet){
		bl.remove(bullet);
	}

	public LinkedList<Bullet> getBullets() {
		return bl;
	}
}
