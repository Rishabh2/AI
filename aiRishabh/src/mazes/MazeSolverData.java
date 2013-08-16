package mazes;

public class MazeSolverData {
	public int maxNodes;
	public int maxDepth;
	public int maxFrontierSize;
	public int pathCost;

	public MazeSolverData() {
		maxNodes = maxDepth = maxFrontierSize = pathCost = 0;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Max Depth: " + maxDepth + "\n");
		str.append("Max Nodes: " + maxNodes + "\n");
		str.append("Max Frontier Size: " + maxFrontierSize + "\n");
		str.append("Path cost: " + pathCost + "\n");
		return str.toString();
	}
}