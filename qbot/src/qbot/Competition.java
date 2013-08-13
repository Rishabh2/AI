package qbot;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;


public class Competition extends JFrame {

	private Arena arena;
	private ArenaView view;
	
	public static void main(String[] args) {
		new Competition(new Arena(new BasicBot(), new BasicBot()));
	}
	
	public Competition(Arena arena) {
		super("QBot Competition");
		this.arena = arena;
		view = new ArenaView(arena);
		setSize(view.getSize());
		add(view);
		setVisible(true);
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(new UpdateTask(), 500, 50);
	}
	
	class UpdateTask extends TimerTask {

		@Override
		public void run() {
			arena.update();
			repaint();
		}
	}
}
