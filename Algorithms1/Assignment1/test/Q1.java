import org.junit.Test;

public class Q1 {

  @Test
  public void test1() {
    QuickFindUF uf = new QuickFindUF(10);
    uf.union(6, 2);
    uf.union(0, 9);
    uf.union(3, 9);
    uf.union(8, 9);
    uf.union(5, 8);
    uf.union(4, 6);
    String s = uf.toString();
    System.out.println(s);
  }
  
  @Test
  public void test2() {
    WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
    uf.union(2, 7);
    uf.union(0, 5);
    uf.union(0, 4);
    uf.union(9, 5);
    uf.union(8, 9);
    uf.union(3, 6);
    uf.union(7, 3);
    uf.union(4, 3);
    uf.union(4, 1);
    String s = uf.toString();
    System.out.println(s);
  }
}
