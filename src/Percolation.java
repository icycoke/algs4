import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private boolean[] statuses;
    private int n;
    private int count;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        int size = n * n;
        uf = new WeightedQuickUnionUF(size);
        statuses = new boolean[size];
        for (int i = 0; i < size; i++) {
            statuses[i] = false;
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
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == uf.find(getIndex(row, col))) {
                return true;
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int col = 1; col <= n; col++) {
            if (isFull(n, col)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValid(int row, int col) {
        return row >= 1 && row <= n && col >= 1 && col <= n;
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + col - 1;
    }

}