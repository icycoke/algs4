import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private boolean[] statuses;
    private final int n;
    private int count;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        int size = n * n;
        uf = new WeightedQuickUnionUF(size + 2);
        statuses = new boolean[size + 2];
        for (int i = 0; i < size + 2; i++) {
            statuses[i] = false;
        }
        statuses[0] = true;
        statuses[size + 1] = true;
        for (int i = 1; i < n + 1; i++) {
            uf.union(i, 0);
        }
        for (int i = size; i > size - n; i--) {
            uf.union(i, size + 1);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            statuses[getIndex(row, col)] = true;
            count++;

            if (isValid(row - 1, col) && isOpen(row - 1, col)) {
                uf.union(getIndex(row, col), getIndex(row - 1, col));
            }
            if (isValid(row + 1, col) && isOpen(row + 1, col)) {
                uf.union(getIndex(row, col), getIndex(row + 1, col));
            }
            if (isValid(row, col - 1) && isOpen(row, col - 1)) {
                uf.union(getIndex(row, col), getIndex(row, col - 1));
            }
            if (isValid(row, col + 1) && isOpen(row, col + 1)) {
                uf.union(getIndex(row, col), getIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
        return statuses[getIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return uf.find(getIndex(row, col)) == uf.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        if (n == 1) {
            return isOpen(1, 1);
        }
        return uf.find(0) == uf.find(n * n + 1);
    }

    private boolean isValid(int row, int col) {
        return row >= 1 && row <= n && col >= 1 && col <= n;
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    public static void main(String[] args){
        Percolation percolation = new Percolation(2);
        System.out.println(percolation.percolates());
        percolation.open(1, 1);
        percolation.open(1, 2);
        System.out.println(percolation.percolates());
        percolation.open(2, 2);
        System.out.println(percolation.percolates());
    }
}