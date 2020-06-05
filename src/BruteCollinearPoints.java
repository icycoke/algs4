import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    private final Point[] points;
    private int numberOfSegments;
    private List<LineSegment> segmentList;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        segmentList = new ArrayList<>();
        this.points = points;
        numberOfSegments = 0;
        if (points.length < 4)
            return;
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p] == null || points[q] == null || points[r] == null || points[s] == null)
                            throw new IllegalArgumentException();
                        Point firstPoint = points[p];
                        Point endPoint1 = points[p];
                        Point endPoint2 = points[p];
                        int[] toBeCompared = new int[]{q, r, s};
                        double firstSlope = firstPoint.slopeTo(points[q]);
                        boolean isInLine = true;
                        for (int index : toBeCompared) {
                            if (firstPoint.compareTo(points[index]) == 0)
                                throw new IllegalArgumentException();
                            if (points[index].compareTo(endPoint1) < 0)
                                endPoint1 = points[index];
                            if (points[index].compareTo(endPoint2) > 0)
                                endPoint2 = points[index];
                            if (Double.compare(firstPoint.slopeTo(points[index]), firstSlope) != 0) {
                                isInLine = false;
                            }
                        }
                        if (isInLine) {
                            segmentList.add(new LineSegment(endPoint1, endPoint2));
                        }
                    }
                }
            }
        }

    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return segmentList.toArray(new LineSegment[0]);
    }

}
