import java.util.Iterator;

public class Subset {
  public static void main(String[] args) {
    int k = Integer.parseInt(args[0], 10);
    RandomizedQueue<String> rq = new RandomizedQueue<String>();
    while (!StdIn.isEmpty()) {
      String data = StdIn.readString();
      if (data == null) {
        break;
      }
      rq.enqueue(data);
    }
    Iterator<String> itr = rq.iterator();
    for (int i = 0; i < k; ++i) {
      if (itr.hasNext()) {
        StdOut.println(itr.next());
      }
    }
  }
}
