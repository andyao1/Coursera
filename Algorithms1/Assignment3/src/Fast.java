import java.util.Arrays;

public class Fast {
  private static Point[] getPoints(Point[] points, int start, int end) {
    Point[] result = new Point[end - start + 1];
    result[0] = points[0];
    for (int i = start; i < end; i++) {
      result[i - start + 1] = points[i];
    }
    return result;
  }

  private static void findPoints(Point[] points, int start, int end) {
    if (end - start >= 3) {
      Point[] result = getPoints(points, start, end);
      Point first = result[0];
      Arrays.sort(result);
      if (result[0].slopeTo(first) == Double.NEGATIVE_INFINITY) {
        printPoints(result);
        result[0].drawTo(result[result.length - 1]);
      }
    }
  }

  private static void printPoints(Point[] points) {
    StdOut.print(points[0]);
    for (int i = 1; i < points.length; i++) {
      StdOut.print(" -> ");
      StdOut.print(points[i]);
    }
    StdOut.println();
  }

  public static void main(String[] args) {
    String filename = args[0];
    int[] data = new In(filename).readAllInts();

    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);

    if (data.length > 0) {
      int N = data[0];
      Point[] points = new Point[N];
      Point[] copies = new Point[N];
      for (int i = 1; i < data.length - 1; i += 2) {
        int x = data[i];
        int y = data[i + 1];
        points[i / 2] = new Point(x, y);
      }

      Arrays.sort(points);
      for (Point point : points) {
        point.draw();
      }
      
      for (int i = 0; i < N; i++) {
        copies[i] = points[i];
      }

      for (int a = 0; a < N; a++) {
        int c = 0;
        copies[c++] = points[a];
        for (int i = 0; i < a; i++) {
          copies[c++] = points[i];
        }
        for (int i = a + 1; i < N; i++) {
          copies[c++] = points[i];
        }
        Point pta = copies[0];
        Arrays.sort(copies, 1, N, pta.SLOPE_ORDER);

        int start = 1;
        for (int curr = 2; curr < N; curr++) {
          Point ptb = copies[curr];
          if (ptb.slopeTo(pta) != copies[start].slopeTo(pta)) {
            findPoints(copies, start, curr);
            start = curr;
          }
        }
        findPoints(copies, start, N);
      }
    }

    // display to screen all at once
    StdDraw.show(0);
  }
}
