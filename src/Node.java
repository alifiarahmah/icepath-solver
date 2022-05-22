enum Move {
	LEFT, RIGHT, UP, DOWN
}

public class Node {
	public Board board;
	public int cost;
	public int depth;
	public Move moveFromParent;
	public Node parent;

	public Node(Board board) {
		this.board = board;
		this.cost = 0;
		this.depth = 0;
		this.moveFromParent = null;
		this.parent = null;
	}

	public Node(Board board, Move moveFromParent, Node parent) {
		this.board = board;
		this.depth = parent.depth + 1;
		this.moveFromParent = moveFromParent;
		this.parent = parent;
		this.cost = parent.depth + board.distanceToEnd();
	}
}
