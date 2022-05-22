import java.util.*;

class NodeComparator implements Comparator<Node> {
	@Override
	public int compare(Node o1, Node o2) {
		return Integer.compare(o1.cost, o2.cost);
	}
}

public class Solver {

	public static void solve(Board initialBoard) {
		ArrayList<Node> solution = getSolution(initialBoard);
		printSolution(solution);
	}

	public static ArrayList<Node> getSolution(Board initialBoard) {
		PriorityQueue<Node> aliveNode = new PriorityQueue<>(new NodeComparator());
		ArrayList<Node> solution = new ArrayList<>();
		Node currentNode;

		currentNode = new Node(initialBoard);

		while (!currentNode.board.isAtEnd()) {
			// ambil node yang udah diexpand, masukin ke aliveNode
			if (currentNode.board.canMoveUp()) {
				aliveNode.add(new Node(currentNode.board.movedUp(), Move.UP, currentNode));
			}
			if (currentNode.board.canMoveDown()) {
				aliveNode.add(new Node(currentNode.board.movedDown(), Move.DOWN, currentNode));
			}
			if (currentNode.board.canMoveLeft()) {
				aliveNode.add(new Node(currentNode.board.movedLeft(), Move.LEFT, currentNode));
			}
			if (currentNode.board.canMoveRight()) {
				aliveNode.add(new Node(currentNode.board.movedRight(), Move.RIGHT, currentNode));
			}
			currentNode = aliveNode.poll();
		}

		while (currentNode.parent != null) {
			solution.add(0, currentNode);
			currentNode = currentNode.parent;
		}
		solution.add(0, currentNode);

		return solution;
	}

	public static void printSolution(ArrayList<Node> solution) {
		System.out.println("Initial board:");
		solution.get(0).board.printBoard();
		System.out.println();
		// for (int i = 1; i < solution.size(); i++){
		// System.out.println(solution.get(i).moveFromParent);
		// solution.get(i).board.printBoard();
		// }
		System.out.println("\nMoves:");
		for (int i = 1; i < solution.size(); i++) {
			System.out.print(solution.get(i).moveFromParent);
			if (i != solution.size() - 1) {
				System.out.print(" -> ");
			}
		}
	}
}
