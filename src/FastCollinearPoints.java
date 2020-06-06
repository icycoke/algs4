import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    // list of segments that contains 4 points
    private List<LineSegment> lineSegmentList;

    public FastCollinearPoints(Point[] points) {
        validate(points);

        lineSegmentList = new ArrayList<>();

        // if the number of points is less than 4, there is no valid segment
        if (points.length < 4) {
            return;
        }

        // number of points
        int numOfPoints = points.length;
        // sorted array of all points
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);

        for (int i = 0; i < numOfPoints - 3; i++) {
            // array contains all points sorted by the slope order from the origin point
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());

            // origin point
            Point originPoint = pointsCopy[0];
            // iterate all possible points by two pointers and generate valid segments
            for (int ptrSlow = 1, ptrFast = 2; ptrFast < numOfPoints; ptrFast++) {

                while (ptrFast < numOfPoints
                        && Double.compare(originPoint.slopeTo(pointsCopy[ptrSlow]), originPoint.slopeTo(pointsCopy[ptrFast])) == 0) {
                    ptrFast++;
                }

                // number of adjacent points
                int numOfAdjacentPoints = ptrFast - ptrSlow + 1;
                // check if the number of adjacent points is greater than 4 and the is not a duplicate segment
                if (numOfAdjacentPoints >= 4 && originPoint.compareTo(pointsCopy[ptrSlow]) < 0) {
                    lineSegmentList.add(new LineSegment(originPoint, pointsCopy[ptrFast - 1]));
                }

                ptrSlow = ptrFast;
            }
        }
    }

    /**
     * Get the number of segments
     *
     * @return number of segments
     */
    public int numberOfSegments() {
        return lineSegmentList.size();
    }

    /**
     * Get the segments
     *
     * @return array contains all valid segments
     */
    public LineSegment[] segments() {
        return lineSegmentList.toArray(new LineSegment[0]);
    }

    /**
     * Check if the points are valid
     *
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
