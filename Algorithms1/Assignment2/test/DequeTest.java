import org.junit.Assert;
import org.junit.Test;

public class DequeTest {
  @Test
  public void test1() {
    Deque<Integer> q = new Deque<Integer>();
    q.addFirst(1);
    q.removeFirst();
    q.addLast(2);
    q.addFirst(1);
    q.removeLast();
    
    Assert.assertEquals(q.size(), 1);
  }
}
