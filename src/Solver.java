import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;


public class Solver {

    // is the board solvable
    private boolean solvable;
    // boards from the initial to the goal
    private java.util.Deque<Board> path;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        // priority queue for the initial board
        final MinPQ<NodeOfBoard> pq;
        // priority queue for the twin board
        final MinPQ<NodeOfBoard> pqForTwin;

        pq = new MinPQ<>();
        pqForTwin = new MinPQ<>();
        Board twinBoard = initial.twin();
        solvable = true;
        path = new ArrayDeque<>();

        pq.insert(new NodeOfBoard(initial, 0, null));
        pqForTwin.insert(new NodeOfBoard(twinBoard, 0, null));
        NodeOfBoard curNode = null;
        NodeOfBoard curTwinNode = null;
        while (!pq.isEmpty()) {
            curTwinNode = pqForTwin.delMin();
            if (curTwinNode.board.isGoal()) {
                solvable = false;
                return;
            }

            curNode = pq.delMin();
            if (curNode.board.isGoal()) {
                solvable = true;
                while (curNode != null) {
                    path.push(curNode.board);
                    curNode = curNode.predecessor;
                }
                return;
            } else {
                for (Board neighbor : curNode.board.neighbors()) {
                    if (curNode.predecessor != null && neighbor.equals(curNode.predecessor.board)) {
                        continue;
                    }
                    pq.insert(new NodeOfBoard(neighbor, curNode.moves + 1, curNode));
                }
                for (Board neighbor : curTwinNode.board.neighbors()) {
                    if (curTwinNode.predecessor != null && neighbor.equals(curTwinNode.predecessor.board)) {
                        continue;
                    }
                    pqForTwin.insert(new NodeOfBoard(neighbor, curTwinNode.moves + 1, curTwinNode));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return path.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!solvable) {
            return null;
        }
        return path;
    }

    private class NodeOfBoard implements Comparable<NodeOfBoard> {

        // board presented by the node
        private final Board board;
        // priority of the node
        private final int priority;
        // predecessor of the node
        private final NodeOfBoard predecessor;
        // current number of moves
        private final int moves;

        public NodeOfBoard(Board board, int moves, NodeOfBoard predecessor) {
            this.board = board;
            this.moves = moves;
            this.priority = board.manhattan() + moves;
            this.predecessor = predecessor;
        }

        @Override
        public int compareTo(NodeOfBoard that) {
            if (this.priority < that.priority) {
                return -1;
            } else if (this.priority == that.priority) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}