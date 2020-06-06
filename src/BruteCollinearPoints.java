import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    // list of segments that contains 4 points
    private List<LineSegment> lineSegmentList;

    public BruteCollinearPoints(Point[] points) {
        validate(points);

        lineSegmentList = new ArrayList<>();

        // if the number of points is less than 4, there is no valid segment
        if (points.length < 4) {
            return;
        }


        // number of points
        int numOfPoints = points.length;


        // iterate every 4 points and check whether they are in the same line
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

    /**
     * Get the number of segments
     * @return number of segments
     */
    public int numberOfSegments() {
        return lineSegmentList.size();
    }

    /**
     * Get the segments
     * @return array contains all valid segments
     */
    public LineSegment[] segments() {
        return lineSegmentList.toArray(new LineSegment[0]);
    }

    /**
     * Check if the points are valid
     * @param points input points
     */
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
