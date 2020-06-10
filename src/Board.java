import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Board {

    // array represents the tiles on the board
    private int[][] tiles;
    // length of the board
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n);
        for (int i = 0; i < n; i++) {
            sb.append("\n");
            for (int j = 0; j < n; j++) {
                sb.append(tiles[i][j] + " ");
            }
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != i * n + j + 1 && tiles[i][j] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0) {
                    count += Math.abs((tiles[i][j] - 1) / n - i);
                    count += Math.abs((tiles[i][j] - 1) % n - j);
                }
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int[][] goal = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                goal[i][j] = i * n + j + 1;
            }
        }
        goal[n - 1][n - 1] = 0;
        return Arrays.deepEquals(tiles, goal);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (!(y instanceof Board)) {
            return false;
        }
        return Arrays.deepEquals(tiles, ((Board) y).tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> queue = new LinkedList<>();
        // indexes toward the blank
        int i = 0;
        int j = 0;

        // find the position of the blank
        while (i < n && tiles[i][j] != 0) {
            while (j < n && tiles[i][j] != 0) {
                j++;
            }
            if (j >= n) {
                i++;
                j = 0;
            }
        }

        // add valid neighbors to the queue
        if (i > 0) {
            Board neighbor = new Board(tiles);
            exchange(neighbor, i, j, i - 1, j);
            queue.add(neighbor);
        }
        if (i < n - 1) {
            Board neighbor = new Board(tiles);
            exchange(neighbor, i, j, i + 1, j);
            queue.add(neighbor);
        }
        if (j > 0) {
            Board neighbor = new Board(tiles);
            exchange(neighbor, i, j, i, j - 1);
            queue.add(neighbor);
        }
        if (j < n - 1) {
            Board neighbor = new Board(tiles);
            exchange(neighbor, i, j, i, j + 1);
            queue.add(neighbor);
        }

        return queue;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int i1 = 0;
        int j1 = 0;
        int i2 = 0;
        int j2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (tiles[i][j] != 0 && tiles[i][j + 1] != 0) {
                    i1 = i;
                    j1 = j;
                    i2 = i;
                    j2 = j + 1;
                    break;
                }
            }
        }
        Board twinBoard = new Board(tiles);
        exchange(twinBoard, i1, j1, i2, j2);
        return twinBoard;
    }

    // exchange two tiles or blank on the board
    private static void exchange(Board board, int i1, int j1, int i2, int j2) {
        int temp = board.tiles[i2][j2];
        board.tiles[i2][j2] = board.tiles[i1][j1];
        board.tiles[i1][j1] = temp;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        Board board = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        System.out.println("Initial board: \n" + board.toString());
        System.out.println("Dimension: " + board.dimension());
        System.out.println("Hamming distance: " + board.hamming());
        System.out.println("Manhattan distance: " + board.manhattan());
        System.out.println("Is goal? " + board.isGoal());
        System.out.println("Equals itself? " + board.equals(board));
        exchange(board, 1, 1, 2, 2);
        System.out.println("After one exchange: \n" + board.toString());
        exchange(board, 1, 1, 2, 2);
        int count = 1;
        for (Board b : board.neighbors()) {
            System.out.println("Neighbor " + count + ": \n" + b.toString());
            count++;
        }
        System.out.println("Twin board: \n" + board.twin().toString());
    }
}