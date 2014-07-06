import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
  private int[][] getBlock1() {
    int[][] blocks = new int[][] { new int[] { 8, 1, 3 },
        new int[] { 4, 0, 2 }, new int[] { 7, 6, 5 } };
    return blocks;
  }

  @Test
  public void testHamming() {
    Board b = new Board(getBlock1());
    Assert.assertEquals(b.hamming(), 5);
  }

  @Test
  public void testManhattan() {
    Board b = new Board(getBlock1());
    Assert.assertEquals(b.manhattan(), 10);
  }

}
