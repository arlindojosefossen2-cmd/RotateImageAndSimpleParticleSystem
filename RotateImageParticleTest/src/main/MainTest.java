package main;

import br.com.ajf.game.model.Game;
import br.com.ajf.game.scene.Scene;
import br.com.ajf.game.thread.IGameThreadManager;

public final class MainTest
{

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 596;

	public static void main(String[] args)
	{
		Game game = new Game("RotateImageAndParticles-test", WIDTH, HEIGHT, IGameThreadManager.GAME_THREAD_TIMER_TASK);
		
		Scene rotateImageScene = new RotateImageScene();
		rotateImageScene.start();
		
		game.addScene(rotateImageScene);
		
		game.init(IGameThreadManager.GAME_THREAD_TIMER_TASK);
	}
}