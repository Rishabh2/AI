package qbot;

/**
 * A snapshot records information about a QBot 
 * @author keshav
 */
public class Snapshot {
	private Vector myPosition;
	private Vector opponentPosition;
	private Block[] blocks;
	
	public Snapshot(Vector myPosition, Vector opponentPosition, Block[] blocks) {
		this.myPosition = myPosition;
		this.opponentPosition = opponentPosition;
		this.blocks = blocks;
	}
	
	public Vector getMyPosition() {
		return myPosition;
	}
	
	public Vector getOpponentPosition() {
		return opponentPosition;
	}
	
	public Block[] getBlocks() {
		return blocks;
	}
}
