import org.junit.Assert;
import org.junit.Test;

public class PointTest {
  @Test
  public void test1() {
    Point p = new Point(285, 167);
    Point q = new Point(285, 153);
    Assert.assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(q), 0.001);

    p = new Point(31310, 3282);
    q = new Point(31310, 277);
    Assert.assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(q), 0.001);

    p = new Point(8, 1);
    q = new Point(8, 3);
    Assert.assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(q), 0.001);

    p = new Point(199, 410);
    q = new Point(199, 42);

    Assert.assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(q), 0.001); 
  }

  @Test
  public void test3() {
    Point p = new Point(290, 122);
    Point q = new Point(286, 353);
    Point r = new Point(293, 33);
    Assert.assertEquals(p.SLOPE_ORDER.compare(q, r), -1);

    p = new Point(3, 2);
    q = new Point(3, 2);
    r = new Point(3, 9);
    Assert.assertEquals(p.SLOPE_ORDER.compare(q, r), -1);
  }
}
