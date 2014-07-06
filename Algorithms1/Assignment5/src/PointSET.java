import java.util.TreeSet;

public class PointSET {
  private TreeSet<Point2D> points;

  public PointSET() {
    // construct an empty set of points
    points = new TreeSet<Point2D>();
  }

  public boolean isEmpty() {
    return points.isEmpty();
  }

  public int size() {
    return points.size();
  }

  public void insert(Point2D p) {
    points.add(p);
  }

  public boolean contains(Point2D p) {
    return points.contains(p);
  }

  public void draw() {
    // draw all of the points to standard draw
    for (Point2D p : points) {
      p.draw();
    }
  }

  public Iterable<Point2D> range(RectHV rect) {
    TreeSet<Point2D> matches = new TreeSet<Point2D>();
    for (Point2D p : points) {
      if (rect.contains(p)) {
        matches.add(p);
      }
    }
    return matches;
  }

  public Point2D nearest(Point2D pt) {
    Point2D nearestPoint = null;
    double nearestDistanceSq = Double.MAX_VALUE;
    
    for (Point2D p : points) {
      double distanceSq = p.distanceSquaredTo(pt);
      if (nearestPoint == null || distanceSq < nearestDistanceSq) {
        nearestPoint = p;
        nearestDistanceSq = distanceSq;
      }
    }
    return nearestPoint;
  }
}