import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private List<LineSegment> lineSegmentList;

    public BruteCollinearPoints(Point[] points) {
        validate(points);

        lineSegmentList = new ArrayList<>();
        int numOfPoints = points.length;

        if (numOfPoints < 4) {
            return;
        }

        for (int p = 0; p < numOfPoints - 3; p++) {
            for (int q = p + 1; q < numOfPoints - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (Double.compare(points[p].slopeTo(points[q]), points[p].slopeTo(points[r])) == 0
                                && Double.compare(points[p].slopeTo(points[q]), points[p].slopeTo(points[s])) == 0) {
                            Point[] pointsInLine = new Point[]{points[p], points[q], points[r], points[s]};
                            Arrays.sort(pointsInLine, Point::compareTo);
                            lineSegmentList.add(new LineSegment(pointsInLine[0], pointsInLine[3]));
                        }
                    }
                }
            }
        }

    }

    public int numberOfSegments() {
        return lineSegmentList.size();
    }

    public LineSegment[] segments() {
        return lineSegmentList.toArray(new LineSegment[0]);
    }

    private void validate(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        for (Point point : points) {
            if (point == null)
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }
    }

}
