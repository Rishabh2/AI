package qbot;

public abstract class DiscreteObject {
	private Vector position;
	private Vector newPosition;
	private DiscreteObject[] children;
	
	public DiscreteObject(Vector position) {
		this.position = position;
		children = new DiscreteObject[0];
	}
	
	public void update(Snapshot snapshot) {
		newPosition = position;
		if (children.length > 0)
			for (DiscreteObject child : children)
				child.update(snapshot);
		newPosition = newPosition.add(calculate(snapshot));
		position = newPosition;
	}
	
	public abstract Vector calculate(Snapshot snapshot);
	
	public void addChildren(DiscreteObject[] objects) {
		DiscreteObject[] newChildren = new DiscreteObject[children.length + objects.length];
		for (int i = 0 ; i < children.length ; i++)
			newChildren[i] = children[i];
		for (int i = children.length ; i < children.length + objects.length ; i++)
			newChildren[i] = objects[i - children.length];
		children = newChildren;
	}
	
	public Vector getPosition() {
		return position;
	}
}
