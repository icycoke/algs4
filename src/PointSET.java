import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeSet;


public class PointSET {

    // red-black BST contains all points
    private TreeSet<Point2D> treeSet;

    // construct an empty set of points
    public PointSET() {
        treeSet = new TreeSet<>();
    }

    // is the set empty
    public boolean isEmpty() {
        return treeSet.size() == 0;
    }

    // number of points in the set
    public int size() {
        return treeSet.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        treeSet.add(p);
    }

    // does the set contain point p
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return treeSet.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : treeSet) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        TreeSet<Point2D> rangedSet = new TreeSet<>();

        for (Point2D p : treeSet) {
            if (rect.contains(p)) {
                treeSet.add(p);
            }
        }

        return rangedSet;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (treeSet.isEmpty()) {
            return null;
        }

        Point2D nearestP = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Point2D curPoint : treeSet) {
            double curDistance = curPoint.distanceTo(p);
            if (nearestP == null || Double.compare(curDistance, minDistance) < 0) {
                nearestP = curPoint;
                minDistance = curPoint.distanceTo(p);
            }
        }

        return nearestP;
    }
}
