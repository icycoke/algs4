import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    // list of segments that contains 4 points
    private List<LineSegment> lineSegmentList;

    public FastCollinearPoints(Point[] points) {
        validate(points);

        //if the number of points is less than 4, there is no valid segment
        if (points.length < 4) {
            return;
        }

        lineSegmentList = new ArrayList<>();

        // number of points
        int numOfPoints = points.length;
        // sorted array of all points
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints, Point::compareTo);

        for (int i = 0; i < numOfPoints; i++) {
            // array contains all points sorted by the slope order from the origin point
            Point[] pointArr = sortedPoints.clone();
            Arrays.sort(pointArr, sortedPoints[i].slopeOrder());

            // origin point
            Point originPoint = pointArr[0];
            // slope of the origin point to the slow pointer
            double slopeSlow = pointArr[0].slopeTo(pointArr[1]);
            // slope of the origin point to the fast pointer
            double slopeFast = pointArr[0].slopeTo(pointArr[1]);
            // iterate all possible points by two pointers and generate valid segments
            for (int ptrSlow = 1, ptrFast = 1; ptrSlow < numOfPoints - 2; ptrSlow = ptrFast, slopeSlow = slopeFast) {
                while (ptrFast < numOfPoints && Double.compare(slopeSlow, slopeFast) == 0) {
                    ptrFast++;
                    slopeFast = originPoint.slopeTo(pointArr[ptrFast]);
                }
                // number of adjacent points
                int numOfAdjacentPoints = ptrFast - ptrSlow + 1;
                if (numOfAdjacentPoints >= 4) {
                    lineSegmentList.add(new LineSegment(originPoint, pointArr[ptrFast - 1]));
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
