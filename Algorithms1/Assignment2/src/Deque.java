import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private class Node {
    private final Item item;
    private Node prev = null;
    private Node next = null;

    private Node(final Item item) {
      if (item == null) {
        throw new NullPointerException();
      }
      this.item = item;
    }
  }

  private Node head;
  private Node tail;
  private int count;

  public Deque() {
    head = null;
    tail = null;
    count = 0;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  public int size() {
    return count;
  }

  public void addFirst(Item item) {
    Node curr = new Node(item);
    curr.next = head;
    if (head != null) {
      head.prev = curr;
    }
    head = curr;
    if (count == 0) {
      tail = head;
    }
    count++;
  }

  public void addLast(Item item) {
    Node curr = new Node(item);
    curr.prev = tail;
    if (tail != null) {
      tail.next = curr;
    }
    tail = curr;
    if (count == 0) {
      head = tail;
    }
    count++;
  }

  public Item removeFirst() {
    if (head != null) {
      Item item = head.item;
      if (head == tail) {
        head = null;
        tail = null;
      } else {
        head = head.next;
        if (head != null) {
          head.prev = null;
        }
      }
      count--;
      return item;
    } else {
      throw new NoSuchElementException();
    }
  }

  public Item removeLast() {
    if (tail != null) {
      Item item = tail.item;
      if (head == tail) {
        head = null;
        tail = null;
      } else {
        tail = tail.prev;
        if (tail != null) {
          tail.next = null;
        }
      }
      count--;
      return item;
    } else {
      throw new NoSuchElementException();
    }
  }

  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {
    private Node current = head;

    public boolean hasNext() {
      return current != null;
    }

    public Item next() {
      if (current != null) {
        Item item = current.item;
        current = current.next;
        return item;
      } else {
        throw new NoSuchElementException();
      }
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    // unit testing
  }
}