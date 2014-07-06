import java.util.Arrays;

public class Brute {
  private static boolean isCollinear(Point a, Point b, Point c, Point d) {
    double sab = a.slopeTo(b);
    double sac = a.slopeTo(c);
    double sad = a.slopeTo(d);
    boolean result = (sab == a.slopeTo(c) && sab == a.slopeTo(d));
    if (result) {
      // StdOut.println(sab + " " + +sac + " " + sad);
    }
    return result;
  }

  private static void printPoints(Point[] pts) {
    StringBuilder sb = new StringBuilder();
    for (Point pt : pts) {
      if (sb.length() != 0) {
        sb.append(" -> ");
      }
      sb.append(pt);
    }
    if (sb.length() > 0) {
      StdOut.println(sb.toString());
      pts[0].drawTo(pts[pts.length - 1]);
    }
  }

  public static void main(String[] args) {
    String filename = args[0];
    int[] data = new In(filename).readAllInts();

    // rescale coordinates and turn on animation mode
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);

    if (data.length > 0) {
      int N = data[0];
      Point[] points = new Point[N];
      for (int i = 1; i < data.length - 1; i += 2) {
        int x = data[i];
        int y = data[i + 1];
        points[i / 2] = new Point(x, y);
      }

      for (int a = 0; a < N; a++) {
        Point pta = points[a];
        pta.draw();
        for (int b = a + 1; b < N; b++) {
          Point ptb = points[b];
          for (int c = b + 1; c < N; c++) {
            Point ptc = points[c];
            for (int d = c + 1; d < N; d++) {
              Point ptd = points[d];
              if (isCollinear(pta, ptb, ptc, ptd)) {
                Point[] collinearPoints = new Point[] { pta, ptb, ptc, ptd };
                Arrays.sort(collinearPoints);
                printPoints(collinearPoints);
              }
            }
          }
        }
      }
    }

    // display to screen all at once
    StdDraw.show(0);
  }
}
