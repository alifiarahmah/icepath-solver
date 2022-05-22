import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Board {
    public ArrayList<ArrayList<Character>> board;
    public int startRow;
    public int startCol;
    public int endRow;
    public int endCol;
    public int currentRow;
    public int currentCol;

    // Constructor

    public Board(String filename) {
        board = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File(filename));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                ArrayList<Character> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    row.add(line.charAt(i));
                    if (line.charAt(i) == 'S') {
                        startRow = board.size();
                        startCol = i;
                        currentRow = startRow;
                        currentCol = startCol;
                    } else if (line.charAt(i) == 'E') {
                        endRow = board.size();
                        endCol = i;
                    }
                }
                board.add(row);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public Board(Board otherBoard) {
        board = new ArrayList<>();
        for (int i = 0; i < otherBoard.board.size(); i++) {
            ArrayList<Character> row = new ArrayList<>(otherBoard.board.get(i));
            board.add(row);
        }
        startRow = otherBoard.startRow;
        startCol = otherBoard.startCol;
        endRow = otherBoard.endRow;
        endCol = otherBoard.endCol;
        currentRow = otherBoard.currentRow;
        currentCol = otherBoard.currentCol;
    }

    public boolean isAtEnd() {
        return currentRow == endRow && currentCol == endCol;
    }

    public boolean canMoveLeft() {
        return currentCol > 0 && board.get(currentRow).get(currentCol - 1) != '#';
    }

    public boolean canMoveRight() {
        return currentCol < board.get(currentRow).size() - 1 && board.get(currentRow).get(currentCol + 1) != '#';
    }

    public boolean canMoveUp() {
        return currentRow > 0 && board.get(currentRow - 1).get(currentCol) != '#';
    }

    public boolean canMoveDown() {
        return currentRow < board.size() - 1 && board.get(currentRow + 1).get(currentCol) != '#';
    }

    public int distanceToEnd() {
        return Math.abs(currentRow - endRow) + Math.abs(currentCol - endCol);
    }

    public void moveUp() {
        while (currentRow > 0 && board.get(currentRow - 1).get(currentCol) != '#') {
            currentRow--;
        }
    }

    public void moveDown() {
        while (currentRow < board.size() - 1 && board.get(currentRow + 1).get(currentCol) != '#') {
            currentRow++;
        }
    }

    public void moveLeft() {
        while (currentCol > 0 && board.get(currentRow).get(currentCol - 1) != '#') {
            currentCol--;
        }
    }

    public void moveRight() {
        while (currentCol < board.get(currentRow).size() - 1 && board.get(currentRow).get(currentCol + 1) != '#') {
            currentCol++;
        }
    }

    public Board movedUp() {
        Board newBoard = new Board(this);
        newBoard.moveUp();
        return newBoard;
    }

    public Board movedDown() {
        Board newBoard = new Board(this);
        newBoard.moveDown();
        return newBoard;
    }

    public Board movedLeft() {
        Board newBoard = new Board(this);
        newBoard.moveLeft();
        return newBoard;
    }

    public Board movedRight() {
        Board newBoard = new Board(this);
        newBoard.moveRight();
        return newBoard;
    }

    public void printBoard() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (i == currentRow && j == currentCol) {
                    System.out.print('X');
                } else {
                    System.out.print(board.get(i).get(j));
                }
            }
            System.out.println();
        }
    }
}
