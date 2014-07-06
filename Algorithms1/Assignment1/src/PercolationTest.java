import org.junit.Assert;
import org.junit.Test;

public class PercolationTest {

  @Test
  public void test1() {
    Percolation p = new Percolation(3);
    p.open(3, 1);
    p.open(1, 3);
    p.open(2, 3);
    p.open(3, 3);
    boolean result = p.isFull(3, 1);
    System.out.print(result);
    Assert.assertFalse(result);
  }
}
