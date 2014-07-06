import java.util.Comparator;

public class Point implements Comparable<Point> {
  private class SlopeComparator implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {
      if (o1 == null || o2 == null) {
        throw new NullPointerException();
      } else {
        double delta = slopeTo(o2) - slopeTo(o1);
        if (delta > 0) {
          return -1;
        } else if (delta < 0) {
          return 1;
        } else {
          return 0;
        }
      }
    }
  }

  // compare points by slope
  public final Comparator<Point> SLOPE_ORDER = new SlopeComparator();

  private final int x; // x coordinate
  private final int y; // y coordinate

  // create the point (x, y)
  public Point(int x, int y) {
    /* DO NOT MODIFY */
    this.x = x;
    this.y = y;
  }

  // plot this point to standard drawing
  public void draw() {
    /* DO NOT MODIFY */
    StdDraw.point(x, y);
  }

  // draw line between this point and that point to standard drawing
  public void drawTo(Point that) {
    /* DO NOT MODIFY */
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  // slope between this point and that point
  public double slopeTo(Point that) {
    if (that == null) {
      throw new NullPointerException();
    }

    if (x == that.x) {
      if (that.y != y) {
        return Double.POSITIVE_INFINITY;
      } else {
        return Double.NEGATIVE_INFINITY;
      }
    } else if (that.y == y) {
      return 0;
    } else {
      return (that.y - y) * 1.0 / (that.x - x);
    }
  }

  // is this point lexicographically smaller than that one?
  // comparing y-coordinates and breaking ties by x-coordinates
  public int compareTo(Point that) {
    if (that == null) {
      throw new NullPointerException();
    }
    if (this.y != that.y) {
      return this.y - that.y;
    } else {
      return this.x - that.x;
    }
  }

  // return string representation of this point
  public String toString() {
    /* DO NOT MODIFY */
    return "(" + x + ", " + y + ")";
  }

  // unit test
  public static void main(String[] args) {
    /* YOUR CODE HERE */
  }
}