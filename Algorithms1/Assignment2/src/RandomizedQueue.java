import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private int count;
  private Item[] a;

  public RandomizedQueue() {
    a = createArray(1);
    count = 0;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  public int size() {
    return count;
  }

  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (a.length == count) {
      Item[] tmp = createArray(a.length * 2);
      a = clone(tmp);
    }
    a[count++] = item;
  }

  public Item dequeue() {
    int pos = getRandomPosition();
    Item item = a[pos];
    a[pos] = a[count - 1];
    a[--count] = null;
    
    if (a.length > count * 4) {
      Item[] tmp = createArray(a.length / 2);
      a = clone(tmp);
    }
    return item;
  }

  private Item[] createArray(int size) {
    int c = size;
    if (c == 0) {
      c = 1;
    }
    return (Item[]) new Object[c];
  }

  private int getRandomPosition() {
    if (count == 0) {
      throw new NoSuchElementException();
    }
    return StdRandom.uniform(0, count);
  }

  private Item[] clone(Item[] tmp) {
    for (int i = 0; i < count; ++i) {
      tmp[i] = a[i];
    }
    return tmp;
  }

  public Item sample() {
    return a[getRandomPosition()];
  }

  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private int curr;
    private int[] positions;

    public RandomizedQueueIterator() {
      curr = 0;
      if (count > 0) {
        positions = new int[count];
        for (int i = 0; i < count; i++) {
          positions[i] = i;
        }
        StdRandom.shuffle(positions);
      }
    }

    public boolean hasNext() {
      return curr < count;
    }

    public Item next() {
      if (curr >= count) {
        throw new NoSuchElementException();
      }
      return a[positions[curr++]];
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    // unit testing
  }
}