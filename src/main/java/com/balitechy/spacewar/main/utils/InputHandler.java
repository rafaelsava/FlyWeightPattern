package com.balitechy.spacewar.main.utils;

import com.balitechy.spacewar.main.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter{
	
	private Game game;
	
	public InputHandler(Game game){
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}

}
