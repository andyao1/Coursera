import org.junit.Assert;
import org.junit.Test;

public class RandomizedQueueTest {
  @Test
  public void test1() {
    RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
    for (int i = 0; i < 8; i++) {
      q.enqueue(i);
    }
    for (int i = 0; i < 7; i++) {
      q.dequeue();
    }
    Assert.assertEquals(q.size(), 1);
  }
}
