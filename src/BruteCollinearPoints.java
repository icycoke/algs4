import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    private int numberOfSegments;
    private List<LineSegment> lineSegmentList;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        if (points.length < 4) {
            return;
        }
        lineSegmentList = new ArrayList<>();
        numberOfSegments = 0;
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                Point firstPoint = points[p];
                double firstSlope = firstPoint.slopeTo(points[q]);
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p] == null || points[q] == null || points[r] == null || points[s] == null) {
                            throw new IllegalArgumentException();
                        }
                        Point endPoint1 = points[p];
                        Point endPoint2 = points[p];
                        int[] toBeCompared = new int[]{q, r, s};
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
                            lineSegmentList.add(new LineSegment(endPoint1, endPoint2));
                            numberOfSegments++;
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
        return lineSegmentList.toArray(new LineSegment[0]);
    }

}
