public class Main {
    public static void main(String[] args) {
        Board board1 = new Board("test/1.txt");
        Solver.solve(board1);

        Board board2 = new Board("test/2.txt");
        Solver.solve(board2);
    }
}
