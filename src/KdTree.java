import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;


public class KdTree {

    // the root node of the tree
    private PointNode root;
    // the size of the tree
    private int size;

    // construct an empty tree
    public KdTree() {
        root = null;
        size = 0;
    }

    // is the tree empty
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the tree
    public int size() {
        return size;
    }

    // add the point to the tree (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (contains(p)) {
            return;
        }

        if (isEmpty()) {
            root = new PointNode(p, new RectHV(0.0, 0.0, 1.0, 1.0), null, null, true);
        } else {
            double x = p.x();
            double y = p.y();
            PointNode parentNode = search(x, y);
            double parentX = parentNode.point.x();
            double parentY = parentNode.point.y();

            if (parentNode.isVertical) {
                if (x < parentX) {
                    RectHV rect = new RectHV(parentNode.rect.xmin(), parentNode.rect.ymin(), parentX, parentNode.rect.ymax());
                    parentNode.left = new PointNode(p, rect, null, null, false);
                } else {
                    RectHV rect = new RectHV(parentX, parentNode.rect.ymin(), parentNode.rect.xmax(), parentNode.rect.ymax());
                    parentNode.right = new PointNode(p, rect, null, null, false);
                }
            } else {
                if (y < parentY) {
                    RectHV rect = new RectHV(parentNode.rect.xmin(), parentNode.rect.ymin(), parentNode.rect.xmax(), parentY);
                    parentNode.left = new PointNode(p, rect, null, null, true);
                } else {
                    RectHV rect = new RectHV(parentNode.rect.xmin(), parentY, parentNode.rect.xmax(), parentNode.rect.ymax());
                    parentNode.right = new PointNode(p, rect, null, null, true);
                }
            }
        }
        size++;
    }

    // does the tree contain point p
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        PointNode searchedNode = search(p.x(), p.y());

        if (searchedNode == null) {
            return false;
        }
        return p.equals(search(p.x(), p.y()).point);
    }

    // draw all points to standard draw
    public void draw() {
        myDraw(root);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        List<Point2D> list = new ArrayList<>();

        myRange(root, rect, list);

        return list;
    }

    // a nearest neighbor in the tree to point p; null if the tree is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }

        return myNearest(root, p, null);
    }

    private Point2D myNearest(PointNode node, Point2D p, Point2D nearestPoint) {
        Point2D res = nearestPoint;
        if (node == null) {
            return res;
        }

        double minDistance;
        if (nearestPoint == null) {
            minDistance = Double.POSITIVE_INFINITY;
        } else {
            minDistance = nearestPoint.distanceSquaredTo(p);
        }
        double curDistance = node.point.distanceSquaredTo(p);

        if (curDistance < minDistance) {
            minDistance = curDistance;
            res = node.point;
        }

        if (node.rect.distanceSquaredTo(p) < minDistance) {
            PointNode sameSideNode = node.left;
            PointNode otherSideNode = node.right;

            if ((node.isVertical && p.x() >= node.point.x()) || (!node.isVertical && p.y() >= node.point.y())) {
                sameSideNode = node.right;
                otherSideNode = node.left;
            }

            res = myNearest(sameSideNode, p, res);
            res = myNearest(otherSideNode, p, res);
        }

        return res;
    }

    // search for the specified x and y
    private PointNode search(double x, double y) {
        PointNode parentNode = null;
        PointNode curNode = root;

        while (curNode != null) {
            parentNode = curNode;
            double px = curNode.point.x();
            double py = curNode.point.y();

            if (Double.compare(x, px) == 0 && Double.compare(y, py) == 0) {
                return curNode;
            }
            if (curNode.isVertical) {
                if (Double.compare(x, px) < 0) {
                    curNode = curNode.left;
                } else {
                    curNode = curNode.right;
                }
            } else {
                if (Double.compare(y, py) < 0) {
                    curNode = curNode.left;
                } else {
                    curNode = curNode.right;
                }
            }
        }

        return parentNode;
    }

    private void myDraw(PointNode node) {
        if (node == null) {
            return;
        }

        node.point.draw();

        myDraw(node.left);
        myDraw(node.right);
    }

    private void myRange(PointNode node, RectHV rect, List<Point2D> list) {
        if (node == null || !node.rect.intersects(rect)) {
            return;
        }

        if (rect.contains(node.point)) {
            list.add(node.point);
        }

        myRange(node.left, rect, list);
        myRange(node.right, rect, list);
    }

    private static class PointNode {

        // the point
        private Point2D point;
        // the axis-aligned rectangle corresponding to this node
        private RectHV rect;
        // the left/bottom subtree
        private PointNode left;
        // the right/top subtree
        private PointNode right;
        // whether the node vertically divide the rectangle
        private boolean isVertical;

        public PointNode(Point2D point, RectHV rect, PointNode left, PointNode right, boolean isVertical) {
            this.point = point;
            this.rect = rect;
            this.left = left;
            this.right = right;
            this.isVertical = isVertical;
        }
    }

    public static void main(String[] args) {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.7, 0.2));
        tree.insert(new Point2D(0.5, 0.4));
        System.out.println(tree.nearest(new Point2D(0.693, 0.496)).toString());
    }
}
